package com.inmind.app.module.addperson;

import com.inmind.app.data.bean.Person;
import com.inmind.app.mvp.IPresenter;
import com.inmind.app.mvp.IView;

/**
 * Created by lixiang on 2017/9/4.
 */
public interface AddPersonContract {
    interface IAddPersonView extends IView {
    }

    interface IAddPersonPresenter extends IPresenter {
        void addPerson(Person person);

        void updatePerson(Person person);

        void deletePerson(Person person);
    }
}
