package com.strong.service.impl;


import com.strong.entity.Employee;
import com.strong.mapper.EmployeeMapper;
import com.strong.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Override
    public Employee selectByUsername(String username) {

        return employeeMapper.selectByUsername(username);

    }
}
