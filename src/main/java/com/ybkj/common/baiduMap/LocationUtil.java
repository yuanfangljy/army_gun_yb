package com.ybkj.common.baiduMap;


import com.ybkj.common.model.BaseModel;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/locationUtil")
public class LocationUtil {

    @RequestMapping(value = "/getLocation",method = RequestMethod.GET)
    public BaseModel getLocation(String lng, String lat) {
       // System.out.println(lng+"============="+lat);
        BaseModel baseModel=new BaseModel();
        baseModel.add("realLocations",BaiDuUtil.getAddress(lng, lat));
        return baseModel;
    }
}
