package com.SDP.BLL;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.tools.java.Environment;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Service
public class Settings {

    @Value("${spring.datasource.password}")
    private String dbpw;

    @Value("${spring.datasource.username}")
    private String user;

    @Value("${spring.datasource.url}")
    private String url;

    public String getDbpw() {
        return dbpw;
    }

    public void setDbpw(String dbpw) {
        this.dbpw = dbpw;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
