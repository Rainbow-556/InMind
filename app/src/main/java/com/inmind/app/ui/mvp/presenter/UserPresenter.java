package com.inmind.app.ui.mvp.presenter;

import com.inmind.app.common.ExecutionCallback;
import com.inmind.app.common.entity.User;
import com.inmind.app.model.UserRepository;
import com.inmind.app.ui.mvp.contract.HomeContract;

import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public class UserPresenter implements HomeContract.IUserPresenter{
    private HomeContract.IHomeView mView;
    private UserRepository mUserRepository;

    public UserPresenter(){
        mUserRepository = new UserRepository();
    }

    @Override
    public void attachView(HomeContract.IHomeView view){
        mView = view;
    }

    @Override
    public boolean isViewInactive(){
        return mView == null;
    }

    @Override
    public void detachView(){
        mView = null;
    }

    @Override
    public void fetchUsers(){
        mView.showLoading(true);
        mUserRepository.getUsers(new ExecutionCallback<List<User>>(){
            @Override
            public void onExecuted(List<User> data, String err){
                if(isViewInactive()){
                    return;
                }
                mView.hideLoading();
                if(err != null){
                    mView.showToast(err);
                    return;
                }
                mView.showUserList(data);
            }
        });
    }

    @Override
    public void deleteUser(User user){
        if(user == null){
            return;
        }
        mView.showLoading(true);
        mUserRepository.deleteUser(user, new ExecutionCallback<Boolean>(){
            @Override
            public void onExecuted(Boolean data, String err){
                if(isViewInactive()){
                    return;
                }
                mView.hideLoading();
                if(err != null){
                    mView.showToast(err);
                    return;
                }
                mView.showToast("delete success");
            }
        });
    }

    @Override
    public void updateUser(User user){
        if(user == null){
            return;
        }
        mView.showLoading(true);
        mUserRepository.updateUser(user, new ExecutionCallback<Boolean>(){
            @Override
            public void onExecuted(Boolean data, String err){
                if(isViewInactive()){
                    return;
                }
                mView.hideLoading();
                if(err != null){
                    mView.showToast(err);
                    return;
                }
                mView.showToast("update success");
            }
        });
    }
}
