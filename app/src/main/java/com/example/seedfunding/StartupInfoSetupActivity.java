package com.example.seedfunding;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seedfunding.StartupModel.Startup_upload;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartupInfoSetupActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;

    public CircleImageView startupProfileImage;
    TextView addphoto;
    private Button saveStartupInfo;
    private EditText startupName,foundersName,teamMember1,teamMember2,teamMember3,teamMember4,teamMember5,StartupSummary,startupDomain;

    private Uri mImageUrl;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_info_setup);

                addphoto=findViewById(R.id.addphoto);
                startupProfileImage=findViewById(R.id.startupProfileImage);
                saveStartupInfo=findViewById(R.id.saveStartupInfo);
                startupDomain=findViewById(R.id.startupDomain);
                startupName=findViewById(R.id.startupName);
                foundersName=findViewById(R.id.foundersName);
                teamMember1=findViewById(R.id.teamMember1);
                teamMember2=findViewById(R.id.teamMember2);
                teamMember3=findViewById(R.id.teamMember3);
                teamMember4=findViewById(R.id.teamMember4);
                teamMember5=findViewById(R.id.teamMember5);
                StartupSummary=findViewById(R.id.StartupSummary);
                mAuth=FirebaseAuth.getInstance();


                mStorageRef= FirebaseStorage.getInstance().getReference("Startups");
                mDatabaseRef= FirebaseDatabase.getInstance().getReference("Startups");

                addphoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        openImageGallery();
                    }
                });

                saveStartupInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                         uploadStartupInfo();
                         Intent intent=new Intent(StartupInfoSetupActivity.this,StartupLoginSignupActivity.class);
                         startActivity(intent);
                         finish();
                    }
                });
    }

    private void openImageGallery() {
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==PICK_IMAGE_REQUEST && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            mImageUrl =data.getData();

            startupProfileImage.setImageURI(mImageUrl);
        }
    }

    private String getFileExtension(Uri uri){
        ContentResolver cr=getContentResolver();
        MimeTypeMap mime=MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(uri));
    }

    private void uploadStartupInfo() {
       if(mImageUrl !=null) {
          StorageReference fileReference=mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mImageUrl));

          fileReference.putFile(mImageUrl)
                  .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                      @Override
                      public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                          String currentUserId = mAuth.getCurrentUser().getUid();
                          Toast.makeText(StartupInfoSetupActivity.this,"Success",Toast.LENGTH_SHORT).show();
                          Startup_upload startup_upload=new Startup_upload(startupName.getText().toString().trim(),foundersName.getText().toString().trim(),
                                  teamMember1.getText().toString().trim(),teamMember2.getText().toString().trim(),teamMember3.getText().toString().trim()
                                  ,teamMember4.getText().toString().trim(),teamMember5.getText().toString().trim(),startupDomain.getText().toString().trim(),StartupSummary.getText().toString().trim()
                          ,taskSnapshot.getMetadata().toString());
                        //  String startup_uploadID=mDatabaseRef.push().getKey();
                          mDatabaseRef.child(currentUserId).setValue(startup_upload);
                      }
                  })
                  .addOnFailureListener(new OnFailureListener() {
                      @Override
                      public void onFailure(@NonNull Exception e) {
                        Toast.makeText(StartupInfoSetupActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                      }
                  })
                  .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                      @Override
                      public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                      }
                  });
       }else {
           Toast.makeText(this,"Add Profile Image",Toast.LENGTH_SHORT).show();
       }
    }
}
