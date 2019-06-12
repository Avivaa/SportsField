package com.example.sportsfield;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Badminton extends Fragment {

    public int starthour;
    public int startminute;
    public int endhour;
    public int endminute;
    public boolean btn_color=false;

    TextView showbadtime;
    TextView showbadFieldID;
    Button button1,button2,button3,button4,button5,button6,
            button7,button8,button9,button10,button11,button12;


    public Badminton() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View badmintonLayout=inflater.inflate(R.layout.fragment_badminton,container,false);
        showbadtime= (TextView) badmintonLayout.findViewById(R.id.showbadTime);
        showbadFieldID= (TextView) badmintonLayout.findViewById(R.id.showbadfieldID);
        button1=(Button)badmintonLayout.findViewById(R.id.one);
        button2=(Button)badmintonLayout.findViewById(R.id.two);
        button3=(Button)badmintonLayout.findViewById(R.id.three);
        button4=(Button)badmintonLayout.findViewById(R.id.four);
        button5=(Button)badmintonLayout.findViewById(R.id.five);
        button6=(Button)badmintonLayout.findViewById(R.id.six);
        button7=(Button)badmintonLayout.findViewById(R.id.seven);
        button8=(Button)badmintonLayout.findViewById(R.id.eight);
        button9=(Button)badmintonLayout.findViewById(R.id.nine);
        button10=(Button)badmintonLayout.findViewById(R.id.ten);
        button11=(Button)badmintonLayout.findViewById(R.id.eleven);
        button12=(Button)badmintonLayout.findViewById(R.id.twelve);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button1.setActivated(btn_color);showbadFieldID.setText("1号"); }
                else { btn_color = false; button1.setActivated(btn_color); showbadFieldID.setText(""); }
        }});
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button2.setActivated(btn_color);showbadFieldID.setText("2号"); }
                else { btn_color = false; button2.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button3.setActivated(btn_color);showbadFieldID.setText("3号"); }
                else { btn_color = false; button3.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button4.setActivated(btn_color);showbadFieldID.setText("4号"); }
                else { btn_color = false; button4.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button5.setActivated(btn_color);showbadFieldID.setText("5号"); }
                else { btn_color = false; button5.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button6.setActivated(btn_color);showbadFieldID.setText("6号"); }
                else { btn_color = false; button6.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button7.setActivated(btn_color);showbadFieldID.setText("7号"); }
                else { btn_color = false; button7.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button8.setActivated(btn_color);showbadFieldID.setText("8号"); }
                else { btn_color = false; button8.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button9.setActivated(btn_color);showbadFieldID.setText("9号"); }
                else { btn_color = false; button9.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button10.setActivated(btn_color);showbadFieldID.setText("10号"); }
                else { btn_color = false; button10.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button11.setActivated(btn_color);showbadFieldID.setText("11号"); }
                else { btn_color = false; button11.setActivated(btn_color); showbadFieldID.setText(""); }
            }});
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!btn_color) { btn_color = true; button12.setActivated(btn_color);showbadFieldID.setText("12号"); }
                else { btn_color = false; button12.setActivated(btn_color); showbadFieldID.setText(""); }
            }});

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysports",Context.MODE_PRIVATE);
        starthour=sharedPreferences.getInt("start_hour_key",0);
        startminute=sharedPreferences.getInt("start_minute_key",0);
        endhour=sharedPreferences.getInt("end_hour_key",0);
        endminute=sharedPreferences.getInt("end_minute_key",0);

        showbadtime.setText(starthour+":"+startminute+" —— "+endhour+":"+endminute);

        return badmintonLayout;
    }


}
