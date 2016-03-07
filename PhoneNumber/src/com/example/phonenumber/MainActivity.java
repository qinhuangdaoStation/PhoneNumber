package com.example.phonenumber;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends ActionBarActivity implements OnFocusChangeListener {

	private EditText edt_phoneNumber;     //手机号码输入框
	private TextView txt;               //合理性提示
	
	private MyDataBaseHelper helper;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        helper = new MyDataBaseHelper(this);

        edt_phoneNumber = (EditText) findViewById(R.id.edt_phoneNumber);
        edt_phoneNumber.setOnFocusChangeListener(this);
        
        txt = (TextView) findViewById(R.id.txt);
    }
    
  //输入框的焦点变化监听
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
    	String isPhone = edt_phoneNumber.getText().toString();
    	if(hasFocus == false){
    		                                   // 手机号码的正则判断
            Pattern pattern = Pattern.compile("^1[3,5,8]\\d{9}$");
            Matcher matcher = pattern.matcher(isPhone);
            if (matcher.find()) {
                //判断是否是重复用户
                if (new MyloginCursor(
                        MainActivity.this.helper.getReadableDatabase())
                        .query(isPhone).size() == 0) {
                    txt.setVisibility(View.INVISIBLE); //格式正确
                    
                } else {
                    txt.setVisibility(View.VISIBLE);
                    txt.setText("用户已存在！");
                }
            } else {                           //若格式匹配有误，则说明格式错误
                    txt.setVisibility(View.VISIBLE);
                
            }
    	}
    }
}
