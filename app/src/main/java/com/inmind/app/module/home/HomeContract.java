package com.inmind.app.module.home;

import com.inmind.app.data.bean.Person;
import com.inmind.app.mvp.IPresenter;
import com.inmind.app.mvp.IView;

import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface HomeContract {
    interface IHomeView extends IView {
        void showUserList(List<Person> persons);
    }

    interface IHomePresenter extends IPresenter {
        void fetchPerson();

        void deletePerson(Person person);

        void updatePerson(Person person);
    }
}
