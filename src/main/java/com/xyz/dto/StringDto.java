package com.xyz.dto;

import java.io.Serializable;

public class StringDto implements Serializable {

	private String message;

	private String str[];

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String[] getStr() {
		return str;
	}

	public void setStr(String[] str) {
		this.str = str;
	}

}
