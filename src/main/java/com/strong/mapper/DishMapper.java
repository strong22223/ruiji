package com.strong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strong.dto.DishDto;
import com.strong.entity.Category;
import com.strong.entity.Dish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper extends BaseMapper<Dish> {

    List<DishDto> selectAllByCategoryId(long categoryId);
}
