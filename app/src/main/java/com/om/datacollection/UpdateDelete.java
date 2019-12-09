package com.om.datacollection;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.om.datacollection.database.DataHelper;
import com.om.datacollection.model.UserData;

public class UpdateDelete extends AppCompatActivity {
    Button btn_search, btn_edit,btn_dele;
    EditText et_name, et_salary, et_age, et_search;
    DataHelper dbh = new DataHelper(this);
    String name;
    int age, salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        btn_search = findViewById(R.id.btn_se_update);
        btn_edit = findViewById(R.id.btn_edit);
        btn_dele = findViewById(R.id.btn_delete);
        et_search = findViewById(R.id.txtsearch);
        et_name = findViewById(R.id.up_name);
        et_age = findViewById(R.id.up_age);
        et_salary = findViewById(R.id.up_salary);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursor = dbh.searchEmployess(Integer.parseInt(et_search.getText().toString()));
                if (cursor.getCount() == 0) {
                    // show message
                    Toast.makeText(UpdateDelete.this, "No Such ID found", Toast.LENGTH_SHORT).show();
                    et_name.setText("User Data Not Found");
                    et_name.setEnabled(false);
                    et_age.setText("Null");
                    et_age.setEnabled(false);
                    et_salary.setText("Null");
                    et_salary.setEnabled(false);
                }
                while (cursor.moveToNext()) {
                    name = cursor.getString(1);
                    age = cursor.getInt(3);
                    salary = cursor.getInt(2);
                    et_name.setText(name);
                    et_name.setEnabled(true);
                    et_age.setText(String.valueOf(age));
                    et_age.setEnabled(true);
                    et_salary.setText(String.valueOf(salary));
                    et_salary.setEnabled(true);
                }

            }
        });
        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if (!TextUtils.isEmpty(et_name.getText().toString())) {
             if (!TextUtils.isEmpty(et_salary.getText().toString())) {
             if (!TextUtils.isEmpty(et_age.getText().toString())) {
             String name = et_name.getText().toString();
             int age = Integer.parseInt(et_age.getText().toString());
             int salary = Integer.parseInt(et_salary.getText().toString());
             UserData es = new UserData();
             es.setId(Integer.parseInt(et_search.getText().toString()));
             es.setAge(age);
             es.setSalary(salary);
             es.setName(name);
             if (dbh.Update(es)) {
             et_name.setText("");
             et_salary.setText("");
             et_age.setText("");
             Toast msg = Toast.makeText(UpdateDelete.this, "Update Successful.", Toast.LENGTH_SHORT);
             msg.show();
             clear();
             } else {
             Toast msg = Toast.makeText(UpdateDelete.this, "Update Error! Please try again later.", Toast.LENGTH_SHORT);
             msg.show();
             }
             } else {
             et_age.setError("Enter age");
             }
             } else {
             et_salary.setError("Enter salary");
             }
             } else {
             et_name.setError("Enter name");
             }
            }
        });
       btn_dele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            if (!TextUtils.isEmpty(et_search.getText().toString())) {
            int id=Integer.parseInt(et_search.getText().toString());
            if (dbh.Delete(id)) {
            Toast msg = Toast.makeText(UpdateDelete.this, "User Deleted Successfully.", Toast.LENGTH_SHORT);
            msg.show();
            clear();
            }else{
            Toast msg = Toast.makeText(UpdateDelete.this, "Not Deleted.", Toast.LENGTH_SHORT);
            msg.show();
            }
            }else{
            et_search.setError("Please enter the ID.");
            }
           }
        });
    }

    private void clear() {
        et_name.setText("");
        et_age.setText("");
        et_salary.setText("");
        et_search.setText("");
    }
}
