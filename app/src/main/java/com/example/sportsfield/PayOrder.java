package com.example.sportsfield;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class PayOrder extends AppCompatActivity {
    public String activityID, inputOrderdate, badfieldID, badname, badID, badnum, tenfieldID,tenname,tenID,tennum,swimname,swimID,swimnum;
    public String starthour, startminute, endhour, endminute;

    public float sportsmoney;
    TextView showmoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_order);

        showmoney = findViewById(R.id.showMoney);
        SharedPreferences sharedPreferences = getSharedPreferences("mysports", Context.MODE_PRIVATE);
        inputOrderdate = sharedPreferences.getString("order_date_key", "");
        starthour = sharedPreferences.getString("start_hour_key", "");
        startminute =sharedPreferences.getString("start_minute_key", "");
        endhour = sharedPreferences.getString("end_hour_key", "");
        endminute = sharedPreferences.getString("end_minute_key", "");
        badfieldID=sharedPreferences.getString("badfield_ID_key", "");
        badname = sharedPreferences.getString("bad_name_key", "");
        badID= sharedPreferences.getString("bad_ID_key", "");
        badnum = sharedPreferences.getString("bad_num_key", "");
        tenfieldID=sharedPreferences.getString("tenfield_ID_key", "");
        tenname = sharedPreferences.getString("ten_name_key", "");
        tenID= sharedPreferences.getString("ten_ID_key", "");
        tennum = sharedPreferences.getString("ten_num_key", "");
        swimname = sharedPreferences.getString("swim_name_key", "");
        swimID= sharedPreferences.getString("swim_ID_key", "");
        swimnum = sharedPreferences.getString("swim_num_key", "");
        activityID=getIntent().getStringExtra("activity_ID_key");

        if(activityID.equals("1")){
            sportsmoney=((Float.parseFloat(endhour)*60+Float.parseFloat(endminute))-(Float.parseFloat(starthour)*60+Float.parseFloat(startminute)))/60*5;
        }else if(activityID.equals("2")){
            sportsmoney=((Float.parseFloat(endhour)*60+Float.parseFloat(endminute))-(Float.parseFloat(starthour)*60+Float.parseFloat(startminute)))/60*8;
        }else{
            swimnum =sharedPreferences.getString("swim_num_key", "");
            sportsmoney=5*Float.parseFloat(swimnum);
        }
        showmoney.setText(String.valueOf(sportsmoney)+"元");
    }
    public void code(View btn){
        Intent intent=new Intent(this,OrderCode.class);
        intent.putExtra("activity_id_key",activityID);
        startActivity(intent);
    }
   public void payorder(View btn) {
        if(activityID.equals("1")) {
            new badorderTask().execute(badfieldID, inputOrderdate, starthour, startminute, endhour, endminute, badname, badID, badnum);
        }
        Intent payorder=new Intent(this,successOrder.class);
        startActivity(payorder);
    }
    private class badorderTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            for (String p : params) {
                Log.i(TAG, "doInBackground:" + p);
            }
            HashMap<String, String> hashMap = new HashMap<>();
            hashMap.put("badfieldID", params[0]);
            hashMap.put("orderdata", params[1]);
            hashMap.put("starthour", params[2]);
            hashMap.put("startminute", params[3]);
            hashMap.put("endhour", params[4]);
            hashMap.put("endminute", params[5]);
            hashMap.put("badname", params[6]);
            hashMap.put("badID", params[7]);
            hashMap.put("badnum", params[8]);

            String ret = dobadOrder(hashMap);
            return ret;

        }

        @Override
        protected void onPostExecute(String s) {


        }
    }
    private String dobadOrder(HashMap orderStr) {
        String retStr = "";
        String encode = "";
        try {

            byte[] data = getRequestData(orderStr, encode).toString().getBytes();
            String urlPath = "http://10.63.174.151:8080/badOrderServlet";
            URL url = new URL(urlPath);

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setConnectTimeout(3000);     //设置连接超时时间
            httpURLConnection.setDoInput(true);// 打开输入流，以便从服务器获取数据
            httpURLConnection.setDoOutput(true);// 打开输出流，以便向服务器提交数据
            httpURLConnection.setRequestMethod("POST");// 设置以Post方式提交数据
            httpURLConnection.setUseCaches(false);// 使用Post方式不能使用缓存
            // /设置请求体的类型是文本类型
            httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置请求体的长度
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
            //获得输出流，向服务器写入数据
            OutputStream outputStream = httpURLConnection.getOutputStream();
            outputStream.write(data);

            int response = httpURLConnection.getResponseCode();       //获得服务器的响应码
            if (response == HttpURLConnection.HTTP_OK) {
                InputStream inputStream = httpURLConnection.getInputStream();
                retStr = inputStreamToString(inputStream);            //处理服务器的响应结果

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retStr;
    }

    public static String inputStreamToString(InputStream inputStream) {
        String resultData = null;      //存储处理结果
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] data = new byte[1024];
        int len = 0;
        try {
            while ((len = inputStream.read(data)) != -1) {
                byteArrayOutputStream.write(data, 0, len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        resultData = new String(byteArrayOutputStream.toByteArray());
        return resultData;
    }

    public static StringBuffer getRequestData(HashMap<String, String> params, String encode) {
        StringBuffer stringBuffer = new StringBuffer();        //存储封装好的请求体信息
        try {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                stringBuffer.append(entry.getKey())
                        .append("=")
                        .append(URLEncoder.encode(entry.getValue(), encode))
                        .append("&");
            }
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);    //删除最后的一个"&"
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuffer;
    }


}
