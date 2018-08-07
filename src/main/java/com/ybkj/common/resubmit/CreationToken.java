package com.ybkj.common.resubmit;

import com.ybkj.common.annotation.Token;
import com.ybkj.common.model.BaseModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Api(value = "/",description = "创建Token功能")
@SuppressWarnings("all")
@RestController
@RequestMapping("/token")
public class CreationToken {


    @Token(save = true)
    @RequestMapping(value = "/saveToken",method = RequestMethod.POST)
    public BaseModel saveToken(HttpServletRequest request, HttpServletResponse response){
        BaseModel baseModel=new BaseModel();
        String token=(String) request.getSession().getAttribute("tokenliu");
        baseModel.add("token",token);
        System.out.println("-------生成token-----"+ token);
        return baseModel;
    }
}
