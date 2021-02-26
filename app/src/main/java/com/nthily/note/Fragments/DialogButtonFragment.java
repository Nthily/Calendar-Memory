package com.nthily.note.Fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.nthily.note.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DialogButtonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DialogButtonFragment extends DialogFragment implements View.OnClickListener {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String TITLE = "title";
    public static final String CONTENT = "content";

    private String mParam1;
    private String mParam2;
    private EditText title, content;
    private Button finish;
    private ImageButton time;

    public DialogButtonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DialogButtonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DialogButtonFragment newInstance(String param1, String param2) {
        DialogButtonFragment fragment = new DialogButtonFragment();
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

    private void findByid(View view) {
        title = view.findViewById(R.id.title);
        time = view.findViewById(R.id.time);
        content = view.findViewById(R.id.content);
        finish = view.findViewById(R.id.finish);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_button, container, false);
        findByid(view);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.finish) {
            String titleStr = title.getText().toString();
            String contentStr = content.getText().toString();
            Intent intent = new Intent();
            intent.putExtra(TITLE, titleStr);
            intent.putExtra(CONTENT, contentStr);

            getTargetFragment().onActivityResult(Calendar.REQUEST_CODE, Activity.RESULT_OK, intent);
        }
    }
}