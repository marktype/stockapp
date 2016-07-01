package com.example.drawer.stockapp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.drawer.stockapp.R;

import java.util.ArrayList;

public class SetupZuHeActivity extends BascActivity implements AdapterView.OnItemSelectedListener{
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


        initSpinnerData();


        mBackimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mSpinner.setOnItemSelectedListener(this);
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
}
