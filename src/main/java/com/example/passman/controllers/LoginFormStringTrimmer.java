package com.example.passman.controllers;

import java.beans.PropertyEditorSupport;

public class LoginFormStringTrimmer extends PropertyEditorSupport {
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (text == null){
            setValue(null);
        }
        else{
            String trimmed = text.trim();
            setValue(trimmed.isEmpty() ? null : trimmed);
        }
    }

}
