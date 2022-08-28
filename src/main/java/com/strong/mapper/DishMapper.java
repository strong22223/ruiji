package com.strong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strong.dto.DishDto;
import com.strong.entity.Dish;
import com.strong.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
    /**
     * 添加套餐的时间来查询菜品
     *
     * @param dish
     * @return
     */
    List<DishDto> selectAllByCategoryId(Dish dish);


}
