package xy.com.mysoul.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xy.com.mysoul.adapter.TestAdapter;
import xy.com.mysoul.base.BaseFragment;
import xy.com.mysoul.utils.UiUtils;
import xy.com.mysoul.view.MainFragmentPage;

public class FourthFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public MainFragmentPage.ResultState fragmentLoadData() {

        return MainFragmentPage.ResultState.SUCCESS;
    }

    @Override
    public View fragmentCreateSuccessView() {
//        TextView tv = new TextView(UiUtils.getContext());
//        tv.setText(this.getClass().getSimpleName());
//        tv.setTextColor(UiUtils.getColor(R.color.pinkline));
        RecyclerView recyclerView = new RecyclerView(UiUtils.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(UiUtils.getContext(), RecyclerView.VERTICAL, false));
        recyclerView.setAdapter(new TestAdapter());
        return recyclerView;
    }
}