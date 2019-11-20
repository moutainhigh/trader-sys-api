package com.zgkj.api.tradeFlow.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class LdapUserAndUserGroupBean implements Serializable{
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private long id;
	private long gid;
	private String uid;
	private String username;



}
