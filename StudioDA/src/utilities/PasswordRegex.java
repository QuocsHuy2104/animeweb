package utilities;

import java.util.regex.Pattern;

public class PasswordRegex {
	public static final String patternPassword = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$";
	private Pattern pattern;
	
	public PasswordRegex() { 
        pattern = Pattern.compile(patternPassword); 
    } 
    
    public boolean validate(final String password) { 
        return pattern.matcher(password).matches(); 
    } 
}
