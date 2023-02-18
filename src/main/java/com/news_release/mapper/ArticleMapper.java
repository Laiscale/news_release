package com.news_release.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.news_release.enity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper

public interface ArticleMapper extends BaseMapper<Article> {
    @Select("SELECT a.*, c.cname FROM article a LEFT JOIN category c ON a.category = c.cid WHERE c.cname = #{category}")
    List<Article> selectArticleWithCategory(@Param("category") String category);
}
