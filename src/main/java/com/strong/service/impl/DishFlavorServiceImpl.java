package com.strong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strong.entity.DishFlavor;
import com.strong.mapper.DishFlavorMapper;
import com.strong.service.DishFlavorService;
import org.springframework.stereotype.Service;

@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor> implements DishFlavorService {
}
