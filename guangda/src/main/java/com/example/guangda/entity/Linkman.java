package com.example.guangda.entity;

import java.io.Serializable;
import java.sql.Date;
import lombok.Data;

/**
 * linkman
 * @author 
 */
@Data
public class Linkman extends LinkmanKey implements Serializable {
    /**
     * 姓名
     */
    private String linkman;

    /**
     * 手机
     */
    private String mobile;

    /**
     * 办公电话
     */
    private String phone;

    /**
     * 出生日期
     */
    private Date birthday;

    /**
     * 部门
     */
    private String depart;

    /**
     * 职位
     */
    private String post;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 区分联系人
     */
    private int area;
    private static final long serialVersionUID = 1L;
}