package com.ybkj.common.baiduMap;


import com.alibaba.fastjson.JSONObject;

/**
 * 百度工具类
 * 
 * @author xuyw
 * @email xyw10000@163.com
 * @date 2014-06-22
 */
public class BaiDuUtil {
  public static String getCity(String lat, String lng) {

    JSONObject obj = getLocationInfo(lat, lng).getJSONObject("result")
            .getJSONObject("addressComponent");
    return obj.getString("city");
  }

  public static JSONObject getLocationInfo(String lat, String lng) {
    String url = "http://api.map.baidu.com/geocoder/v2/?location=" + lat
            + "," + lng
            + "&output=json&ak=G4o7IkuwiV94LpnCn9GERMjjUIVx2G10&pois=0";
    JSONObject obj = JSONObject.parseObject(HttpUtil.getRequest(url));
    //System.out.println("\n"+"经纬度查询地址为：" + url);
    //System.out.println("\n"+"经纬度坐标返回结果为：" + obj.toString());
    return obj;

  }

  public static String getAddress(String lng, String lat) {  //lng经度，lat纬度
    JSONObject obj = getLocationInfo(lat, lng).getJSONObject("result");
    StringBuilder address = new StringBuilder();
    String province = obj.getJSONObject("addressComponent").getString(
            "province");
    String city = obj.getJSONObject("addressComponent").getString("city");
    if (province != null && city != null && province.equals(city)) { 	//直辖市的省份名称和城市名称一样，判断重复时去掉
      address.append(
              obj.getJSONObject("addressComponent").getString("city"))
              .append(obj.getJSONObject("addressComponent").getString(
                      "district"))
              .append(obj.getJSONObject("addressComponent").getString(
                      "street"))
              .append(obj.getString("sematic_description"));
    } else {
      address.append(
              obj.getJSONObject("addressComponent").getString("province"))
              .append(obj.getJSONObject("addressComponent").getString(
                      "city"))
              .append(obj.getJSONObject("addressComponent").getString(
                      "district"))
              .append(obj.getJSONObject("addressComponent").getString(
                      "street"))
              .append(obj.getString("sematic_description"));
    }

    return address.toString();
  }

  public static void main(String[] args) {
    System.out.println("\n"+"经纬度具体地址为："
            + BaiDuUtil.getAddress("113.95421137700424", "22.590718833511666"));
  }

}
