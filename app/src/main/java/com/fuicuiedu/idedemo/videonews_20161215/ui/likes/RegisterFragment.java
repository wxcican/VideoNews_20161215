package com.fuicuiedu.idedemo.videonews_20161215.ui.likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fuicuiedu.idedemo.videonews_20161215.R;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.BombClient;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.UserApi;
import com.fuicuiedu.idedemo.videonews_20161215.bombapi.entity.UserEntity;
import com.fuicuiedu.idedemo.videonews_20161215.commons.ToastUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class RegisterFragment extends DialogFragment{

    @BindView(R.id.etUsername)
    EditText mEtUsername;
    @BindView(R.id.etPassword)
    EditText mEtPassword;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //取消标题栏
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = inflater.inflate(R.layout.dialog_register,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @OnClick(R.id.btnRegister)
    public void onClick(){
        String username = mEtUsername.getText().toString();
        String password = mEtPassword.getText().toString();

        //用户名和密码不能为空
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            ToastUtils.showShort(R.string.username_or_password_can_not_be_null);
            return;
        }

        //显示进度条
        mBtnRegister.setVisibility(View.GONE);

        // TODO: 2016/12/21 0021 网络模块，注册请求
        UserApi userApi = BombClient.getInstance().getUserApi();

        UserEntity userEntity = new UserEntity(username,password);

        Call<ResponseBody> call = userApi.register(userEntity);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    //当注册成功会触发的方法
    public interface OnRegisterSuccessListener{
        /** 当注册成功时，来调用*/
        void registerSuccess();
    }

    private OnRegisterSuccessListener listener;

    public void setListener(OnRegisterSuccessListener listener){
        this.listener = listener;
    }


}
