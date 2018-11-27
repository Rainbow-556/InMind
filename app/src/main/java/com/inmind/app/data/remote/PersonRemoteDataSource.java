package com.inmind.app.data.remote;

import com.inmind.app.data.PersonDataSource;
import com.inmind.app.data.bean.Person;
import com.inmind.app.util.ExecutionCallback;

import java.util.List;

/**
 * Created by lixiang on 2018/11/27.<br/>
 */
public final class PersonRemoteDataSource implements PersonDataSource {
    @Override
    public void getPersons(ExecutionCallback<List<Person>> callback) {
    }

    @Override
    public void updatePerson(Person person, ExecutionCallback<Person> callback) {
    }

    @Override
    public void deletePerson(Person person, ExecutionCallback<Person> callback) {
    }

    @Override
    public void addPerson(Person person, ExecutionCallback<Person> callback) {
    }
}
