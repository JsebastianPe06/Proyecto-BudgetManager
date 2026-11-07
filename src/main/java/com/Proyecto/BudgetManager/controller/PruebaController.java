package com.Proyecto.BudgetManager.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waos") //Url requiere este "controller para visualizarse
public class PruebaController {
    
    @GetMapping("/hola")  //para visualizar, final del url. Ejemplo: localhost:8080/waos/hola
    public String holaPe()
    {
        return "Hola Mundo!!!! :v";
    }
    
    @GetMapping("/holanombre/{name}")  //para visualizar, final de url con variables. Ejemplo: localhost:8080/waos/holanombre/Juan
    public String hola(@PathVariable String name)
    {
        return "Hola Mundo!!!! :v :  "+ name;
    }
}


