package com.pearamone.didyouknow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget       .ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.NewsViewHolder> implements Filterable {

    Context mContext;
    List<Fact> mData;
    List<Fact> mDataFiltered;
    boolean isDark = false;

    public SearchAdapter(Context mContext, List<Fact> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;
    }

    public SearchAdapter(Context mContext, List<Fact> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_news,parent,false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {

        holder.img_user.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_transition_animation));
        holder.container.setAnimation(AnimationUtils.loadAnimation(mContext,R.anim.fade_scale_animation));

        holder.tv_title.setText(String.valueOf(mDataFiltered.get(position).getFactId()));
        holder.tv_content.setText(mDataFiltered.get(position).getFact());
        String tableName = mDataFiltered.get(position).getTableName();
        int tableImage;
        switch (tableName){
            case DatabaseFacts.TABLE_GENERAL_NAME:
                tableImage = R.drawable.facts_circle;
                holder.tv_date.setText("General Fact");
                break;
            case DatabaseFacts.TABLE_ANIMAL_NAME:
                tableImage = R.drawable.animal_circle;
                holder.tv_date.setText("Animal Fact");
                break;
            case DatabaseFacts.TABLE_COMPUTER_NAME:
                tableImage = R.drawable.computer_circle;
                holder.tv_date.setText("Computer Fact");
                break;
            case DatabaseFacts.TABLE_COUNTRIES_NAME:
                tableImage = R.drawable.country_circle;
                holder.tv_date.setText("Country Fact");
                break;
            case DatabaseFacts.TABLE_FOOD_NAME:
                tableImage = R.drawable.food_circle;
                holder.tv_date.setText("Food Fact");
                break;
            case DatabaseFacts.TABLE_HACKS_NAME:
                tableImage = R.drawable.lifehacks_circle;
                holder.tv_date.setText("Life Hack");
                break;
            case DatabaseFacts.TABLE_HUMANBODY_NAME:
                tableImage = R.drawable.humanbody_circle;
                holder.tv_date.setText("Human Body Fact");
                break;
            case DatabaseFacts.TABLE_PEOPLE_NAME:
                tableImage = R.drawable.people_circle;
                holder.tv_date.setText("People Fact");
                break;
            case DatabaseFacts.TABLE_PSYCHOLOGY_NAME:
                tableImage = R.drawable.psychology_circle;
                holder.tv_date.setText("Psychology Fact");
                break;
            case DatabaseFacts.TABLE_SCIENCE_NAME:
                tableImage = R.drawable.chemistry_circle;
                holder.tv_date.setText("Chemistry Fact");
                break;
            case DatabaseFacts.TABLE_UNIVERSE_NAME:
                tableImage = R.drawable.universe_circle;
                holder.tv_date.setText("Universe Fact");
                break;
            case DatabaseFacts.TABLE_WEATHER_NAME:
                tableImage = R.drawable.weather_circle;
                holder.tv_date.setText("Weather Fact");
                break;
            default:
                tableImage = R.mipmap.ic_launcher;
        }
        holder.img_user.setImageResource(tableImage);
    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String Key = constraint.toString();
                if (Key.isEmpty()) {
                    mDataFiltered = mData ;
                }
                else {
                    List<Fact> lstFiltered = new ArrayList<>();
                    for (Fact row : mData) {
                        if (row.getFact().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }
                    }
                    mDataFiltered = lstFiltered;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDataFiltered = (List<Fact>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder{

        TextView tv_title, tv_content, tv_date;
        ImageView img_user;
        RelativeLayout container;

        public NewsViewHolder(View itemView){
            super(itemView);
            tv_title = itemView.findViewById(R.id.tvTitle);
            tv_content = itemView.findViewById(R.id.textView2);
            tv_date = itemView.findViewById(R.id.tv_date);
            img_user = itemView.findViewById(R.id.img_user);
            container = itemView.findViewById(R.id.container);
            if(isDark) {
                setDarkTheme();
            }
        }

        private void setDarkTheme(){
            container.setBackgroundResource(R.drawable.card_bg_dark);
        }
    }
}
