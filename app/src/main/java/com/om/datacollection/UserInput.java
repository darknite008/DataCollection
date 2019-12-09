package com.om.datacollection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.om.datacollection.database.DataHelper;
import com.om.datacollection.model.UserData;

public class UserInput extends AppCompatActivity {
    EditText etname, etsalary, etage;
    Button btnRegister;
    DataHelper dataHelper=new DataHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_input);

        etname=findViewById(R.id.etname);
        etsalary=findViewById(R.id.etsalary);
        etage=findViewById(R.id.etage);
        btnRegister=findViewById(R.id.btnregister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userstore();
            }
        });
    }

     void userstore() {
        if (!TextUtils.isEmpty(etname.getText().toString())) {
        if (!TextUtils.isEmpty(etsalary.getText().toString())) {
        if (!TextUtils.isEmpty(etage.getText().toString())) {
            String name = etname.getText().toString();
             int age = Integer.parseInt(etage.getText().toString());
             int salary = Integer.parseInt(etsalary.getText().toString());
             UserData ud= new UserData();
            ud.setAge(age);
            ud.setSalary(salary);
            ud.setName(name);
             if (dataHelper.adduser(ud) ) {
               etname.setText("");
               etsalary.setText("");
               etage.setText("");
               Toast msg = Toast.makeText(this,"User Inserted!.",Toast.LENGTH_SHORT);
               msg.show();
             }else {
               Toast msg = Toast.makeText(this,"Error signing up! Please try again later.",Toast.LENGTH_SHORT);
               msg.show();
             }
            } else {
              etage.setError("Please Enter Age");
                }
            } else {
                etsalary.setError("Please Enter Salary");
            }
        } else {
            etname.setError("Please Provide Your Name");
        }

    }
}
