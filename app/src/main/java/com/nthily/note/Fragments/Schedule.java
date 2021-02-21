package com.nthily.note.Fragments;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import com.nthily.note.R;
import com.nthily.note.Utilities.Utils;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Schedule#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Schedule extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean flag = false;

    public ScrollView scrollView;
    public LinearLayout list_linear;
    public LinearLayout text_ui;
    public ConstraintLayout card_layout;
    public ImageButton expand;

    public Schedule() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Cal_List.
     */
    // TODO: Rename and change types and number of parameters
    public static Schedule newInstance(String param1, String param2) {
        Schedule fragment = new Schedule();
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
        View view = inflater.inflate(R.layout.fragment_cal__list, container, false);
        scrollView = view.findViewById(R.id.scrollview);
        list_linear = view.findViewById(R.id.List_linearlayout);
        text_ui = view.findViewById(R.id.text_ui);
        expand =  view.findViewById(R.id.expand);
        card_layout = view.findViewById(R.id.card_layout);
        text_ui.setVisibility(View.GONE);

        expand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!flag) {
                    flag = true;
                    Utils.expand(text_ui);
                    expand.setImageResource(R.drawable.ic_close);
                    Utils.setShowAnimation(expand, 800);
                } else {
                    flag = false;
                    Utils.collapse(text_ui);
                    expand.setImageResource(R.drawable.ic_expand_more_black_18dp);
                    Utils.setShowAnimation(expand, 800);
                }
            }
        });
        return view;
    }


}