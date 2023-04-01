package com.example.lab11;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.lab11.ViewCourses;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CourseRVAdapter extends RecyclerView.Adapter<CourseRVAdapter.ViewHolder> {

    // variable for our array list and context
    private ArrayList<CourseModal> courseModalArrayList;
    private Context context;
    // constructor
    public CourseRVAdapter(ArrayList<CourseModal> courseModalArrayList, Context context) {
        this.courseModalArrayList = courseModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // on below line we are inflating our layout
        // file for our recycler view items.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.course_rv_item, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data
        // to our views of recycler view item.
        CourseModal modal = courseModalArrayList.get(position);
        holder.courseNameTV.setText(modal.getCourseName());
        holder.courseDescTV.setText(modal.getCourseDescription());
        holder.courseDateTV.setText(modal.getselectedDate());

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener(){
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

                menu.setHeaderTitle("Chọn");
                menu.add(0, R.id.update, 0, "Cập Nhật").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
                        Intent i = new Intent(context, UpdateNoteActivity.class);
                        // below we are passing all our values.
                        i.putExtra("name", modal.getCourseName());
                        i.putExtra("description", modal.getCourseDescription());
                        i.putExtra("ngay", modal.getselectedDate());
                        // starting our activity.
                        context.startActivity(i);
                        return false;
                    }
                });
                menu.add(0, R.id.delete, 0, "Xóa").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @SuppressLint("NotifyDataSetChanged")
                    @Override
                    public boolean onMenuItemClick(@NonNull MenuItem menuItem) {
//                        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
//                        int position = info.position;
                        DBHandler dbHandler = new DBHandler(context);
                        //dbHandler.deleteCourse(modal.getId());
                          dbHandler.deleteCourse(modal.getCourseName());
                          courseModalArrayList.remove(holder.getAdapterPosition());
//                        courseModalArrayList.remove(position);
                        Toast.makeText(context.getApplicationContext(), "Delete", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
                        return true;
                    }
                });

            }
        });


        // below line is to add on click listener for our recycler view item.
        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // on below line we are calling an intent.
                Intent i = new Intent(context, UpdateNoteActivity.class);

                // below we are passing all our values.
                i.putExtra("name", modal.getCourseName());
                i.putExtra("description", modal.getCourseDescription());
                i.putExtra("ngay", modal.getselectedDate());


                // starting our activity.
                context.startActivity(i);
            }
        });
*/
    }

    @Override
    public int getItemCount() {
        // returning the size of our array list
        return courseModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our text views.
        private TextView courseNameTV, courseDescTV, courseDateTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            courseNameTV = itemView.findViewById(R.id.idTVCourseName);
            courseDescTV = itemView.findViewById(R.id.idTVCourseDescription);
            courseDateTV = itemView.findViewById(R.id.idTVDate);

        }
    }
}

