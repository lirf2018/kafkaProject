package com.uhuibao.util;

import com.alibaba.fastjson.annotation.JSONField;

/**
 * 创建人: lirf
 * 创建时间:  2018/12/11 17:32
 * 功能介绍: 旅游啦 对接
 */
public class LuyulaContentBean {

    private String subtype;// : BUSSERVICES
    private String orderNumber;// // 貴公司的訂單號
    private String name; // 客⼾姓名
    private String date; // 到港⽇期
    private String hotelCode;  // 下⾞點酒店代碼
    private String flightNumber;  // 航班編號
    private Integer qty;  // ⼈數
    private String mobile;  // ⼿機號碼
    private String remark;  // 備註

    @JSONField(name = "created_at")
    private String createdat;  // 建單⽇期

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHotelCode() {
        return hotelCode;
    }

    public void setHotelCode(String hotelCode) {
        this.hotelCode = hotelCode;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreatedat() {
        return createdat;
    }

    public void setCreatedat(String createdat) {
        this.createdat = createdat;
    }
}
