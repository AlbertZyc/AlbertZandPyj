package com.use.zyc.losed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.use.zyc.losed.adapter.RecyclerViewAdapter;
import com.use.zyc.losed.bean.JsonBean;
import com.use.zyc.losed.util.JsonUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<JsonBean> jsonBeans = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getJsonData();//��ʼ������ Ҳ����JSON���� ����ֵ
        RecyclerView recyclerView = findViewById(R.id.repositoriesd_recyclerview_center);//ʵ����recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//���������Ǻ���
        recyclerView.setLayoutManager(linearLayoutManager);//��������һ���
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(jsonBeans);//adapter��������
        recyclerView.setAdapter(adapter);//��adapter
    }

    private void getJsonData() {
        String json = JsonUtil.getJson(this,"JsonResult.json");
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0 ; i < jsonArray.length() ; i++){//ѭ��ȡֵ
                JsonBean jsonBean = new JsonBean();
                JSONObject jsonObject = jsonArray.getJSONObject(i);//һ��25��
                jsonBean.setAuthor(jsonObject.getString("author"));//��ȡ��������
                jsonBean.setName(jsonObject.getString("name"));//��ȡ��Ŀ����
                jsonBean.setDescription(jsonObject.getString("description"));//��ȡ����
                jsonBean.setLanguage(jsonObject.getString("language"));//��ȡ����
                jsonBean.setStars(jsonObject.getInt("stars"));//��ȡ����
                jsonBean.setForks(jsonObject.getInt("forks"));//��ȡ����
                //������ǹ����ߵ�ͷ�񼯺��� ������ ����һ��
                JSONArray builtBys = jsonObject.getJSONArray("builtBy");
                List<JsonBean.BuiltByBean> builtByBeans = new ArrayList<>();
                for(int j = 0 ; j < builtBys.length() ; j++){
                    JsonBean.BuiltByBean builtByBean = new JsonBean.BuiltByBean();
                    JSONObject builtBy =builtBys.getJSONObject(j);
                    builtByBean.setAvatar(builtBy.getString("username"));//����������
                    builtByBean.setAvatar(builtBy.getString("href"));//����������
                    builtByBean.setAvatar(builtBy.getString("avatar"));//����������
                    builtByBeans.add(builtByBean);
                }
                jsonBean.setBuiltBy(builtByBeans);
                jsonBeans.add(jsonBean);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
