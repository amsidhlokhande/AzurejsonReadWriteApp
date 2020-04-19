package com.amsidh.mvc.model;

import java.io.Serializable;

public class UserDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6549704844830412155L;

	private Integer userId;
	private String userName;
	private Double carbonValue;

	public UserDetail() {
	}

	public UserDetail(Integer userId, String userName, Double carbonValue) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.carbonValue = carbonValue;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Double getCarbonValue() {
		return carbonValue;
	}

	public void setCarbonValue(Double carbonValue) {
		this.carbonValue = carbonValue;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((carbonValue == null) ? 0 : carbonValue.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserDetail other = (UserDetail) obj;
		if (carbonValue == null) {
			if (other.carbonValue != null)
				return false;
		} else if (!carbonValue.equals(other.carbonValue))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserDetail [userId=" + userId + ", userName=" + userName + ", carbonValue=" + carbonValue + "]";
	}

}
