package com.strong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.strong.common.R;
import com.strong.entity.Category;

public interface CategoryService extends IService<Category> {
    /**
     * 自己得remove方法,在删除的时间会判断当前 菜品的种类是否 关联了 具体的菜品
     * 1->>删除失败
     * 0->>可以删除
     * @param id
     * @return
     */
    R<String> removeById(Long id);
}
