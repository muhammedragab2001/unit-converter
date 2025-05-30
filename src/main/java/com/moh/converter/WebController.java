/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.moh.converter;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author moh
 */
//الخطوة 2: إنشاء WebController لتقديم صفحة HTML
@Controller
public class WebController {
    // the root of app
    @GetMapping("/")
    public String homePage(){
        return "index"; // display html file
    }
}
