package com.yzy.supercleanmaster.base;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;

import com.yzy.supercleanmaster.R;
import com.yzy.supercleanmaster.utils.FragmentArgs;

import java.lang.reflect.Method;

public class FragmentContainerActivity extends BaseSwipeBackActivity {

    /**
     * 启动一个界面
     *
     * @param activity
     * @param clazz
     */
    public static void launch(Activity activity, Class<? extends Fragment> clazz, FragmentArgs args) {
        Intent intent = new Intent(activity, FragmentContainerActivity.class);
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtra("args", args);
        activity.startActivity(intent);
    }

    public FragmentContainerActivity() {
        super();
    }

    public static void launchForResult(Fragment fragment, Class<? extends Fragment> clazz, FragmentArgs args,
                                       int requestCode) {
        if (fragment.getActivity() == null)
            return;
        Activity activity = fragment.getActivity();

        Intent intent = new Intent(activity, FragmentContainerActivity.class);
        intent.putExtra("className", clazz.getName());
        if (args != null)
            intent.putExtra("args", args);
        fragment.startActivityForResult(intent, requestCode);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String className = getIntent().getStringExtra("className");
        if (TextUtils.isEmpty(className)) {
            finish();
            return;
        }

        FragmentArgs values = (FragmentArgs) getIntent().getSerializableExtra("args");

        Fragment fragment = null;
        if (savedInstanceState == null) {
            try {
                Class clazz = Class.forName(className);
                fragment = (Fragment) clazz.newInstance();
                if (values != null) {
                    try {
                        Method method = clazz.getMethod("setArguments", new Class[]{Bundle.class});
                        method.invoke(fragment, FragmentArgs.transToBundle(values));
                    } catch (Exception e) {
//						e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                finish();
                return;
            }
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ui_fragment_container);


        if (fragment != null) {
            getFragmentManager().beginTransaction().add(R.id.fragmentContainer, fragment, className).commit();
        }

        if (getActionBar() != null)
            getActionBar().setDisplayShowHomeEnabled(false);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:

                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
