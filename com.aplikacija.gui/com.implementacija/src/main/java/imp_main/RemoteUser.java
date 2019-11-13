package imp_main;

import java.io.Serializable;

class RemoteUser implements Serializable {

	private static final long serialVersionUID = -8342996454124019307L;
	private String firstname;
	private String lastname;
	private String index;
	private String group;

	public RemoteUser() {

	}

	public RemoteUser(String firtname, String lastname, String index, String group) {
		this.firstname = firtname;
		this.lastname = lastname;
		this.index = index;
		this.group = group;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	
	
	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return this.firstname + " " + this.lastname + " " + this.index + " " + this.group;
 	}
	
}