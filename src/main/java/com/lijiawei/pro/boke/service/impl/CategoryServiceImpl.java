package com.lijiawei.pro.boke.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijiawei.pro.boke.bean.entity.Category;
import com.lijiawei.pro.boke.bean.vo.CategoryVO;
import com.lijiawei.pro.boke.service.CategoryService;
import com.lijiawei.pro.boke.mapper.CategoryMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
* @author lijiawei
* @description 针对表【t_category】的数据库操作Service实现
* @createDate 2023-03-01 00:02:25
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Override
    public CategoryVO getVOById(Integer categoryId) {
        Category category = this.getById(categoryId);
        CategoryVO categoryVO = new CategoryVO();
        BeanUtils.copyProperties(category,categoryVO);
        return categoryVO;
    }
}




