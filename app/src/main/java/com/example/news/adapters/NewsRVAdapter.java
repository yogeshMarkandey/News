package com.example.news.adapters;

import android.content.Context;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.example.news.R;
import com.example.news.data.NewsArticle;



// Recycler view Adapter for
public class NewsRVAdapter extends ListAdapter<NewsArticle, NewsRVAdapter.NewsViewHolder> {
    private static final String TAG = "NewsRVAdapter";

    private Context context;
    private OnTapListener listener;

    public NewsRVAdapter(Context context, OnTapListener listener) {
        super(DIFF_CALLBACK);
        this.context = context;
        this.listener = listener;
    }

    public static final DiffUtil.ItemCallback<NewsArticle> DIFF_CALLBACK = new DiffUtil.ItemCallback<NewsArticle>() {
        @Override
        public boolean areItemsTheSame(@NonNull NewsArticle oldItem, @NonNull NewsArticle newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) ;

        }

        @Override
        public boolean areContentsTheSame(@NonNull NewsArticle oldItem, @NonNull NewsArticle newItem) {
            return oldItem.getTitle().equals(newItem.getTitle())&&
                    oldItem.getUrl().equals(newItem.getUrl());
        }
    };


    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_rv_news, parent, false);

        return new NewsViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.bind(getItem(position));

    }


    public class NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tvDate, tvTime,tvTitle,tvContent;
        private ImageView imageView;
        private OnTapListener listener;
        public NewsViewHolder(@NonNull View itemView, OnTapListener listener) {
            super(itemView);
            this.listener = listener;
            tvDate = itemView.findViewById(R.id.textView_date);
            tvTitle = itemView.findViewById(R.id.textView_heading);
            tvContent = itemView.findViewById(R.id.textView_news);
            imageView = itemView.findViewById(R.id.imageView);
            itemView.setOnClickListener(this::onClick);
        }

        public void bind(NewsArticle article){
            Log.d(TAG, "bind: called");

            tvTitle.setText(article.getTitle());
            String description = article.getDescription() == null? "NA" : article.getDescription();
            tvContent.setText(description);
            String url = article.getUrlToImage();

            String date = article.getPublishedAt() == null? "NA" :"Date : " + article.getPublishedAt().substring(0,10);
            tvDate.setText(date);
            RequestOptions options = new RequestOptions()
                    .placeholder(R.drawable.back_image_view)
                    .error(R.drawable.back_image_view);

            Glide.with(context)
                    .load(url)
                    .apply(options)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(imageView);
        }

        @Override
        public void onClick(View view) {
           listener.OpenUrl(getItem(getAdapterPosition()).getUrl());
        }
    }

    public interface OnTapListener{
        void OpenUrl(String url);

    }
}
