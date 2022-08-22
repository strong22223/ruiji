package com.strong.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strong.common.R;
import com.strong.entity.Employee;
import com.strong.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;


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

    /**
     * 添加员工
     *
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> insert(HttpServletRequest request, @RequestBody Employee employee) {

        log.info("新增员工,员工信息->>{}", employee.toString());

        //设置加密密码
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));

        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //设置创建用户
        Long empId = (long) request.getSession().getAttribute("employee");

        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);


        employeeService.save(employee);
        return R.success("添加成功");
    }

    /**
     * 分页查询
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {

        //构造分页构造器
        Page pageInfo = new Page(page, pageSize);
        //构造调价构造器
        LambdaQueryWrapper<Employee> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName, name);
        //添加排序条件
        lambdaQueryWrapper.orderByDesc(Employee::getUpdateTime);
        //执行查询
        employeeService.page(pageInfo, lambdaQueryWrapper);

        log.info("page=>{},pageSize->>{},name->>{}", page, pageSize, name);
        return R.success(pageInfo);
    }

    @PutMapping
    public R<String> update(HttpServletRequest request, @RequestBody Employee employee) {
        //1.首先测试获取用户的信息
        log.info("更新的用户信息->>{}", employee);
        //获取修改信息的用户的信息
        Long emId = (Long) request.getSession().getAttribute("employee");
        //更新修改时间

        employee.setUpdateUser(emId);
        employee.setUpdateTime(LocalDateTime.now());
        //调用Service更新数据
        employeeService.updateById(employee);
        return R.success("更新用户信息成功");
    }

    /**
     * 根据用户 的id 来查询信息
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> getById(@PathVariable Long id) {
        log.info("根据id查询用户的信息->>{}", id);
        Employee employee = employeeService.getById(id);
        return R.success(employee);
    }
}
