package com.strong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strong.entity.Category;
import com.strong.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
