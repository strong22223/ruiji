package com.strong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strong.common.R;
import com.strong.common.exception.DeleteFailCauseRelevanceOther;
import com.strong.entity.Category;
import com.strong.entity.Dish;
import com.strong.entity.Setmeal;
import com.strong.mapper.CategoryMapper;
import com.strong.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    //配合category来查询 该表下面师傅关联菜品
    @Autowired
    private DishServiceImpl dishService;

    //配合category来查询 该表下面师傅关联  套餐
    @Autowired
    private SetMealServiceImpl setMealService;


    @Override
    public R<String> removeById(Long id) {
        /**
         *         1.首先查询 该分类名称下面是否有关联的 ##菜品## 和 ##套餐##
         *              假如拥有两者中的任意一个的时间 ,就去抛出异常
         */
        //首先创建  构造器
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int dishCount = dishService.count(dishLambdaQueryWrapper);

        //查看当前是否菜品是否关联了菜品,如果
        if (dishCount > 0) {
            throw new DeleteFailCauseRelevanceOther("删除错误,当前产品已经关联其他菜品或者套餐!");
        }

        //首先创建  构造器
        LambdaQueryWrapper<Setmeal> setMealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        //添加查询条件
        setMealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int setMealCount = setMealService.count(setMealLambdaQueryWrapper);

        //查看当前是否菜品是否关联了菜品,如果
        if (setMealCount > 0) {
            throw new DeleteFailCauseRelevanceOther("删除错误,当前产品已经关联其他菜品或者套餐!");
        }
        //到最后的时间 正常删除
        super.removeById(id);


        return R.success("删除成功");
    }
}
