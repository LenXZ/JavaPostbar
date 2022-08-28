package entity;

public class User {
		private String idString;
		private String nameString;//用户名
		private String pwdString;//密码
		private int gender;//性别
		private int salary;//工资
		//创建类的构造器
		public User(String idString,String nameString,String pwdString,int gender,int salary){
			this.idString=idString;//将参数值赋给属性值
			this.nameString=nameString;
			this.pwdString=pwdString;
			this.gender=gender;
			this.salary=salary;
		}
		public String getPwdString() {
			return pwdString;
		}
		public User() {
			super();
			// TODO Auto-generated constructor stub
		}
		public void setPwdString(String pwdString) {
			this.pwdString = pwdString;
		}
		//获取值
		public String getIdString() {
			return idString;
		}
		//修改值
		public void setIdString(String idString) {
			this.idString = idString;
		}
		public String getNameString() {
			return nameString;
		}
		public void setNameString(String nameString) {
			this.nameString = nameString;
		}
		public int getGender() {
			return gender;
		}
		public void setGender(int gender) {
			this.gender = gender;
		}
		public int getSalary() {
			return salary;
		}
		public void setSalary(int salary) {
			this.salary = salary;
		}
		@Override
		public String toString() {
			return "用户 [ID="+ idString + ", 用户名=" + nameString + ", 性别=" + gender + ", 薪水="
					+ salary + "]";
		}
		

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + gender;
		result = prime * result
				+ ((idString == null) ? 0 : idString.hashCode());
		result = prime * result
				+ ((nameString == null) ? 0 : nameString.hashCode());
		result = prime * result
				+ ((pwdString == null) ? 0 : pwdString.hashCode());
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
			User other = (User) obj;
			if (gender != other.gender)
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
			if (salary != other.salary)
				return false;
			return true;
		}
	public static void main(String[] args) {
		
	}

}
