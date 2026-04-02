package com.example.tienda_demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello world",
        description = "Documentación API people Java spring boot practical"
)
public class HelloWorldRestController {

    @Operation(summary = "Recupera usuario",
            description = "Retorna usuario segun parametro recibido"
    )
    @GetMapping("/user/{user}")

    public String Saludar(@PathVariable String user){
        return "Hello World " + user;
    }
}
