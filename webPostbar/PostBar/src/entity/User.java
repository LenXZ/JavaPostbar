package entity;

public class User {
	String idString="";
	String pwdString="";
	String nameString="";
	String genderString="";
	String authority="";
	public User(String idString,String nameString, String pwdString, 
			String genderString, String authority) {
		super();
		this.idString = idString;
		this.pwdString = pwdString;
		this.nameString = nameString;
		this.genderString = genderString;
		this.authority = authority;
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	@Override
	public String toString() {
		return "User [authority=" + authority + ", genderString="
				+ genderString + ", idString=" + idString + ", nameString="
				+ nameString + ", pwdString=" + pwdString + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((authority == null) ? 0 : authority.hashCode());
		result = prime * result
				+ ((genderString == null) ? 0 : genderString.hashCode());
		result = prime * result
				+ ((idString == null) ? 0 : idString.hashCode());
		result = prime * result
				+ ((nameString == null) ? 0 : nameString.hashCode());
		result = prime * result
				+ ((pwdString == null) ? 0 : pwdString.hashCode());
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
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (genderString == null) {
			if (other.genderString != null)
				return false;
		} else if (!genderString.equals(other.genderString))
			return false;
		if (idString == null) {
			if (other.idString != null)
				return false;
		} else if (!idString.equals(other.idString))
			return false;
		if (nameString == null) {
			if (other.nameString != null)
				return false;
		} else if (!nameString.equals(other.nameString))
			return false;
		if (pwdString == null) {
			if (other.pwdString != null)
				return false;
		} else if (!pwdString.equals(other.pwdString))
			return false;
		return true;
	}
	public String getPwdString() {
		return pwdString;
	}
	public void setPwdString(String pwdString) {
		this.pwdString = pwdString;
	}
	public String getNameString() {
		return nameString;
	}
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	public String getGenderString() {
		return genderString;
	}
	public void setGenderString(String genderString) {
		this.genderString = genderString;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	
}
