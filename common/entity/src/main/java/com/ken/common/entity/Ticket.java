package com.ken.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

//电影票
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Ticket implements Serializable {
    private Integer id;
    private Integer schedulingid;
    private Integer seatid;
    private Integer memberid; //此处只表示购买用户id，不做详细查询
}


