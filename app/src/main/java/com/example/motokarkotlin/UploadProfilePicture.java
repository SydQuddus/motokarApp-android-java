package com.example.motokarkotlin;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.motokarkotlin.Cache.RegistrationCache;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.*;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class UploadProfilePicture extends AppCompatActivity {

    RegistrationCache cacheObj;

    DatabaseReference databaseAccount;

    StorageReference profilePictureRef;

    private ImageView backButton;

    ImageView importButton, uploadButton, img;

    private StorageTask uploadTask;

    public Uri imguri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_profile_image);

        //Set Database and storage
        profilePictureRef = FirebaseStorage.getInstance().getReference("profilePicture");
        databaseAccount = FirebaseDatabase.getInstance().getReference("Accounts").child(cacheObj.username);

        //Set Back button
        backButton = (ImageView) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        databaseAccount.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Picasso.get().load(dataSnapshot.child("profilePicture").getValue().toString()).into(img);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("myTag","there was an error");
            }
        });

        importButton = (ImageView) findViewById(R.id.importButton);
        uploadButton = (ImageView) findViewById(R.id.uploadButton);
        img = (ImageView)findViewById(R.id.imgview);

        importButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filechooser();
            }
        });
        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(uploadTask != null && uploadTask.isInProgress()) {
                    Toast.makeText(UploadProfilePicture.this, "Upload in progress", Toast.LENGTH_SHORT).show();
                } else {
                    fileuploader();
                }
            }
        });

    }

    private String getExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(cr.getType(uri));
    }
    private void fileuploader()
    {
        final StorageReference Ref = profilePictureRef.child(System.currentTimeMillis()+"."+getExtension(imguri));

        uploadTask = Ref.putFile(imguri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                        //Upload URL
                        Ref.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                databaseAccount.child("profilePicture").setValue(String.valueOf(uri));
                            }
                        });

                        //final toast
                        Toast.makeText(UploadProfilePicture.this, "Profile picture uploaded successfully", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception exception) {
                        // Handle unsuccessful uploads
                        // ...
                    }
                });
    }


    private void filechooser()
    {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null)
        {
            imguri=data.getData();
            img.setImageURI(imguri);

            Log.d("myTag","requestCode: " + requestCode + ", resultCode: " + resultCode);
        }
        else{
            Log.d("myTag","requestCode: " + requestCode + ", resultCode: " + resultCode);
        }
    }
}