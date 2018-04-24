package com.picha.proverbs.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.picha.proverbs.DetailActivity;
import com.picha.proverbs.MainActivity;
import com.picha.proverbs.R;
import com.picha.proverbs.adapter.AdapterProverbs;
import com.picha.proverbs.helper.BaseActivity;
import com.picha.proverbs.model.ProverbsModel;
import com.picha.proverbs.okhttp.ApiClient;
import com.picha.proverbs.okhttp.CallServiceListener;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;

public class MiniGameFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterProverbs adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public MiniGameFragment() {
        // Required empty public constructor
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return AnimationUtils.loadAnimation(getActivity(),
                enter ? android.R.anim.fade_in : android.R.anim.fade_out);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mini_game ,container, false);

        view.findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).dialogTM("ขออภัย","ฟังก์ชันนี้ยังไม่เปิดให้บริการ");
            }
        });

        return view;
    }
}
