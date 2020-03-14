package com.yoyozhangh.github.todayinfomation.main.fragment.hangzhou;


import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.yoyozhangh.github.todayinfomation.R;
import com.yoyozhangh.github.todayinfomation.base.BaseFragment;
import com.yoyozhangh.github.todayinfomation.base.ViewInject;
import com.yoyozhangh.github.todayinfomation.main.fragment.shenzhen.ShenzhenFragment;

import java.util.ArrayList;

import butterknife.BindView;


@ViewInject(mainlayoutid = R.layout.fragment_hangzhou)
public class HangzhouFragment extends BaseFragment {

    @BindView(R.id.tl_tablayout)
    TabLayout tlTablayout;
    @BindView(R.id.vp_viewpager)
    ViewPager vpViewpager;

//    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    public void afterBindView() {
//
//        for (int i = 0; i < 5; i++) {
//            arrayList.add("深圳");
//        }

        tlTablayout.setupWithViewPager(vpViewpager);

//        vpViewpager.setAdapter(new PagerAdapter() {
//            @Override
//            public int getCount() {
//                return 0;
//            }
//
//            @Override
//            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
//                return false;
//            }
//        });

        vpViewpager.setAdapter(new FragmentStatePagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                Log.e("HangzhouFragment", "getItem: position=" + position);

                //根据不同的position 分别new 不同的fragment
//                switch (position){
//                    case 
//                }

                return new ShenzhenFragment();
            }

            @Override
            public int getCount() {
//                return arrayList.size();
                return 5;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
//                return arrayList.get(position);
                return "深圳";
            }
        });

//        vpViewpager.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                arrayList.add("深圳");
//                vpViewpager.getAdapter().notifyDataSetChanged();
//            }
//        },5000);
//        // 预加载多少条目到内存中
//        vpViewpager.setOffscreenPageLimit(0);

        //FragmentPagerAdapter 会走 onPause onDestroyView
        //FragmentStatePagerAdapter 会走 onPause onDestroyView onDestroy  onDetach
    }
}
