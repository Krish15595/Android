package com.example.android.ui.dept_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.Repositories.DeptRepository
import com.example.android.Repositories.UserRepository
import com.example.android.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class DeptViewModelFactory(
    private val repository: DeptRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return DeptViewModel(repository) as T
    }
}