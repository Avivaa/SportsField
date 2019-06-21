package com.example.sportsfield;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import org.w3c.dom.Text;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "";
    private Spinner sp1, sp2, sp3, sp4;
    public TextView showOrderDate;
    public String orderdate;
    public String startHour;
    public String startMinute;
    public String endHour;
    public String endMinute;
    SliderLayout slider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        slider = findViewById(R.id.slider);
        HashMap<String,String> urlMaps = new HashMap<>();
        urlMaps.put("Run", "https://i.loli.net/2019/06/21/5d0cb97eb83c792979.jpg");
        urlMaps.put("Badminton", "https://i.loli.net/2019/06/21/5d0cb9208747374931.jpg");
        urlMaps.put("Tennis", "https://i.loli.net/2019/06/21/5d0cb9401900f95925.jpg");
        urlMaps.put("Swim","https://i.loli.net/2019/06/21/5d0cb95e9be4255324.jpg");

        for(String name : urlMaps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)//描述
                    .image(urlMaps.get(name))//image方法可以传入图片url
                    .setScaleType(BaseSliderView.ScaleType.Fit)//图片缩放类型，https://sm.ms/可将本地图片上传获取url
                    .setOnSliderClickListener(onSliderClickListener);//图片点击
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);//传入参数
            slider.addSlider(textSliderView);//添加一个滑动页面
        }

        slider.setPresetTransformer(SliderLayout.Transformer.Accordion);//滑动动画
//        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);//默认指示器样式
        slider.setCustomIndicator((PagerIndicator)findViewById(R.id.custom_indicator2));//自定义指示器
        slider.setCustomAnimation(new DescriptionAnimation());//设置图片描述显示动画
        slider.setDuration(4000);//设置滚动时间，也是计时器时间
        slider.addOnPageChangeListener(onPageChangeListener);

        showOrderDate = findViewById(R.id.showOrderdate);
        Date today= Calendar.getInstance().getTime();
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd"); //小写m表示分钟
        orderdate= sdf.format(today);
        showOrderDate.setText(orderdate);

        sp1 = findViewById(R.id.starthour);
        sp2 = findViewById(R.id.startminute);
        sp3 = findViewById(R.id.endhour);
        sp4 = findViewById(R.id.endminute);

    }
    private BaseSliderView.OnSliderClickListener onSliderClickListener=new BaseSliderView.OnSliderClickListener() {
        @Override
        public void onSliderClick(BaseSliderView slider) {
            Toast.makeText(MainActivity.this,slider.getBundle().get("extra") + "",
                    Toast.LENGTH_SHORT).show();
        }
    };
    private ViewPagerEx.OnPageChangeListener onPageChangeListener=new ViewPagerEx.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

        @Override
        public void onPageSelected(int position) {
            Log.d("ansen", "Page Changed: " + position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {}
    };
    public void openApply(View btn) {

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                startHour = (MainActivity.this.getResources().getStringArray(R.array.hour)[arg2]);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                startMinute = (MainActivity.this.getResources().getStringArray(R.array.minute)[arg2]);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sp3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                endHour = MainActivity.this.getResources().getStringArray(R.array.hour)[arg2];
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });

        sp4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                endMinute = (MainActivity.this.getResources().getStringArray(R.array.minute)[arg2]);
            }

            public void onNothingSelected(AdapterView<?> arg0) {
            }
        });
        SharedPreferences sharedPreferences = getSharedPreferences("mysports", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit(); //编辑改写都要用editor
        editor.putString("order_date_key", orderdate);
        editor.putString("start_hour_key", startHour);
        editor.putString("start_minute_key", startMinute);
        editor.putString("end_hour_key", endHour);
        editor.putString("end_minute_key", endMinute);
        editor.apply();

        Intent apply = new Intent(this, ApplyActivity.class);//打开其他工程文件
        startActivity(apply);
    }
}