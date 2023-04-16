package com.example.obana1;

import com.example.obana1.database.SchemeLoader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Class for start initialization of database
 */
@Component
public class Init implements InitializingBean {

    @Autowired
    SchemeLoader schemeLoader;


    @Override
    public void afterPropertiesSet() {
    }

}
