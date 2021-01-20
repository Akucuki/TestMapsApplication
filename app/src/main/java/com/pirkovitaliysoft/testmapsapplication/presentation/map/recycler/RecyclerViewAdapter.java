package com.pirkovitaliysoft.testmapsapplication.presentation.map.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pirkovitaliysoft.testmapsapplication.R;
import com.pirkovitaliysoft.testmapsapplication.presentation.model.PlaceEntity;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<PlaceEntity> places;

    public void setPlaces(List<PlaceEntity> places){
        this.places = places;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_card, parent, false);
        return new RecyclerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.getRowNumberTextView().setText(String.valueOf(position + 1));

        PlaceEntity place = places.get(position);
        holder.getPlaceNameTextView().setText(place.getName());
    }

    @Override
    public int getItemCount() {
        if(places == null){
            return 0;
        }
        return places.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        private TextView rowNumberTextView, placeNameTextView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            rowNumberTextView = itemView.findViewById(R.id.row_number);
            placeNameTextView = itemView.findViewById(R.id.place_name);
        }

        public TextView getRowNumberTextView() {
            return rowNumberTextView;
        }

        public TextView getPlaceNameTextView() {
            return placeNameTextView;
        }
    }
}
