package com.ken.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film implements Serializable {
    private Integer id;
    private String name ;
    private String descr;
}
