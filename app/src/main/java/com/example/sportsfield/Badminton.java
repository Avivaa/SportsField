package com.example.sportsfield;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class Badminton extends Fragment {

    public String orderdate, starthour, startminute, endhour, endminute;
    public String badname, badID, badnum, badfieldID;
    public String activityID;
    public boolean btn_color = false;
    public boolean selected_color = true;

    EditText edbadName;
    EditText edbadID;
    EditText edbadNum;
    TextView showbadtime;
    TextView showbadFieldID;
    Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12,Order;
    
    Connection connection;
    Handler myHandler;
    public Badminton() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View badmintonLayout = inflater.inflate(R.layout.fragment_badminton, container, false);
        showbadtime = (TextView) badmintonLayout.findViewById(R.id.showbadTime);
        showbadFieldID = (TextView) badmintonLayout.findViewById(R.id.showbadfieldID);
        edbadName = (EditText) badmintonLayout.findViewById(R.id.edbadName);
        edbadID = (EditText) badmintonLayout.findViewById(R.id.edbadID);
        edbadNum = (EditText) badmintonLayout.findViewById(R.id.edbadNum);
        button1 = (Button) badmintonLayout.findViewById(R.id.one);
        button2 = (Button) badmintonLayout.findViewById(R.id.two);
        button3 = (Button) badmintonLayout.findViewById(R.id.three);
        button4 = (Button) badmintonLayout.findViewById(R.id.four);
        button5 = (Button) badmintonLayout.findViewById(R.id.five);
        button6 = (Button) badmintonLayout.findViewById(R.id.six);
        button7 = (Button) badmintonLayout.findViewById(R.id.seven);
        button8 = (Button) badmintonLayout.findViewById(R.id.eight);
        button9 = (Button) badmintonLayout.findViewById(R.id.nine);
        button10 = (Button) badmintonLayout.findViewById(R.id.ten);
        button11 = (Button) badmintonLayout.findViewById(R.id.eleven);
        button12 = (Button) badmintonLayout.findViewById(R.id.twelve);
        Order = (Button) badmintonLayout.findViewById(R.id.badmintonOrder);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button1.setActivated(btn_color);
                    showbadFieldID.setText("1号");
                } else {
                    btn_color = false;
                    button1.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button2.setActivated(btn_color);
                    showbadFieldID.setText("2号");
                } else {
                    btn_color = false;
                    button2.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button3.setActivated(btn_color);
                    showbadFieldID.setText("3号");
                } else {
                    btn_color = false;
                    button3.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button4.setActivated(btn_color);
                    showbadFieldID.setText("4号");
                } else {
                    btn_color = false;
                    button4.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button5.setActivated(btn_color);
                    showbadFieldID.setText("5号");
                } else {
                    btn_color = false;
                    button5.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button6.setActivated(btn_color);
                    showbadFieldID.setText("6号");
                } else {
                    btn_color = false;
                    button6.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button7.setActivated(btn_color);
                    showbadFieldID.setText("7号");
                } else {
                    btn_color = false;
                    button7.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button8.setActivated(btn_color);
                    showbadFieldID.setText("8号");
                } else {
                    btn_color = false;
                    button8.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button9.setActivated(btn_color);
                    showbadFieldID.setText("9号");
                } else {
                    btn_color = false;
                    button9.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button10.setActivated(btn_color);
                    showbadFieldID.setText("10号");
                } else {
                    btn_color = false;
                    button10.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button11.setActivated(btn_color);
                    showbadFieldID.setText("11号");
                } else {
                    btn_color = false;
                    button11.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) {
                    btn_color = true;
                    button12.setActivated(btn_color);
                    showbadFieldID.setText("12号");
                } else {
                    btn_color = false;
                    button12.setActivated(btn_color);
                    showbadFieldID.setText("");
                }
            }
        });

        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               badname=edbadName.getText().toString();
               badID=edbadID.getText().toString();
               badfieldID=showbadFieldID.getText().toString();
               badnum=edbadNum.getText().toString();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysports", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //编辑改写都要用editor
                editor.putString("badfield_ID_key", badfieldID);
                editor.putString("bad_name_key", badname);
                editor.putString("bad_ID_key", badID);
                editor.putString("bad_num_key", badnum);
                editor.apply();

                activityID = "1";
                Intent pay = new Intent(getActivity(), PayOrder.class);
                pay.putExtra("activity_ID_key", activityID);
                startActivity(pay);

                }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysports",Context.MODE_PRIVATE);
        orderdate=sharedPreferences.getString("order_date_key","");
        starthour=sharedPreferences.getString("start_hour_key","");
        startminute=sharedPreferences.getString("start_minute_key","");
        endhour=sharedPreferences.getString("end_hour_key","");
        endminute=sharedPreferences.getString("end_minute_key","");

        showbadtime.setText(orderdate + " " + starthour + ":" + startminute + " —— " + endhour + ":" + endminute);


        myHandler = new Handler(){
            public void handleMessage(Message msg){
                if (msg.what == 3) {
                    Bundle bdl = (Bundle) msg.obj;
                    String fieldID = bdl.getString("fieldID");
                    if(fieldID.equals("1号")){
                        button1.setActivated(selected_color); }
                    if(fieldID.equals("2号")){
                        button2.setActivated(selected_color); }
                    if(fieldID.equals("3号")){
                        button3.setActivated(selected_color);; }
                    if(fieldID.equals("4号")){
                        button4.setActivated(selected_color); }
                    if(fieldID.equals("5号")){
                        button5.setActivated(selected_color); }
                    if(fieldID.equals("6号")){
                        button6.setActivated(selected_color); }
                    if(fieldID.equals("7号")){
                        button7.setActivated(selected_color); }
                    if(fieldID.equals("8号")){
                        button8.setActivated(selected_color); }
                    if(fieldID.equals("9号")){
                        button9.setActivated(selected_color); }
                    if(fieldID.equals("10号")){
                        button10.setActivated(selected_color); }
                    if(fieldID.equals("11号")){
                        button11.setActivated(selected_color);}
                    if(fieldID.equals("12号")){
                        button12.setActivated(selected_color); }

                }
                Toast.makeText(getActivity(),"黄色为已选场地",Toast.LENGTH_SHORT).show();
                super.handleMessage(msg);
            }
        };

        Thread t= new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    connection = DriverManager.getConnection("jdbc:mysql://10.64.75.33/sportsfield", "root", "jiafeitom");
                    Log.i("open","连接成功");
                } catch (ClassNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                try {
                    test(connection,orderdate);    //测试数据库连接
                } catch (java.sql.SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        t.start();

        return badmintonLayout;
    }
    public String handleString(String s)
    {   try{ byte bb[]=s.getBytes("utf-8");
        s=new String(bb);
    }
    catch(Exception ee){}
        return s;
    }
    private void test(Connection con,String orderdate) throws java.sql.SQLException {
        try {
            String sql = "select badfieldID from badminton where orderdate=? and (CAST(starthour AS signed)*60+CAST(startminute AS signed) between'"+
                    (Integer.parseInt(starthour)*60+Integer.parseInt(startminute)) +"'and'" +(Integer.parseInt(endhour)*60+Integer.parseInt(endminute))+
                    "'or CAST(endhour AS signed)*60+CAST(endminute AS signed) between '"+
                    (Integer.parseInt(starthour)*60+Integer.parseInt(startminute))+"'and '"+(Integer.parseInt(endhour)*60+Integer.parseInt(endminute))+"')";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, orderdate.trim());
            ResultSet rs = stmt.executeQuery();
            //<code>ResultSet</code>最初指向第一行
            Bundle bundle = new Bundle();
            while (rs.next()) {
                bundle.clear();
                bundle.putString("fieldID", rs.getString("badfieldID"));

                Message msg= myHandler.obtainMessage(3);
                msg.obj= bundle;
                myHandler.sendMessage(msg);

            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {

        } finally {
            if (con!= null)
                try {
                    con.close();
                } catch (SQLException e) {
                }
        }
    };
}

