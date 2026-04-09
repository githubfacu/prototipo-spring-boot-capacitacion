package com.example.tienda_demo.services;

import com.example.tienda_demo.domain.Person;
import com.example.tienda_demo.helpers.ReportPDFImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Lazy
@Service("indian")
@ConditionalOnProperty(prefix = "implementation", value = "people", havingValue = "indian")
public class PeopleServiceIndianImpl implements PeopleService{

    private ReportPDFImpl reportPDF;
    ArrayList<Person> indian = new ArrayList<>(
            List.of(new Person(1L, "Raj", "Krishna", "krishna@email.com", "aaabc6"),
                    new Person(2L, "Bahubali", "Kumar", "bahubali@email.com", "aaabcd1"),
                    new Person(3L, "Radha", "Kumar", "ritu@email.com", "aaabc67"))
    );

    public PeopleServiceIndianImpl(ReportPDFImpl reportPDF){
        this.reportPDF = reportPDF;
        log.info("ejecutando constructor de PeopleServiceIndianImpl");
    }

    @Override
    public List<Person> listAllPeople(){
        this.reportPDF.generatePdfReport(indian);
        return this.indian;
    }
}
