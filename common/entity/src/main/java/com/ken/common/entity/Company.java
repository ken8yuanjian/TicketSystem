package com.ken.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company implements Serializable {
    private Integer id;
    private String name ;
    private float  amount;
}
