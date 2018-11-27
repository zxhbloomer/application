package com.jack.springcloud.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "亲戚实体",description = "关系亲戚")
public class People {

    @ApiModelProperty(value="亲戚昵称")
    private String nickname;
    @ApiModelProperty(value="重要程度")
    private Integer degree;
}
