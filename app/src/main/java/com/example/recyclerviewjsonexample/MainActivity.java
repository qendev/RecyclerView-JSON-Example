package com.example.recyclerviewjsonexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListener {
    //create constants keys that will be used both in the MainActivity and Detailed Activity.Hence make them public.
    public static final String  EXTRA_URL="imageUrl";
    public static final String EXTRA_CREATOR="creatorName";
    public static final String EXTRA_LIKES="likesCount";
    //create member variables.
    private RecyclerView mRecyclerview;
    private ExampleAdapter mExampleAdapter;
    private ArrayList<ExampleItem> mExampleList;
    //RequestQue needed for volley.
    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerview=findViewById(R.id.recycler_view);
        mRecyclerview.setHasFixedSize(true);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(this));

        mExampleList=new ArrayList<>();

        mRequestQueue= Volley.newRequestQueue(this);
        parseJSON();
    }
    private void parseJSON(){
        //first will need a link from whre we will pass the JSON.
        String url="https://pixabay.com/api/?key=5303976-fd6581ad4ac165d1b75cc15b3&q=kitten&image_type=photo&pretty=true";
        JsonObjectRequest request=new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        //to get the Json Array out of the Json Object.

                        try {
                            JSONArray jsonArray=response.getJSONArray("hits");

                            for(int i=0;i <jsonArray.length();i++){
                                JSONObject hit=jsonArray.getJSONObject(i);

                                String creatorName=hit.getString("user");
                                String imageUrl=hit.getString("webformatURL");
                                int likeCount=hit.getInt("likes");
                                //we add a new item to this for everyobject in the JSON Array
                                mExampleList.add(new ExampleItem(imageUrl,creatorName,likeCount));
                            }

                            //to fill the Recyclerview there is need of an adapter.

                            mExampleAdapter=new ExampleAdapter(MainActivity.this,mExampleList);
                            mRecyclerview.setAdapter(mExampleAdapter);

                            mExampleAdapter.setOnItemClickListener(MainActivity.this);




                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        //start the Detail Activity and pass the values of the clicked items to it.
        Intent detailIntent=new Intent(this,DetailActivity.class);
        ExampleItem clickedItem=mExampleList.get(position);

        detailIntent.putExtra(EXTRA_URL,clickedItem.getImageUrl());
        detailIntent.putExtra(EXTRA_CREATOR,clickedItem.getCreator());
        detailIntent.putExtra(EXTRA_LIKES,clickedItem.getLIkeCount());

        startActivity(detailIntent);


    }
}
