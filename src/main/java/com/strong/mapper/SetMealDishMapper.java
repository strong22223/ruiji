package com.strong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strong.dto.SetmealDto;
import com.strong.entity.Setmeal;
import com.strong.entity.SetmealDish;
import com.strong.service.SetMealDishService;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetMealDishMapper extends BaseMapper<SetmealDish>   {


}
