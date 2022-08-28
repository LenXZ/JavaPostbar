package entity;

public class Emp {

	String idString="";
	String nameString="";
	String genderString="";
	int salary=0;
	public String getIdString() {
		return idString;
	}
	public void setIdString(String idString) {
		this.idString = idString;
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
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((genderString == null) ? 0 : genderString.hashCode());
		result = prime * result
				+ ((idString == null) ? 0 : idString.hashCode());
		result = prime * result
				+ ((nameString == null) ? 0 : nameString.hashCode());
		result = prime * result + salary;
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
		Emp other = (Emp) obj;
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
		if (salary != other.salary)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Emp [ ID=" + idString
				+ ", 姓名=" + nameString + ", 性别=" + genderString + ",薪水=" + salary + "]";
	}
	public Emp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Emp(String idString, String nameString, String genderString,
			int salary) {
		super();
		this.idString = idString;
		this.nameString = nameString;
		this.genderString = genderString;
		this.salary = salary;
	}
	
}
