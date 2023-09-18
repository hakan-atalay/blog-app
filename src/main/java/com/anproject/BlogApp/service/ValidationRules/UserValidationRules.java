package com.anproject.BlogApp.service.ValidationRules;

public class UserValidationRules extends BaseValidationRules{

    public void passwordCheck(String Password){
        if(!Password.matches(".*\\d.*")){
            throw new IllegalArgumentException("Şifrenizde en az bir adet sayı bulunmalıdır.");
        }
    }
}
