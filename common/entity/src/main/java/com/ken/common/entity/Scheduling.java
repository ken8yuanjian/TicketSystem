package com.ken.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scheduling implements Serializable {
    private Integer id; //id
    private String start; //放映开始时间
    private String end;   //放映结束时间
    private Float price;//票价
    private Film film;  //电影
    private Room room;  //放映间
}
