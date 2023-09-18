package com.anproject.BlogApp.service.ValidationRules;

import org.springframework.util.StringUtils;

public abstract class BaseValidationRules {

    public void checkString(String Text){
        if(StringUtils.isEmpty(Text)){
            throw new IllegalArgumentException("Bu alan boş bırakılamaz");
        }
    }

}
