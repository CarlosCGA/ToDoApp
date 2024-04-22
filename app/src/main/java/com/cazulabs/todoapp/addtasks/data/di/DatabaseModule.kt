package com.cazulabs.todoapp.addtasks.data.di

import android.content.Context
import androidx.room.Room
import com.cazulabs.todoapp.addtasks.data.TaskDAO
import com.cazulabs.todoapp.addtasks.data.ToDoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideTasksDAO(toDoDatabase: ToDoDatabase): TaskDAO {
        return toDoDatabase.getTaskDAO()
    }

    @Provides
    @Singleton
    fun provideToDoDatabase(@ApplicationContext appContext: Context): ToDoDatabase {
        return Room.databaseBuilder(appContext, ToDoDatabase::class.java, "TaskDatabase").build()
    }
}