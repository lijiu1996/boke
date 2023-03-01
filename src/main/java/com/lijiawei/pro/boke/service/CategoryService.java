package com.lijiawei.pro.boke.service;

import com.lijiawei.pro.boke.bean.entity.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lijiawei.pro.boke.bean.vo.CategoryVO;

/**
* @author lijiawei
* @description 针对表【t_category】的数据库操作Service
* @createDate 2023-03-01 00:02:25
*/
public interface CategoryService extends IService<Category> {

    CategoryVO getVOById(Integer categoryId);
}
