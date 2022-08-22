package com.strong.common.exception;

public class DeleteFailCauseRelevanceOther extends RuntimeException {

    private static final String relevanceOtherSetMealAndDish = "删除错误,当前产品已经关联其他菜品或者套餐";


    public DeleteFailCauseRelevanceOther(String msg) {
        super(msg);
    }
}
