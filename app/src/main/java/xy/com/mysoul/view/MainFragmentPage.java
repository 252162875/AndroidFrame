package xy.com.mysoul.view;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
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

    public int mCurrentState = STATE_NONE;
    private View mErrorView;
    private View mLoadingView;
    private View mEmptyView;
    private View mSuccessView;
    private FrameLayout flMainfragmentContent;
    private SwipeRefreshLayout swipe_refresh;

    public MainFragmentPage(Context context) {
        super(context);
        initView();
    }


    private void initView() {
        this.setBackgroundColor(UiUtils.getColor(R.color.test));
        View root = UiUtils.inflateView(R.layout.layout_main_fragment);
        flMainfragmentContent = (FrameLayout) root.findViewById(R.id.fl_mainfragment_content);
        swipe_refresh = (SwipeRefreshLayout) root.findViewById(R.id.swipe_refresh);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadData();
            }
        });
        if (mErrorView == null) {
            mErrorView = createErrorView();
            flMainfragmentContent.addView(mErrorView);
        }
        if (mLoadingView == null) {
            mLoadingView = createLoadingView();
            flMainfragmentContent.addView(mLoadingView);
        }
        if (mEmptyView == null) {
            mEmptyView = createEmptyView();
            flMainfragmentContent.addView(mEmptyView);
        }

        showRightPage();
        addView(root);
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
                flMainfragmentContent.addView(mSuccessView);
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

    // 加载数据，这个方法是给别人调用的
    public void loadData() {
        if (mCurrentState != STATE_LOADING) {
            mCurrentState = STATE_NONE;
            showRightPage();
        }
        if (mCurrentState == STATE_NONE) {
            // 加载网络数据
            // 启动子线程
            new Thread(new Runnable() {

                @Override
                public void run() {

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    ResultState result = onLoad();
                    if (result != null) {
                        mCurrentState = result.state;
                        UiUtils.runOnUiThread(new Runnable() {

                            @Override
                            public void run() {
                                showRightPage();
                                if (swipe_refresh.isRefreshing()) {
                                    swipe_refresh.setRefreshing(false);
                                }
                            }
                        });

                    }
                }
            }).start();
        }
    }

    protected abstract ResultState onLoad();

    // 由于SuccessView都不一样，所以LoadingPage不能完成这项功能
    // 所以LoadingPage需要把创建SuccessView的工作叫给别人做
    public abstract View createSuccessView();

    private View createErrorView() {
        View view = UiUtils.inflateView(R.layout.layout_error);
        Button btnRetry = (Button) view.findViewById(R.id.btnRetry);
        btnRetry.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                loadData();
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

    // 1、class-enum
    // 2、创建对象的写法优化
    // 3、构造方法私有
    public enum ResultState {
        ERROR(STATE_ERROR), SUCCESS(STATE_SUCCESS), LOADING(STATE_LOADING), EMPTY(
                STATE_EMPTY);
        int state;

        private ResultState(int state) {
            this.state = state;
        }
        // 通过控制对象的数量，达到表示某一个状态的效果

    }
}