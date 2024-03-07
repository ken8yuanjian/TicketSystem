package com.ken.common.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor

//设置实体类与表名的对应关系，实体类名与表名可以不相同
@TableName ("User")
public class User implements Serializable {
    //设置表id字段，以及id的生成算法，AUTO表示自增，不写默认为雪花算法
    @TableId(type = IdType.AUTO)
    private Integer id;

    //设置表字段和实体字段的对应关系，可以不相同
    @TableField("username")
    private String username;
    private String passwd;
    private String name;
    private String tel;
    private float amount;
}
