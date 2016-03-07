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

	private EditText edt_phoneNumber;     //�ֻ����������
	private TextView txt;               //��������ʾ
	
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
    
  //�����Ľ���仯����
    @Override
    public void onFocusChange(View v, boolean hasFocus) {
    	String isPhone = edt_phoneNumber.getText().toString();
    	if(hasFocus == false){
    		                                   // �ֻ�����������ж�
            Pattern pattern = Pattern.compile("^1[3,5,8]\\d{9}$");
            Matcher matcher = pattern.matcher(isPhone);
            if (matcher.find()) {
                //�ж��Ƿ����ظ��û�
                if (new MyloginCursor(
                        MainActivity.this.helper.getReadableDatabase())
                        .query(isPhone).size() == 0) {
                    txt.setVisibility(View.INVISIBLE); //��ʽ��ȷ
                    
                } else {
                    txt.setVisibility(View.VISIBLE);
                    txt.setText("�û��Ѵ��ڣ�");
                }
            } else {                           //����ʽƥ��������˵����ʽ����
                    txt.setVisibility(View.VISIBLE);
                
            }
    	}
    }
}
