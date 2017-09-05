package com.inmind.app.ui.mvp.presenter;

import com.inmind.app.common.ExecutionCallback;
import com.inmind.app.common.entity.Person;
import com.inmind.app.model.PersonRepository;
import com.inmind.app.ui.mvp.contract.HomeContract;

import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public class HomePresenter implements HomeContract.IHomePresenter{
    private HomeContract.IHomeView mView;
    private PersonRepository mPersonRepository;

    public HomePresenter(PersonRepository userRepository){
        this.mPersonRepository = userRepository;
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
    public void fetchPerson(){
        mView.showLoading(true);
        mPersonRepository.getPersons(new ExecutionCallback<List<Person>>(){
            @Override
            public void onExecuted(List<Person> data, String err){
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
    public void deletePerson(Person user){
        if(user == null){
            return;
        }
        mView.showLoading(true);
        mPersonRepository.deletePerson(user, new ExecutionCallback<Person>(){
            @Override
            public void onExecuted(Person data, String err){
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
    public void updatePerson(Person user){
        if(user == null){
            return;
        }
        mView.showLoading(true);
        mPersonRepository.updatePerson(user, new ExecutionCallback<Person>(){
            @Override
            public void onExecuted(Person data, String err){
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
