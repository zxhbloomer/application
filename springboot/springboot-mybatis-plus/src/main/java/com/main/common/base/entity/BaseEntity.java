package com.main.common.base.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.main.common.util.SnowFlakeIdGenerator;
import lombok.Data;

import java.io.Serializable;

@Data
public class BaseEntity implements Serializable{

    /** 主键ID **/
    @TableId(value="id")
    private Long id;

    /** ID生成策略(雪花算法) */
    private static final SnowFlakeIdGenerator snowFlakeIdGenerator = new SnowFlakeIdGenerator(0,0);

    /** 为实体生成一个ID */
    public BaseEntity generateId(){
        this.id = snowFlakeIdGenerator.nextId();
        return this;
    }

}
