package com.example.recyclerviewjsonexample;

public class ExampleItem {
    //declare memeber variables.
    private String mImageUrl;
    private String mCreatior;
    private int mLikes;

    //create a constractor for this class that will later on pass those value when we create new object.
    public ExampleItem(String imageUrl,String creator,int likes){
        mImageUrl=imageUrl;
        mCreatior=creator;
        mLikes=likes;
    }
    //create getter methods to those values out of those objects.
    public String getImageUrl(){
        return mImageUrl;
    }

    public String getCreator(){
        return mCreatior;
    }
    public int getLIkeCount(){
        return mLikes;
    }

}
