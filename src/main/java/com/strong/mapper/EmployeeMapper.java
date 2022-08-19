package com.strong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strong.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    Employee selectByUsername(@Param("username") String username);



}
