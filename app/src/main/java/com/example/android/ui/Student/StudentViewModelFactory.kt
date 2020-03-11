package com.example.android.ui.Student

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.android.Repositories.CourseRepository
import com.example.android.Repositories.DeptRepository
import com.example.android.Repositories.StudentRepository
import com.example.android.Repositories.UserRepository
import com.example.android.ui.home.HomeViewModel

@Suppress("UNCHECKED_CAST")
class StudentViewModelFactory(
    private val repository: StudentRepository
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return StudentViewModel(repository) as T
    }
}