package com.example.android.ui.course_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.Repositories.CourseRepository
import com.example.android.Repositories.DeptRepository
import com.example.android.Repositories.UserRepository
import com.example.android.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class CourseViewModelFactory(
    private val repository: CourseRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CourseViewModel(repository) as T
    }
}