package com.example.android.hclinic;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class BigMenuAdapter extends RecyclerView.Adapter<BigMenuAdapter.ViewHolder> {
    // Member variables.
    private ArrayList<BigMenu> mMenuData;
    private Context mContext;

    public BigMenuAdapter(ArrayList<BigMenu> mMenuData, Context mContext) {
        this.mMenuData = mMenuData;
        this.mContext = mContext;
    }

    @Override
    public BigMenuAdapter.ViewHolder onCreateViewHolder(
             ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mContext).
                inflate(R.layout.list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get current menu.
        BigMenu currentMenu= mMenuData.get(position);

        // Populate the textviews with data.
        holder.bindTo(currentMenu);
    }

    @Override
    public int getItemCount() {
        return mMenuData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        // Member Variables for the TextViews
        private TextView mTitleText;
        private TextView mLinkTo;
        private ImageView mSportsImage;

        /**
         * Constructor for the ViewHolder, used in onCreateViewHolder().
         *
         * @param itemView The rootview of the list_item.xml layout file.
         */
        ViewHolder(View itemView) {
            super(itemView);
            mSportsImage = itemView.findViewById(R.id.sportsImage);

            // Initialize the views.
            mTitleText = itemView.findViewById(R.id.title);
            mLinkTo = itemView.findViewById(R.id.linkTo);
        }

        void bindTo(BigMenu currentMenu){
            // Populate the textviews with data.
            mTitleText.setText(currentMenu.getTitle());
            mLinkTo.setText(currentMenu.getLinkto());
            Glide.with(mContext).load(currentMenu.getImageResource()).into(mSportsImage);
        }
    }
}
