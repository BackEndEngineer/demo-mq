package com.controller;

import com.service.SenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SenderController {

    @Autowired
    private SenderService senderService;

    @RequestMapping(value = "/sender")
    public String sendMessage(@RequestParam("message")String message){
        senderService.sendInfo(message);
        return "sendRes";
    }
}
