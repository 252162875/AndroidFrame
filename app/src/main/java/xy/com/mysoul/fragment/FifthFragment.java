package xy.com.mysoul.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import xy.com.mysoul.R;
import xy.com.mysoul.base.BaseFragment;
import xy.com.mysoul.utils.UiUtils;
import xy.com.mysoul.view.MainFragmentPage;

public class FifthFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public MainFragmentPage.ResultState fragmentLoadData() {

        return MainFragmentPage.ResultState.EMPTY;
    }

    @Override
    public View fragmentCreateSuccessView() {
        TextView tv = new TextView(UiUtils.getContext());
        tv.setText(this.getClass().getSimpleName());
        tv.setTextColor(UiUtils.getColor(R.color.pinkline));
        return tv;
    }
}