package com.example.playwithui.di

import android.app.Application
import com.example.playwithui.modal.repository.SongRepo
import com.example.playwithui.viewmodal.MainViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
class ApplicationModule {

    @Provides
    fun providesViewModel( repo: SongRepo)= MainViewModel(repo)
}