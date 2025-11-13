package ProgettoUninaSwap;

public class Login_entity {
	private String Username; 
	private String Password; 
	
	public Login_entity(String Username, String Password) {
		this.Username = Username; 
		this.Password = Password; 
	}

//Getter
	public String getUsername() {
		return Username; 
	}
	
	public String getPassword() {
		return Password; 
	}
	
//Setter
	public void setUsername(String Username) {
		this.Username = Username;
	}
	
	public void setPassword(String Password) {
		this.Password = Password;
	}
}
