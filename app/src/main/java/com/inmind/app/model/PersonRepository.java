package com.inmind.app.model;

import com.inmind.app.common.ExecutionCallback;
import com.inmind.app.common.entity.Person;

import java.util.List;

/**
 * Created by lixiang on 2017/8/20.
 */
public interface PersonRepository{

    void getPersons(ExecutionCallback<List<Person>> callback);

    void updatePerson(Person person, ExecutionCallback<Person> callback);

    void deletePerson(Person person, ExecutionCallback<Person> callback);

    void addPerson(Person person, ExecutionCallback<Person> callback);
}
