package com.alvin.mes;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private EditText edittext;
    private EditText numbertext;
    private EditText contenttext;
    Button button1,button2;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //通过id获得EditText
		edittext=(EditText)findViewById(R.id.mobile);
        //通过id获得button
        button1=(Button)this.findViewById(R.id.button1);
        //当鼠标点击是创建监听，里面是个内部类
        button1.setOnClickListener(new ButtonClickListener());
        //找到文本输入框,按钮
        numbertext=(EditText)this.findViewById(R.id.number2);
        contenttext=(EditText)this.findViewById(R.id.content);
        button2=(Button)this.findViewById(R.id.button2);
        //处理按钮的点击事件，内部类
        button2.setOnClickListener(new ButtonClickListener());
    }
    private final class ButtonClickListener implements View.OnClickListener{
    	//方法重写
    	public void onClick(View v){
    		//如果点击的事件是button1按钮，就对该事件进行处理
	    		if(v==button1){
	    		//获得编辑框中的内容
	    		String number=edittext.getText().toString();
	    		//创建一个意图
	    		Intent intent=new Intent();
	    		//设置匹配过滤器激活Activity
	    		intent.setAction("android.intent.action.CALL");
	    		intent.setData(Uri.parse("tel:"+number));
	    		//通过意图激活Activity,方法内部会自动对Intent添加类别
	    		startActivity(intent);
    		}
	    	//如果点击的事件是button2按钮，就对该事件进行处理
	    		if(v==button2){
	    			//得到电话号码和内容框中的内容
	    			String number=numbertext.getText().toString();
	    			String content=contenttext.getText().toString();
	    			//创建一个消息管理器对象
	    			android.telephony.SmsManager manager=android.telephony.SmsManager.getDefault();
	    			//短信的字数限制，把很长的短信分成几条短信发送
	    			ArrayList<String> texts=manager.divideMessage(content);
	    			//对arrayList进行一次遍历
	    			for(String text:texts){
	    				manager.sendTextMessage(number, null, text, null,null);
	    			}
	    			Toast.makeText(getApplicationContext(), R.string.sucess, Toast.LENGTH_SHORT).show();
	    			contenttext.setText(null);
	    		}
    	}
    }
}
