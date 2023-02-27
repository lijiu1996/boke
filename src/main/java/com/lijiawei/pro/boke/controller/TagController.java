package com.lijiawei.pro.boke.controller;

import com.lijiawei.pro.boke.bean.Result;
import com.lijiawei.pro.boke.bean.vo.HotTagVO;
import com.lijiawei.pro.boke.service.TagService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RequestMapping("tags")
@RestController
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 统计最热门标签
     *      攻克困难 -- 写mybatis做关联查询 实现select(max(count))
     * @return
     */
    @GetMapping("/hot")
    private Result getHottestTag() {
        int limit = 2;
        List<HotTagVO> hotTag = tagService.getHotTag(limit);
        return Result.ok().data(hotTag);
    }

}
