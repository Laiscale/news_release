package com.news_release.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.news_release.enity.Plank;
import com.news_release.mapper.PlankMapper;
import com.news_release.service.PlankService;
import org.springframework.stereotype.Service;

@Service
public class PlankServiceImpl extends ServiceImpl<PlankMapper, Plank> implements PlankService {
}
