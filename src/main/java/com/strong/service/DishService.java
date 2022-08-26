package com.strong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strong.dto.DishDto;
import com.strong.entity.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {

    void saveWithDishFlavor(DishDto dishDto);

    /**
     * 根据菜品id 查询菜品信息 和菜品口味
     *
     * @param id
     * @return
     */
    DishDto getByIdWithDishFlavor(Long id);

    void updateWithDishFlavor(DishDto dishDto);

    /**
     * 依据categoryId来查询菜品分类下关联的所有的Dish
     *
     * @param categoryId
     * @return
     */
    List<DishDto> selectAllByCategoryId(Long categoryId);
}
