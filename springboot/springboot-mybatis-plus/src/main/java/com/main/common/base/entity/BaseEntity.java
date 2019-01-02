package com.main.common.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.main.common.util.SnowFlakeIdGenerator;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class BaseEntity implements Serializable{

    /** 主键ID **/
    @TableId(value="id")
    private Long id;
    /** 逻辑删除:0=已删除(false),1=未删除(true) **/
    private Boolean status;
    /** 创建时间 **/
    private Date createTime;
    /** 更新时间 **/
    private Date updateTime;

    /** ID生成策略(雪花算法) */
    private static final SnowFlakeIdGenerator snowFlakeIdGenerator = new SnowFlakeIdGenerator(0,0);

    /** 为实体生成一个ID */
    public BaseEntity generateId(){
        this.id = snowFlakeIdGenerator.nextId();
        return this;
    }

}
