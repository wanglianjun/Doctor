package com.alex.doctor.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.alex.doctor.R;

import com.alex.doctor.UserInfoActivity;
import com.alex.doctor.base.BaseFragment;
import com.alex.doctor.databinding.FragmentMineBinding;

/**
 * 个人中心
 * Created by SmileXie on 2017/6/29.
 */

public class MineFragment extends BaseFragment<FragmentMineBinding> {

    @Override
    public int setContent() {
        return R.layout.fragment_mine;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        bindingView.avatarIv.setOnClickListener(v -> startActivity(UserInfoActivity.class));
    }
}
