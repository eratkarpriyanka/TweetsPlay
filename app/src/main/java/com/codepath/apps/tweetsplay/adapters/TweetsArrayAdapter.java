package com.codepath.apps.tweetsplay.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.apps.tweetsplay.R;
import com.codepath.apps.tweetsplay.models.Tweet;
import com.codepath.apps.tweetsplay.models.User;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * get {@link Tweet} objects and convert them into Views
 */
public class TweetsArrayAdapter extends ArrayAdapter<Tweet>{

    public TweetsArrayAdapter(Context context,List<Tweet> listTweets){
        super(context,0,listTweets);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Tweet tweet = getItem(position);

        if(convertView == null){

            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_tweet,parent,false);
            ImageView ivProfilePhoto = (ImageView)convertView.findViewById(R.id.ivProfilePhoto);
            TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
            TextView tvScreenName = (TextView) convertView.findViewById(R.id.tvScreenName);
            TextView tvText = (TextView) convertView.findViewById(R.id.tvText);
            ViewHolder viewHolder = new ViewHolder(ivProfilePhoto,tvName,tvScreenName,tvText);
            convertView.setTag(viewHolder);
        }

        ViewHolder viewHolder = (ViewHolder) convertView.getTag();
        User user = tweet.getUser();
        if(user!=null) {

            viewHolder.tvName.setText(user.getName());
            viewHolder.tvScreenName.setText(user.getScreenName());
            String imgUrl = user.getProfileImgUrl();
            if(imgUrl!=null && !imgUrl.isEmpty()) {
                Picasso.with(getContext()).load(imgUrl).placeholder(R.drawable.ic_launcher).error(R.drawable.ic_launcher).into(viewHolder.ivProfilePhoto);
            }
        }
        viewHolder.tvText.setText(tweet.getBody());
        return convertView;
    } 

    class ViewHolder{

        private ImageView ivProfilePhoto;
        private TextView tvName;
        private TextView tvScreenName;
        private TextView tvText;

        public ViewHolder(ImageView ivProfilePhoto, TextView tvName, TextView tvScrName, TextView tvText){

            this.ivProfilePhoto = ivProfilePhoto;
            this.tvName = tvName;
            this.tvScreenName = tvScrName;
            this.tvText = tvText;
        }
    }
}
