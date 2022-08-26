package com.strong.dto;


import com.strong.entity.Setmeal;
import com.strong.entity.SetmealDish;
import lombok.Data;
import java.util.List;

/**
 * 套餐导入
 */
@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
