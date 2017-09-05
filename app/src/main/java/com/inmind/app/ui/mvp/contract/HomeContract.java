package com.inmind.app.ui.mvp.contract;

import com.inmind.app.common.entity.Person;
import com.inmind.app.ui.mvp.IPresenter;
import com.inmind.app.ui.mvp.IView;

import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public interface HomeContract{

    interface IHomeView extends IView{
        void showUserList(List<Person> persons);
    }

    interface IHomePresenter extends IPresenter<IHomeView>{
        void fetchPerson();

        void deletePerson(Person person);

        void updatePerson(Person person);
    }
}
