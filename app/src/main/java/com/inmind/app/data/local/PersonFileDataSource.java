package com.inmind.app.data.local;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inmind.app.data.PersonDataSource;
import com.inmind.app.util.CommonUtil;
import com.inmind.app.util.ExecutionCallback;
import com.inmind.app.util.RunUtil;
import com.inmind.app.data.bean.Person;
import com.inmind.app.InMindApp;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class PersonFileDataSource implements PersonDataSource {
    /*personData为json: []*/
    private static final String FILE_NAME = "personData.txt";
    private static final File DATA_FILE = new File(InMindApp.getInstance().getExternalCacheDir(), FILE_NAME);
    private static volatile List<Person> sPersonList = new ArrayList<>();
    private static final Gson GSON = new Gson();

    @Override
    public void getPersons(ExecutionCallback<List<Person>> callback) {
        RunUtil.Work<List<Person>> work = new RunUtil.Work<List<Person>>() {
            @Override
            public List<Person> execute() {
                if (!DATA_FILE.exists()) {
                    return Collections.emptyList();
                }
                List<Person> list = Collections.emptyList();
                ByteArrayOutputStream baos = null;
                FileInputStream in = null;
                try {
                    in = new FileInputStream(DATA_FILE);
                    baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        baos.write(buffer, 0, len);
                    }
                    String json = new String(baos.toByteArray());
                    list = GSON.fromJson(json, new TypeToken<List<Person>>() {}.getType());
                    for (Person p : list) {
                        p.initExtraFields();
                    }
                    Collections.sort(list, new Comparator<Person>() {
                        @Override
                        public int compare(Person o1, Person o2) {
                            long diff = o1.remainDays - o2.remainDays;
                            if (diff > 0) {
                                return 1;
                            } else if (diff < 0) {
                                return -1;
                            } else {
                                return 0;
                            }
                        }
                    });
                    sPersonList.clear();
                    sPersonList.addAll(list);
                    Log.e("lx", "getPersons(): " + list.toString());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    CommonUtil.closeStream(in);
                    CommonUtil.closeStream(baos);
                }
                return list;
            }
        };
        RunUtil.runOnWorkThread(work, callback);
    }

    @Override
    public void updatePerson(final Person person, ExecutionCallback<Person> callback) {
        final RunUtil.Work<Person> work = new RunUtil.Work<Person>() {
            @Override
            public Person execute() {
                int index = sPersonList.indexOf(person);
                if (index <= -1) {
                    this.errMsg = "person not exists";
                    return person;
                }
                sPersonList.set(index, person);
                Log.e("lx", "updatePerson(): " + person.toString());
                BufferedOutputStream out = null;
                try {
                    String json = GSON.toJson(sPersonList);
                    out = new BufferedOutputStream(new FileOutputStream(DATA_FILE));
                    out.write(json.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    sPersonList.remove(person);
                } finally {
                    CommonUtil.closeStream(out);
                }
                return person;
            }
        };
        RunUtil.runOnWorkThread(work, callback);
    }

    @Override
    public void deletePerson(final Person person, ExecutionCallback<Person> callback) {
        final RunUtil.Work<Person> work = new RunUtil.Work<Person>() {
            @Override
            public Person execute() {
                int index = sPersonList.indexOf(person);
                if (index <= -1) {
                    this.errMsg = "person not exists";
                    return person;
                }
                sPersonList.remove(index);
                Log.e("lx", "deletePerson(): " + person.toString());
                BufferedOutputStream out = null;
                try {
                    String json = GSON.toJson(sPersonList);
                    out = new BufferedOutputStream(new FileOutputStream(DATA_FILE));
                    out.write(json.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    sPersonList.remove(person);
                } finally {
                    CommonUtil.closeStream(out);
                }
                return person;
            }
        };
        RunUtil.runOnWorkThread(work, callback);
    }

    @Override
    public void addPerson(final Person person, ExecutionCallback<Person> callback) {
        final RunUtil.Work<Person> work = new RunUtil.Work<Person>() {
            @Override
            public Person execute() {
                if (sPersonList.contains(person)) {
                    this.errMsg = "person is exists";
                    return person;
                }
                if (!sPersonList.isEmpty()) {
                    int id = -1;
                    for (Person p : sPersonList) {
                        if (p.id > id) {
                            id = p.id;
                        }
                    }
                    person.id = id + 1;
                } else {
                    person.id = 1;
                }
                Log.e("lx", "addPerson(): " + person.toString());
                BufferedOutputStream out = null;
                try {
                    sPersonList.add(person);
                    String json = GSON.toJson(sPersonList);
                    out = new BufferedOutputStream(new FileOutputStream(DATA_FILE));
                    out.write(json.getBytes());
                } catch (Exception e) {
                    e.printStackTrace();
                    sPersonList.remove(person);
                } finally {
                    CommonUtil.closeStream(out);
                }
                return person;
            }
        };
        RunUtil.runOnWorkThread(work, callback);
    }
}
