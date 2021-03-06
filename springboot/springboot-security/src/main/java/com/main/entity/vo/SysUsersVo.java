package com.main.entity.vo;


import lombok.Data;

import java.io.Serializable;

@Data
public class SysUsersVo implements Serializable {

    private Long id;
    private String username;
    private String password;
    private String roles;
    private Boolean enabled;
    private Boolean accountNonExpired;
    private Boolean credentialsNonExpired;
    private Boolean accountNonLocked;

}
