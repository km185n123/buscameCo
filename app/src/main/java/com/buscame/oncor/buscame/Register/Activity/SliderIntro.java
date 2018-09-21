package com.buscame.oncor.buscame.Register.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.buscame.oncor.buscame.DashBoard.Activity.DashBoard;
import com.buscame.oncor.buscame.R;
import com.buscame.oncor.buscame.Register.Fragment.AppIntroSampleSlider;
import com.github.paolorotolo.appintro.AppIntro;

public class SliderIntro extends AppIntro {


    // Please DO NOT override onCreate. Use init
    @Override
    public void init(Bundle savedInstanceState) {

        //adding the three slides for introduction app you can ad as many you needed
        addSlide(AppIntroSampleSlider.newInstance(R.layout.intro_welcome));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.intro_busca_mi_carro));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.intro_busca_mi_mascota));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.intro_busca_mi_moto));
        addSlide(AppIntroSampleSlider.newInstance(R.layout.intro_busca_mi_bici));

        // Show and Hide Skip and Done buttons
        showStatusBar(false);
        showSkipButton(false);

        // Turn vibration on and set intensity
        // You will need to add VIBRATE permission in Manifest file
        // setVibrate(true);
        // setVibrateIntensity(30);

        //Add animation to the intro slider
        setDepthAnimation();
    }

    @Override
    public void onSkipPressed() {
        // Do something here when users click or tap on Skip button.
        Toast.makeText(getApplicationContext(),
                "texto", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(getApplicationContext(), DashBoard.class);
        startActivity(i);
    }

    @Override
    public void onNextPressed() {
        // Do something here when users click or tap on Next button.
    }

    @Override
    public void onDonePressed() {
        // Do something here when users click or tap tap on Done button.
        finish();
    }

    @Override
    public void onSlideChanged() {
        // Do something here when slide is changed
    }
}