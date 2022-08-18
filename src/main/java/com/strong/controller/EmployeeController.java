package com.strong.controller;


import com.strong.common.R;
import com.strong.entity.Employee;
import com.strong.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * employee用户登录
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {
        //1.将页面提交的数据进行Md5 数据加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //2.根据提交的用户名查询数据库  **em代表查询到的数据
        Employee em = employeeService.selectByUsername(employee.getUsername());
        //3.如果没有数据就直接判断失败返回结果
        if (em == null) {
            return R.error("用户名不存在");
        }
        //4.密码对比,如果不一致的时间返回错误信息
        if (!password.equals(em.getPassword())) {
            return R.error("密码错误");
        }
        //5.查看状态
        if (em.getStatus() == 0) {
            return R.error("用户被禁用");
        }

        request.getSession().setAttribute("employee", em.getId());
        return R.success(em);
    }

    @PostMapping("/logout")
    public R<String> logout(HttpServletRequest request) {
        request.getSession().removeAttribute("employee");

        return R.success("退出成功");
    }

}
