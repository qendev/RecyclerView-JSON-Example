package com.example.recyclerviewjsonexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.recyclerviewjsonexample.MainActivity.EXTRA_CREATOR;
import static com.example.recyclerviewjsonexample.MainActivity.EXTRA_LIKES;
import static com.example.recyclerviewjsonexample.MainActivity.EXTRA_URL;

public class DetailActivity extends AppCompatActivity {
    //Here we will catch the necessary intent with the values out of the EXTRAS and set the Detail Activity views to those values.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent=getIntent();
        String imageUrl=intent.getStringExtra(EXTRA_URL);
        String creatorName=intent.getStringExtra(EXTRA_CREATOR);
        int likeCount=intent.getIntExtra(EXTRA_LIKES,0);

        ImageView imageView=findViewById(R.id.image_view_detail);
        TextView textviewCreator=findViewById(R.id.text_view_creator_detail);
        TextView textViewLikes=findViewById(R.id.text_view_likes_detail);

        Picasso.with(this).load(imageUrl).fit().centerInside().into(imageView);
        textviewCreator.setText(creatorName);
        textViewLikes.setText("Likes: " + likeCount);
    }
}
