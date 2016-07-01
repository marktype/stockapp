package com.example.drawer.stockapp.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.drawer.stockapp.R;
import com.example.drawer.stockapp.customview.MyListView;
import com.example.drawer.stockapp.listener.OnFragmentInteractionListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link MyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    public MyListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyListFragment newInstance(String param1, String param2) {
        MyListFragment fragment = new MyListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_list, container, false);
//        if (!mParam1.equals("调仓")){
//            TextView mTxt = (TextView) view.findViewById(R.id.test);
//            mTxt.setText(mParam1);
//        }else {
            initWeight(view);
//        }

        return view;
    }

        public void initWeight(View view){
            MyListView mList = (MyListView) view.findViewById(R.id.fragment_list);
            Log.d("tag", "initWeight: "+mList.requestFocus());

            ArrayAdapter adapter = new ArrayAdapter(getActivity(),R.layout.txt_item_layout,setData());
            mList.setAdapter(adapter);
        }


    public ArrayList<String> setData(){
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add("测试数据"+i+"--"+mParam1);
        }
        return list;
    }

}
