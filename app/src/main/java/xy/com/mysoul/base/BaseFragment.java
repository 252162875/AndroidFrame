package xy.com.mysoul.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import xy.com.mysoul.utils.UiUtils;
import xy.com.mysoul.view.MainFragmentPage;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        TextView tv = new TextView(UiUtils.getContext());
//        tv.setText(this.getClass().getSimpleName());
//        tv.setTextColor(UiUtils.getColor(R.color.pinkline));
        Toast.makeText(UiUtils.getContext(), this.getClass().getSimpleName() + "---onCreateView", Toast.LENGTH_SHORT).show();
        MainFragmentPage mainFragmentPage = new MainFragmentPage(UiUtils.getContext()) {

            @Override
            public View createSuccessView() {
                return fragmentCreateSuccessView();
            }
        };
        return mainFragmentPage;
    }

    // BaseFragment依然不清楚成功之后的布局是怎样，所以又把它转交出去，让子类实现
    public abstract View fragmentCreateSuccessView();
}