package br.com.loadti.desafiostatic.controller;


import br.com.loadti.desafiostatic.objetos.cdrParameter;
import br.com.loadti.desafiostatic.response.Response;
import br.com.loadti.desafiostatic.services.ParquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

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

    @PostMapping("/reader")
    public Response readerParquet(@RequestBody cdrParameter parameters) throws ParseException {

        return cdrService.readerParquet(folder, parameters.getDataInicial(), parameters.getDataFinal(), parameters.getChamador(), parameters.getRecebedor(), parameters.getAntenain(), parameters.getAntenaout());
    }
}
