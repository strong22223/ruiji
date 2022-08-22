package com.strong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.strong.entity.Setmeal;
import com.strong.mapper.SetMealMapper;
import com.strong.service.SetMealService;

import org.springframework.stereotype.Service;

@Service
public class SetMealServiceImpl extends ServiceImpl<SetMealMapper, Setmeal> implements SetMealService {
}
