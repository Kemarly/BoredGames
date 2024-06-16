package com.example.boredgames;

/*import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Profile extends AppCompatActivity {

    ImageButton homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        homebutton = (ImageButton) findViewById(R.id.homeIcon);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoHome();
            }
        });
    }

    public void GoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}*/

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;


public class Profile extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextPhoneNumber;
    private ImageView btnUploadImage;
    private Button btnSaveProfile;
    private Button btnClearFields;
    private String savedImageUri;

    private ImageButton Home;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile); // Make sure you have this layout file

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        btnUploadImage = findViewById(R.id.imageViewUpload);
        btnSaveProfile = findViewById(R.id.btnSaveProfile);
        btnClearFields = findViewById(R.id.btnClearFields);
        btnUploadImage.setImageResource(R.drawable.baseline_person_pin_24);
        Home = findViewById(R.id.HomeButton);

        Home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoHome();
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        });

        btnSaveProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProfile();
            }
        });

        btnClearFields.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });

        loadSavedProfileData();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(btnUploadImage);
            saveImageUri(selectedImageUri);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveProfile();
    }

    private void saveProfile() {
        SharedPreferences preferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("firstName", editTextFirstName.getText().toString());
        editor.putString("lastName", editTextLastName.getText().toString());
        editor.putString("phoneNumber", editTextPhoneNumber.getText().toString());
        if (savedImageUri != null) {
            editor.putString("profileImageUri", savedImageUri);
        }
        editor.apply();
    }

    private void loadSavedProfileData() {
        SharedPreferences preferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        editTextFirstName.setText(preferences.getString("firstName", ""));
        editTextLastName.setText(preferences.getString("lastName", ""));
        editTextPhoneNumber.setText(preferences.getString("phoneNumber", ""));
        btnUploadImage.setImageResource(R.drawable.baseline_person_pin_24);
        savedImageUri = preferences.getString("profileImageUri", "");
        Glide.with(this).load(savedImageUri).into(btnUploadImage);
    }

    private void clearFields() {
        editTextFirstName.setText("");
        editTextLastName.setText("");
        editTextPhoneNumber.setText("");
        btnUploadImage.setImageResource(R.drawable.baseline_person_pin_24);
        SharedPreferences preferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("profileImageUri");
        editor.apply();
        savedImageUri = null;
    }

    private void saveImageUri(Uri imageUri) {
        savedImageUri = imageUri.toString();
        SharedPreferences preferences = getSharedPreferences("UserProfile", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("profileImageUri", savedImageUri);
        editor.apply();
    }

    private void GoHome(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}