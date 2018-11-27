package com.inmind.app.module.addperson;

import com.inmind.app.data.PersonRepository;
import com.inmind.app.mvp.BasePresenter;
import com.inmind.app.util.ExecutionCallback;
import com.inmind.app.data.bean.Person;
import com.inmind.app.data.PersonDataSource;

/**
 * Created by lixiang on 2017/9/4.
 */
public final class AddPersonPresenter extends BasePresenter<AddPersonContract.IAddPersonView> implements AddPersonContract.IAddPersonPresenter {
    private PersonRepository mPersonRepository;

    public AddPersonPresenter() {
        this.mPersonRepository = new PersonRepository();
    }

    @Override
    public void addPerson(Person person) {
        if (person == null) {
            return;
        }
        getView().showLoading(true);
        mPersonRepository.addPerson(person, new ExecutionCallback<Person>() {
            @Override
            public void onExecuted(Person data, String err) {
                AddPersonContract.IAddPersonView view = getView();
                if (view == null) {
                    return;
                }
                view.hideLoading();
                if (err != null) {
                    view.showToast(err);
                    return;
                }
                view.showToast(data.nickName + " add success");
            }
        });
    }

    @Override
    public void updatePerson(Person person) {
        if (person == null) {
            return;
        }
        getView().showLoading(true);
        mPersonRepository.updatePerson(person, new ExecutionCallback<Person>() {
            @Override
            public void onExecuted(Person data, String err) {
                AddPersonContract.IAddPersonView view = getView();
                if (view == null) {
                    return;
                }
                view.hideLoading();
                if (err != null) {
                    view.showToast(err);
                    return;
                }
                view.showToast(data.nickName + " update success");
            }
        });
    }

    @Override
    public void deletePerson(Person person) {
        if (person == null) {
            return;
        }
        getView().showLoading(true);
        mPersonRepository.deletePerson(person, new ExecutionCallback<Person>() {
            @Override
            public void onExecuted(Person data, String err) {
                AddPersonContract.IAddPersonView view = getView();
                if (view == null) {
                    return;
                }
                view.hideLoading();
                if (err != null) {
                    view.showToast(err);
                    return;
                }
                view.showToast(data.nickName + " delete success");
            }
        });
    }
}
