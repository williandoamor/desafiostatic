package br.com.loadti.desafiostatic.schemas;

import org.apache.avro.Schema;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ParseSchema {


    public static Schema parserSchema(String local) throws IOException {

        Schema.Parser parser = new Schema.Parser();

        try {

            return parser.parse(new ClassPathResource(local).getInputStream());

        } catch (Exception e) {

            e.printStackTrace();
            System.out.println("Erro do avsc " + e.getMessage());
            throw e;

        }
    }
}
