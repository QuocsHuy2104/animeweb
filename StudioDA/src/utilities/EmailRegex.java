package utilities;

import java.util.regex.Pattern;

public class EmailRegex {
		
		String emailRegex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
		
		private Pattern pattern;
		
		public EmailRegex() { 
	        pattern = Pattern.compile(emailRegex); 
	    } 
	    
	    public boolean validate(final String email) { 
	        return pattern.matcher(email).matches(); 
	    } 
}
