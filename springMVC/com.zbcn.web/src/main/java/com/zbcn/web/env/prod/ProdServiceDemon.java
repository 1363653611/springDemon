package com.zbcn.web.env.prod;

import com.zbcn.web.env.EnvService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdServiceDemon implements EnvService {

    @Value("${zbcn.env}")
    private String name;

    public String getEnv(){
        return this.name;
    }
}
