package com.pestmonitors.pestmonitors.controllers;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class SampleForLogsController {

    //private final Logger log = LoggerFactory.getLogger(SampleForLogsController.class);
    @GetMapping("/sample") //este por si usas SIFT (mira documentacion curso)
    public void sampleForLog(){

        //MDC.put("userid", name);

        log.trace("Ejecutando hola mundo trace");
        log.debug("Ejecutando hola mundo debug");
        log.info("Ejecutando hola mundo info");
        log.warn("Ejecutando hola mundo warn");
        log.error("Ejecutando hola mundo error");
    }
}
