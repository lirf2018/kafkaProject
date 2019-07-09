package com.uhuibao.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 创建人: lirf
 * 创建时间:  2018/12/11 16:18
 * 功能介绍:  旅游啦 对接
 */
public class LuyulaUtil {

    private static volatile LuyulaUtil luyulaUtil;
    private static String email = "services@uhuibao.com";
    private static String password = "Uhui0030";
    private static String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiIsImp0aSI6IjE3Mzc5ZThmYWFiMDgzNzE5M2E1Y2E4NjQxOWY4MGQ1NTg0ZmZiODA0NmMzNzI0YjEwZWU3OTZjOWM2OGUxNjM3NDc5MGVkZjJjYjM4NDVkIn0.eyJhdWQiOiI5IiwianRpIjoiMTczNzllOGZhYWIwODM3MTkzYTVjYTg2NDE5ZjgwZDU1ODRmZmI4MDQ2YzM3MjRiMTBlZTc5NmM5YzY4ZTE2Mzc0NzkwZWRmMmNiMzg0NWQiLCJpYXQiOjE1NDQ2MDE4NDgsIm5iZiI6MTU0NDYwMTg0OCwiZXhwIjoxNTc2MTM3ODQ4LCJzdWIiOiIxMyIsInNjb3BlcyI6W119.ThqYg7HRYOuz4b2qDAIavvnOwyQoYvuhB9dv0IO6G3S8AVwqpYvIeFiPuKsSg1CKBJfc3WPxM9wsynfh4E51ZiKZZ5MeK320A6BkW5VxI2l9dCM_ZY9vvUwg0ExKYtP42eOIOxS-QyZfRnValpnivp5mtS5RodQFPT68Ie1oCC2N8F6TqhspM2AGHUg0gjfTvv9ndQlRg3Js0CNfqrfl40j-V9mdrW9U3ERHb2WT-PyC8DQKcj35yIs-VJuiYw82gWL3uiheCa3e9mHrxHDxD4awLbTeuCwNQitqyuekMYd5SDebh196XJdgulgszFuMfU2niYUqZKDKSStvwQdW7ILmPKi93CMxGTe2BPaKmmfJcuecs47FjcvLo5y1kfJblbmSvy5lW-1hm9Ml0IN-K5QTBsKM8UZ3ygwJARLDixthTPia28kKjAfV-F5nAkLZ1odYD1lJlQwh3VrHqN227OdaMQXPP8cuR1PV4eX6qIHumhzCoMLHDJnzp1xLG2yZ9Tinu8gxj21z-gUuutMiyJSOzf4sMruVAdjWKkRU83AYY1ftc_YXYc_FsH62kMrTWTTrbb-ct5T_5nrFmG4yrVxZCQOzvTHjNwQlJ0DsHH4jqCmDd-gYVInrOd07WlLbQ9jPcdL02rw2V8P7Xtomus4bl6wV94bhE7khEogqT9U";

    public static LuyulaUtil getInstence() {
        if (null == luyulaUtil) {
            synchronized (LuyulaUtil.class) {
                if (null == luyulaUtil) {
                    luyulaUtil = new LuyulaUtil();
                }
            }
        }
        return luyulaUtil;
    }


    public String getToken() {
        try {
            String url = "https://api.luyula.com/api/login";
            List<NameValuePair> paramsList = new ArrayList<>();
            paramsList.add(new BasicNameValuePair("email", email));
            paramsList.add(new BasicNameValuePair("password", password));

            String result = HttpsRequest.httpsPostNameValuePair(url, paramsList, null);
            if (StringUtils.isNotEmpty(result)) {
                JSONObject object = JSONObject.parseObject(result);
                String code = object.getString("success");
                if (null != code) {
                    return object.getJSONObject("success").getString("token");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 所有能接送的酒店下⾞點
     *
     * @return
     */
    public JSONArray getTransport() {
        try {
            String url = "https://api.luyula.com/api/hotels/transport";
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("Accept", "application/json");
            headersMap.put("Authorization", "Bearer " + token);
            String result = HttpsRequest.httpsGetNameValuePair(url, null, headersMap);
            if (StringUtils.isNotEmpty(result)) {
                return JSONArray.parseArray(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 下单
     *
     * @param bean
     * @return
     */
    public String orderSubmit(LuyulaContentBean bean) {
        try {
            String url = "https://api.luyula.com/api/orders";

            JSONObject content = new JSONObject();
            content.put("content", JSONObject.toJSONString(bean));
            JSONArray detailArr = new JSONArray();
            detailArr.add(content);

//            JSONObject orderDetail1 = new JSONObject();
//            orderDetail1.put("subtype", "BUSSERVICES");//'subtype' : BUSSERVICES
//            orderDetail1.put("orderNumber", "testUHB1544609186124");//'orderNumber' // 貴公司的訂單號
//            orderDetail1.put("name", "测试订单");//'name', // 客⼾姓名
//            orderDetail1.put("date", "2018-12-12");//'date', // 到港⽇期
//            orderDetail1.put("hotelCode", "W8H");//'hotelCode' // 下⾞點酒店代碼
//            orderDetail1.put("flightNumber", "Test8888");//'flightNumber', // 航班編號
//            orderDetail1.put("qty", 1);//'qty', // ⼈數
//            orderDetail1.put("mobile", "13418915218");//'mobile', // ⼿機號碼
//            orderDetail1.put("remark", "测试订单");//'remark', // 備註
//            orderDetail1.put("created_at", DateUtil.getNow());//'createdat' // 建單⽇期
//
//            JSONObject contentObj = new JSONObject();
//            contentObj.put("content", orderDetail1.toString());
//
//            JSONArray contents = new JSONArray();
//            contents.add(contentObj);
//
            JSONObject obj = new JSONObject();
            obj.put("type", "TRANSPORT");
            obj.put("amount", "1.00");
            obj.put("status", "CONFIRM");
            obj.put("detail", detailArr);
//
//            System.out.println(obj.toString());
            //头部
            Map<String, String> headersMap = new HashMap<>();
            headersMap.put("Content-Type", "application/json");
            headersMap.put("Authorization", "Bearer " + token);
            System.out.println(obj);
//            String result = HttpUtils.httpPost(url, obj.toString(), headersMap);
//            System.out.println("---->" + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void main(String[] args) {
//        System.out.println("---->" + LuyulaUtil.getInstence().getTransport());
        LuyulaContentBean bean = new LuyulaContentBean();
        String subtype = "BUSSERVICES";// : BUSSERVICES
        String orderNumber = "testUHB" + System.currentTimeMillis();// // 貴公司的訂單號
        String name = "测试订单"; // 客⼾姓名
        String date = "2018-12-12"; // 到港⽇期
        String hotelCode = "W8H";  // 下⾞點酒店代碼
        String flightNumber = "Test8888";  // 航班編號
        Integer qty = 1;  // ⼈數
        String mobile = "13418915218";  // ⼿機號碼
        String remark = "测试订单";  // 備註
        String createdat = DateUtil.getNow();  // 建單⽇期

        bean.setSubtype(subtype);
        bean.setOrderNumber(orderNumber);
        bean.setName(name);
        bean.setDate(date);//YYYY-MM-dd
        bean.setHotelCode(hotelCode);
        bean.setFlightNumber(flightNumber);
        bean.setQty(qty);
        bean.setMobile(mobile);
        bean.setRemark(remark);
        bean.setCreatedat(createdat);
        LuyulaUtil.getInstence().orderSubmit(bean);
    }

}
