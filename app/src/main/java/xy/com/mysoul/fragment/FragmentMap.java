package xy.com.mysoul.fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import xy.com.mysoul.base.BaseFragment;

/**
 * fragment集合管理类
 */
public class FragmentMap {

    private final HashMap<Integer, BaseFragment> mSavedFragment;
    private final ArrayList<Integer> keys;

    public FragmentMap() {
        mSavedFragment = new HashMap<Integer, BaseFragment>();
        keys = new ArrayList<>();
    }

    public void addFragment(int po, BaseFragment fragment) {
        if (fragment != null) {
            mSavedFragment.put(po, fragment);
            keys.add(po);
        }
    }

    public BaseFragment getFragment(int key) {
        return mSavedFragment.get(key);
    }

    public void showRightToTargt(int key, View view, FragmentManager manage) {
        FragmentTransaction transaction = manage.beginTransaction();
        BaseFragment fm = getFragment(key);
        if (fm.isVisible()) {
            return;
        }
        if (fm.isAdded()) {
            fm.onResume();
        } else {
            transaction.add(view.getId(), fm);
        }
        for (int i = 0; i < keys.size(); i++) {
            Integer tempKey = keys.get(i);
            BaseFragment fragment = mSavedFragment.get(tempKey);
            if (fragment != null) {
                FragmentTransaction ft = manage.beginTransaction();
                if (key == tempKey) {
                    ft.show(fragment);
                } else {
                    ft.hide(fragment);
                }
                ft.commit();
            }
        }
        transaction.commit();
    }

}