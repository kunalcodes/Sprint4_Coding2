package com.example.covid_tracker_app;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.net.CacheRequest;

public class ModelViewHolder extends RecyclerView.ViewHolder {

    private TextView mTvDate;
    private TextView mTvPositive;
    private TextView mTvNegative;
    private TextView mTvHospitalizedCurrently;
    private TextView mTvOnVentilatorCurrently;
    private TextView mTvDeath;
    private TextView mTvDateChecked;
    private CardView mCvCardView;
    private ItemClickListener itemClickListener;

    public ModelViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);
        this.itemClickListener = itemClickListener;
        initViews(itemView);
    }

    private void initViews(View itemView) {
        mTvDate = itemView.findViewById(R.id.tvDate);
        mTvPositive = itemView.findViewById(R.id.tvPositive);
        mTvNegative = itemView.findViewById(R.id.tvNegative);
        mTvHospitalizedCurrently = itemView.findViewById(R.id.tvHospitalizedCurrently);
        mTvOnVentilatorCurrently = itemView.findViewById(R.id.tvOnVentilatorCurrently);
        mTvDeath = itemView.findViewById(R.id.tvDeath);
        mTvDateChecked = itemView.findViewById(R.id.tvDateChecked);
        mCvCardView = itemView.findViewById(R.id.cvCardView);
    }

    public void setData(ResponseModel responseModel) {
        String Date = responseModel.getDate() + "";
        String DateChecked = responseModel.getDateChecked() + "";
        mTvDate.setText("Date: " + Date.substring(0, 4) + "-" + Date.substring(4, 6) + "-" + Date.substring(6));
        mTvPositive.setText("Positive: " + responseModel.getPositive());
        mTvNegative.setText("Negative: " + responseModel.getNegative());
        mTvHospitalizedCurrently.setText("Hospitalized Currently: " + responseModel.getHospitalizedCurrently());
        mTvOnVentilatorCurrently.setText("On Ventilator Currently: " + responseModel.getOnVentilatorCurrently());
        mTvDeath.setText("Death: " + responseModel.getDeath());
        mTvDateChecked.setText("Date Checked: " + DateChecked.substring(0, 10));

//        mCvCardView.setOnClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                itemClickListener.onItemClicked(getAdapterPosition());
//            }
//        });

        mCvCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemClickListener.onItemClicked(getAdapterPosition());
            }
        });
    }
}
