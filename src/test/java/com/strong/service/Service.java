package com.strong.service;

import com.strong.entity.Employee;
import com.strong.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;

@SpringBootTest
public class Service {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;
    /**
     * 测试单个查询
     */
    @Test
    void selectByUsernameTest() {
        Employee employee = new Employee();
        employee.setUsername("王志强");
        employee.setPhone("17793391758");

        int insert = employeeMapper.insert(employee);
        System.out.println("Result->>"+insert);
        Long id = employee.getId();
        System.out.println("Id ->>"+id);

    }

    @Test
    void typeAliasTest() {
        String username = "admin";
        Employee employee = employeeService.selectByUsername(username);
        Employee employee1 = employeeMapper.selectByUsername(username);
        System.out.println(employee);
    }

    @Test
    void saveTest() {
        Employee em = new Employee();
        em.setName("张三");
         em.setPhone("17793391748");
        em.setUsername("wangzhi");
        em.setSex("1");
        em.setIdNumber("399483722888393847");

        employeeService.save(em);
    }
}
