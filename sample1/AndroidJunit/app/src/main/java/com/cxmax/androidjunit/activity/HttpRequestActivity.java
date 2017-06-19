package com.cxmax.androidjunit.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.cxmax.androidjunit.R;
import com.cxmax.androidjunit.request.GithubService;
import com.cxmax.androidjunit.request.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @describe :
 * @usage :
 * <p>
 * <p>
 * Created by cxmax on 2017/3/19.
 */

public class HttpRequestActivity extends AppCompatActivity {

    @BindView(R.id.listView)
    ListView listView;
    private List<String> datas = new ArrayList<>();
    private Callback<List<User>> callback;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_callback);
        ButterKnife.bind(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(HttpRequestActivity.this,datas.get(position),Toast.LENGTH_SHORT).show();
            }
        });
        //加载数据
        loadData();
    }

    public void loadData() {
        callback = new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                for (User user : response.body()) {
                    datas.add(user.login);
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(HttpRequestActivity.this,android.R.layout.simple_list_item_1, datas);
                listView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        };
        GithubService githubService = GithubService.Factory.create();
        githubService.followingUser("geniusmart").enqueue(callback);
    }

    public Callback<List<User>> getCallback(){
        return callback;
    }
}
