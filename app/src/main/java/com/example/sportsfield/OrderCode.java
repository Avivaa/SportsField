package com.example.sportsfield;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Hashtable;

public class OrderCode extends AppCompatActivity {
    public String activityID, orderdate, badfieldID, badname, badID, badnum, tenfieldID,tenname,tenID,tennum,swimname,swimID,swimnum;
    public String starthour, startminute, endhour, endminute;
    public StringBuffer stringBuffer;
    public String orderStr;
    public String backnews;

    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_code);
        imageView=findViewById(R.id.showOrderCode);

        SharedPreferences sharedPreferences = getSharedPreferences("mysports", Context.MODE_PRIVATE);
        orderdate = sharedPreferences.getString("order_date_key", "");
        starthour = sharedPreferences.getString("start_hour_key", "");
        startminute = sharedPreferences.getString("start_minute_key", "");
        endhour = sharedPreferences.getString("end_hour_key", "");
        endminute = sharedPreferences.getString("end_minute_key", "");
        activityID=getIntent().getStringExtra("activity_id_key");
        backnews=getIntent().getStringExtra("backnews");
        stringBuffer=new StringBuffer("运动订单信息：");
        Toast.makeText(this,backnews,Toast.LENGTH_SHORT).show();

        if(activityID.equals("1")) {
            badfieldID = sharedPreferences.getString("badfield_ID_key", "");
            badname = sharedPreferences.getString("bad_name_key", "");
            badID = sharedPreferences.getString("bad_ID_key", "");
            badnum = sharedPreferences.getString("bad_num_key", "");
            stringBuffer.append("\n"+"姓名："+badname+","+"学号："+badID+","+"人数："+badnum+"\n"+
                                 "时间:"+orderdate+","+starthour+":"+startminute+"——"+endhour+":"+endminute+"\n"+
                                 "羽毛球场:"+badfieldID);

        }else if(activityID.equals("2")) {
            tenfieldID = sharedPreferences.getString("tenfield_ID_key", "");
            tenname = sharedPreferences.getString("ten_name_key", "");
            tenID = sharedPreferences.getString("ten_ID_key", "");
            tennum = sharedPreferences.getString("ten_num_key", "");
            stringBuffer.append("\n"+"姓名："+tenname+","+"学号："+tenID+","+"人数："+tennum+"\n"+
                    "时间:"+orderdate+","+starthour+":"+startminute+"——"+endhour+":"+endminute+"\n"+
                    "网球场:"+tenfieldID);
        }else {
            swimname = sharedPreferences.getString("swim_name_key", "");
            swimID = sharedPreferences.getString("swim_ID_key", "");
            swimnum = sharedPreferences.getString("swim_num_key", "");
            stringBuffer.append("\n"+"姓名："+swimname+","+"学号："+swimID+","+"人数："+swimnum);
        }
        orderStr=stringBuffer.toString();
        Bitmap bitmap = createQRImage(orderStr, 500, 500, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        imageView.setImageBitmap(bitmap);

    }

    public  Bitmap createQRImage(String Str, int width, int height, Bitmap bitmap) {
            try {
                // 判断URL合法性
                if (Str == null || "".equals(Str) || Str.length() < 1) {
                    return null;
                }
                Hashtable hints = new Hashtable();
                hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
                // 图像数据转换，使用了矩阵转换
                BitMatrix bitMatrix = new QRCodeWriter().encode(Str,
                        BarcodeFormat.QR_CODE, width, height, hints);
                int[] pixels = new int[width * height];
                // 下面这里按照二维码的算法，逐个生成二维码的图片，
                // 两个for循环是图片横列扫描的结果
                for (int y = 0; y < height; y++) {
                    for (int x = 0; x < width; x++) {
                        if (bitMatrix.get(x, y)) {
                            pixels[y * width + x] = 0xff000000;
                        } else {
                            pixels[y * width + x] = 0xffffffff;
                        }
                    }
                }
                // 生成二维码图片的格式，使用ARGB_8888
                bitmap = Bitmap.createBitmap(width, height,
                        Bitmap.Config.ARGB_8888);
                bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
                return bitmap;
            } catch (WriterException e) {
                e.printStackTrace();
            }
            return null;
        }

}
