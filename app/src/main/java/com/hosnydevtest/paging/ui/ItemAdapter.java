package com.hosnydevtest.paging.ui;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.hosnydevtest.paging.R;
import com.hosnydevtest.paging.model.Items;
import com.hosnydevtest.paging.model.Owner;

public class ItemAdapter extends PagedListAdapter<Items, ItemAdapter.ViewHolder> {

    private Context context;

    protected ItemAdapter(Context context) {
        super(diffCallback);
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.format_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Items items = getItem(position);
        if (items!=null) {
            holder.set_userName(items.owner.display_name);
            holder.set_userImage(items.owner.profile_image);
            holder.set_userScore(items.score);
        }else
            Toast.makeText(context, "null items", Toast.LENGTH_SHORT).show();
    }

    private static DiffUtil.ItemCallback<Items> diffCallback = new DiffUtil.ItemCallback<Items>() {
        @Override
        public boolean areItemsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.answer_id == newItem.answer_id;
        }

        @SuppressLint("DiffUtilEquals")
        @Override
        public boolean areContentsTheSame(@NonNull Items oldItem, @NonNull Items newItem) {
            return oldItem.equals(newItem);
        }
    };

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView _userImage;
        private TextView _userName,_userScore;
        private ProgressBar _progressbar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            _userImage = itemView.findViewById(R.id.image_item);
            _userName = itemView.findViewById(R.id.name_item);
            _progressbar = itemView.findViewById(R.id.progress_image_item);
            _userScore = itemView.findViewById(R.id.score_item);
        }

        void set_userName(String name) {
            _userName.setText(name);
        }

        void set_userImage(String image) {
            _progressbar.setVisibility(View.VISIBLE);
            Glide.with(context)
                    .load(image)
                    .centerCrop()
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            _progressbar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(_userImage);

        }

        void set_userScore(int score){
            _userScore.setText(String.valueOf(score));
        }

    }
}
