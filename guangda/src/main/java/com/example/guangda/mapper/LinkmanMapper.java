package com.example.guangda.mapper;

import com.example.guangda.entity.Linkman;
import com.example.guangda.entity.LinkmanKey;

public interface LinkmanMapper {
    void addLinkman(Linkman linkman);
    void deleteLinkman(LinkmanKey key);
    void updateByPrimaryKey(Linkman linkman);
}