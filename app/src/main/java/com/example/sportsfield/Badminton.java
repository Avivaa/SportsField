package com.example.sportsfield;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
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
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import static android.content.ContentValues.TAG;

public class Badminton extends Fragment {

    public String inputOrderdate, starthour, startminute, endhour, endminute;
    public String badname, badID, badnum, badfieldID;
    public String activityID;
    public boolean btn_color = false;

    EditText edbadName;
    EditText edbadID;
    EditText edbadNum;
    TextView showbadtime;
    TextView showbadFieldID;
    Button button1, button2, button3, button4, button5, button6,
            button7, button8, button9, button10, button11, button12,Order;


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
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("mysports", Context.MODE_PRIVATE);
        inputOrderdate = sharedPreferences.getString("order_date_key", "");
        starthour = sharedPreferences.getString("start_hour_key", "");
        startminute = sharedPreferences.getString("start_minute_key", "");
        endhour = sharedPreferences.getString("end_hour_key", "");
        endminute = sharedPreferences.getString("end_minute_key", "");

        showbadtime.setText(inputOrderdate + " " + starthour + ":" + startminute + " —— " + endhour + ":" + endminute);

        return badmintonLayout;
    }
}

