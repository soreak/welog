package com.soreak.web;

import com.soreak.NotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
//        int a = 1/0;
//        System.out.println(a);
//        String b = null;
//        if (b == null ){
//            throw  new NotFoundException("no");
//        }

        return "index";
    }

    @GetMapping("/blog")
    public String blog(){
//        int a = 1/0;
//        System.out.println(a);
//        String b = null;
//        if (b == null ){
//            throw  new NotFoundException("no");
//        }

        return "blog";
    }
}
