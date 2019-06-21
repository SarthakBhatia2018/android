package com.example.firebasefirstproject;

import android.app.DownloadManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
public class RecyclerViewHolders extends RecyclerView.ViewHolder implements View.OnClickListener {
    private static final String TAG = RecyclerViewHolders.class.getSimpleName();
    public ImageView markIcon;
    public TextView categoryTitle;
    public ImageView deleteIcon;
    private List<Task> taskObject;
    OnNoteListener onNoteListener;
    public RecyclerViewHolders(final View itemView, final List<Task> taskObject,OnNoteListener onNoteListener) {
        super(itemView);
        this.taskObject = taskObject;
        categoryTitle = (TextView)itemView.findViewById(R.id.task_title);
        markIcon = (ImageView)itemView.findViewById(R.id.task_icon);
        deleteIcon = (ImageView)itemView.findViewById(R.id.task_delete);
        itemView.setOnClickListener(this);
        this.onNoteListener=onNoteListener;
        deleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Delete icon has been clicked", Toast.LENGTH_LONG).show();
                String taskTitle = taskObject.get(getAdapterPosition()).getTask();
                Log.d(TAG, "Task Title " + taskTitle);
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                Query applesQuery = ref.orderByChild("task").equalTo(taskTitle);
                applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot appleSnapshot: dataSnapshot.getChildren()) {
                            appleSnapshot.getRef().removeValue();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        onNoteListener.onNoteClick(getAdapterPosition());
    }

    public interface OnNoteListener{
        void onNoteClick(int position);
    }
}