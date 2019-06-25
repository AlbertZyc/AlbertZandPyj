package com.use.zyc.losed.util;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JsonUtil {
    //��JSON�ļ�ת���ɿ��Զ�ȡ��String�ַ���
    public static  String getJson(Context context , String filePath){
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(assetManager.open(filePath),"utf-8"));
            String line;
            while((line = bufferedReader.readLine())!=null){
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();//����json�ַ���
    }

}
