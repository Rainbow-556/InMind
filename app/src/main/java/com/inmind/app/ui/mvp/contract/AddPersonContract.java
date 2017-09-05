package com.inmind.app.ui.mvp.contract;

import com.inmind.app.common.entity.Person;
import com.inmind.app.ui.mvp.IPresenter;
import com.inmind.app.ui.mvp.IView;

/**
 * Created by lixiang on 2017/9/4.
 */
public interface AddPersonContract{
    interface IAddPersonView extends IView{
    }

    interface IAddPersonPresenter extends IPresenter<IAddPersonView>{
        void addPerson(Person person);
    }
}
