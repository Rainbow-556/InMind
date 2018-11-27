package com.inmind.app.data;

import com.inmind.app.util.ExecutionCallback;
import com.inmind.app.data.bean.Person;

import java.util.List;

/**
 * Created by lixiang on 2017/8/20.
 */
public interface PersonDataSource {
    void getPersons(ExecutionCallback<List<Person>> callback);

    void updatePerson(Person person, ExecutionCallback<Person> callback);

    void deletePerson(Person person, ExecutionCallback<Person> callback);

    void addPerson(Person person, ExecutionCallback<Person> callback);
}
