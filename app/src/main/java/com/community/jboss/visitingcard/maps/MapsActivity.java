package com.community.jboss.visitingcard.maps;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.community.jboss.visitingcard.R;
import com.community.jboss.visitingcard.data.VisitingCard;
import com.community.jboss.visitingcard.visitingCard.ViewVisitingCard;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private MapsActivityViewModel viewModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        View bottomSheet = findViewById(R.id.bottom_sheet);
        viewModel = ViewModelProviders.of(this).get(MapsActivityViewModel.class);

        final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);

        //behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        recyclerView = findViewById(R.id.rcl_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CustomAdaptor());

        // TODO: List item click should result in launching of ViewVisitingCard Acitivity with the info of the tapped Visiting card.

        //TODO: Create Custom pins for the selected location
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //TODO: Implement geo-fencing(NOT AS A WHOLE) just visual representation .i.e., a circle of an arbitrary radius with the PIN being the centre of it.
        //TODO: Make the circle color as @color/colorAccent
    }


    // TODO: Replace the stating location with user's current location.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
       // Log.i("MY_TAG","gmap is ready to be used.");
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    // RecyclerView object henceforth

    /**
     * This class is responsible for creation and maintainance of the RV object.
     */
    private class CustomAdaptor extends RecyclerView.Adapter<CustomAdaptor.CustomViewHolder> {

        @NonNull
        @Override
        public CustomAdaptor.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = getLayoutInflater().inflate(R.layout.visiting_card_compact, viewGroup, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomAdaptor.CustomViewHolder customViewHolder, int i) {
            VisitingCard visitingCard = viewModel.getVisitingCard(i);
            customViewHolder.name.setText(visitingCard.getName());
            customViewHolder.email.setText(visitingCard.getEmail());
        }

        @Override
        public int getItemCount() {
            return viewModel.getVisitingCardCount();
        }

        /**
         * The class to encapsulate each viewholder position.
         */
        class CustomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

            private TextView name;
            private TextView email;
            private ViewGroup viewGroup;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(R.id.txtv_name);
                email = itemView.findViewById(R.id.txtv_email);
                viewGroup = itemView.findViewById(R.id.vgrp_contact_details);

                viewGroup.setOnClickListener(this);
            }

            @Override
            public void onClick(View v) {
                // do Random stuff for now.
                //Log.i("MY_TAG", "The item clicked has the following index : " + this.getAdapterPosition());
            }
        }
    }
}
