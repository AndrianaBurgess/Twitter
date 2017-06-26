package com.codepath.apps.twitter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.codepath.apps.twitter.models.Tweet;

import java.util.List;

/**
 * Created by aburgess11 on 6/26/17.
 */

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder>{

    private List<Tweet> mTweets;
    //pass in tweets array
    Context context;
    public TweetAdapter(List<Tweet> tweets){
        mTweets = tweets;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View tweetView = inflater.inflate(R.layout.item_tweet,parent,false);
        ViewHolder viewHolder = new ViewHolder(tweetView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //get the data according to position
        Tweet tweet = mTweets.get(position);
        //populate the views accoring to this data
        holder.tvUsername.setText(tweet.user.name);
        holder.tvBody.setText(tweet.body);
        Glide.with(context).load(tweet.user.profileImageUrl).into(holder.ivProfileImage);
    }

    @Override
    public int getItemCount() {
        return mTweets.size();
    }

    //for  each row , inflate the layout and pass into viewholder

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView ivProfileImage;
        public TextView tvUsername;
        public TextView tvBody;

        public ViewHolder(View itemView) {
            super(itemView);


            ivProfileImage = (ImageView) itemView.findViewById(R.id.ivProfileImage);
            tvBody = (TextView) itemView.findViewById(R.id.tvBody);
            tvUsername = (TextView) itemView.findViewById(R.id.tvUserName);
        }
    }
}
