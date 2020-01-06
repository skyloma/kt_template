package com.loma.project.model;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.loma.project.ProjectDaterailActivity;

public class Project {
    public String date;
    public String  vesion;
    public String  endDate;
    public boolean android;
    public String  icon;
    public String  description;
    public String  testURL;
    public String  type;
    public boolean ios;
    public String  devURL;
    public boolean pc;
    public String  name;
    public int     progress;
    public int     id;
    public String  src_vcs;

    public void  read(View view){
       view.getContext().startActivity(new Intent(view.getContext(),ProjectDaterailActivity.class));
    }
}
