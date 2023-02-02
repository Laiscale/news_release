package com.news_release.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.news_release.enity.ArticleComment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper

public interface ArticleCommentMapper extends BaseMapper<ArticleComment> {
}
