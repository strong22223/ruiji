package com.strong.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.strong.common.R;
import com.strong.entity.Category;
import com.strong.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@ResponseBody
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 添加菜品
     *
     * @param category
     * @return
     */
    @PostMapping
    public R<String> save(@RequestBody Category category) {
        log.info(" category->>{}", category);
        categoryService.save(category);

        return R.success("添加成功");
    }

    @GetMapping("/page")
    public R<Page> page(int page, int pageSize) {
        //分页构造器
        Page<Category> pageInfo = new Page<>(page, pageSize);
        //添加条件构造器
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        // 添加过滤条件
        queryWrapper.orderByDesc(Category::getSort);

        //进行分页查询
        categoryService.page(pageInfo, queryWrapper);
        return R.success(pageInfo);
    }

    /**
     * 删除菜品
     *
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> deleteById(@RequestParam("ids") Long id) {
        log.info("删除菜品的信息->>{}", id);
        categoryService.removeById(id);

        return R.success("删除成功");
    }

    @PutMapping
    public R<String> update(@RequestBody Category category) {
        log.info("更新->>{}", category);
        categoryService.updateById(category);
        return R.success("修改成功");
    }
}

