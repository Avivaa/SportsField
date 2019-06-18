package com.example.sportsfield;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Tennis extends Fragment {
    public String inputOrderdate, starthour, startminute, endhour, endminute;
    public String tenname, tenID, tennum, tenfieldID;
    public String activityID;
    public boolean btn_color=false;

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
        inputOrderdate=sharedPreferences.getString("order_date_key","");
        starthour=sharedPreferences.getString("start_hour_key","");
        startminute=sharedPreferences.getString("start_minute_key","");
        endhour=sharedPreferences.getString("end_hour_key","");
        endminute=sharedPreferences.getString("end_minute_key","");

        showtentime.setText(inputOrderdate+" "+starthour+":"+startminute+" —— "+endhour+":"+endminute);

        return tennisLayout;
    }

}
