package com.example.sportsfield;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Tennis extends Fragment {
    public String orderdate, starthour, startminute, endhour, endminute;
    public String tenname, tenID, tennum, tenfieldID;
    public String activityID;
    public boolean btn_color=false;
    public boolean selected_color=true;
    Handler myHandler;
    Connection connection;

    EditText edtenName;
    EditText edtenID;
    EditText edtenNum;
    TextView showtenFieldID;
    TextView showtentime;
    Button button1,button2,button3,button4,button5,button6,button7,button8,Order;

    public Tennis() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View tennisLayout=inflater.inflate(R.layout.fragment_tennis,container,false);
        showtentime= (TextView) tennisLayout.findViewById(R.id.showtenTime);
        showtenFieldID= (TextView) tennisLayout.findViewById(R.id.showtenfieldID);
        edtenName = (EditText) tennisLayout.findViewById(R.id.edtenName);
        edtenID = (EditText) tennisLayout.findViewById(R.id.edtenID);
        edtenNum = (EditText) tennisLayout.findViewById(R.id.edtenNum);
        button1=(Button)tennisLayout.findViewById(R.id.ONE);
        button2=(Button)tennisLayout.findViewById(R.id.TWO);
        button3=(Button)tennisLayout.findViewById(R.id.THREE);
        button4=(Button)tennisLayout.findViewById(R.id.FOUR);
        button5=(Button)tennisLayout.findViewById(R.id.FIVE);
        button6=(Button)tennisLayout.findViewById(R.id.SIX);
        button7=(Button)tennisLayout.findViewById(R.id.SEVEN);
        button8=(Button)tennisLayout.findViewById(R.id.EIGHT);
        Order=(Button)tennisLayout.findViewById(R.id.tennisOrder);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button1.setActivated(btn_color);showtenFieldID.setText("1号"); }
                else { btn_color = false; button1.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button2.setActivated(btn_color);showtenFieldID.setText("2号"); }
                else { btn_color = false; button2.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button3.setActivated(btn_color);showtenFieldID.setText("3号"); }
                else { btn_color = false; button3.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button4.setActivated(btn_color);showtenFieldID.setText("4号"); }
                else { btn_color = false; button4.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button5.setActivated(btn_color);showtenFieldID.setText("5号"); }
                else { btn_color = false; button5.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button6.setActivated(btn_color);showtenFieldID.setText("6号"); }
                else { btn_color = false; button6.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button7.setActivated(btn_color);showtenFieldID.setText("7号"); }
                else { btn_color = false; button7.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button8.setActivated(btn_color);showtenFieldID.setText("8号"); }
                else { btn_color = false; button8.setActivated(btn_color); showtenFieldID.setText(""); }
            }});
        Order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tenname=edtenName.getText().toString();
                tenID=edtenID.getText().toString();
                tenfieldID=showtenFieldID.getText().toString();
                tennum=edtenNum.getText().toString();
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysports", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit(); //编辑改写都要用editor
                editor.putString("tenfield_ID_key", tenfieldID);
                editor.putString("ten_name_key", tenname);
                editor.putString("ten_ID_key", tenID);
                editor.putString("ten_num_key", tennum);
                editor.apply();

                activityID = "2";
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

        showtentime.setText(orderdate+" "+starthour+":"+startminute+" —— "+endhour+":"+endminute);

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

        return tennisLayout;
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
            String sql = "select tenfieldID from tennis where orderdate= ? and (CAST(starthour AS signed)*60+CAST(startminute AS signed) between'"+
                    (Integer.parseInt(starthour)*60+Integer.parseInt(startminute)) +"'and'" +(Integer.parseInt(endhour)*60+Integer.parseInt(endminute))+
                    "'or CAST(endhour AS signed)*60+CAST(endminute AS signed) between '"+ (Integer.parseInt(starthour)*60+Integer.parseInt(startminute))+
                    "'and '"+(Integer.parseInt(endhour)*60+Integer.parseInt(endminute))+"')";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, orderdate);//创建Statement
            ResultSet rs = stmt.executeQuery();
            //<code>ResultSet</code>最初指向第一行
            Bundle bundle = new Bundle();
            while (rs.next()) {
                bundle.clear();
                bundle.putString("fieldID", rs.getString("tenfieldID"));

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
