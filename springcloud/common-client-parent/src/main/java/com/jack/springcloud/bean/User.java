package com.jack.springcloud.bean;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;

@Data
@ApiModel(value = "用户实体",description = "用户信息")
public class User {


    @ApiModelProperty(value="用户名称")
    private String username;
    @ApiModelProperty(value="用户密码")
    private String password;
    @ApiModelProperty(value="年龄")
    private Integer age;
    @ApiModelProperty(value="性别")
    private Boolean sex;

    @ApiModelProperty(value="爱好大全")
    private String[] likes;
    @ApiModelProperty(value="账号大全")
    private ArrayList<Long> accountList;

    @ApiModelProperty(value="亲人")
    private People people;
    @ApiModelProperty(value="家人大全")
    private People[] peoples;
    @ApiModelProperty(value="家人列表")
    private ArrayList<People> peopleList;


}
