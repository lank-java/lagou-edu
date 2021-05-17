package com.lank;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lank
 * @since 2021/5/17 20:42
 */
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo(){
        return "demo";
    }

    @GetMapping("/ok")
    public String ok(){
        return "ok";
    }
}
