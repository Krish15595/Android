package com.example.android.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.Db.entities.Course_info
import com.example.android.R
import kotlinx.android.synthetic.main.row_singlename.view.*

class CourseListAdapter(val courseList: ArrayList<Course_info>) :
    RecyclerView.Adapter<CourseListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CourseListAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.row_singlename, parent, false)
        val viewHolder = CourseListAdapter.ViewHolder(listItem)
        return viewHolder
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

    override fun onBindViewHolder(holder: CourseListAdapter.ViewHolder, position: Int) {
        holder.name.text=courseList[position].c_name
    }
}