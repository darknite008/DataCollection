package com.om.datacollection.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.om.datacollection.R;
import com.om.datacollection.model.UserData;

import java.util.List;

public class AdapterRecycleView extends RecyclerView.Adapter<AdapterRecycleView.ContactsViewHolder> {
Context context;
List<UserData> userData;

    public AdapterRecycleView(Context context, List<UserData> userData) {
        this.context = context;
        this.userData = userData;
    }

    @NonNull
    @Override
    public AdapterRecycleView.ContactsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_view, parent, false);
        return new ContactsViewHolder(view);
        //activity_adapter_view
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterRecycleView.ContactsViewHolder holder, final int position) {
    final UserData ud= userData.get(position);
    holder.id.append(String.valueOf(ud.getId()));
    holder.name.append(ud.getName());
    holder.salary.append(String.valueOf(ud.getSalary()));
    holder.age.append(String.valueOf(ud.getAge()));
        holder.imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userData.remove(position);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return userData.size();
    }

    public class ContactsViewHolder extends RecyclerView.ViewHolder {
        TextView id,name,salary,age;
        ImageButton imageButton;
        public ContactsViewHolder(@NonNull View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.userid);
            name=itemView.findViewById(R.id.username);
            salary=itemView.findViewById(R.id.usersalary);
            age=itemView.findViewById(R.id.userage);
            imageButton=itemView.findViewById(R.id.imageButton);


        }
    }
}
