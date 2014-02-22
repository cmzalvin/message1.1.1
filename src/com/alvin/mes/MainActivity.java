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
        //ͨ��id���EditText
		edittext=(EditText)findViewById(R.id.mobile);
        //ͨ��id���button
        button1=(Button)this.findViewById(R.id.button1);
        //��������Ǵ��������������Ǹ��ڲ���
        button1.setOnClickListener(new ButtonClickListener());
        //�ҵ��ı������,��ť
        numbertext=(EditText)this.findViewById(R.id.number2);
        contenttext=(EditText)this.findViewById(R.id.content);
        button2=(Button)this.findViewById(R.id.button2);
        //����ť�ĵ���¼����ڲ���
        button2.setOnClickListener(new ButtonClickListener());
    }
    private final class ButtonClickListener implements View.OnClickListener{
    	//������д
    	public void onClick(View v){
    		//���������¼���button1��ť���ͶԸ��¼����д���
	    		if(v==button1){
	    		//��ñ༭���е�����
	    		String number=edittext.getText().toString();
	    		//����һ����ͼ
	    		Intent intent=new Intent();
	    		//����ƥ�����������Activity
	    		intent.setAction("android.intent.action.CALL");
	    		intent.setData(Uri.parse("tel:"+number));
	    		//ͨ����ͼ����Activity,�����ڲ����Զ���Intent������
	    		startActivity(intent);
    		}
	    	//���������¼���button2��ť���ͶԸ��¼����д���
	    		if(v==button2){
	    			//�õ��绰��������ݿ��е�����
	    			String number=numbertext.getText().toString();
	    			String content=contenttext.getText().toString();
	    			//����һ����Ϣ����������
	    			android.telephony.SmsManager manager=android.telephony.SmsManager.getDefault();
	    			//���ŵ��������ƣ��Ѻܳ��Ķ��ŷֳɼ������ŷ���
	    			ArrayList<String> texts=manager.divideMessage(content);
	    			//��arrayList����һ�α���
	    			for(String text:texts){
	    				manager.sendTextMessage(number, null, text, null,null);
	    			}
	    			Toast.makeText(getApplicationContext(), R.string.sucess, Toast.LENGTH_SHORT).show();
	    			contenttext.setText(null);
	    		}
    	}
    }
}
