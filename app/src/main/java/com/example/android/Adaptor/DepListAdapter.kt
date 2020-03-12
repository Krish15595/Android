package com.example.android.Adaptor

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.Db.entities.Dept_info
import com.example.android.R
import kotlinx.android.synthetic.main.row_singlename.view.*

class DepListAdapter(val deptList: ArrayList<Dept_info>):RecyclerView.Adapter<DepListAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DepListAdapter.ViewHolder {
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

    override fun onBindViewHolder(holder: DepListAdapter.ViewHolder, position: Int) {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        holder.name.text=deptList[position].d_name
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
