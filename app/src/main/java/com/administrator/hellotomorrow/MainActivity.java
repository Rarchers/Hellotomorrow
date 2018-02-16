package com.administrator.hellotomorrow;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private EditText accountedit;
    private EditText passwordedit;
    private Button login;
    private CheckBox remmember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountedit =(EditText)findViewById(R.id.account);
        passwordedit =(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.button);
        remmember =(CheckBox)findViewById(R.id.checkBox);
        final boolean check=pref.getBoolean("remmember",false);
        if (check){
            String account =pref.getString("account","");
            String password =pref.getString("password","");
            accountedit.setText("account");
            passwordedit.setText(password);
            remmember.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                String account =accountedit.getText().toString();
                String password =passwordedit.getText().toString();
                if (account.equals("Rarcher")&&password.equals("steamcat")){
                    editor=pref.edit();
                    if (remmember.isChecked()){
                    editor.putBoolean("remember",true);
                    editor.putString("account",account);
                    editor.putString("password",password);
                }
                else {
                    editor.clear();
                }
                Intent intent =new Intent (MainActivity.this,tomorrow.class);
                startActivity(intent);
            }
            else{
                    Toast.makeText(MainActivity.this,"唔，我们不能让你用错误密码或者用户名来查看留言",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}
