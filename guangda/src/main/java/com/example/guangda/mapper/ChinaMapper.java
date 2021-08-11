package com.example.guangda.mapper;

import com.example.guangda.entity.China;

import java.util.List;

/**
 * @author asus
 * @create 2021-07-30 16:45
 */
public interface ChinaMapper {
    List<China> selectChina(int pid);
}
