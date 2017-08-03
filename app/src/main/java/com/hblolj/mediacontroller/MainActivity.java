package com.hblolj.mediacontroller;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.hblolj.mediacontroller.Fragment.AirConditionerFragment;
import com.hblolj.mediacontroller.Fragment.DimingLightFragment;
import com.hblolj.mediacontroller.Fragment.PlayerFragment;
import com.hblolj.mediacontroller.Fragment.PowerAmplifierFragment;
import com.hblolj.mediacontroller.Fragment.ProjectorFragement;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.content)
    public LinearLayout content;
    @BindView(R.id.rl_dimingLight)
    public RelativeLayout dimingLight;
    @BindView(R.id.rl_projector)
    public RelativeLayout projector;
    @BindView(R.id.airConditioner)
    public RelativeLayout airConditioner;
    @BindView(R.id.powerAmplifier)
    public RelativeLayout powerAmplifier;
    @BindView(R.id.player)
    public RelativeLayout player;

    private DimingLightFragment dimingLightFragment;
    private ProjectorFragement projectorFragement;
    private AirConditionerFragment airConditionerFragment;
    private PowerAmplifierFragment powerAmplifierFragment;
    private PlayerFragment playerFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initEvents();
        setDefaultFragment();
    }

    //设置默认Fragment
    private void setDefaultFragment() {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        dimingLightFragment = new DimingLightFragment();
        transaction.replace(R.id.content, dimingLightFragment);
        transaction.commit();
    }

    private void initEvents() {
        dimingLight.setOnClickListener(this);
        projector.setOnClickListener(this);
        airConditioner.setOnClickListener(this);
        powerAmplifier.setOnClickListener(this);
        player.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //底部导航栏点击
        //1.去除旧状态、设置新状态
        //2.切换Fragment
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        switch (v.getId()){
            case R.id.rl_dimingLight:
                if (dimingLightFragment == null){
                    dimingLightFragment = new DimingLightFragment();
                }
                transaction.replace(R.id.content, dimingLightFragment);
                break;
            case R.id.rl_projector:
                if (projectorFragement == null){
                    projectorFragement = new ProjectorFragement();
                }
                transaction.replace(R.id.content, projectorFragement);
                break;
            case R.id.airConditioner:
                if (airConditionerFragment == null){
                    airConditionerFragment = new AirConditionerFragment();
                }
                transaction.replace(R.id.content, airConditionerFragment);
                break;
            case R.id.powerAmplifier:
                if (powerAmplifierFragment == null){
                    powerAmplifierFragment = new PowerAmplifierFragment();
                }
                transaction.replace(R.id.content, powerAmplifierFragment);
                break;
            case R.id.player:
                if (playerFragment == null){
                    playerFragment = new PlayerFragment();
                }
                transaction.replace(R.id.content, playerFragment);
                break;
        }
        transaction.commit();
    }
}
