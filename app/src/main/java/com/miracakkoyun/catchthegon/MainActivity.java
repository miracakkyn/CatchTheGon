package com.miracakkoyun.catchthegon;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    TextView score;
    ImageView imageView;
    int score2=0;
    Button button;
    boolean oyun;
    private Handler handler = new Handler();
    private Runnable runnable;



    Random random=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        startRandomPositioning();
        textView=findViewById(R.id.textView);
        score=findViewById(R.id.score);
        button=findViewById(R.id.button);
        oyun=false;
    }
    public void baslat(View view){
        button.setVisibility(View.GONE);
        oyun=true;
        new CountDownTimer(30000,500){

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {

            }
        }.start();
        new CountDownTimer(30000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                textView.setText("Time: "+String.valueOf(millisUntilFinished/1000));
            }

            @Override
            public void onFinish() {
                textView.setText("Time: 0");
                button.setVisibility(View.VISIBLE);
                oyun=false;
            }
        }.start();
    }
    public void imageView(View view){
        if(oyun==true) {
            score2++;
            score.setText("Score: " + score2);
        }
    }
    private void startRandomPositioning() {
        runnable = new Runnable() {
            @Override
            public void run() {
                int maxX = getWindow().getDecorView().getWidth() - imageView.getWidth();
                int maxY = getWindow().getDecorView().getHeight() - imageView.getHeight();

                int x = random.nextInt(maxX);
                int y = random.nextInt(maxY);

                imageView.setX(x);
                imageView.setY(y);

                handler.postDelayed(this, 500);
            }
        };
        handler.post(runnable);
    }
}