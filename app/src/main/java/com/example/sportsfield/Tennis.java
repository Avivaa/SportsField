package com.example.sportsfield;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Tennis extends Fragment {
    public int starthour;
    public int startminute;
    public int endhour;
    public int endminute;
    public boolean btn_color=false;

    TextView showtentime;
    TextView showtenFieldID;
    Button button1,button2,button3,button4,button5,button6,button7,button8;

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
        button1=(Button)tennisLayout.findViewById(R.id.ONE);
        button2=(Button)tennisLayout.findViewById(R.id.TWO);
        button3=(Button)tennisLayout.findViewById(R.id.THREE);
        button4=(Button)tennisLayout.findViewById(R.id.FOUR);
        button5=(Button)tennisLayout.findViewById(R.id.FIVE);
        button6=(Button)tennisLayout.findViewById(R.id.SIX);
        button7=(Button)tennisLayout.findViewById(R.id.SEVEN);
        button8=(Button)tennisLayout.findViewById(R.id.EIGHT);

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

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysports",Context.MODE_PRIVATE);
        starthour=sharedPreferences.getInt("start_hour_key",0);
        startminute=sharedPreferences.getInt("start_minute_key",0);
        endhour=sharedPreferences.getInt("end_hour_key",0);
        endminute=sharedPreferences.getInt("end_minute_key",0);

        showtentime.setText(starthour+":"+startminute+" —— "+endhour+":"+endminute);

        return tennisLayout;
    }

}
