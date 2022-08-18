package com.strong.service;


import com.strong.entity.Employee;

public interface EmployeeService {
    /**
     * 依据用户查询
     *
     * @param username
     * @return
     */
    Employee selectByUsername(String username);
}
