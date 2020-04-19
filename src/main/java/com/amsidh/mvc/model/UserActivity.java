package com.amsidh.mvc.model;

import java.io.Serializable;

public class UserActivity implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2931748205076034834L;

	public static UserActivity createUserActivity(String userId, String attributeName) {
		return new UserActivity(userId, attributeName);
	}

	String userId;
	
	String attributeName;

	public UserActivity() {
		super();
		// TODO Auto-generated constructor stub
	}

	private UserActivity(String userId, String attributeName) {
		super();
		this.userId = userId;
		this.attributeName = attributeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attributeName == null) ? 0 : attributeName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		UserActivity other = (UserActivity) obj;
		if (attributeName == null) {
			if (other.attributeName != null)
				return false;
		} else if (!attributeName.equals(other.attributeName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "UserActivity [userId=" + userId + ", attributeName=" + attributeName + "]";
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}
	
	
}
