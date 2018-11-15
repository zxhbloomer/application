package com.main.controller;


import com.main.entity.Person;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
@Api("/swaggerController相关API")
public class SwaggerController {

	@ApiOperation(
			value = "根据 '条件' 查询用户信息",
			notes = "查询数据库中某个用户的信息",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			response = String.class
//			responseContainer= "List" //这些对象是有效的 "List", "Set" or "Map".，其他无效
	)
	/**
		 path	以地址的形式提交数据					@PathVariable
		 query	直接跟参数完成自动映射赋值			@RequestParam
		 body	以流的形式提交 仅支持POST
		 header	参数在request headers 里边提交		@RequestHeader
		 form	以form表单的形式提交 仅支持POST
	 */
	@ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "用户ID", paramType = "query", required = true, dataType = "int"),
			@ApiImplicitParam(name = "sex", value = "用户性别", paramType = "query", required = true, dataType = "boolean"),
			@ApiImplicitParam(name = "usernames", value = "用户名称", paramType = "query", required = true, dataType = "string",allowMultiple=true),

	})
	@ApiResponses(value = {@ApiResponse(code = 200, message = "请求成功"), @ApiResponse(code = 401, message = "权限不足"), @ApiResponse(code = 403, message = "缺少某个请求参数"), @ApiResponse(code = 404, message = "找不到路径"), @ApiResponse(code = 500, message = "服务器错误")})
	@GetMapping("/getUser")
	public String getUser(Integer id,Boolean sex,String[] usernames){
		HashMap<String,Object> user = new HashMap<>();
		user.put("id",id);
		user.put("usernames", Arrays.toString(usernames));
		user.put("sex",sex);
		return user.toString();
	}

	@ApiOperation(
			value = "根据 '实体' 查询用户信息",
			notes = "根据实体在数据查询用户信息",
			produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
			response = Person.class
	)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "person", value = "用户实体查询对象", paramType = "query")
	})
	@PostMapping("/getUserByPerson")
	public String getUserByPerson(Person person){
		return "Success";
	}

	@GetMapping("/test")
	public String test(){
		return "success";
	}
}
