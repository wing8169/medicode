package com.chinjiaxiong.headevaluator;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.StringTokenizer;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    MyRecyclerViewAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.recycle_view_row, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String data = mData.get(position);
        StringTokenizer st = new StringTokenizer(data, ",");
        st.nextToken();
        holder.ageView.setText(   "Age    : " + st.nextToken());
        holder.genderView.setText("Gender : " + st.nextToken());
        holder.signalView.setText("Signal : " + st.nextToken());
        holder.scoreView.setText( "Score  : " + st.nextToken());
        holder.dateView.setText(  "Date   : " + st.nextToken());
        holder.timeView.setText(  "Time   : " + st.nextToken());
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView ageView, genderView, signalView, scoreView, dateView, timeView;

        ViewHolder(View itemView) {
            super(itemView);
            ageView = itemView.findViewById(R.id.age);
            genderView = itemView.findViewById(R.id.gender);
            signalView = itemView.findViewById(R.id.signal);
            scoreView = itemView.findViewById(R.id.score);
            dateView = itemView.findViewById(R.id.date);
            timeView = itemView.findViewById(R.id.time);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}