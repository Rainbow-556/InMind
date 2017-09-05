package com.inmind.app.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.inmind.app.common.CommonUtil;
import com.inmind.app.common.ExecutionCallback;
import com.inmind.app.common.RunUtil;
import com.inmind.app.common.entity.Person;
import com.inmind.app.ui.base.InMindApplication;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lixiang on 2017/8/19.
 */
public class PersonRepositoryImpl implements PersonRepository{
    /*personData为json: []*/
    private static final String FILE_NAME = "personData.txt";
    private static final File DATA_FILE = new File(InMindApplication.getInstance().getExternalCacheDir(), FILE_NAME);
    private static volatile List<Person> sPersonList = new ArrayList<>();
    private static final Gson GSON = new Gson();

    @Override
    public void getPersons(ExecutionCallback<List<Person>> callback){
        RunUtil.runOnWorkThread(new RunUtil.Work<List<Person>>(){
            @Override
            public List<Person> execute(){
                List<Person> list = null;
                ByteArrayOutputStream baos = null;
                FileInputStream in = null;
                try{
                    in = new FileInputStream(DATA_FILE);
                    baos = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while((len = in.read(buffer)) != -1){
                        baos.write(buffer, 0, len);
                    }
                    String json = new String(baos.toByteArray());
                    list = GSON.fromJson(json, new TypeToken<List<Person>>(){}.getType());
                    sPersonList.clear();
                    sPersonList.addAll(list);
                    Log.e("lx", "getPersons(): " + list.toString());
                }catch(Exception e){
                    e.printStackTrace();
                }finally{
                    CommonUtil.closeStream(in);
                    CommonUtil.closeStream(baos);
                }
                return list;
            }
        }, callback);
    }

    @Override
    public void updatePerson(Person person, ExecutionCallback<Person> callback){

    }

    @Override
    public void deletePerson(Person person, ExecutionCallback<Person> callback){
    }

    @Override
    public void addPerson(final Person person, ExecutionCallback<Person> callback){
        Log.e("lx", "addPerson(): " + person.toString());
        final RunUtil.Work<Person> work = new RunUtil.Work<Person>(){
            @Override
            public Person execute(){
                if(sPersonList.contains(person)){
                    this.errMsg = "person is exists";
                    return person;
                }
                if(!sPersonList.isEmpty()){
                    person.id = sPersonList.get(sPersonList.size() - 1).id + 1;
                }else{
                    person.id = 1;
                }
                BufferedOutputStream out = null;
                try{
                    sPersonList.add(person);
                    String json = GSON.toJson(sPersonList);
                    out = new BufferedOutputStream(new FileOutputStream(DATA_FILE));
                    out.write(json.getBytes());
                }catch(Exception e){
                    e.printStackTrace();
                    sPersonList.remove(person);
                }finally{
                    CommonUtil.closeStream(out);
                }
                return person;
            }
        };
        RunUtil.runOnWorkThread(work, callback);
    }
}
