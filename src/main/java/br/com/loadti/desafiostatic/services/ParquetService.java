package br.com.loadti.desafiostatic.services;

import br.com.loadti.desafiostatic.records.CreadRecord;
import br.com.loadti.desafiostatic.response.Response;
import br.com.loadti.desafiostatic.schemas.ParseSchema;
import br.com.loadti.desafiostatic.writeParquet.WriterParquet;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.spark.api.java.function.MapFunction;
import org.apache.spark.sql.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

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


    public void readerParquet(String folder) {

        try {
            Dataset<Row> cdr = null;
            SparkSession session = SparkSession
                    .builder()
                    .appName("Desafio Static")
                    .master("local")
                    .config("recursiveFileLookup", "true")
                    .config("spark.io.compression.codec", "snappy")
                    .getOrCreate();

            session.sql("set spark.sql.files.ignoreCorruptFiles=true");

           /* SQLContext context = new SQLContext(session);
            String path = folder + "cdr-*.parquet";
            Dataset<Row> cdr = context.parquetFile(path);*/

            cdr = session.read().format("parquet")
                    .option("recursiveFileLookup", "true")
                    .load(folder);

            cdr = cdr.filter(cdr.col("exchange_id").equalTo("7985"));

            cdr.show();



        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}
