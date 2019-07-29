package com.edu.wkx.myConcencamp.di.module

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
open class AppModule(val application: Application) {

    @Provides
    @Singleton
    fun provideContext(): Context = application

}