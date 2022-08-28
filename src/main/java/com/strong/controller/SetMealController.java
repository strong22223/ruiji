package com.strong.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strong.common.R;
import com.strong.dto.SetmealDto;
import com.strong.entity.Category;
import com.strong.entity.Setmeal;
import com.strong.service.CategoryService;
import com.strong.service.SetMealDishService;
import com.strong.service.SetMealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetMealController {

    @Autowired
    private SetMealService setMealService;

    @Autowired
    private CategoryService categoryService;
    @Autowired
    private SetMealDishService setMealDishService;


    /**
     * 添套餐管理 - 添加套餐
     *
     * @param setmealDto
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto) {
        log.info("封装的数据->>{}", setmealDto);
        setMealService.saveWithDishes(setmealDto);

        return R.success("添加套餐成功.");
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


        //获取SetMeal(套餐)数据的集合
        List<Setmeal> pageList = setMealService.page(page, pageSize, name);
        Page<SetmealDto> setmealDtoPage = new Page<>();
        //设置一个list集合来存储setRecords()
        List<SetmealDto> setmealDishes = new ArrayList<>();

        //将所偶有查询出来的套餐信息和菜品信息\套餐名称对应起来
        for (Setmeal setmeal : pageList) {
            //获取套餐分类的名称
            Long categoryId = setmeal.getCategoryId();
            Category byId = categoryService.getById(categoryId);

            //将
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(setmeal, setmealDto);
            //设置套餐名称
            setmealDto.setCategoryName(byId.getName());
            setmealDishes.add(setmealDto);
            //setMealDish是否用于封装
            //Long setmealId = setmeal.getId();

        }
        //存储setRecords()
        setmealDtoPage.setRecords(setmealDishes);
        // 在这里面设置套餐的分类
        return R.success(setmealDtoPage);

    }

    /**
     * 删除套餐的信息
     */
    @DeleteMapping
    public R<String> deleteSetmealById(@RequestParam List<Long> ids) {
        log.info("参数->>{}", ids);
        for (long id : ids) {
//            log.info("参数->>{}", id);
        }

        return null
                ;
    }

}
