package com.example.motokarkotlin;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.motokarkotlin.Model.Vehicle;
import com.example.motokarkotlin.ViewHolder.VehicleViewHolder;
//import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class ExploreFragmentBC2 extends Fragment{

    //ExploreAdapter exploreAdapter;
    //StoriesAdapter storiesAdapter;


    private DatabaseReference mRef;
    //private FirebaseRecyclerAdapter<Vehicle, VehicleViewHolder> recyclerAdapter;
    private RecyclerView vehicle_list = null;
    private View view;
    private LinearLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.explore_fragment, container, false);
        vehicle_list = view.findViewById(R.id.vehicle_list);
        manager = new LinearLayoutManager(getContext());
        vehicle_list.setHasFixedSize(true);

        mRef = FirebaseDatabase.getInstance().getReference().child("Vehicle");

//        recyclerAdapter = new FirebaseRecyclerAdapter<Vehicle, VehicleViewHolder>(
//
//                Vehicle.class, R.layout.list_item_explore_vehicles, VehicleViewHolder.class, mRef
//
//        ) {
//            @Override
//            protected void populateViewHolder(VehicleViewHolder viewHolder, Vehicle vehicleModel, int position) {
//                viewHolder.setVehicleName(vehicleModel.getVehicleName());
//                viewHolder.setVehicleRate(vehicleModel.getVehicleRate());
//            }
//        };


//        recyclerAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
//            @Override
//            public void onItemRangeInserted(int positionStart, int itemCount) {
//                super.onItemRangeInserted(positionStart, itemCount);
//                int friendlyMessageCount = recyclerAdapter.getItemCount();
//                int lastVisiblePosition = manager.findLastCompletelyVisibleItemPosition();
//                // If the recycler view is initially being loaded or the
//                // user is at the bottom of the list, scroll to the bottom
//                // of the list to show the newly added message.
//                if (lastVisiblePosition == -1 ||
//                        (positionStart >= (friendlyMessageCount - 1) &&
//                                lastVisiblePosition == (positionStart - 1))) {
//                    vehicle_list.scrollToPosition(positionStart);
//                }
//            }
//        });

//        Query query = FirebaseDatabase.getInstance().getReference("Vehicle");
//        FirebaseRecyclerOptions<Vehicle> options = new FirebaseRecyclerOptions.Builder<Vehicle>().setQuery(query, Vehicle.class).build();
//
//        recyclerAdapter = new FirebaseRecyclerAdapter<Vehicle, VehicleViewHolder>(options) {
//            @Override
//            public VehicleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_explore_vehicles, parent, false);
//                return new VehicleViewHolder(view);
//            }
//
//            @Override
//            protected void onBindViewHolder(VehicleViewHolder holder, int position, Vehicle model) {
//
//            }
//        };

        vehicle_list.setLayoutManager(manager);
        //vehicle_list.setAdapter(recyclerAdapter);




//        recyclerAdapter = new FirebaseRecyclerAdapter<Vehicle, VehicleViewHolder>(Vehicle.class, R.layout.list_item_explore_vehicles, VehicleViewHolder.class, vehicle) {
//            @Override
//            protected void populateViewHolder(VehicleViewHolder viewHolder, Vehicle vehicleModel, int position){
//
//                viewHolder.vehicleName.setText(vehicleModel.getVehicleName());
//                viewHolder.vehicleRate.setText(vehicleModel.getVehicleRate());
//            }
//        };



//        final ArrayList<String> vehicleNames = new ArrayList<>();
//        final ArrayList<String> storiesNames = new ArrayList<>();


//        final FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference dbRef = db.getReference("Vehicle");

//        dbRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {

//                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//                    vehicleNames.add(snapshot.child("vehicleName").getValue().toString());
//                }

//                vehicle_list = view.findViewById(R.id.vehicle_list);
//                vehicle_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//                vehicle_list.setHasFixedSize(true);
//                fetchVehicle();

                //exploreAdapter = new ExploreAdapter(getActivity(), vehicleNames);
                //vehicle_list.setAdapter(exploreAdapter);
            //}

            //@Override
            //public void onCancelled(DatabaseError databaseError) {
                //System.out.println("The read failed: " + databaseError.getCode());
                //Log.d("myTag", "Didn't Work :(");
            //}
        //});

//        storiesNames.add("Masha");
//        storiesNames.add("Gwen Kelly");
//        storiesNames.add("Jimmy Chan");
//        RecyclerView stories_list = view.findViewById(R.id.stories_list);
//        stories_list.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,true));
//        //stories_list.setLayoutManager(new LinearLayoutManager(getActivity()));
//        storiesAdapter = new StoriesAdapter(getActivity(), storiesNames);
//        stories_list.setAdapter(storiesAdapter);

        return view;
    }


    private void fetchVehicle() {

//        Query query = FirebaseDatabase.getInstance().getReference("Vehicle").child("vehicle1");
//
//        FirebaseRecyclerOptions<Vehicle> options =
//                new FirebaseRecyclerOptions.Builder<Vehicle>()
//                        .setQuery(query, new SnapshotParser<Vehicle>() {
//                            @NonNull
//                            @Override
//                            public Vehicle parseSnapshot(@NonNull DataSnapshot snapshot) {
//
//                                Log.d("myTag", "Step 2");
//
//                                return new Vehicle(snapshot.child("vehicleName").getValue().toString(),
//                                        snapshot.child("vehicleRate").getValue().toString());
//
//                            }
//                            }).build();

    }

}



