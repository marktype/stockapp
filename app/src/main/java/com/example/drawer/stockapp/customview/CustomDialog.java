package com.example.drawer.stockapp.customview;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.drawer.stockapp.R;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class CustomDialog extends Dialog {
    private EditText editText;
    private Button positiveButton, negativeButton;
    private TextView title,message;

    public CustomDialog(Context context) {
        super(context, R.style.myDialog);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.activity_dialog_my_layout, null);
        title = (TextView) mView.findViewById(R.id.title);
        message = (TextView) mView.findViewById(R.id.message);   //消息
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);
        super.setContentView(mView);
    }

    public View getMessageText(){
        return message;
    }

    public void setMessageText(String text){
        message.setText(text);
    }
    public void setTitle(String text){
        title.setText(text);
    }
    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器 
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
    /**
     * 取消键监听器 
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }
}
