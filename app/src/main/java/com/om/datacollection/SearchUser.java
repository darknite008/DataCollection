package com.om.datacollection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.SearchView;
import android.widget.Toast;

import com.om.datacollection.adapter.AdapterRecycleView;
import com.om.datacollection.database.DataHelper;
import com.om.datacollection.model.UserData;

import java.util.ArrayList;
import java.util.List;

public class SearchUser extends AppCompatActivity {
    RecyclerView recyclerView;
    SearchView searchView;
    String name;
    int age,salary,id;
    DataHelper dataHelper=new DataHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        searchView = findViewById(R.id.searchuser);
        recyclerView = findViewById(R.id.recyclesearch);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Cursor cursor=dataHelper.searchEmployess(Integer.parseInt(query));

              if(cursor.getCount()==0){
                  Toast.makeText(SearchUser.this, "NO ID FOUND!Please Try Again!", Toast.LENGTH_SHORT).show();
                  return false;
              }while (cursor.moveToNext()){
                    List<UserData> userDataList=new ArrayList<>();
                    id=cursor.getInt(0);
                    name=cursor.getString(1);
                    age=cursor.getInt(3);
                    salary=cursor.getInt(2);
                    userDataList.add(new UserData(id,name,salary,age));
                    AdapterRecycleView adapterRecycleView=new AdapterRecycleView(getApplicationContext(),userDataList);
                    recyclerView.setAdapter(adapterRecycleView);
                    recyclerView.setLayoutManager(new LinearLayoutManager(SearchUser.this));
                }

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
