package com.example.android.hclinic;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Member variables.
    private RecyclerView mRecyclerView;
    private ArrayList<BigMenu> mMenuData;
    private BigMenuAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerView);

        // Set the Layout Manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2, 0, false));

        // Initialize the ArrayList that will contain the data.
        mMenuData = new ArrayList<>();

        // Initialize the adapter and set it to the RecyclerView.
        mAdapter = new BigMenuAdapter(mMenuData, this);
        mRecyclerView.setAdapter(mAdapter);

        // Get the data.
        initializeData();
    }

    private void initializeData() {
        // Get the resources from the XML file.
        String[] menuList = getResources()
                .getStringArray(R.array.menu_titles);
        String[] menuLinkInfo = getResources()
                .getStringArray(R.array.menu_links);

        TypedArray menuImageResources =
                getResources().obtainTypedArray(R.array.menu_images);

        // Clear the existing data (to avoid duplication).
        mMenuData.clear();

        // Create the ArrayList of Sports objects with titles and
        // information about each sport.
        for(int i=0;i<menuList.length;i++){
            mMenuData.add(new BigMenu(menuList[i],menuLinkInfo[i],
                    menuImageResources.getResourceId(i,0)));
        }

        // Notify the adapter of the change.
        mAdapter.notifyDataSetChanged();

        // clean up data
        menuImageResources.recycle();
    }
}
