package br.com.loadti.desafiostatic.writeParquet;

import br.com.loadti.desafiostatic.response.Response;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericData;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.parquet.avro.AvroParquetWriter;
import org.apache.parquet.hadoop.ParquetWriter;
import org.apache.parquet.hadoop.metadata.CompressionCodecName;

import java.io.IOException;
import java.util.List;

public class WriterParquet {

    public Response writeParquet(List<GenericData.Record> recorList, Schema schema, String folder) throws IOException {

        ParquetWriter<GenericData.Record> writer = null;
        //Path path = new Path(folder + "-" + System.currentTimeMillis() + ".parquet");

        try {

            Path path = new Path(folder + "cdr" + System.currentTimeMillis() + ".parquet");
            writer = AvroParquetWriter
                    .<GenericData.Record>builder(path)
                    .withRowGroupSize(ParquetWriter.DEFAULT_BLOCK_SIZE)
                    .withPageSize(ParquetWriter.DEFAULT_PAGE_SIZE)
                    .withSchema(schema)
                    .withConf(new Configuration())
                    .withCompressionCodec(CompressionCodecName.SNAPPY)
                    .withValidation(false)
                    .withDictionaryEncoding(false)
                    .build();



            for (GenericData.Record record : recorList) {

                writer.write(record);
            }

            return new Response().OK();

        } catch (Exception e) {

            e.printStackTrace();
            return new Response().Error("Falha ao gerar arquivo parquet " + e.getMessage());

        } finally {

            if (writer != null) {

                writer.close();
            }
        }

    }
}
