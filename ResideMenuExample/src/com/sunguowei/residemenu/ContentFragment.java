package com.sunguowei.residemenu;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

@SuppressLint("ValidFragment")
public class ContentFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_content, null);
        view.findViewById(R.id.btn_left).setOnClickListener((MainActivity)getActivity());
        view.findViewById(R.id.btn_right).setOnClickListener((MainActivity)getActivity());
        return view;
    }

}
