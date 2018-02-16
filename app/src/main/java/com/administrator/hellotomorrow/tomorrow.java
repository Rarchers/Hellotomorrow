package com.administrator.hellotomorrow;

import android.annotation.TargetApi;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class tomorrow extends AppCompatActivity {

    private EditText edit;
    private TextView msg;
    private TextView notice;
    private CheckBox makeit;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tomorrow);
        FloatingActionButton today =(FloatingActionButton)findViewById(R.id.today);
        msg =(TextView)findViewById(R.id.msg);
        Button fresh=(Button)findViewById(R.id.button4);
        makeit =(CheckBox)findViewById(R.id.checkBox2);
        notice=(TextView)findViewById(R.id.notice);


       today.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(tomorrow.this,Main.class);
                startActivity(intent);
                finish();
            }
        });


        fresh.setOnClickListener(new  View.OnClickListener(){
        @TargetApi(Build.VERSION_CODES.N)
        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        public void onClick(View view) {
            String input =load();
            Calendar c=Calendar.getInstance();
            int todaydate=c.get(Calendar.DAY_OF_MONTH);
            String todaydate1=String.valueOf(todaydate);
            //  Save(todaydate1);
            String day=load2();
            String yasterday;
           if (!TextUtils.isEmpty(input))
            {
                // edit.setText(input);
                // edit.setSelection(input.length());

                if (todaydate1.equals(day)){
                    yasterday=input;

                    msg.setText(input);
                }
                // Toast.makeText(this,"成功恢复",Toast.LENGTH_SHORT).show();


            }

            if (todaydate1.equals(day)){

                msg.setVisibility(View.GONE);
               if (makeit.isChecked()){
                   msg.setTextColor(0xFF4081F);
               }
                notice.setText("时间旅行者没收到您的消息呐，快去给明天的自己写点什么吧");

            }
            else {

                msg.setVisibility(View.VISIBLE);
                notice.setText("");
            }
        }
    });

   //   Toast.makeText(this,daynow,Toast.LENGTH_LONG).show();
    }


    public String load(){
            FileInputStream in =null;
        BufferedReader reader =null;
        StringBuilder content =new StringBuilder();
        try {
            in = openFileInput("write");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (reader!=null){
                try {
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
            return content.toString();
    }


    public String load2(){
        FileInputStream in =null;
        BufferedReader reader =null;
        StringBuilder content =new StringBuilder();
        try {
            in = openFileInput("tomorrow");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (reader!=null){
                try {
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }

    public void Save (String input){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{out=openFileOutput("today",MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);
            Toast.makeText(this,"获得当前时间成功",Toast.LENGTH_LONG).show();


        }catch (IOException e){
            e.printStackTrace();
        }
        finally {
            try{
                if (writer!=null){
                    writer.close();
                }

            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public String load3(){
        FileInputStream in =null;
        BufferedReader reader =null;
        StringBuilder content =new StringBuilder();
        try {
            in = openFileInput("today");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line=reader.readLine())!=null){
                content.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if (reader!=null){
                try {
                    reader.close();
                }
                catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return content.toString();
    }



}
