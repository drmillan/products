package com.chocodev.products.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    private String version;

    @Autowired
    public VersionController(@Value("${project.version}") String version) {
        this.version = version;
    }

    @GetMapping("/version")
    public String version() {
        return version;
    }
}
