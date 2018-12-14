package com.jack.springcloud.common.vo;


import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class User implements Serializable {
	private static final long serialVersionUID = -4083327605430665846L;


	private String userName;
	private String scope;
	private String active;
	private String exp;
	private String authorities;
	private String jti;
	private String clientId;


	private List<String> allowPermissionService;
}
