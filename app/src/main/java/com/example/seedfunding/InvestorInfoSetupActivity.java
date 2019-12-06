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

import com.example.seedfunding.InvestorModel.Investor_upload;

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

public class InvestorInfoSetupActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;

    public CircleImageView InvestorProfileimage;
    TextView addphoto;
    private Button saveInvestorInfo;
    private EditText InvestorName,InvestorAbout,InvestorExperience,InvestorEducation,InvestorDomain,InvestorBudget;

    private Uri mInvestorImageUrl;
    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_info_setup);
        addphoto=findViewById(R.id.addphoto);
        InvestorProfileimage=findViewById(R.id.investorProfileImage);
        InvestorName=findViewById(R.id.InvestorName);
        InvestorAbout=findViewById(R.id.InvestorAbout);
        InvestorExperience=findViewById(R.id.InvestorExperience);
        InvestorEducation=findViewById(R.id.InvestorEducation);
        InvestorDomain=findViewById(R.id.InvestorDomain);
        InvestorBudget=findViewById(R.id.InvestorBudget);
        saveInvestorInfo=findViewById(R.id.saveInvestorInfo);
        mAuth=FirebaseAuth.getInstance();


        mStorageRef= FirebaseStorage.getInstance().getReference("Investors");
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("Investors");

        addphoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openImageGallery();
            }
        });

        saveInvestorInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadInvestorInfo ();
                Intent intent=new Intent(InvestorInfoSetupActivity.this,InvestorLoginSignupActivity.class);
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

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            mInvestorImageUrl = data.getData();

            InvestorProfileimage.setImageURI(mInvestorImageUrl);
        }
        }

        private String getFileExtension (Uri uri){
            ContentResolver cr = getContentResolver();
            MimeTypeMap mime = MimeTypeMap.getSingleton();
            return mime.getExtensionFromMimeType(cr.getType(uri));
        }


        private void uploadInvestorInfo () {
            if (mInvestorImageUrl != null) {
                StorageReference fileReference = mStorageRef.child(System.currentTimeMillis() + "." + getFileExtension(mInvestorImageUrl));

                fileReference.putFile(mInvestorImageUrl)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                String currentUserId = mAuth.getCurrentUser().getUid();
                                Toast.makeText(InvestorInfoSetupActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                Investor_upload Investor_upload = new Investor_upload(InvestorName.getText().toString().trim(), InvestorAbout.getText().toString().trim(),
                                        InvestorExperience.getText().toString().trim(), InvestorBudget.getText().toString().trim(), InvestorEducation.getText().toString().trim()
                                        , InvestorDomain.getText().toString().trim(), taskSnapshot.getMetadata().toString());
                                //  String startup_uploadID=mDatabaseRef.push().getKey();
                                mDatabaseRef.child(currentUserId).setValue(Investor_upload);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(InvestorInfoSetupActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {

                            }
                        });
            } else {
                Toast.makeText(this, "Add Profile Image", Toast.LENGTH_SHORT).show();
            }
        }
    }

