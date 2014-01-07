package com.bluewind.index.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluewind.index.dao.IIndexMapper;

@Service
public class IndexService {
	
	@Autowired
	private IIndexMapper indexMapper;

	public String test(){
		return "service inject successfully!! haha";
	}
	public List<Map> findAddressList(Map map){
		return indexMapper.queryAddress(map);
	}
	
	public List<Map> findAddressList4Page(Map map){
		return indexMapper.queryAddressForPage(map);
	}
}
