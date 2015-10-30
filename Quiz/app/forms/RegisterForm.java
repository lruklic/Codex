package forms;

import play.data.validation.Constraints.Required;

public class RegisterForm {
	
	@Required
	public String username;
	
	@Required
	public String password;
	
	@Required
	public String retypePassword;
	
	@Required
	public String email;
	
	public String firstName;
	
	public String lastName;

}
