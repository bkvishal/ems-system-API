package com.employetracker.controller;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author : Vishal Srivastava
 * @Date : 28-08-2021
 **/

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class AnotherService {

    public AnotherService() {
        System.out.println("prototype bean is up");
        // this class does not have any dependency so that it will not get initialized at starting of the applicationContext because it is a prototype bean.
    }
}
