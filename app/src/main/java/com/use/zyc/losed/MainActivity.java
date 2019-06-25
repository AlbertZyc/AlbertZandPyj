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
        getJsonData();//初始化数据 也就是JSON解析 ，赋值
        RecyclerView recyclerView = findViewById(R.id.repositoriesd_recyclerview_center);//实例化recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//设置纵向还是横向
        recyclerView.setLayoutManager(linearLayoutManager);//和上面是一起的
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(jsonBeans);//adapter传入数据
        recyclerView.setAdapter(adapter);//绑定adapter
    }

    private void getJsonData() {
        String json = JsonUtil.getJson(this,"JsonResult.json");
        try {
            JSONArray jsonArray = new JSONArray(json);
            for(int i = 0 ; i < jsonArray.length() ; i++){//循环取值
                JsonBean jsonBean = new JsonBean();
                JSONObject jsonObject = jsonArray.getJSONObject(i);//一共25条
                jsonBean.setAuthor(jsonObject.getString("author"));//获取作者名称
                jsonBean.setName(jsonObject.getString("name"));//获取项目名称
                jsonBean.setDescription(jsonObject.getString("description"));//获取描述
                jsonBean.setLanguage(jsonObject.getString("language"));//获取语言
                jsonBean.setStars(jsonObject.getInt("stars"));//获取星星
                jsonBean.setForks(jsonObject.getInt("forks"));//获取叉子
                //下面就是贡献者的头像集合了 最多五个 最少一个
                JSONArray builtBys = jsonObject.getJSONArray("builtBy");
                List<JsonBean.BuiltByBean> builtByBeans = new ArrayList<>();
                for(int j = 0 ; j < builtBys.length() ; j++){
                    JsonBean.BuiltByBean builtByBean = new JsonBean.BuiltByBean();
                    JSONObject builtBy =builtBys.getJSONObject(j);
                    builtByBean.setAvatar(builtBy.getString("username"));//贡献者名称
                    builtByBean.setAvatar(builtBy.getString("href"));//贡献者链接
                    builtByBean.setAvatar(builtBy.getString("avatar"));//贡献者名称
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
