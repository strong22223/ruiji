package com.strong.mapper;


import com.strong.entity.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface EmployeeMapper {

    Employee selectByUsername(@Param("username") String username);

}
