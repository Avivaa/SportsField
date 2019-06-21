package com.example.sportsfield;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class PayOrder extends AppCompatActivity implements Runnable{
    public String activityID, orderdate, badfieldID, badname, badID, badnum, tenfieldID,tenname,tenID,tennum,swimname,swimID,swimnum;
    public String starthour, startminute, endhour, endminute;
    public float sportsmoney;
    TextView showmoney;
    Connection connection;
    Handler handler;
    public String getbacknews="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);

        showmoney = findViewById(R.id.showMoney);
        activityID = getIntent().getStringExtra("activity_ID_key");
        SharedPreferences sharedPreferences = getSharedPreferences("mysports", Context.MODE_PRIVATE);
        orderdate = sharedPreferences.getString("order_date_key", "");
        starthour = sharedPreferences.getString("start_hour_key", "");
        startminute = sharedPreferences.getString("start_minute_key", "");
        endhour = sharedPreferences.getString("end_hour_key", "");
        endminute = sharedPreferences.getString("end_minute_key", "");

        if (activityID.equals("1")) {
            sportsmoney = ((Float.parseFloat(endhour) * 60 + Float.parseFloat(endminute)) - (Float.parseFloat(starthour) * 60 + Float.parseFloat(startminute))) / 60 * 5;
        } else if (activityID.equals("2")) {
            sportsmoney = ((Float.parseFloat(endhour) * 60 + Float.parseFloat(endminute)) - (Float.parseFloat(starthour) * 60 + Float.parseFloat(startminute))) / 60 * 8;
        } else {
            swimnum = sharedPreferences.getString("swim_num_key", "");
            sportsmoney = 5 * Float.parseFloat(swimnum);
        }
        showmoney.setText(String.valueOf(sportsmoney) + "元");

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 3) {
                    Bundle bdl = (Bundle) msg.obj;
                    getbacknews = bdl.getString("backnews");
                }
                super.handleMessage(msg);
            }
        };
    }
    public void code(View btn){
        Thread t= new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        SharedPreferences sharedPreferences = getSharedPreferences("mysports", Context.MODE_PRIVATE);
        badfieldID = sharedPreferences.getString("badfield_ID_key", "");
        badname = sharedPreferences.getString("bad_name_key", "");
        badID = sharedPreferences.getString("bad_ID_key", "");
        badnum = sharedPreferences.getString("bad_num_key", "");
        tenfieldID = sharedPreferences.getString("tenfield_ID_key", "");
        tenname = sharedPreferences.getString("ten_name_key", "");
        tenID = sharedPreferences.getString("ten_ID_key", "");
        tennum = sharedPreferences.getString("ten_num_key", "");
        swimname = sharedPreferences.getString("swim_name_key", "");
        swimID = sharedPreferences.getString("swim_ID_key", "");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://10.64.255.177/sportsfield", "root", "jiafeitom");
            Log.i("open","连接成功");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        try {
            test(connection);    //测试数据库连接
        } catch (java.sql.SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public String handleString(String s)
    {   try{ byte bb[]=s.getBytes("utf-8");
        s=new String(bb);
    }
    catch(Exception ee){}
        return s;
    }
    PreparedStatement sql;
    public void test(Connection con1) throws java.sql.SQLException {
        try {
            if(activityID.equals("1")){
                String insertCondition="INSERT INTO badminton VALUES (?,?,?,?,?,?,?,?,?)";
                sql=connection.prepareStatement(insertCondition);
                sql.setString(1,handleString(badfieldID));
                sql.setString(2,handleString(orderdate));
                sql.setString(3,handleString(starthour));
                sql.setString(4,handleString(startminute));
                sql.setString(5,handleString(endhour));
                sql.setString(6,handleString(endminute));
                sql.setString(7,handleString(badname));
                sql.setString(8,handleString(badID));
                sql.setString(9,handleString(badnum));}
            else if(activityID.equals("2")){
                String insertCondition="INSERT INTO tennis VALUES (?,?,?,?,?,?,?,?,?)";
                sql=connection.prepareStatement(insertCondition);
                sql.setString(1,handleString(tenfieldID));
                sql.setString(2,handleString(orderdate));
                sql.setString(3,handleString(starthour));
                sql.setString(4,handleString(startminute));
                sql.setString(5,handleString(endhour));
                sql.setString(6,handleString(endminute));
                sql.setString(7,handleString(tenname));
                sql.setString(8,handleString(tenID));
                sql.setString(9,handleString(tennum));}
            else {
                String insertCondition="INSERT INTO swim VALUES (?,?,?,?,?,?,?,?)";
                sql=connection.prepareStatement(insertCondition);
                sql.setString(1,handleString(orderdate));
                sql.setString(2,handleString(starthour));
                sql.setString(3,handleString(startminute));
                sql.setString(4,handleString(endhour));
                sql.setString(5,handleString(endminute));
                sql.setString(6,handleString(swimname));
                sql.setString(7,handleString(swimID));
                sql.setString(8,handleString(swimnum));}

            int m=sql.executeUpdate();
            Bundle bundle = new Bundle();
            if(m!=0){
                bundle.putString("backnews","预定成功");
            }
            else {
                bundle.putString("backnews","预定失败");  }
            Message msg= handler.obtainMessage(3);
            msg.obj= bundle;
            handler.sendMessage(msg);
        } catch (SQLException e) {

        } finally {
            if (con1 != null)
                try {
                    con1.close();
                } catch (SQLException e) {
                }
        }

    }
}


