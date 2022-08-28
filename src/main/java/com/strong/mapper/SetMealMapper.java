package com.strong.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.strong.dto.SetmealDto;
import com.strong.entity.Setmeal;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetMealMapper extends BaseMapper<Setmeal> {
    void save(SetmealDto setmealDto);

    /**
     * 分页插叙套餐
     *
     * @param begin
     * @param end
     * @param name
     * @return
     */
    List<Setmeal> page(int begin, int end, String name);
}
