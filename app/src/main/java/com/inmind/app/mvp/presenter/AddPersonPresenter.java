package com.inmind.app.mvp.presenter;

import com.inmind.app.common.ExecutionCallback;
import com.inmind.app.common.entity.Person;
import com.inmind.app.model.PersonRepository;
import com.inmind.app.mvp.contract.AddPersonContract;

/**
 * Created by lixiang on 2017/9/4.
 */
public final class AddPersonPresenter implements AddPersonContract.IAddPersonPresenter{
    private AddPersonContract.IAddPersonView mView;
    private PersonRepository mPersonRepository;

    public AddPersonPresenter(PersonRepository repository){
        this.mPersonRepository = repository;
    }

    @Override
    public void attachView(AddPersonContract.IAddPersonView view){
        this.mView = view;
    }

    @Override
    public boolean isViewInactive(){
        return mView == null;
    }

    @Override
    public void detachView(){
    }

    @Override
    public void addPerson(Person person){
        if(person == null){
            return;
        }
        mView.showLoading(true);
        mPersonRepository.addPerson(person, new ExecutionCallback<Person>(){
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
                mView.showToast(data.nickName+" add success");
            }
        });
    }

    @Override
    public void updatePerson(Person person){
        if(person == null){
            return;
        }
        mView.showLoading(true);
        mPersonRepository.updatePerson(person, new ExecutionCallback<Person>(){
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
                mView.showToast(data.nickName+" update success");
            }
        });
    }

    @Override
    public void deletePerson(Person person){
        if(person == null){
            return;
        }
        mView.showLoading(true);
        mPersonRepository.deletePerson(person, new ExecutionCallback<Person>(){
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
                mView.showToast(data.nickName+" delete success");
            }
        });
    }
}
