package com.example.playwithui.viewmodal

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playwithui.modal.repository.SongRepository

class ViewModalFactory(private val repository: SongRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        println(" Inn view odal factory")
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            println("Assighnabke class")
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}