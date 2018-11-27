package com.inmind.app.data;

import com.inmind.app.data.bean.Person;
import com.inmind.app.data.local.PersonFileDataSource;
import com.inmind.app.data.remote.PersonRemoteDataSource;
import com.inmind.app.util.ExecutionCallback;

import java.util.List;

/**
 * Created by lixiang on 2018/11/27.<br/>
 * 内存缓存、本地、网络的读取逻辑？
 * 可参考：https://github.com/googlesamples/android-architecture/blob/todo-mvp/todoapp/app/src/main/java/com/example/android/architecture/blueprints/todoapp/data/source/TasksRepository.java
 */
public final class PersonRepository implements PersonDataSource {
    private PersonDataSource mLocalDataSource, mRemoteDataSource;

    public PersonRepository() {
        mLocalDataSource = new PersonFileDataSource();
        mRemoteDataSource = new PersonRemoteDataSource();
    }

    @Override
    public void getPersons(ExecutionCallback<List<Person>> callback) {
        mLocalDataSource.getPersons(callback);
    }

    @Override
    public void updatePerson(Person person, ExecutionCallback<Person> callback) {
        mLocalDataSource.updatePerson(person, callback);
    }

    @Override
    public void deletePerson(Person person, ExecutionCallback<Person> callback) {
        mLocalDataSource.deletePerson(person, callback);
    }

    @Override
    public void addPerson(Person person, ExecutionCallback<Person> callback) {
        mLocalDataSource.addPerson(person, callback);
    }
}
