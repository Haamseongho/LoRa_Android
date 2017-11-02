package com.example.haams.myapplication.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.haams.myapplication.R;
import com.example.haams.myapplication.fragments.MpicFragment1;
import com.example.haams.myapplication.fragments.MpicFragment2;
import com.example.haams.myapplication.fragments.MpicFragment3;

/**
 * Created by haams on 2017-10-26.
 */

public class MpicPagerAdapter extends PagerAdapter {

    LayoutInflater inflater;

    public MpicPagerAdapter(LayoutInflater inflater) {
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        view = inflater.inflate(R.layout.activity_child_bannerview, null);
        ImageView img = (ImageView) view.findViewById(R.id.mbanner1);
        Glide.with(view.getContext())
                .load(R.drawable.old_man + (position + 2)).centerCrop()
                .crossFade(1000)
                .skipMemoryCache(true)
                .into(img);
        container.addView(view);


        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
