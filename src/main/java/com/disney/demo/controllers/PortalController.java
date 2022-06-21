package com.disney.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author d.andresperalta
 */
@Controller
public class PortalController {

    @GetMapping("/")
    public String index() {
        
        return "index.html";
    }
}
