package com.hd.imms.entity.performance;

import lombok.Data;

@Data
public class UserQuery {
    private Integer current;
    private Integer size;
    private String userName;
    private String sex;
}
