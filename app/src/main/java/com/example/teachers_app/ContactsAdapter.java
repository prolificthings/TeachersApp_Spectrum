package com.example.teachers_app;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.viewHolder> {

private ArrayList<Phone> people;
private Context context;
public ContactsAdapter(Context context, ArrayList<Phone> list){
    this.context = context;
    people = list;
}

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contactlist_item,parent,false);
        return new viewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.name.setText(people.get(position).getName());
        holder.branch.setText(people.get(position).getBranch());
        holder.year.setText(people.get(position).getYear());
        holder.number.setText(people.get(position).getNumber());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder{

        private static final int REQUEST_CALL = 1;
    TextView name, branch, year, number;
    ImageView call;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.nameTxt);
            branch = itemView.findViewById(R.id.branchTxt);
            year = itemView.findViewById(R.id.yearTxt);
            number = itemView.findViewById(R.id.numbeTxt);
            call = itemView.findViewById(R.id.cllImg);

            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    call.setBackgroundColor(context.getResources().getColor(R.color.green));
                    callBtn();

                }
            });
        }

        private void callBtn() {
            String num = number.getText().toString();
            if (num.trim().length()>0){
                if(ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions((Activity) context,new String[]{Manifest.permission.CALL_PHONE},REQUEST_CALL);
                } else{
                    String dial = "tel:"+ num;
                    context.startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
            }
        }

    }

}
