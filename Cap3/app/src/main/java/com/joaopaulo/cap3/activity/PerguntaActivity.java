package com.joaopaulo.cap3.activity;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.joaopaulo.cap3.R;

public class PerguntaActivity extends AppCompatActivity {
    private int jumpTime;


    private ProgressBar timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pergunta);
        timer = (ProgressBar) findViewById(R.id.progressBar);

        final int progressTime = 60000;

        final Thread t = new Thread(){

            @Override
            public void run(){


                jumpTime = 0;
                while(jumpTime < progressTime){
                    try {
                        sleep(1000);
                        jumpTime += 1;
                        timer.setProgress(jumpTime);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }

                }
            }


        };
        t.start();



    }

    @Override
    public void onPause(){
        super.onPause();
        jumpTime = 0;

    }


}
