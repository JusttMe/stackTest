package com.renameit.stacktest;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private FrameLayout frameLayout;
    private Map<String, Fragment> stack = new ArrayMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frameLayout = (FrameLayout) findViewById(R.id.container);
    }

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.btn_1:

                Fragment1 fragment1 = new Fragment1();
                addFragment(fragment1, fragment1.TAG);
                break;
            case R.id.btn_2:
                Fragment2 fragment2 = new Fragment2();
                addFragment(fragment2, fragment2.TAG);

                break;
            case R.id.btn_3:
                Fragment3 fragment3 = new Fragment3();
                addFragment(fragment3, fragment3.TAG);

                break;
            case R.id.btn_4:
                Fragment4 fragment4 = new Fragment4();
                addFragment(fragment4, fragment4.TAG);

                break;
            case R.id.btn_5:
                Fragment5 fragment5 = new Fragment5();
                addFragment(fragment5, fragment5.TAG);

                break;
            case R.id.btn_6:
                Fragment6 fragment6 = new Fragment6();
                addFragment(fragment6, fragment6.TAG);

                break;
        }
        Log.i("TAG", "onBtnClick: " + view.getId());
    }

    private void addFragment(Fragment fragment, String tag) {
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        for (Map.Entry<String, Fragment> entry : stack.entrySet()) {
            String fragmentTag = entry.getKey();
            Fragment fragmentValue = entry.getValue();
            if (fragmentTag.equals(tag)) {
                supportFragmentManager.beginTransaction()
                        .replace(R.id.container, fragmentValue, fragmentTag)
                        .commit();
                stack.remove(entry.getKey());
                stack.put(fragmentTag, fragmentValue);
                return;
            }
        }

        supportFragmentManager.beginTransaction()
                .add(R.id.container, fragment, tag)
                .commit();
        stack.put(tag, fragment);

    }

    @Override
    public void onBackPressed() {

        int last = stack.entrySet().size() - 1;
        int prelast = stack.entrySet().size() - 1;
        int position = 0;
        for (Map.Entry<String, Fragment> entry : stack.entrySet()) {
            if (position == prelast) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, entry.getValue(), entry.getKey())
                        .commit();
            }
            if (position == last) {
                stack.remove(entry.getKey());
            }

            position++;
        }
    }
}
