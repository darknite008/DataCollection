package com.om.datacollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.Toast;

import com.om.datacollection.adapter.AdapterRecycleView;
import com.om.datacollection.database.DataHelper;
import com.om.datacollection.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class ShowUserData extends AppCompatActivity {
    RecyclerView recyclerView;
    DataHelper dataHelper = new DataHelper(this);
    List<UserData> userDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_user_data);

        recyclerView = findViewById(R.id.recycle);

        Cursor cursor = dataHelper.GetEmployess();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data Found!", Toast.LENGTH_SHORT).show();
            return;
        }
        while (cursor.moveToNext()) {
            userDataList.add(new UserData(cursor.getInt(0), cursor.getString(1),
                    cursor.getInt(2), cursor.getInt(3)));
        }
        AdapterRecycleView adapterRecycleView = new AdapterRecycleView(this, userDataList);
        recyclerView.setAdapter(adapterRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
      }
    }


