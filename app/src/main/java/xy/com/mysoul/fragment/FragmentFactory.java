package xy.com.mysoul.fragment;

import java.util.HashMap;

import xy.com.mysoul.base.BaseFragment;

/**
 * 创建fragment
 */
public class FragmentFactory {

    // 不需要每一次都创建fragment 所以将每一页的Fragment对象进行缓存
    private static HashMap<Integer, BaseFragment> mSavedFragment = new HashMap<Integer, BaseFragment>();

    public static BaseFragment getFragment(int position) {
        BaseFragment fragment = mSavedFragment.get(position);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new FirstFragment();
                    break;
                case 1:
                    fragment = new SecondFragment();
                    break;
                case 2:
                    fragment = new ThirdFragment();
                    break;
                case 3:
                    fragment = new FourthFragment();
                    break;
                case 4:
                    fragment = new FifthFragment();
                    break;
            }
            mSavedFragment.put(position, fragment);
        }
        return fragment;
    }

    public static int getFragmentCount() {
        return mSavedFragment.size();
    }

    public static HashMap<Integer, BaseFragment> getFragments() {
        return mSavedFragment;
    }
}