package com.strong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strong.entity.Dish;
import com.strong.mapper.CategoryMapper;
import com.strong.mapper.DishMapper;
import com.strong.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private CategoryMapper categoryMapper;

}
