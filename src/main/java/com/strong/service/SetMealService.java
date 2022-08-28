package com.strong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strong.dto.SetmealDto;
import com.strong.entity.Category;
import com.strong.entity.Setmeal;

import java.util.List;

public interface SetMealService extends IService<Setmeal> {
    void saveWithDishes(SetmealDto setmealDto);

    /**
     * 分页查询
     * @param page 当前页
     * @param pageSize 分页的数量
     * @param name 查询条件
     * @return
     */
    List<Setmeal> page(int page , int pageSize , String name);
}
