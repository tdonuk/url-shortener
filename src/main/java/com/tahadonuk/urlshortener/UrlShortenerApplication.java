package com.tahadonuk.urlshortener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcAutoConfiguration;

import java.util.Properties;

@SpringBootApplication(exclude = {R2dbcAutoConfiguration.class})
public class UrlShortenerApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(UrlShortenerApplication.class);

        Properties props = new Properties();

        props.put("spring.datasource.username","postgres");
        props.put("spring.datasource.password","dbpassword");
        props.put("spring.datasource.driver-class-name","org.postgresql.Driver");
        props.put("spring.datasource.url","jdbc:postgresql://postgredb.cjmrmjazz9un.eu-central-1.rds.amazonaws.com:5432/URLShortenerDB");
        props.put("spring.jpa.generate-ddl", "true");
        props.put("spring.jpa.hibernate.ddl-auto","update");

        app.setDefaultProperties(props);

        app.run(args);
    }

}
