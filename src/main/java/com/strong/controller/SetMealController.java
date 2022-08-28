package com.strong.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strong.common.R;
import com.strong.dto.SetmealDto;
import com.strong.entity.Category;
import com.strong.entity.Setmeal;
import com.strong.service.CategoryService;
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




//        Page<Setmeal> setmealPage = new Page<>();
//        List<Setmeal> pageList = setMealService.page(page, pageSize, name);
//
//
//        ArrayList<SetmealDto> setmealDtos = new ArrayList<>();
//        // 在这里面设置套餐的分类
//        for (Setmeal setmeal : pageList) {
//            //复制数据
//            SetmealDto setmealDto = new SetmealDto();
//            BeanUtils.copyProperties(setmeal, setmealDto);
//            //更新菜品的名称
//            Long categoryId = setmeal.getCategoryId();
//            Category category = categoryService.getById(categoryId);
//            if (category != null) {
//                setmealDto.setCategoryName(category.getName());
//            }
//            setmealDtos.add(setmealDto);
//        }
//        setmealPage.setRecords(pageList);
//        return R.success(setmealPage);
    }
}
