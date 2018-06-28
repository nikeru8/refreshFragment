package com.hello.kaiser.refreshfragment;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 *
 */
public class OneFragment extends Fragment {

    //廣播參數
    protected LocalBroadcastManager broadcastManager;
    private Activity activity;

    private Button button;
    private TextView text_change;


    public static OneFragment newInstance() {
        Bundle args = new Bundle();

        OneFragment fragment = new OneFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public OneFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof Activity)
            activity = (Activity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        registerReceiver();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_one, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        button = (Button) view.findViewById(R.id.button);
        text_change = (TextView) view.findViewById(R.id.text_change);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshData();
            }
        });

    }

    //接收廣播 （信箱）
    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(final Context context, Intent intent) {
            //從美國寄來的信，可以是任何東西
            String action = intent.getStringExtra("changeSomething");
            if ("changeText".equals(action)) {
                new Handler().post(new Runnable() {
                    @Override
                    public void run() {
                        text_change.setText("你按了我～！！我刷新囉！");
                    }
                });
            }
        }
    };

    @Override
    public void onDetach() {
        super.onDetach();
        broadcastManager.unregisterReceiver(mReceiver);
    }

    //呼叫刷新畫面郵差）
    private void refreshData() {
        //指定要刷新的頁面給intent （
        Intent intent = new Intent("changeFragment");
        //要帶過去的參數
        intent.putExtra("changeSomething", "changeText");
        LocalBroadcastManager.getInstance(activity).sendBroadcast(intent);
    }

    //註冊廣播 同時刷新兩個fragment
    private void registerReceiver() {
        broadcastManager = LocalBroadcastManager.getInstance(getActivity());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("changeFragment");
        broadcastManager.registerReceiver(mReceiver, intentFilter);
    }
}
