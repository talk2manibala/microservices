package com.microservices.mstraining.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Locale;

@RestController
@RequestMapping("/api")
public class MessageController {

    @RequestMapping(path = "/api/message", method = RequestMethod.GET)
    public String greet() {
        return "Welcome to MicroServices Learning Session !!";
    }

    @Autowired
    MessageSource messageSource;

    @GetMapping(path = "custom/message")
    public String sayHelloi18n() {
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("welcome.message",
                new Object[]{"Mani", "Bala"},
                "default welcome message",
                locale);
    }

}
