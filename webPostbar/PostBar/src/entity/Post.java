package entity;

public class Post {
	String idString="";
	String userString="";
	String dateString="";
	String textString="";
	public Post(String idString, String userString, String dateString,
			String textString) {
		super();
		this.idString = idString;
		this.userString = userString;
		this.dateString = dateString;
		this.textString = textString;
	}
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
	}
	public String getUserString() {
		return userString;
	}
	public void setUserString(String userString) {
		this.userString = userString;
	}
	public String getDateString() {
		return dateString;
	}
	public void setDateString(String dateString) {
		this.dateString = dateString;
	}
	public String getTextString() {
		return textString;
	}
	public void setTextString(String textString) {
		this.textString = textString;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateString == null) ? 0 : dateString.hashCode());
		result = prime * result
				+ ((idString == null) ? 0 : idString.hashCode());
		result = prime * result
				+ ((textString == null) ? 0 : textString.hashCode());
		result = prime * result
				+ ((userString == null) ? 0 : userString.hashCode());
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
		Post other = (Post) obj;
		if (dateString == null) {
			if (other.dateString != null)
				return false;
		} else if (!dateString.equals(other.dateString))
			return false;
		if (idString == null) {
			if (other.idString != null)
				return false;
		} else if (!idString.equals(other.idString))
			return false;
		if (textString == null) {
			if (other.textString != null)
				return false;
		} else if (!textString.equals(other.textString))
			return false;
		if (userString == null) {
			if (other.userString != null)
				return false;
		} else if (!userString.equals(other.userString))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Post [dateString=" + dateString + ", idString=" + idString
				+ ", textString=" + textString + ", userString=" + userString
				+ "]";
	}
	
}
