package com.ktdsuniversity.edu;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbTestController {

    @Autowired
    private DataSource dataSource;

    @GetMapping("/db-check")
    public String check() throws Exception {
        return dataSource.getConnection().toString();
    }
}