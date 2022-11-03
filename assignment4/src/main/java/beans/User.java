package beans;

import javax.faces.bean.ManagedBean;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ManagedBean
public class User {
	
	@NotNull()
	@Size(min=3, max=15)
	private String firstName;
	
	@NotNull()
	@Size(min=3, max=15)
	private String lastName;
	
	public User() {
		firstName = "Tanner";
		lastName = "Ray";
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
