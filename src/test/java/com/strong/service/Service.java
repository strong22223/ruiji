package com.strong.service;

import com.strong.entity.Employee;
import com.strong.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Service {


    @Autowired
    EmployeeService employeeService;

    /**
     * 测试单个查询
     */
    @Test
    void selectByUsernameTest() {
        String username = "admin";
        Employee employee = employeeService.selectByUsername(username);
        System.out.println(employee);
    }
    @Test
    void typeAliasTest() {
        String username = "admin";
        Employee employee = employeeService.selectByUsername(username);
        System.out.println(employee);
    }
}
