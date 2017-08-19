package com.inmind.app.common;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.inmind.app.common.entity.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by lixiang on 2017/1/13.
 */
public final class FileUtil{
    public static final String PATH = "/inmind";
    public static final String FILE_NAME = "inmind_data.json";
    public static final Gson GSON = new Gson();

    public static ArrayList<User> getBirthDayDataFromSDCard(Context context){
        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+PATH+"/"+FILE_NAME);
        if(!f.exists()){
            return null;
        }
        InputStream in = null;
        ByteArrayOutputStream baos = null;
        try{
            in = new FileInputStream(f);
            baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while(in.read(buffer) != -1){
                baos.write(buffer);
            }
            String json = new String(baos.toByteArray());
            Log.d("lx", "json: "+json);
            return parseJson(json);
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            if(in != null){
                try{
                    in.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
            if(baos != null){
                try{
                    baos.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private static ArrayList<User> parseJson(String json) throws JSONException{
        if(TextUtils.isEmpty(json)){
            return null;
        }
        JSONArray arr = new JSONArray(json);
        if(arr == null || arr.length() <= 0){
            return null;
        }
        int len = arr.length();
        ArrayList<User> list = new ArrayList<>(len);
        User model;
        for(int i = 0; i < len; i++){
            JSONObject obj = arr.optJSONObject(i);
            if(obj == null){
                continue;
            }
            model = new User();
            model.realName = obj.optString("realName", "");
            model.nickName = obj.optString("nickName", "");
            model.sex = obj.optInt("sex", 0);
            model.birthdayType = obj.optInt("birthdayType", 2);
            model.lunarYear = obj.optInt("lunarYear", 0);
            model.lunarMonth = obj.optInt("lunarMonth", 0);
            model.lunarDay = obj.optInt("lunarDay", 0);

            model.solarYear = obj.optInt("solarYear", 0);
            model.solarMonth = obj.optInt("solarMonth", 0);
            model.solarDay = obj.optInt("solarDay", 0);
            model.animal = obj.optString("animal", "");
            list.add(model);
        }
        return list;
    }

    public static boolean saveBirthDayDataToSDCard(ArrayList<User> list){
        if(list == null || list.size() == 0){
            return true;
        }
        String json = GSON.toJson(list);
        Log.d("lx", "save= "+json);
//        StringBuilder sb = new StringBuilder();
//        sb.append("[");
//        int len = list.size();
//        User model;
//        for(int i = 0; i < len; i++){
//            model = list.get(i);
//            if(model != null){
//                sb.append("{")
//                        .append(model.toString())
//                        .append("}")
//                        .append(",");
//            }
//        }
//        if(sb.toString().endsWith(",")){
//            sb.deleteCharAt(sb.length()-1);
//        }
//        sb.append("]");
        File parent = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+PATH);
        if(!parent.exists()){
            parent.mkdir();
        }
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()+PATH+"/"+FILE_NAME);
//        if(file.exists()){
//           file.delete();
//        }
        OutputStream out = null;
        try{
            out = new FileOutputStream(file);
            out.write(json.getBytes());
        }catch(Exception e){
            return false;
        }finally{
            if(out != null){
                try{
                    out.close();
                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
}
