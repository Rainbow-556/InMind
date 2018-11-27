package com.inmind.app.module.home;

import com.inmind.app.data.PersonRepository;
import com.inmind.app.data.local.PersonFileDataSource;
import com.inmind.app.mvp.BasePresenter;
import com.inmind.app.util.ExecutionCallback;
import com.inmind.app.data.bean.Person;
import com.inmind.app.data.PersonDataSource;

import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class HomePresenter extends BasePresenter<HomeContract.IHomeView> implements HomeContract.IHomePresenter {
    private PersonRepository mPersonRepository;

    public HomePresenter() {
        this.mPersonRepository = new PersonRepository();
    }

    @Override
    public void fetchPerson() {
        getView().showLoading(true);
        mPersonRepository.getPersons(new ExecutionCallback<List<Person>>() {
            @Override
            public void onExecuted(List<Person> data, String err) {
                HomeContract.IHomeView view = getView();
                if (view == null) {
                    return;
                }
                view.hideLoading();
                if (err != null) {
                    view.showToast(err);
                    return;
                }
                view.showUserList(data);
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
                HomeContract.IHomeView view = getView();
                if (view == null) {
                    return;
                }
                view.hideLoading();
                if (err != null) {
                    view.showToast(err);
                    return;
                }
                view.showToast("delete success");
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
                HomeContract.IHomeView view = getView();
                if (view == null) {
                    return;
                }
                view.hideLoading();
                if (err != null) {
                    view.showToast(err);
                    return;
                }
                view.showToast("update success");
            }
        });
    }
}
