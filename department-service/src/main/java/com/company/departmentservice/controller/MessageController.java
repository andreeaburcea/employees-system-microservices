package com.company.departmentservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//from org cloud -> will force this spring bean to reload the configuration.
// will trigger the refresh event to reload the configuration.
// whenever we update the configuration file, we need to reflect that configuration changes for this add value annotation.
// that s why we need to force this spring bean to reload the configuration file.
@RefreshScope
//spring bean
public class MessageController {

//    let's read the value from properties files and our REST API will return that value.
    // property key:
    // create the key in the config file in git repo
    @Value("${spring.boot.message}")
    private String message;

//    rest api to return the message
    @GetMapping("message")
    public  String message() {
        return  message;
    }

}
