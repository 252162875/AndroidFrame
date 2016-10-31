package xy.com.mysoul.view;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import xy.com.mysoul.R;
import xy.com.mysoul.utils.UiUtils;

public abstract class MainFragmentPage extends FrameLayout {
    public static final int STATE_NONE = 0;
    public static final int STATE_LOADING = 1;
    public static final int STATE_ERROR = 2;
    public static final int STATE_SUCCESS = 3;
    public static final int STATE_EMPTY = 4;

    public int mCurrentState = STATE_ERROR;

    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;
    private View mSuccessView;

    public MainFragmentPage(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        if (mErrorView == null) {
            mErrorView = createErrorView();
            addView(mErrorView);
        }
        if (mLoadingView == null) {
            mLoadingView = createLoadingView();
            addView(mLoadingView);
        }
        if (mEmptyView == null) {
            mEmptyView = createEmptyView();
            addView(mEmptyView);
        }

        showRightPage();
    }

    private void showRightPage() {
        // 三元表达式
        mLoadingView
                .setVisibility((mCurrentState == STATE_LOADING || mCurrentState == STATE_NONE) ? View.VISIBLE
                        : View.GONE);
        mErrorView.setVisibility(mCurrentState == STATE_ERROR ? View.VISIBLE
                : View.GONE);
        mEmptyView.setVisibility(mCurrentState == STATE_EMPTY ? View.VISIBLE
                : View.GONE);

        if (mSuccessView == null && mCurrentState == STATE_SUCCESS) {
            mSuccessView = createSuccessView();
            if (mSuccessView != null) {
                addView(mSuccessView);
            }

        }
        if (mCurrentState == STATE_SUCCESS) {
            if (mSuccessView != null) {
                mSuccessView.setVisibility(View.VISIBLE);
            }
        } else {
            if (mSuccessView != null) {
                mSuccessView.setVisibility(View.GONE);
            }
        }
    }

    // 由于SuccessView都不一样，所以LoadingPage不能完成这项功能
    // 所以LoadingPage需要把创建SuccessView的工作叫给别人做
    public abstract View createSuccessView();

    private View createErrorView() {
        // View view = View.inflate(context, resource, root)
        View view = UiUtils.inflateView(R.layout.layout_error);
        Button btnRetry = (Button) view.findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//                loadData();
                Toast.makeText(UiUtils.getContext(), "加载~~~", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    private View createLoadingView() {
        View view = UiUtils.inflateView(R.layout.layout_loading);
        return view;
    }

    private View createEmptyView() {
        View view = UiUtils.inflateView(R.layout.layout_empty);
        return view;
    }
}