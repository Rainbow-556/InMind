package com.inmind.app.model;

import com.inmind.app.common.ExecutionCallback;
import com.inmind.app.common.entity.User;

import java.util.List;

/**
 * Created by lixiang on 2017/8/20.
 */
public interface UserRepository{

    void getUsers(ExecutionCallback<List<User>> callback);

    void updateUser(User user, ExecutionCallback<Boolean> callback);

    void deleteUser(User user, ExecutionCallback<Boolean> callback);
}
