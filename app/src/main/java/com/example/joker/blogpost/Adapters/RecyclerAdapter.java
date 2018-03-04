package com.example.joker.blogpost.Adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.joker.blogpost.R;
import com.example.joker.blogpost.SQLHelper.ContractorClass;

import java.net.URI;
import java.net.URL;


/**
 * Created by joker on 4/3/18.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = RecyclerAdapter.class.getSimpleName();

    private Cursor cursor;
    private Context context;

    private String[] link = {
            "http://www.diwalifestival.org/assets/images/exploring-diwali.jpg",
            "https://akm-img-a-in.tosshub.com/indiatoday/images/story/201603/holi_story-and-fb_647_032116055857.jpg",
            "https://blogmedia.evbstatic.com/wp-content/uploads/bloguk/shutterstock_199419065-730x487.jpg"
    };

    public RecyclerAdapter(Cursor cursor, Context context) {
        this.cursor = cursor;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Log.d(TAG, "Error in onBindViewHolder ");
        if(!cursor.moveToPosition(position))
            return;

        String userName = cursor.getString(cursor.getColumnIndex(ContractorClass.BlogPost.USER_NAME));
        String title = cursor.getString(cursor.getColumnIndex(ContractorClass.BlogPost.POST_TITLE));
        String imageUri = cursor.getString(cursor.getColumnIndex(ContractorClass.BlogPost.IMAGE_URI));
        long id = cursor.getLong(cursor.getColumnIndex(ContractorClass.BlogPost._ID));

        holder.itemView.setTag(id);
        holder.titleTextView.setText(title);
        Glide.with(context).load(link[position/3]).into(holder.imageView);
        holder.userTextView.setText(userName);
        Log.d("URI ",imageUri);

    }

    @Override
    public int getItemCount() {
        return cursor == null ? 0 :cursor.getCount();
    }

    public void swipeCursor(Cursor allPost) {
        if(cursor!=null)
            cursor.close();
        cursor = allPost;

        notifyDataSetChanged();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userTextView;
        private ImageView imageView;
        private TextView titleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            userTextView  = itemView.findViewById(R.id.userName);
            imageView = itemView.findViewById(R.id.postImage);
            titleTextView = itemView.findViewById(R.id.postTitle);
        }
    }
}
