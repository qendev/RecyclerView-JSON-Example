package com.example.recyclerviewjsonexample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {
    //create memeber variable
    private Context mContext;
    private ArrayList<ExampleItem>mExampleList;
    private OnItemClickListener mListener;


    public interface OnItemClickListener{
        //create method that will foward the clicks from the Adapter to the MainActivity.
        void onItemClick(int position);
    }
    //To set our Activity(mListener) to the listener.
    public void setOnItemClickListener(OnItemClickListener listener){
        mListener=listener;
    }

    //create a constructor for the adapter where we will pass the values to the adapter.
    public ExampleAdapter(Context context,ArrayList<ExampleItem>exampleList){
         mContext=context;
         mExampleList=exampleList;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(mContext).inflate(R.layout.example_item,viewGroup,false);
        return new ExampleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder exampleViewHolder, int i) {
        ExampleItem currentItem=mExampleList.get(i);

        String imageUrl=currentItem.getImageUrl();
        String creatorName=currentItem.getCreator();
        int likeCount=currentItem.getLIkeCount();

        //set the views of the card to the values
        exampleViewHolder.mTextViewCreator.setText(creatorName);
        exampleViewHolder.mTextViewLikes.setText("Like: " + likeCount);
        Picasso.with(mContext).load(imageUrl).fit().centerInside().into(exampleViewHolder.mImageView);

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }

    //first create the ViewHolder Class.
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;
        public TextView mTextViewCreator;
        public TextView mTextViewLikes;
        //Constructure matching Super.
        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView=itemView.findViewById(R.id.image_view);
            mTextViewCreator=itemView.findViewById(R.id.text_view_creator);
            mTextViewLikes=itemView.findViewById(R.id.text_view_likes);


            //A way to catch the clicks in this Adapter.
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mListener!=null){
                        int position=getAdapterPosition();
                        if(position!=RecyclerView.NO_POSITION){
                            mListener.onItemClick(position);
                        }
                    }

                }
            });
        }
    }
}
