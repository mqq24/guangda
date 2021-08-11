package com.example.guangda.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * client
 * @author 
 */
@Data
public class Client implements Serializable {
    /**
     * 客户编号
     */
    private Integer precustomerno;

    /**
     * 企业名称
     */
    private String precustomername;

    /**
     * 投保意向
     */
    private String insure;

    /**
     * 证件类型
     */
    private String idtype;

    /**
     * 行业类型
     */
    private String busicategory;

    /**
     * 单位性质
     */
    private String grpnature;

    /**
     * 证件号码
     */
    private String idno;

    /**
     * 单位总人数
     */
    private Integer sumnumpeople;

    /**
     * 预计人数
     */
    private Integer presumpeople;

    /**
     * 单位省份
     */
    private String province;

    /**
     * 单位市
     */
    private String city;

    /**
     * 单位区/县
     */
    private String county;

    /**
     * 详细地址
     */
    private String detailaddress;

    /**
     * 映射
     */
    private List<Linkman> linkman;

    /**
     * 模查
     */
    private String nameMobileId;

    private static final long serialVersionUID = 1L;
}