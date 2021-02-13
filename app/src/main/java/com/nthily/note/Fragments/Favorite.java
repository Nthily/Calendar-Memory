package com.nthily.note.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nthily.note.Utilities.HitokoRetriever;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.nthily.note.R;
/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Favorite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favorite extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public TextView hitoko_in_frag;
    public SmartRefreshLayout refreshLayout;

    private String mParam1;
    private String mParam2;

    public View view;

    public Favorite() {
        // Required empty public constructor
    }
    public static Favorite newInstance(String param1, String param2) {
        Favorite fragment = new Favorite();
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
            setArguments(new Bundle());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        hitoko_in_frag = view.findViewById(R.id.hitoko);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        refreshLayout.setHeaderHeight(100);


        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshlayout) {
                new HitokoRetriever.checkConnection(getActivity(), hitoko_in_frag).execute("https://v1.hitokoto.cn/");
                refreshlayout.finishRefresh(1500);
            }
        });

        return view;
    }
}