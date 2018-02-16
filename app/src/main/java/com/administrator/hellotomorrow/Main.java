package com.administrator.hellotomorrow;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       // drawerLayout =(DrawerLayout)findViewById(R.id.drawer_layout);
        //NavigationView navigationView=(NavigationView)findViewById(R.id.nav_view);
        final EditText todayword=(EditText)findViewById(R.id.editText2);
        Button send=(Button)findViewById(R.id.button3);
        FloatingActionButton tomorrow =(FloatingActionButton)findViewById(R.id.tomorrow1);
        ActionBar actionBar =getSupportActionBar();
        if (actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.user);
        }
        final NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(1);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("hello tomorrow")
                .setContentText("时间旅行者准备就绪")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.user)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .build();
       // navigationView.setCheckedItem(R.id.quit);
      //  navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener(){

      //      @Override
    //        public boolean onNavigationItemSelected( MenuItem item) {
     //          finish();
    //            return false;
     //       }
     //   });


        send.setOnClickListener(new View.OnClickListener(){

            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                Calendar c=Calendar.getInstance();
                int today=c.get(Calendar.DAY_OF_MONTH);

                String write= todayword.getText().toString();
                String history="";
              history.concat(write);

                String tomorrow=String.valueOf(today);
                Save(tomorrow);
                Save2(write);
                todayword.clearComposingText();

            }
        });

        manager.notify(1,notification);


        tomorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Main.this,tomorrow.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void Savethehistory (String input){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{out=openFileOutput("tomorrow",MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);
            Toast.makeText(this,"时间旅行者已上路",Toast.LENGTH_LONG).show();


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
    public void Save (String input){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{out=openFileOutput("tomorrow",MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);
            Toast.makeText(this,"时间旅行者已上路",Toast.LENGTH_LONG).show();


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
    public void Save2 (String input){
        FileOutputStream out=null;
        BufferedWriter writer=null;
        try{out=openFileOutput("write",MODE_PRIVATE);
            writer=new BufferedWriter(new OutputStreamWriter(out));
            writer.write(input);

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
    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}
