package Entities;

public class PasswordValidator {
    public String Validate(String password){
        if (password == null || password.trim().isEmpty()) {
            return "Password cannot be empty.";
        }
        if (password.length() < 8){
            return "Password must be at least 8 character long.";
        }
        if (!password.matches(".*[A-Z].*")) {
            return "Password must contain at least one uppercase letter.";
        }
        if (!password.matches(".*[0-9].*")){
            return "Password must contain at least one number";
        }
        return "" ;
    }


}
