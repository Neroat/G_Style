package com.rollingstone.gstyle.service;

import org.springframework.stereotype.Service;

@Service
public class DefaultValidIdPwd {
    public boolean isRight(String id, String password) {
        if(id.equals("gjchsAdmin") && password.equals("rhkdwnwnddkdrhduddidtk"))
            return true;
        else
            return false;
    }
}
