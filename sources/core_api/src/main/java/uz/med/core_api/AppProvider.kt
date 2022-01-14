package uz.med.core_api

import android.content.Context
import kotlinx.coroutines.CoroutineDispatcher

interface AppProvider {

    fun provideContext(): Context

    fun provideCoroutineDispatcher(): CoroutineDispatcher

}
