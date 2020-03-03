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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class StartupInfoSetupActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;

    public CircleImageView startupProfileImage;
    TextView addphoto;
    private Button saveStartupInfo;
    private EditText startupName,foundersName,teamMember1,teamMember2,teamMember3,teamMember4,teamMember5,StartupSummary,startupDomain;
    // String startupId;
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

                String currentStartupUserId = mAuth.getCurrentUser().getUid();
                Intent intent=new Intent(StartupInfoSetupActivity.this,SendFundingInterestActivity.class);
                intent.putExtra("currentStartupUserId",currentStartupUserId);
                startActivity(intent);



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
                                  ,teamMember4.getText().toString().trim(),teamMember5.getText().toString().trim(),StartupSummary.getText().toString().trim(),startupDomain.getText().toString().trim()
                          ,taskSnapshot.getMetadata().toString());

                          mDatabaseRef.child(currentUserId).setValue(startup_upload);

                      //    HashMap<String,Object> hashMap=new HashMap<>();
                     //     hashMap.put("currentStartupUserId",currentUserId);
                      //    mDatabaseRef.child(currentUserId).push().setValue(hashMap);

                         Intent intent=new Intent(StartupInfoSetupActivity.this,SendFundingInterestActivity.class);
                          intent.putExtra("currentStartupUserId",currentUserId);
                          startActivity(intent);



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


      /**  String currentUserId = mAuth.getCurrentUser().getUid();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Startups").child(currentUserId);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("startupName",startupName.getText().toString());
        hashMap.put("foundersName",foundersName.getText().toString());
        hashMap.put("teamMember1",teamMember1.getText().toString());
        hashMap.put("teamMember2",teamMember2.getText().toString());
        hashMap.put("teamMember3",teamMember3.getText().toString());
        hashMap.put("teamMember4",teamMember4.getText().toString());
        hashMap.put("teamMember5",teamMember5.getText().toString());
        hashMap.put("StartupSummary",StartupSummary.getText().toString());
        hashMap.put("StartupDomain",startupDomain.getText().toString());
        hashMap.put("mImageUrl","default");
        hashMap.put("startupId",currentUserId);


        mDatabaseRef.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Intent intent=new Intent(StartupInfoSetupActivity.this,StartupLoginSignupActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
       **/


    }
}
