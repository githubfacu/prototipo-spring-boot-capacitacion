package com.example.tienda_demo.helpers;

import com.example.tienda_demo.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class ReportPDFImpl {

    public void generatePdfReport(List<Person> people){
        log.info("Generate pdf report {}", people);
    }
}
