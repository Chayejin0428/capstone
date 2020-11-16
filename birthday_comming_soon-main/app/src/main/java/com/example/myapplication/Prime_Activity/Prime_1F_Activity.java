package com.example.myapplication.Prime_Activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.github.chrisbanes.photoview.PhotoViewAttacher;

public class Prime_1F_Activity extends AppCompatActivity {

    int flag = 0;
    TextView textview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prime_1f);
        textview=findViewById(R.id.textView6);
        moving_101();
        moving_102();
        moving_103();
        moving_104();
        moving_105();
        moving_106();
        moving_107();

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    // 101호에서 최단거리 이동
    private void moving_101() {
        final Button b = (Button) findViewById(R.id.p_101);
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    {
                        ObjectAnimator animation1 = ObjectAnimator.ofFloat(iv, "translationX", 1380, 1380, 1620); //x 좌표
                        animation1.setDuration(1500);
                        animation1.start();

                        ObjectAnimator animation3 = ObjectAnimator.ofFloat(iv, "translationY", 470, 390, 390); //y 좌표
                        animation3.setDuration(1500);
                        animation3.start();
                    }
                    flag = 1;
                } else {
                    {
                        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "translationX", 0); //기존의 이미지 위치로 복귀
                        animation.setDuration(500);
                        animation.start();

                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(iv, "translationY", 0);
                        animation2.setDuration(500);
                        animation2.start();
                    }
                    flag = 0;
                }

            }
        });
    }

    // 102호에서 최단거리 이동
    private void moving_102() {
        final Button b = (Button) findViewById(R.id.p_102);
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    {
                        ObjectAnimator animation1 = ObjectAnimator.ofFloat(iv, "translationX",  1200,1380, 1380, 1620); //x 좌표
                        animation1.setDuration(1600);
                        animation1.start();

                        ObjectAnimator animation3 = ObjectAnimator.ofFloat(iv, "translationY",  470, 470, 390, 390); //y 좌표
                        animation3.setDuration(1600);
                        animation3.start();
                    }
                    flag = 1;
                } else {
                    {
                        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "translationX", 0); //기존의 이미지 위치로 복귀
                        animation.setDuration(500);
                        animation.start();

                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(iv, "translationY", 0);
                        animation2.setDuration(500);
                        animation2.start();
                    }
                    flag = 0;
                }

            }
        });
    }

    // 103호에서 최단거리 이동
    private void moving_103() {
        final Button b = (Button) findViewById(R.id.p_103);
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    {
                        ObjectAnimator animation1 = ObjectAnimator.ofFloat(iv, "translationX",  920, 1380, 1380, 1620); //x 좌표
                        animation1.setDuration(1800);
                        animation1.start();

                        ObjectAnimator animation3 = ObjectAnimator.ofFloat(iv, "translationY",  470, 470, 390, 390); //y 좌표
                        animation3.setDuration(1800);
                        animation3.start();
                    }
                    flag = 1;
                } else {
                    {
                        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "translationX", 0); //기존의 이미지 위치로 복귀
                        animation.setDuration(500);
                        animation.start();

                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(iv, "translationY", 0);
                        animation2.setDuration(500);
                        animation2.start();
                    }
                    flag = 0;
                }

            }
        });
    }

    //104호에서 최단거리 이동
    private void moving_104() {
        final Button button = (Button) findViewById(R.id.p_104);
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    {
                        ObjectAnimator animation1 = ObjectAnimator.ofFloat(iv, "translationX",  720, 720, 1380, 1380, 1620);
                        animation1.setDuration(2100);
                        animation1.start();

                        ObjectAnimator animation3 = ObjectAnimator.ofFloat(iv, "translationY",  620, 470, 470, 390, 390);
                        animation3.setDuration(2100);
                        animation3.start();
                    }
                    flag = 1;
                } else {
                    {
                        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "translationX", 0);
                        animation.setDuration(500);
                        animation.start();

                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(iv, "translationY", 0);
                        animation2.setDuration(500);
                        animation2.start();
                    }
                    flag = 0;
                }

            }
        });
    }

    //105호에서 최단거리 이동
    private void moving_105() {
        final Button button = (Button) findViewById(R.id.p_105);
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    {
                        ObjectAnimator animation1 = ObjectAnimator.ofFloat(iv, "translationX", 720,1380, 1380, 1620);
                        animation1.setDuration(1900);
                        animation1.start();

                        ObjectAnimator animation3 = ObjectAnimator.ofFloat(iv, "translationY",  470,470, 390, 390);
                        animation3.setDuration(1900);
                        animation3.start();
                    }
                    flag = 1;
                } else {
                    {
                        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "translationX", 0);
                        animation.setDuration(500);
                        animation.start();

                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(iv, "translationY", 0);
                        animation2.setDuration(500);
                        animation2.start();
                    }
                    flag = 0;
                }

            }
        });
    }

    //106호에서 최단거리 이동
    private void moving_106() {
        final Button button = (Button) findViewById(R.id.p_106);
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    {
                        ObjectAnimator animation1 = ObjectAnimator.ofFloat(iv, "translationX", 720,1380, 1380, 1620);
                        animation1.setDuration(1900);
                        animation1.start();

                        ObjectAnimator animation3 = ObjectAnimator.ofFloat(iv, "translationY", 470, 470, 390, 390);
                        animation3.setDuration(1900);
                        animation3.start();
                    }
                    flag = 1;
                } else {
                    {
                        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "translationX", 0);
                        animation.setDuration(500);
                        animation.start();

                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(iv, "translationY", 0);
                        animation2.setDuration(500);
                        animation2.start();
                    }
                    flag = 0;
                }

            }
        });
    }

    //107호에서 최단거리 이동
    private void moving_107() {
        final Button button = (Button) findViewById(R.id.p_107);
        final ImageView iv = (ImageView) findViewById(R.id.imageView1);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (flag == 0) {
                    {
                        ObjectAnimator animation1 = ObjectAnimator.ofFloat(iv, "translationX", 895, 1380, 1380, 1620);
                        animation1.setDuration(1800);
                        animation1.start();

                        ObjectAnimator animation3 = ObjectAnimator.ofFloat(iv, "translationY", 470, 470, 390, 390);
                        animation3.setDuration(1800);
                        animation3.start();
                    }
                    flag = 1;
                } else {
                    {
                        ObjectAnimator animation = ObjectAnimator.ofFloat(iv, "translationX", 0);
                        animation.setDuration(500);
                        animation.start();

                        ObjectAnimator animation2 = ObjectAnimator.ofFloat(iv, "translationY", 0);
                        animation2.setDuration(500);
                        animation2.start();
                    }
                    flag = 0;
                }

            }
        });
    }
}