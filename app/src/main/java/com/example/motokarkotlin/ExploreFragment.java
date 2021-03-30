package com.example.motokarkotlin;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.app.Activity.RESULT_OK;


public class ExploreFragment extends Fragment{

    RegistrationCache cacheObj;
    DatabaseReference sRef, vRef;
    ExploreAdapter vehicleAdapter;
    StoriesAdapter storiesAdapter;
    RecyclerView vehicle_list,stories_list;
    ImageView storiesButton;
    StorageReference storageStoriesReference;
    ProgressDialog progressDialog;
    Uri photoURI;
    StorageTask uploadTask;
    View view;

    private static final int CAMERA_REQUEST_CODE = 1;
    private static final int REQUEST_CAPTURE_IMAGE = 100;

    private void openCameraIntent() {
        Intent pictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(pictureIntent.resolveActivity(getActivity().getPackageManager()) != null){
            //Create a file to store the image
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
            }
            if (photoFile != null) {
                photoURI = FileProvider.getUriForFile(getContext(),"com.example.android.fileprovider", photoFile);
                pictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(pictureIntent, REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    String imageFilePath;

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "IMG_" + timeStamp + "_";
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        imageFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CAPTURE_IMAGE) {

            progressDialog.setMessage("Uploading...");
            progressDialog.show();

            Uri imageUri = Uri.fromFile(new File(imageFilePath));

            final StorageReference filepath = storageStoriesReference.child(imageUri.getLastPathSegment());
            filepath.putFile(photoURI).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    //Upload URL
                    filepath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {

                            final ArrayList<String> storiesPicturesArray = new ArrayList<>();
                            final String imageUrl = String.valueOf(uri);
                            final DatabaseReference addSRef = FirebaseDatabase.getInstance().getReference("Stories");

                            addSRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    if(dataSnapshot.child(cacheObj.username).exists()){

                                        for(DataSnapshot ds : dataSnapshot.child(cacheObj.username).child("images").getChildren()){
                                            storiesPicturesArray.add(ds.getValue().toString());
                                        }

                                        storiesPicturesArray.add(imageUrl);
                                        addSRef.child(cacheObj.username).child("images").setValue(storiesPicturesArray);

                                    }else{

                                        storiesPicturesArray.add(imageUrl);
                                        addSRef.child(cacheObj.username).child("username").setValue(cacheObj.username);
                                        addSRef.child(cacheObj.username).child("images").setValue(storiesPicturesArray);
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    // Code
                                }
                            });
                        }
                    });

                    Toast.makeText(getContext(), "Upload Successful!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getContext(), "Upload Failed!", Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            });


        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view =  inflater.inflate(R.layout.explore_fragment, container, false);
        vehicle_list = view.findViewById(R.id.vehicle_list);
        stories_list = view.findViewById(R.id.stories_list);

        progressDialog = new ProgressDialog(getContext());

        storageStoriesReference = FirebaseStorage.getInstance().getReference();

        storiesButton = view.findViewById(R.id.storiesButton);
        storiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCameraIntent();
            }
        });

        sRef = FirebaseDatabase.getInstance().getReference("Stories");
        sRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                ArrayList<String> storiesUsername = new ArrayList<>();
                ArrayList<String> storiesImages = new ArrayList<>();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(cacheObj.username.equals(snapshot.child("username").getValue().toString())) {
                        storiesUsername.add(snapshot.child("username").getValue().toString());

                        ArrayList<String> storyImage = new ArrayList<>();

                        for(DataSnapshot snapshotImageUrl: snapshot.child("images").getChildren()){
                            storyImage.add(snapshotImageUrl.getValue().toString());
                        }

                        String imagesString = TextUtils.join(";", storyImage);
                        storiesImages.add(imagesString);
                    }
                }

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(!cacheObj.username.equals(snapshot.child("username").getValue().toString())) {
                        storiesUsername.add(snapshot.child("username").getValue().toString());

                        ArrayList<String> storyImage = new ArrayList<>();

                        for(DataSnapshot snapshotImageUrl: snapshot.child("images").getChildren()){
                            storyImage.add(snapshotImageUrl.getValue().toString());
                        }

                        String imagesString = TextUtils.join(";", storyImage);
                        storiesImages.add(imagesString);
                    }
                }

                stories_list.setHasFixedSize(true);
                stories_list.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
                storiesAdapter = new StoriesAdapter(getContext(), storiesUsername, storiesImages);
                stories_list.setAdapter(storiesAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        final ArrayList<String> vehicleIdArray = new ArrayList<>();
        final ArrayList<String> vehicleNameArray = new ArrayList<>();
        final ArrayList<String> vehicleRateArray = new ArrayList<>();
        final ArrayList<String> vehicleRCArray = new ArrayList<>();
        final ArrayList<String> vehicleReviewArray = new ArrayList<>();
        final ArrayList<String> vehiclePictureArray = new ArrayList<>();
        final ArrayList<String> vehicleConfigArray = new ArrayList<>();
        final ArrayList<String> vehicleDescriptionArray = new ArrayList<>();
        final ArrayList<String> vehicleDoorsArray = new ArrayList<>();
        final ArrayList<String> vehicleRateDayArray = new ArrayList<>();
        final ArrayList<String> vehicleRateWeekArray = new ArrayList<>();
        final ArrayList<String> vehicleRateMonthArray = new ArrayList<>();
        final ArrayList<String> vehicleSeatsArray = new ArrayList<>();
        final ArrayList<String> vehicleTransmissionArray = new ArrayList<>();
        final ArrayList<String> vehicleYearArray = new ArrayList<>();
        final ArrayList<String> vehicleUserName = new ArrayList<>();

        vRef = FirebaseDatabase.getInstance().getReference("Vehicle");
        vRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    if(!cacheObj.username.equals(snapshot.child("username").getValue().toString())) {
                        vehicleIdArray.add(snapshot.child("vehicleId").getValue().toString());
                        vehicleNameArray.add(snapshot.child("vehicleName").getValue().toString());
                        vehicleRateArray.add(snapshot.child("vehicleRate").getValue().toString());
                        vehicleRCArray.add(snapshot.child("vehicleReviewCum").getValue().toString());
                        vehicleReviewArray.add(snapshot.child("vehicleReview").getValue().toString());
                        vehiclePictureArray.add(snapshot.child("vehiclePicture").getValue().toString());
                        vehicleConfigArray.add(snapshot.child("vehicleConfig").getValue().toString());
                        vehicleDescriptionArray.add(snapshot.child("vehicleDescription").getValue().toString());
                        vehicleDoorsArray.add(snapshot.child("vehicleDoors").getValue().toString());
                        vehicleRateDayArray.add(snapshot.child("vehicleRateDay").getValue().toString());
                        vehicleRateWeekArray.add(snapshot.child("vehicleRateWeek").getValue().toString());
                        vehicleRateMonthArray.add(snapshot.child("vehicleRateMonth").getValue().toString());
                        vehicleSeatsArray.add(snapshot.child("vehicleSeats").getValue().toString());
                        vehicleTransmissionArray.add(snapshot.child("vehicleTransmission").getValue().toString());
                        vehicleYearArray.add(snapshot.child("vehicleYear").getValue().toString());
                        vehicleUserName.add(snapshot.child("username").getValue().toString());
                    }
                }

                vehicle_list.setHasFixedSize(true);
                vehicle_list.setLayoutManager(new LinearLayoutManager(getContext()));
                vehicleAdapter = new ExploreAdapter(getContext(),
                        vehicleIdArray,
                        vehicleNameArray,
                        vehicleRateArray,
                        vehicleRCArray,
                        vehicleReviewArray,
                        vehiclePictureArray,
                        vehicleConfigArray,
                        vehicleDescriptionArray,
                        vehicleDoorsArray,
                        vehicleRateDayArray,
                        vehicleRateWeekArray,
                        vehicleRateMonthArray,
                        vehicleSeatsArray,
                        vehicleTransmissionArray,
                        vehicleYearArray,
                        vehicleUserName
                        );
                vehicle_list.setAdapter(vehicleAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        return view;
    }

}





