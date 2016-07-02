package com.example.drawer.stockapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.drawer.stockapp.R;

import java.util.ArrayList;

public class SetupZuHeActivity extends BascActivity implements AdapterView.OnItemSelectedListener,View.OnClickListener{
    private Spinner mSpinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_zu_he);

        initWight();
    }

    public void initWight(){
        ImageView mBackimg = (ImageView) findViewById(R.id.back_img);
        mSpinner = (Spinner) findViewById(R.id.stock_spinner);
        TextView mAddTxt = (TextView) findViewById(R.id.add_stock_txt);
        TextView mSrueBuild = (TextView) findViewById(R.id.setup_sure_txt);

        initSpinnerData();


        mBackimg.setOnClickListener(this);
        mSpinner.setOnItemSelectedListener(this);
        mAddTxt.setOnClickListener(this);
        mSrueBuild.setOnClickListener(this);
    }

    /**
     * 初始化spinner数据
     */
    public void initSpinnerData(){
        ArrayList<String> arrayList = new ArrayList<>();
        for (int i = 0;i<5;i++){
            arrayList.add("策略名称"+i);
        }
        ArrayAdapter adapter = new ArrayAdapter(this,R.layout.txt_item_layout,arrayList);
        mSpinner.setAdapter(adapter);

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_img:
                finish();
                break;
            case R.id.add_stock_txt:
                Intent intent = new Intent(this,SerchActivity.class);
                startActivity(intent);
                break;
            case R.id.setup_sure_txt:
                finish();
                break;
        }
    }
}
