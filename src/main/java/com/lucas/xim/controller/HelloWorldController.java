package com.lucas.xim.controller;

import com.lucas.xim.rto.TestRTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api")
public class HelloWorldController {
    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "HelloWorld";
    }

    @ResponseBody
    @RequestMapping(value = "/hello2", method = RequestMethod.POST)
    public String hello2(@RequestParam String name){
        return "HelloWorld2";
    }

    @ResponseBody
    @RequestMapping(value = "/hello3", method = RequestMethod.POST)
    public TestRTO hello3(@RequestBody @Valid TestRTO testRTO){
        return testRTO;
    }



}
