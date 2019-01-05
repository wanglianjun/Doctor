package com.alex.doctor;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.alex.doctor.base.BaseActivity;
import com.alex.doctor.databinding.ActivityLoginBinding;
import com.alex.doctor.util.Utils;

/**
 * 登录页面，点击登录按钮进入主页
 * Created by SmileXie on 2017/6/9.
 */

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Utils.setStatusTextColor(true, LoginActivity.this);
        setRightTitle(getString(R.string.register));
        setLeftTitle(getString(R.string.close));
        hideBackBtn();
    }

    public void loginBtnClick(View view) {
        startActivity(new Intent(LoginActivity.this, ActivityMain.class));
    }

    @Override
    protected void leftClick() {
        finish();
    }

}
