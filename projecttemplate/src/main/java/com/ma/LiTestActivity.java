package com.ma;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Li;

import com.ma.model.User;

import xui.LiAdapter;

public class LiTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_li_test);
        Li li =findViewById(R.id.li);
        LiAdapter<User> adapter = new LiAdapter<User>(R.layout.user_layout,(holder, item, position) -> {

        });
        li.setAdapter(adapter);
//        li.setEmptyView(getLayoutInflater().inflate(R.layout.empty));
    }
}
