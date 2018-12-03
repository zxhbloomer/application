package com.main.domain;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysUserVo implements Serializable {

    private Long id;
    private Integer enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;
    private String username;
    private String password;
    private String roles;

}
