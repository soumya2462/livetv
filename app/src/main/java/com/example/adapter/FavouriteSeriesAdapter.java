package com.example.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.item.ItemSeries;
import com.example.livetvseries.R;
import com.example.util.NetworkUtils;
import com.example.util.PopUpAds;
import com.example.util.RvOnClickListener;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavouriteSeriesAdapter extends RecyclerView.Adapter<FavouriteSeriesAdapter.ItemRowHolder> {

    private ArrayList<ItemSeries> dataList;
    private Context mContext;
    private RvOnClickListener clickListener;
    private int columnWidth;

    public FavouriteSeriesAdapter(Context context, ArrayList<ItemSeries> dataList) {
        this.dataList = dataList;
        this.mContext = context;
        columnWidth = NetworkUtils.getScreenWidth(mContext);
    }

    @NonNull
    @Override
    public ItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tv_series_item, parent, false);
        return new ItemRowHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final ItemRowHolder holder, final int position) {
        final ItemSeries singleItem = dataList.get(position);

        holder.image.setLayoutParams(new RelativeLayout.LayoutParams(columnWidth, columnWidth / 3 + 80));
        holder.view.setLayoutParams(new RelativeLayout.LayoutParams(columnWidth, columnWidth / 3 + 80));

        holder.text.setText(singleItem.getSeriesName());
        Picasso.get().load(singleItem.getSeriesPoster()).placeholder(R.drawable.place_holder_movie).into(holder.image);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpAds.showInterstitialAds(mContext, holder.getAdapterPosition(), clickListener);
            }
        });
    }

    @Override
    public int getItemCount() {
        return (null != dataList ? dataList.size() : 0);
    }

    public void setOnItemClickListener(RvOnClickListener clickListener) {
        this.clickListener = clickListener;
    }

    class ItemRowHolder extends RecyclerView.ViewHolder {
        RoundedImageView image;
        TextView text;
        CardView cardView;
        View view;

        ItemRowHolder(View itemView) {
            super(itemView);
            view = itemView.findViewById(R.id.view_movie_adapter);
            image = itemView.findViewById(R.id.image);
            text = itemView.findViewById(R.id.text);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
