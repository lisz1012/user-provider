package com.lisz.controller;

import com.lisz.api.UserApi;
import com.lisz.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.Map;

/**
 * 用OpenFeign的时候
 * 各个重写的方法上面都不用加 @GetMapping @PostMapping 等注解，因为在API包里的哪个UserApi接口上已经有了
 * 但是最好是把该写的注解全写上
 */
@RestController
public class UserController implements UserApi {

	@Value("${server.port}")
	private int port;

	@Value("${spring.boot.admin.notify.slack.channel}")
	private String channel;

	@Value("${spring.boot.admin.notify.slack.webhook-url}")
	private String webhookUrl;

	@Autowired
	private RestTemplate restTemplate;

	@GetMapping("/alive")
	public String alive(){
		String json = "{\"channel\": \"#app-health\", \"username\": \"Oncall Assistant\", " +
						"\"text\": \"user-provider is still alive!!\", " +
						"\"icon_emoji\": \":scream_cat:\"}";
		restTemplate.postForEntity(webhookUrl, json, Void.class);
		return "port: " + port;
	}

	@GetMapping("/user/{id}")
	public String getById(int id) {
		return "user" + id;
	}

	@GetMapping("/map")
	public Map<Integer, String> getMap(@RequestParam int id) { //这里 @RequestParam 可写可不写，默认也是去参数里面去找，而不是body
		System.out.println(id);
		return Collections.singletonMap(id, "hahaha");
	}

	@GetMapping("/map2")
	public Map<Integer, String> getMap2(@RequestParam int id, @RequestParam String name) {
		System.out.println(id);
		return Collections.singletonMap(id, name);
	}

	@GetMapping("/map3")
	public Map<Integer, String> getMap3(Map<String, Object> map) {
		System.out.println(map);
		return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
	}

	@PostMapping("/map4")
	public Map<Integer, String> postMap(@RequestBody Map<String, Object> map) {
		System.out.println(map);
		return Collections.singletonMap(Integer.parseInt(map.get("id").toString()), map.get("name").toString());
	}

	@PostMapping("/map5")
	public User postMap2(@RequestBody User user) {
		return new User(user.getId() + 1, user.getName() + "!!!~");
	}
}
