package com.chydee.solarpowercalculator.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chydee.solarpowercalculator.R;
import com.chydee.solarpowercalculator.model.Appliances;

import java.text.BreakIterator;
import java.util.ArrayList;

public class CalculatorAdapter extends RecyclerView.Adapter<CalculatorAdapter.CalculatorViewHolder> {

    //Generals
    private ArrayList<Appliances> mAppliances;
    private OnItemClickListener mListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    @NonNull
    @Override
    public CalculatorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appliance_layout, parent, false);
        CalculatorViewHolder calculatorViewHolder = new CalculatorViewHolder(view, mListener);
        return calculatorViewHolder;
    }

    public CalculatorAdapter(ArrayList<Appliances> appliances){
        mAppliances = appliances;
    }

    public interface OnItemClickListener{
        void onDeleteClick(int position);
    }

    @Override
    public void onBindViewHolder(@NonNull CalculatorViewHolder holder, int position) {
        Appliances currentAppliance = mAppliances.get(position);

        holder.name.setText(currentAppliance.getApplianceName());
        holder.wattage.setText(currentAppliance.getApplianceWattage() + " Watt");
        holder.quantity.setText(currentAppliance.getApplianceQuantity()+ "Pieces");
        holder.duration.setText(currentAppliance.getApplianceDurationOfUse() + "Hrs");

    }

    @Override
    public int getItemCount() {
        return mAppliances.size();
    }

    //This is a public static inner class for our ViewHolder
    public static class CalculatorViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView wattage;
        public TextView duration;
        public TextView quantity;
        public ImageView remove;
        //public TextView quantity;

        public CalculatorViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            name = itemView.findViewById(R.id.appliance_name_txt);
            wattage = itemView.findViewById(R.id.wattage_value_txt);
            duration = itemView.findViewById(R.id.duration_value_txt);
            quantity = itemView.findViewById(R.id.quantity_value_txt);
            remove = itemView.findViewById(R.id.delete_appliance_img_btn);

            remove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });
        }
    }
}
