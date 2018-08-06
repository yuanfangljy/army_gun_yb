package com.ybkj.gun.controller;

import com.ybkj.common.annotation.Token;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * index的控制层
 * @author karyzeng 2018.03.12
 * @version 1.0
 */
@Controller
public class IndexController {

	@Token(save = true)
	@RequestMapping("/savetoken")
	@ResponseBody
	public String getToken(HttpServletRequest request, HttpServletResponse response){
		return (String) request.getSession().getAttribute("token");
	}

	@Token(remove = true)
	@RequestMapping("/removetoken")
	@ResponseBody
	public String removeToken(HttpServletRequest request, HttpServletResponse response){
		return "success";
	}

}