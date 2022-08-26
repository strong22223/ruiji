package com.strong.service;

import com.strong.dto.DishDto;
import com.strong.entity.Employee;
import com.strong.mapper.DishMapper;
import com.strong.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Wrapper;
import java.util.List;

@SpringBootTest
@Slf4j
public class Service {


    @Autowired
    EmployeeService employeeService;

    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    DishMapper dishMapper;
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
        List<DishDto> dishDtoList = dishMapper.selectAllByCategoryId(1397844263642378242L);
        log.info(String.valueOf(dishDtoList));
    }
}
