package com.example.mahesh.stackoverflowapplication.adapters;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mahesh.stackoverflowapplication.R;
import com.example.mahesh.stackoverflowapplication.model.Tags;

import java.util.List;

public class TagsSelectedAdapter extends RecyclerView.Adapter<TagsSelectedAdapter.ViewHolder> {

    private List<Tags> selectedTags;
    private RemoveListener removeListener;

    public TagsSelectedAdapter(List<Tags> selectedTags) {
        this.selectedTags = selectedTags;
    }

    public void setRemoveListener(RemoveListener removeListener) {
        this.removeListener = removeListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_interested_tags_selector, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tagName.setText(selectedTags.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return selectedTags.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tagName;
        ImageButton tagDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tagName = itemView.findViewById(R.id.tag_name);
            tagDelete = itemView.findViewById(R.id.delete_tag);
            tagDelete.setOnClickListener(
                    new View.OnClickListener() {
                        public static final String TAG = "sdlflksddsjkf";

                        @Override
                        public void onClick(View view) {
                            removeListener.removed(selectedTags.get(getAdapterPosition()));
                            selectedTags.remove(getAdapterPosition());
                            notifyItemRemoved(getAdapterPosition());
                            Log.d(TAG, "onClickAdapter: "+getAdapterPosition());

                        }
                    }
            );
        }


    }
    public interface RemoveListener{
        void removed(Tags tags);
    }
}
