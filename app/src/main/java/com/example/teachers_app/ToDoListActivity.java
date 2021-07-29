package com.example.teachers_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.graphics.ColorSpace;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.Date;

public class ToDoListActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Button add;

    DatabaseReference dbRef;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    String userID;

    ProgressDialog loader;
    String key = "";
    String task;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do_list);

        recyclerView = findViewById(R.id.tdlView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout(true);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        loader = new ProgressDialog(this);

        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        userID = currentUser.getUid();
        dbRef = FirebaseDatabase.getInstance().getReference().child("tasks").child(userID);

        add = findViewById(R.id.addBtn);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addTask();
            }
        });


    }

    private void addTask() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);

        View view = inflater.inflate(R.layout.tdl_input,null);
        dialog.setView(view);

        AlertDialog alertDialog = dialog.create();
        alertDialog.setCancelable(true);

        EditText task = view.findViewById(R.id.taskTxt);
        Button cancel = view.findViewById(R.id.cancelBtn);
        Button save = view.findViewById(R.id.saveBtn);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newTask = task.getText().toString();
                String id = dbRef.push().getKey();
                String date = DateFormat.getDateInstance().format(new Date());

                if (TextUtils.isEmpty(newTask)){
                    task.setError("Enter a task");
                    return;
                }
                else{
                    loader.setMessage("Adding new task...");
                    loader.setCanceledOnTouchOutside(false);
                    loader.show();

                    tdlModel model = new tdlModel(newTask,id,date);
                    dbRef.child(id).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(ToDoListActivity.this, "Task is inserted successfully!", Toast.LENGTH_SHORT).show();
                                loader.dismiss();

                            } else{
                                String error = task.getException().toString();
                                Toast.makeText(ToDoListActivity.this, "Failed: "+error, Toast.LENGTH_SHORT).show();
                                loader.dismiss();
                            }
                        }
                    });
                }
            }
        });
        alertDialog.show();
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerOptions<tdlModel> options =
                new FirebaseRecyclerOptions.Builder<tdlModel>()
                        .setQuery(dbRef, tdlModel.class)
                        .build();

        FirebaseRecyclerAdapter<tdlModel,viewHolder> adapter = new FirebaseRecyclerAdapter<tdlModel, viewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewHolder holder, int position, @NonNull tdlModel model) {
                holder.setDate(model.getDate());
                holder.setTask(model.getTask());

                holder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        key = getRef(position).getKey();
                        task = model.getTask();

                        updateTask();

                    }
                });
            }

            @NonNull
            @Override
            public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tdl_list_item,parent,false);
                return new viewHolder(view);
            }
        };

        recyclerView.setAdapter(adapter);
        adapter.startListening();
    }

    private void updateTask() {
        AlertDialog.Builder updateDialog = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View update_view = inflater.inflate(R.layout.tdl_update,null);
        updateDialog.setView(update_view);

        AlertDialog dialog = updateDialog.create();

        EditText updateTask = update_view.findViewById(R.id.taskTxt_update);
        updateTask.setText(task);
        updateTask.setSelection(task.length());

        Button tdlUpdate = update_view.findViewById(R.id.updateBtn);
        Button tdlDlt = update_view.findViewById(R.id.dltBtn);

        tdlUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                task = updateTask.getText().toString().trim();
                String date = DateFormat.getDateInstance().format(new Date());
                tdlModel model = new tdlModel(task,key,date);

                dbRef.child(key).setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ToDoListActivity.this, "Task updated successfully!", Toast.LENGTH_SHORT).show();
                        } else{
                            String error = task.getException().toString();
                            Toast.makeText(ToDoListActivity.this, "Update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                dialog.dismiss();
            }
        });

        tdlDlt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.child(key).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(ToDoListActivity.this, "Task deleted successfully!", Toast.LENGTH_SHORT).show();
                        } else{
                            String error = task.getException().toString();
                            Toast.makeText(ToDoListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        View view;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setTask(String task){
            TextView taskDisplayTxt = view.findViewById(R.id.taskDisplay);
            taskDisplayTxt.setText(task);
        }

        public void setDate(String date){
            TextView dateDisplayTxt = view.findViewById(R.id.date);
            dateDisplayTxt.setText(date);
        }
    }
}