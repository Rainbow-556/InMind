package com.inmind.app.common;

import java.io.Closeable;

/**
 * Created by lixiang on 2017/8/19.
 */
public final class CommonUtil{

    public void closeStream(Closeable closeable){
        try{
            if(closeable != null){
                closeable.close();
            }
        }catch(Exception e){
        }
    }
}
