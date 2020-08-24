package com.rkcpinfo.uizuulapp;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    OAuth2RestTemplate restTemplate;

    @Value("${catalog.api:http://localhost:8181/catalog/products}")
    String catalogApi;

    @GetMapping("/")
    public String index(Model model)
    {        
        ResponseEntity<ProductDTO[]> productResp = restTemplate.getForEntity(catalogApi, ProductDTO[].class);
        List<ProductDTO> productDTOS = Arrays.asList(productResp.getBody());

        model.addAttribute("products", productDTOS);

        return "index.vue";
    }
}

@Data
class ProductDTO
{
    Long id;
    String name;
    double price;
    String promotion;
}
