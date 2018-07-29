package com.example.mayank.shoocal;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mayank.shoocal.Model.ApiResponse;
import com.example.mayank.shoocal.Model.User;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    MaterialBetterSpinner spinner;
    String spinnerText;
    EditText etFirstName;
    EditText etLastName;
    EditText etPhone;
    EditText etAddress;
    EditText etRest;
    FloatingActionButton fab;
    String[] SPINNERLIST = {
            "Owner",
            "Manager",
            "Other"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, SPINNERLIST);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spinner = findViewById(R.id.material_design_spinner);
        spinner.setAdapter(arrayAdapter);
        spinner.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                spinnerText = spinner.getText().toString();
            }
        });
        etFirstName = findViewById(R.id.input_first_name);
        etLastName = findViewById(R.id.input_last_name);
        etPhone = findViewById(R.id.input_phone);
        etAddress = findViewById(R.id.input_address);
        etRest = findViewById(R.id.input_rest_name);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getValue();
            }
        });
    }
    public void getValue(){
        String firstName = etFirstName.getText().toString();
        String lastName = etLastName.getText().toString();
        String phone = etPhone.getText().toString();
        String address = etAddress.getText().toString();
        String restName = etRest.getText().toString();
        if(spinnerText == null || firstName.equals("") || lastName.equals("") || address.equals("") || restName.equals("")){
            Toast.makeText(this,"All Fields Are Compulsory",Toast.LENGTH_SHORT).show();
            return;
        }
        int reqtType;
        if(spinnerText.equals("Owner"))
            reqtType = 1;
        else if(spinnerText.equals("Manager"))
            reqtType = 2;
        else
            reqtType = 3;
        User user = new User(firstName,lastName,phone,address,restName,reqtType);
        ViewModel.callPostApi(user,this);
        Log.d("Hello : : : :: : ", "getValue: " + user.toString());
    }

    public static void ResponseView(Context context,int resType){
        if(resType == 2){
            Toast.makeText(context,"There seems to be some Error.\n Please try later.",Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(context,Result.class);
        i.putExtra(Result.ResponseType,resType);
        context.startActivity(i);
    }
}
