package com.example.android.ui.dept_info

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.android.Adaptor.DepListAdaptor
import com.example.android.Db.AppDatabase
import com.example.android.Db.entities.Dept_info
import com.example.android.R
import com.example.android.Repositories.DeptRepository
import com.example.android.databinding.ActivityDeptBinding
import com.example.android.util.Constants
import kotlinx.android.synthetic.main.activity_dept.*

class DeptActivity : AppCompatActivity(), DeptListenter {


    private lateinit var viewModels: DeptViewModel
    private lateinit var deptList:ArrayList<Dept_info>
    private lateinit var mAdaptor: DepListAdaptor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dept)
        val mService by lazy { Constants.getApi(this@DeptActivity) }
        deptList= arrayListOf()


        val db = AppDatabase(this)
        val repository = DeptRepository(mService, db)
        val factory = DeptViewModelFactory(repository)
        initHomeViewModel(factory)

    }

    private fun initHomeViewModel(factory: DeptViewModelFactory) {
       /* val layoutManager = LinearLayoutManager(this@DeptActivity)
        rc_dept.layoutManager = layoutManager
        mAdaptor= DepListAdaptor(deptList )
        rc_dept.adapter=mAdaptor*/
        val binding: ActivityDeptBinding = DataBindingUtil.setContentView(this, R.layout.activity_dept)
        viewModels = ViewModelProvider(this, factory).get(DeptViewModel::class.java)
        binding.viewModel = viewModels
        viewModels.deptListenter = this
        viewModels.getDept().observe(this, Observer { dept ->
            Log.d("data","${dept[dept.size-1].d_name}")
            deptList= dept as ArrayList<Dept_info>
            //viewData(deptList)
            mAdaptor=DepListAdaptor(deptList)
            val layoutManager = LinearLayoutManager(this@DeptActivity)
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            rc_dept.layoutManager = layoutManager
            rc_dept.setHasFixedSize(true)
//        mAdaptor= DepListAdaptor(this, deptList)
            rc_dept.setAdapter(mAdaptor)

        })


/*        val layoutManager = LinearLayoutManager(this@DeptActivity)
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rc_dept.layoutManager = layoutManager
        rc_dept.setHasFixedSize(true)
        rc_dept.adapter=DepListAdaptor(deptList)*/


       // Log.d("deplist",deptList[deptList.size-1].d_name)

    }

    private fun viewData(deptList: List<Dept_info>) {
       // rc_dept.setAdapter(DepListAdaptor(deptList as ArrayList<Dept_info>))
    }

    override fun onStarted() {

    }

    override fun onSuccess(dept: Dept_info) {
        Log.d("deptList","${dept.d_id}")


    }

    override fun onFailure(message: String) {

    }
}
