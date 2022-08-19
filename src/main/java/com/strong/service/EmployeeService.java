package com.strong.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.strong.entity.Employee;
import org.apache.ibatis.annotations.Param;

public interface EmployeeService extends IService<Employee> {

  Employee selectByUsername(@Param("username") String username);

}
