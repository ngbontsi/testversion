package com.vaadin.utils;

import com.vaadin.ui.TextField;

public class ValidatorUtil {

    private ValidatorUtil(){

    }

    public static boolean isEmpty(TextField input){
        if(input== null || input.getValue().equals("")){
            return true;
        }

        return false;
    }

}
