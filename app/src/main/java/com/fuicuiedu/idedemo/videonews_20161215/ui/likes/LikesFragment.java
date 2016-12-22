package com.fuicuiedu.idedemo.videonews_20161215.ui.likes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.fuicuiedu.idedemo.videonews_20161215.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/12/21 0021.
 */

public class LikesFragment extends Fragment implements RegisterFragment.OnRegisterSuccessListener{

    @BindView(R.id.tvUsername)
    TextView mTvUsername;
    @BindView(R.id.btnRegister)
    Button mBtnRegister;
    @BindView(R.id.btnLogin)
    Button mBtnLogin;
    @BindView(R.id.btnLogout)
    Button mBtnLogout;
    @BindView(R.id.divider)
    View mDivider;

    private View view;

    private RegisterFragment mRegisterFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null){
            view = inflater.inflate(R.layout.fragment_likes,container,false);
            ButterKnife.bind(this,view);
            // TODO: 2016/12/21 0021 判断用户登录状态，更新UI
        }
        return view;
    }

    @OnClick({R.id.btnRegister,R.id.btnLogin,R.id.btnLogout})
    public void onClick(View view){
        switch (view.getId()){
            //注册
            case R.id.btnRegister:
                if (mRegisterFragment == null){
                    mRegisterFragment = new RegisterFragment();
                    //添加注册成功的监听
                    mRegisterFragment.setListener(this);
                }
                mRegisterFragment.show(getChildFragmentManager(),"Register Dialog");
                break;
            //登录
            case R.id.btnLogin:
                break;
            //退出登录
            case R.id.btnLogout:
                //用户下线
                break;
        }
    }

    //添加注册成功的监听
    @Override
    public void registerSuccess(String username, String objectId) {
        //关闭注册的对话框
        mRegisterFragment.dismiss();
        //用户上线
        userOnLine(username,objectId);
    }

    //用户上线
    private void userOnLine(String username,String objectId){
        //更新UI
        mBtnLogin.setVisibility(View.INVISIBLE);
        mBtnRegister.setVisibility(View.INVISIBLE);
        mBtnLogout.setVisibility(View.VISIBLE);
        mDivider.setVisibility(View.INVISIBLE);
        mTvUsername.setText(username);
        // TODO: 2016/12/22 0022 存储用户信息
        // TODO: 2016/12/22 0022 刷新收藏列表
    }
}
