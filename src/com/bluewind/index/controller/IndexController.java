package com.bluewind.index.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.bluewind.index.service.IndexService;
import com.common.paginate.Page;

@Controller
public class IndexController{
	
	@Autowired
	private IndexService service;

	@RequestMapping(value="/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		String test = service.test();
		System.out.println(test);
		Map map = new HashMap();
		List<Map> addressList = service.findAddressList(map);
		
		request.setAttribute("addressList", addressList);
		request.setAttribute("test", test);
		
		return new ModelAndView("index");
	}
	
	@RequestMapping(value="/list")
	public ModelAndView list(HttpServletRequest request,HttpServletResponse response) throws Exception{
		Map map = new HashMap();
		Page page = new Page();
		map.put("page", page);
		
		List<Map> addressList = service.findAddressList4Page(map);
		
		
		request.setAttribute("pageStr", page.getPageStr());
		request.setAttribute("addressList", addressList);
		return new ModelAndView("index");
	}
	
}
