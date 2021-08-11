package com.example.guangda.entity;

import java.io.Serializable;
import lombok.Data;

/**
 * linkman
 * @author 
 */
@Data
public class LinkmanKey implements Serializable {
    /**
     * 准客户号码
     */
    private Integer precustomerno;

    /**
     * 联系人顺序号
     */
    private Integer linkorderno;

    private static final long serialVersionUID = 1L;
}