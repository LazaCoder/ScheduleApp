package com.example.scheduleapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CourseViewModel : ViewModel() {
    // StateFlow holding the list of courses (initially empty).
    private val _courses = MutableStateFlow<List<Course>>(emptyList())
    val courses: StateFlow<List<Course>> = _courses

    init {
        viewModelScope.launch {
            try {
                // Make the API call and update state.
                val response = ApiClient.apiService.getCourses()
                _courses.value = response.courses
            } catch (e: Exception) {
                e.printStackTrace() // Handle the error appropriately in production
            }
        }
    }
}
