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

public class ProverbsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdapterProverbs adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public ProverbsFragment() {
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
        View view = inflater.inflate(R.layout.fragment_proverbs ,container, false);
        recyclerView = view.findViewById(R.id.dummyfrag_scrollableview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        recyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        getData();
        return view;
    }

    private void getData() {
        ApiClient.GET post = new ApiClient.GET(getActivity());
        post.setURL(BaseActivity.BASE_URL+"type=data");
        post.execute();
        post.setListenerCallService(new CallServiceListener() {
            @Override
            public void ResultData(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                setView(data);
            }

            @Override
            public void ResultError(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                ((MainActivity) getActivity()).hideProgressDialog();
                ((MainActivity) getActivity()).dialogResultError2();
            }

            @Override
            public void ResultNull(String data) {
                mSwipeRefreshLayout.setRefreshing(false);
                ((MainActivity) getActivity()).hideProgressDialog();
                ((MainActivity) getActivity()).dialogResultNull();
            }
        });
    }

    private void setView(String json) {
        Gson gson = new Gson();
        Type collectionType = new TypeToken<Collection<ProverbsModel>>() {}.getType();
        Collection<ProverbsModel> enums = gson.fromJson(json, collectionType);
        final ArrayList<ProverbsModel> posts = new ArrayList<ProverbsModel>(enums);

        adapter = new AdapterProverbs(getActivity(), posts);
        recyclerView.setAdapter(adapter);

        adapter.SetOnItemClickListener(new AdapterProverbs.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                int ID =Integer.parseInt(posts.get(position).getIdproverbs());
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("name", posts.get(position).getProverbs());
                intent.putExtra("image", posts.get(position).getPicture());
                intent.putExtra("detail", posts.get(position).getMean());
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        });

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getData();
            }
        });
        ((MainActivity) getActivity()).hideProgressDialog();
    }
}
