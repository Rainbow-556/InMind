package com.inmind.app.model;

import android.os.SystemClock;

import com.inmind.app.common.ExecutionCallback;
import com.inmind.app.common.RunUtil;
import com.inmind.app.common.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public class UserRepository{
    public void getUsers(ExecutionCallback<List<User>> callback){
        RunUtil.runOnWorkThread(new RunUtil.Work<List<User>>(){
            @Override
            public List<User> execute(){
                List<User> list = new ArrayList<>();
                for(int i = 0; i < 5; i++){
                    User user = new User();
                    user.nickName = "user_" + i;
                    list.add(user);
                }
                SystemClock.sleep(3000);
                return list;
            }
        }, callback);
    }

    public void updateUser(User user, ExecutionCallback<Boolean> callback){

    }

    public void deleteUser(User user, ExecutionCallback<Boolean> callback){
    }
}
