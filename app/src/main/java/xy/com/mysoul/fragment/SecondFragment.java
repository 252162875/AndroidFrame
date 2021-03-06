package xy.com.mysoul.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import xy.com.mysoul.base.BaseFragment;
import xy.com.mysoul.view.MainFragmentPage;

public class SecondFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public MainFragmentPage.ResultState fragmentLoadData() {

        return MainFragmentPage.ResultState.ERROR;
    }

    @Override
    public View fragmentCreateSuccessView() {
        return null;
    }
}