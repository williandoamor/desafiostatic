package br.com.loadti.desafiostatic.controller;


import br.com.loadti.desafiostatic.cdr.Cdr;
import br.com.loadti.desafiostatic.response.Response;
import br.com.loadti.desafiostatic.services.ParquetService;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class CdrController {

    private String folder = new File(".").getCanonicalPath() + "/parquet/";


    private ParquetService cdrService;

    @Autowired
    public CdrController(ParquetService cdrService) throws IOException {
        this.cdrService = cdrService;
    }


    @PostMapping("/fileupload")
    public Response sendFile(@RequestParam("file") MultipartFile file) {

        return cdrService.readFile(file, folder);

    }

    @GetMapping("/reader")
    public List<Cdr> readerParquet() throws ParseException {

        return cdrService.readerParquet(folder, "2019-01-01", "2020-11-30", "5521987366501", "5521908100740", "", "");
    }
}
