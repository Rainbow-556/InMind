package com.inmind.app.ui.mvp.contract;

import com.inmind.app.common.entity.User;
import com.inmind.app.ui.mvp.IPresenter;
import com.inmind.app.ui.mvp.IView;

import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface HomeContract{

    interface IHomeView extends IView{
        void showUserList(List<User> users);
    }

    interface IUserPresenter extends IPresenter<IHomeView>{
        void fetchUsers();

        void deleteUser(User user);

        void updateUser(User user);
    }
}
