package com.example.drawer.stockapp.fragment;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.activity.AttentionActivity;
import com.example.drawer.stockapp.utils.ManagerUtil;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 *
 * create an instance of this fragment.
 */
public class MyFragment extends Fragment implements View.OnClickListener{
        private View mView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_my, container, false);
        initWight();
        return mView;
    }

    public void initWight(){
        CircleImageView circleImageView = (CircleImageView) mView.findViewById(R.id.user_head);
        Picasso.with(getActivity()).load(R.mipmap.ic_launcher).into(circleImageView);

        TextView mFenSi = (TextView) mView.findViewById(R.id.fensi_num_txt);
        mFenSi.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));   //设置字体风格
        TextView mFoucs = (TextView) mView.findViewById(R.id.foucs_txt);
        mFoucs.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));
        TextView mCollect = (TextView) mView.findViewById(R.id.collect_num_txt);
        mCollect.setTypeface(Typeface.createFromAsset(getActivity().getAssets(),"fonts/DIN Medium.ttf"));

        LinearLayout mAttention = (LinearLayout) mView.findViewById(R.id.attention_lin);

        mAttention.setOnClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        SystemBarTintManager tintManager = ManagerUtil.newInstance(getActivity());
        ManagerUtil.setStataBarColorBlack(getActivity(),tintManager);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.attention_lin:
                Intent intent = new Intent(getContext(), AttentionActivity.class);
                startActivity(intent);
                break;
        }
    }
}
