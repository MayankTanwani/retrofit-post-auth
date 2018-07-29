package com.example.mayank.shoocal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    public static final String ResponseType = "API_RESPONSE_CODE";
    public int resType=0;
    public TextView tvHeading;
    public TextView tvDetail;
    public ImageView ivImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvHeading = findViewById(R.id.textHeading);
        tvDetail = findViewById(R.id.textDetail);
        ivImage = findViewById(R.id.imageView);
        Intent i = getIntent();
        if(i.hasExtra(ResponseType)){
            resType = i.getIntExtra(ResponseType,0);
        }
        if(resType==0){
            ivImage.setImageResource(R.drawable.checkcircle);
            tvHeading.setText(R.string.success);
            tvDetail.setText(R.string.requested_for_demo);
        }
        else if(resType==-1){
            ivImage.setImageResource(R.drawable.information);
            tvHeading.setText(R.string.already_requested);
            tvDetail.setText(R.string.please_wait);
        }
    }
}
