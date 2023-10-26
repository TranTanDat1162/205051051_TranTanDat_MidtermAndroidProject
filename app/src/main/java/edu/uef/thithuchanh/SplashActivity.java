package edu.uef.thithuchanh;


import androidx.appcompat.app.AppCompatActivity;


import android.annotation.SuppressLint;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;


public class SplashActivity extends AppCompatActivity {


    private static int DELAY_TIME = 4000;


    Animation topAnim, bottomAnim;
    ImageView imageView;
    TextView app_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);


        imageView = findViewById(R.id.imageView);
        app_name = findViewById(R.id.textView);


        imageView.setAnimation(topAnim);
        app_name.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent i = new Intent(SplashActivity.this,MainActivity.class);
                Pair[] pairs = new Pair[2];
                pairs[0] = new Pair(imageView, "splash_image");
                pairs[1] = new Pair(imageView, "splash_text");


                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,pairs);
                startActivity(i,options.toBundle());
            }
        },DELAY_TIME);
    }
}
