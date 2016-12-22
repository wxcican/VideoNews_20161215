package com.fuicuiedu.idedemo.videonews_20161215.ui.likes;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.fuicuiedu.idedemo.videonews_20161215.R;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.BombClient;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.UserApi;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.result.ErrorResult;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.result.UserResult;
import com.fuicuiedu.idedemo.videonews_20161215.commons.ToastUtils;
import com.google.gson.Gson;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gqq on 2016/12/7.
 */

public class LoginFragment extends DialogFragment {

    private Unbinder mUnbinder;

    @BindView(R.id.etUsername)
    EditText mEtUsername;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //无标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_login, container, false);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick(R.id.btnLogin)
    public void onClick() {
        final String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();
        //用户名密码能不为空
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
            ToastUtils.showShort(R.string.username_or_password_can_not_be_null);
            return;
        }
        //显示进度条
        mBtnLogin.setVisibility(View.GONE);

        //登录网络请求
        UserApi userApi = BombClient.getInstance().getUserApi();
        Call<UserResult> call = userApi.login(username,password);
        call.enqueue(new Callback<UserResult>() {
            @Override
            public void onResponse(Call<UserResult> call, Response<UserResult> response) {
                //隐藏进度条
                mBtnLogin.setVisibility(View.VISIBLE);
                //登录未成功
                if (!response.isSuccessful()){
                    try {
                        String error = response.errorBody().string();
                        ErrorResult errorResult = new Gson().fromJson(error,ErrorResult.class);
                        ToastUtils.showShort(errorResult.getError());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return;
                }
                //登录成功
                UserResult result = response.body();
                listener.loginSuccess(username,result.getObjectId());
                ToastUtils.showShort(R.string.login_success);
            }

            @Override
            public void onFailure(Call<UserResult> call, Throwable t) {
                //隐藏进度条
                mBtnLogin.setVisibility(View.VISIBLE);
                ToastUtils.showShort(t.getMessage());
            }
        });
        
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mUnbinder.unbind();
    }

    //当登录成功之后触发的方法
    public interface OnLoginSuccessListener {
        /**
         * 当登录成功时，将来调用
         */
        void loginSuccess(String username, String objectId);
    }

    private OnLoginSuccessListener listener;

    public void setListener(@NonNull OnLoginSuccessListener listener) {
        this.listener = listener;
    }
}
