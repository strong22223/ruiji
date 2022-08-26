package com.strong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strong.dto.SetmealDto;
import com.strong.entity.Category;
import com.strong.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetMealMapper extends BaseMapper<Setmeal>   {
    void save(SetmealDto setmealDto);

}
