package com.example.sportsfield;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    private Spinner sp1,sp2,sp3,sp4;
    public int startHour;
    public int startMinute;
    public int endHour;
    public int endMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp1 = findViewById(R.id.starthour);
        sp2 = findViewById(R.id.startminute);
        sp3 = findViewById(R.id.endhour);
        sp4 = findViewById(R.id.endminute);

    }
    public void openApply(View btn){

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                startHour = Integer.parseInt(MainActivity.this.getResources().getStringArray(R.array.hour)[arg2]);
            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                startMinute=Integer.parseInt(MainActivity.this.getResources().getStringArray(R.array.minute)[arg2]);
            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                endHour= Integer.parseInt(MainActivity.this.getResources().getStringArray(R.array.hour)[arg2]);
            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });

        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                endMinute= Integer.parseInt(MainActivity.this.getResources().getStringArray(R.array.minute)[arg2]);
            }
            public void onNothingSelected(AdapterView<?> arg0) { }
        });


        SharedPreferences sharedPreferences = getSharedPreferences("mysports", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit(); //编辑改写都要用editor
        editor.putInt("start_hour_key",startHour);
        editor.putInt("start_minute_key",startMinute);
        editor.putInt("end_hour_key",endHour);
        editor.putInt("end_minute_key",endMinute);
        editor.apply();

        Intent apply = new Intent(this,ApplyActivity.class);//打开其他工程文件
        startActivity(apply);
    }

}
