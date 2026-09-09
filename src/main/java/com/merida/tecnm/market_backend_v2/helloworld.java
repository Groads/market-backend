package com.merida.tecnm.market_backend_v2;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/saludar")
public class helloworld {
    @GetMapping("/saludo")
    public String helloworlds(){
        return "Hello world!";
    }
}
