package com.strong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strong.dto.DishDto;
import com.strong.entity.Dish;
import com.strong.entity.DishFlavor;
import com.strong.mapper.DishMapper;
import com.strong.service.DishService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorServiceImpl dishFlavorService;

    @Autowired
    private DishMapper dishMapper;

    /**
     * 新增菜品,同时保存对应的口味 ,将口味和菜品关联
     *
     * @param dishDto
     */
    @Override
    @Transactional
    public void saveWithDishFlavor(DishDto dishDto) {
        //封装dish数据
        this.save(dishDto);
        //获取dish的Id麻将dish的id和 dishFlavor关联起来
        Long dishId = dishDto.getId();
        //封装dishFlavor数据
        for (DishFlavor flavor : dishDto.getFlavors()) {
            //设置dishFlavor与dish挂了立案的Id
            flavor.setDishId(dishId);
            //保存关联Id后的DishFlavor
            dishFlavorService.save(flavor);
        }

    }

    @Override
    public DishDto getByIdWithDishFlavor(Long id) {
        Dish dish = this.getById(id);
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dish.getId());

        List<DishFlavor> list = dishFlavorService.list(queryWrapper);

        //拷贝数据
        DishDto dishDto = new DishDto();
        BeanUtils.copyProperties(dish, dishDto);
        dishDto.setFlavors(list);
        //返回数值
        return dishDto;
    }

    @Override
    public void updateWithDishFlavor(DishDto dishDto) {
        //更新dishDto的基本信息
        this.updateById(dishDto);

        //重新获取口味的信息
        //首先删除
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, dishDto.getId());
        dishFlavorService.remove(queryWrapper);
        //其次直接更新
        Long dishId = dishDto.getId();
        //封装dishFlavor数据
        for (DishFlavor flavor : dishDto.getFlavors()) {
            //设置dishFlavor与dish挂了立案的Id
            flavor.setDishId(dishId);
            dishFlavorService.save(flavor);

        }
    }

    @Override
    public List<DishDto> selectAllByCategoryId(Long categoryId) {

        return dishMapper.selectAllByCategoryId(categoryId);
    }

}
