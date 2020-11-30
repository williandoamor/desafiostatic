package br.com.loadti.desafiostatic.services;

import br.com.loadti.desafiostatic.cdr.Cdr;
import br.com.loadti.desafiostatic.records.CreadRecord;
import br.com.loadti.desafiostatic.response.Response;
import br.com.loadti.desafiostatic.schemas.ParseSchema;
import br.com.loadti.desafiostatic.util.Numeric;
import br.com.loadti.desafiostatic.writeParquet.WriterParquet;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.commons.collections.CollectionUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import static org.apache.spark.sql.functions.lit;

@Service
public class ParquetService {

    public Response readFile(MultipartFile file, String folder) {

        StringTokenizer st;
        String line = "";
        List<GenericData.Record> records = new ArrayList<>();

        if (file == null) {

            return new Response().Error("Por favor informe um arquivo válido.");

        }
        //
        try {

            Schema schema = ParseSchema.parserSchema("/cdrSchema.avsc");
            BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()));

            //
            while ((line = br.readLine()) != null) {

                st = new StringTokenizer(line, ";");

                /*Chama a classe que cria o arquivo parquet e o adiciona a lista*/
                records.add(new CreadRecord().getRecord(st, schema));
            }

            /*Quando terminar de ler e montar a estrutura
             * salva o arquivo no formato parquet em disco*/
            return new WriterParquet().writeParquet(records, schema, folder);


        } catch (Exception e) {

            return new Response().Error("Erro ao ler o arquivo " + e.getMessage());
        }

    }


    public List<Cdr> readerParquet(String folder, String dataini, String datafim, String chamador, String recebedor, String antenain, String antenaout) throws ParseException {

        try {

            SparkSession session = SparkSession
                    .builder()
                    .appName("Desafio Static")
                    .master("local")
                    .config("recursiveFileLookup", "true")
                    .config("spark.io.compression.codec", "snappy")
                    .getOrCreate();

            session.sql("set spark.sql.files.ignoreCorruptFiles=true");

            Dataset<Row> cdrDataset = session.read().format("parquet")
                    .option("recursiveFileLookup", "true")
                    .load(folder);

            /*Verifica se foi passado data para realizar a filtragem
             * do arquivo*/
            if (dataini != null && !"".equalsIgnoreCase(dataini)
                    && datafim != null && !"".equalsIgnoreCase(datafim)) {

                /*Filtra a data maior que a data inicial e menor que a data final*/
                cdrDataset = cdrDataset.filter(lit(dataini).gt(date("start_time"))).filter(lit(datafim).gt(date("start_time")));

            }
            //
            /*Filtra pelo numero que fez a chamada*/
            if (!"".equalsIgnoreCase(chamador)) {

                cdrDataset = cdrDataset.filter(cdrDataset.col("calling_number").equalTo(chamador));

            }
            //
            /*Filtra pelo recebedor da chamada*/
            if (!"".equalsIgnoreCase(recebedor)) {

                cdrDataset = cdrDataset.filter(cdrDataset.col("called_number").equalTo(recebedor));
            }
            //
            /*Filtra pela antena de entrada*/
            if (!"".equalsIgnoreCase(antenain)) {

                cdrDataset = cdrDataset.filter(cdrDataset.col("cell_in").equalTo(antenain));
            }
            //
            /*Filtra pela antena de saida*/
            if (!"".equalsIgnoreCase(antenaout)) {

                cdrDataset = cdrDataset.filter(cdrDataset.col("cell_out").equalTo(antenaout));
            }
            //

            return dsForList(cdrDataset.collectAsList());

        } catch (Exception e) {

            e.printStackTrace();
            throw e;

        }

    }

    /*Converte o DatasetRow em objeto do tipo CDR*/
    private List<Cdr> dsForList(List<Row> rw) {

        List<Cdr> aCdr = new ArrayList<>();
        //
        try {

            for (int i = 0; i < rw.size(); i++) {

                Cdr cdr = new Cdr();

                cdr.setExchange_id(Integer.parseInt(rw.get(i).getAs("exchange_id")));
                cdr.setCalling_number(rw.get(i).getAs("calling_number"));
                cdr.setCalled_number(rw.get(i).getAs("called_number"));
                cdr.setSerial_number(rw.get(i).getAs("serial_number"));
                cdr.setCall_type(rw.get(i).getAs("call_type"));
                cdr.setCall_duration(rw.get(i).getAs("call_duration"));
                cdr.setStart_time(rw.get(i).getAs("start_time"));
                cdr.setImsi(rw.get(i).getAs("imsi"));
                cdr.setSwitch_call(rw.get(i).getAs("switch_call"));
                cdr.setCell_in(rw.get(i).getAs("cell_in"));
                cdr.setCell_out(rw.get(i).getAs("cell_out"));
                cdr.setTecnologia(rw.get(i).getAs("tecnologia"));
                cdr.setFilename(rw.get(i).getAs("filename"));
                cdr.setFirst_lac(rw.get(i).getAs("first_lac"));
                cdr.setLast_lac(rw.get(i).getAs("last_lac"));
                cdr.setGgcn_addrress(rw.get(i).getAs("ggcn_addrress"));

                aCdr.add(cdr);

            }

            return aCdr;

        } catch (Exception e) {

            e.printStackTrace();
            throw e;
        }
    }
}
