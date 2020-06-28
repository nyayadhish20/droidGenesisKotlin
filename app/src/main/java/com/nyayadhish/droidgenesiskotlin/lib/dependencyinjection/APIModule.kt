package com.nyayadhish.droidgenesiskotlin.lib.dependencyinjection

import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.google.gson.Gson
import com.nyayadhish.droidgenesiskotlin.lib.application.MainApplication
import com.nyayadhish.droidgenesiskotlin.lib.preferrences.AppData
import com.nyayadhish.droidgenesiskotlin.lib.retrofit.APIService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by Nikhil Nyayadhish
 */
@Module
class APIModule(
    @get:Provides
    @get:Singleton
    internal val app: MainApplication
) {
    internal val meGson: Gson
        @Provides
        @Singleton
        get() = Gson()


    internal val apiServices: APIService.Companion
        @Provides
        @Singleton
        get() = APIService.Companion

    internal val dashboardApiServices: APIService
        @Provides
        @Singleton
        get() = APIService.getDashboardBaseUrl()

    internal val onBoardingApiServices: APIService
        @Provides
        @Singleton
        get() = APIService.getOnBoardingBaseUrl()



    internal val appData: AppData
        @Provides
        @Singleton
        get() = AppData(app)

    @Provides
    @Singleton
    internal fun providePreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(app)
    }
}
