package com.soreak.service;

import com.soreak.pojo.User;

public interface UserService {
    User checkUser(String username,String password);
}
