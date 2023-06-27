package com.zch.graduationdesign.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rec")
public class test {


    @PostMapping()
    public void index(@RequestBody String[] data){
        System.out.println(data);
        System.out.println(data[0]);
        System.out.println(data[1]);
    }

}
