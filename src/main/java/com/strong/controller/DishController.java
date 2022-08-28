package com.strong.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strong.common.R;
import com.strong.dto.DishDto;
import com.strong.entity.Dish;
import com.strong.service.CategoryService;
import com.strong.service.DishFlavorService;
import com.strong.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private DishService dishService;
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody DishDto dishDto) {

        log.info("数据->>{}", dishDto);
        dishService.saveWithDishFlavor(dishDto);
        return R.success("新增菜品成功!");

    }

    /**
     * 分页请求
     *
     * @param page
     * @param pageSize
     * @param name
     * @return
     */

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name) {
        //分页构造器
        Page pageInfo = new Page(page, pageSize);
        List<Dish> dishList;

        //条件构造器
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null, Dish::getName, name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        //查询出来分页的数据
        Page list = dishService.page(pageInfo, queryWrapper);
        //将所有查询出的数据复制给 dishList
        dishList = list.getRecords();
        List<DishDto> dishDtoList = new ArrayList<>();

        for (Dish dish : dishList) {
            // 实现拷贝
            DishDto dishDto = new DishDto();
            BeanUtils.copyProperties(dish, dishDto);
            //首先获取categoryId
            Long categoryId = dish.getCategoryId();
            //查询categoryId 对应的名称 赋值给 每一个dishDto

            dishDto.setCategoryName(
                    categoryService.getById(categoryId).getName()
            );
            //添加处理过的数据
            dishDtoList.add(dishDto);
        }
        pageInfo.setRecords(dishDtoList);
        log.info("封装出来处理过的集合->>{}", dishList);

        return R.success(pageInfo);
    }

    //回显数据
    @GetMapping("/{id}")
    public R<DishDto> get(@PathVariable long id) {
        DishDto dishDto = dishService.getByIdWithDishFlavor(id);

        return R.success(dishDto);
    }

    @PutMapping
    public R<String> update(@RequestBody DishDto dishDto) {
        dishService.updateWithDishFlavor(dishDto);

        return R.success("在修改成功");
    }

    /**
     * 改变当前菜品的起售状态
     * @param dish
     */
//    public  void status (Dish dish){
//        Long categoryId = dish.getCategoryId();
//        dishService.updateStatus(dish);
//
//    }

    @GetMapping("/list")
    public R<List<DishDto>> list(Dish dish) {
        List<DishDto> dishDtoList = dishService.selectAllByCategoryId(dish);

        return R.success(dishDtoList);
    }
}
