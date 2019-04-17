package com.example.mahesh.stackoverflowapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mahesh.stackoverflowapplication.R;
import com.example.mahesh.stackoverflowapplication.model.Tags;

import java.util.List;

public class UserInterestAdapter extends RecyclerView.Adapter<UserInterestAdapter.ViewHolder> {

    private List<Tags> userInterestedList;
    private ClickListner listner;



    public void setListner(ClickListner listner) {
        this.listner = listner;
    }

    public UserInterestAdapter(List<Tags> userInterestedList) {

        this.userInterestedList = userInterestedList;

    }

    public void setUserInterestedList(List<Tags> userInterestedList) {
        this.userInterestedList = userInterestedList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_user_interest, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Tags tags = userInterestedList.get(i);
        tags.setPosition(i);
        viewHolder.userInterestedTags.setText(tags.getName());
    }

    @Override
    public int getItemCount() {
        return userInterestedList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView userInterestedTags;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userInterestedTags = itemView.findViewById(R.id.user_interested_tags);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            listner.listne(userInterestedList.get(getAdapterPosition()));

            userInterestedList.remove(getAdapterPosition());
            notifyItemRemoved(getAdapterPosition());

            Log.d("lsdsdfsdfs", "onClick: "+getAdapterPosition());
        }
    }

    public interface ClickListner{
        void listne(Tags tags);
    }
}
