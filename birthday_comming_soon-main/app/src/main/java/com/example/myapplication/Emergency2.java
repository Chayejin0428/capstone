package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Emergency2 extends AppCompatActivity {

    ImageView mainImageView2;
    TextView title2, description_1_2;

    String data1_2, data2_2;

    int myImage2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.emergency2);

        mainImageView2 = findViewById(R.id.imageView2_2);
        title2 = findViewById(R.id.textView3_2);
        description_1_2 = findViewById(R.id.textView4_2);
        description_1_2.setMovementMethod(new ScrollingMovementMethod());



        getData();
        setData();
    }


    private void getData(){
        if(getIntent().hasExtra("images2") && getIntent().hasExtra("description_1_2")&&
                getIntent().hasExtra("title2"))
        {
            data1_2 = getIntent().getStringExtra("title2");
            data2_2 = getIntent().getStringExtra("description_1_2");

            myImage2 = getIntent().getIntExtra("images2", 1);

        } else{
            Toast.makeText(this,"No data", Toast.LENGTH_SHORT).show();
        }


    }

    private void setData() {
        title2.setText(data1_2);
        description_1_2.setText(data2_2);

        mainImageView2.setImageResource(myImage2);
    }
}
