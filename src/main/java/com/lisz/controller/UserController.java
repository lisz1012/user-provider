package com.lisz.controller;

import com.lisz.api.UserApi;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
public class UserController implements UserApi {
	@GetMapping("/alive")
	public String alive(){
		return "OK";
	}

	@GetMapping("/user/{id}")
	public String getById(int id) {
		return "user" + id;
	}

	@GetMapping("/map")
	public Map<Integer, String> map(@RequestParam int id) { //这里 @RequestParam 可写可不写，默认也是去参数里面去找，而不是body
		System.out.println(id);
		return Collections.singletonMap(id, "hahaha");
	}

	@GetMapping("/map2")
	public Map<Integer, String> map2(@RequestParam int id, @RequestParam String name) {
		System.out.println(id);
		return Collections.singletonMap(id, name);
	}

	@GetMapping("/map3")
	public Map<Integer, String> map3(Map<String, Object> map) {
		System.out.println(map);
		return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
	}

	@PostMapping("/map4")
	public Map<Integer, String> map4(Map<String, Object> map) {
		System.out.println(map);
		return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
	}
}
