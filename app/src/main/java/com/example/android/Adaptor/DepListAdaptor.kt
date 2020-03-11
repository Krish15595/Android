package com.example.android.Adaptor

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.Db.entities.Dept_info
import com.example.android.Model.DeptModel
import com.example.android.R
import kotlinx.android.synthetic.main.row_singlename.view.*







class DepListAdaptor( val deptList: List<DeptModel>):RecyclerView.Adapter<DepListAdaptor.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepListAdaptor.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem = layoutInflater.inflate(R.layout.row_singlename, parent, false)
        val viewHolder = ViewHolder(listItem)
        return viewHolder
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.name
    }

    override fun getItemCount(): Int {
       // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        return deptList.size
    }

    override fun onBindViewHolder(holder: DepListAdaptor.ViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.name.text=deptList[position].name
    }
    /* class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
         val name = view.name
     }
     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepListAdaptor.ViewHolder {

         return ViewHolder(LayoutInflater.from(context).inflate(R.layout.row_singlename, parent, false))
     }


     override fun getItemCount(): Int {
         return deptList.size
     }

     override fun onBindViewHolder(holder: DepListAdaptor.ViewHolder, position: Int) {
         holder.name?.text=deptList.get(position).d_name
     }*/

}
