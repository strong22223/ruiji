package com.strong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strong.dto.SetmealDto;
import com.strong.entity.Setmeal;
import com.strong.entity.SetmealDish;
import com.strong.mapper.SetMealMapper;
import com.strong.service.DishService;
import com.strong.service.SetMealDishService;
import com.strong.service.SetMealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {

    @Autowired
    private DishService dishService;
    @Autowired
    private SetMealDishService setMealDishService;
    @Autowired
    private SetMealMapper setMealMapper;

    @Override
    @Transactional
    public void saveWithDishes(SetmealDto setmealDto) {


        //调用MP来完成，存储功能
        this.save(setmealDto);
        Long id = setmealDto.getId();
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        for (SetmealDish setmealDish : setmealDishes) {
            //绑定关系
            setmealDish.setSetmealId(id);
            //存储套餐 下的菜品
            setMealDishService.save(setmealDish);
        }
    }

    @Override
    public List<Setmeal> page(int page, int pageSize, String name) {
        //首先计算分页的开始和结束
        int begin = (page - 1) * pageSize;
        int end = begin + pageSize;
        List<Setmeal> pageList = setMealMapper.page(begin, end, name);
        return pageList;
    }
}
