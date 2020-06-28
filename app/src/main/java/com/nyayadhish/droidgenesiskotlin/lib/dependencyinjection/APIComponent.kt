package com.nyayadhish.droidgenesiskotlin.lib.dependencyinjection

import com.google.gson.Gson
import com.nyayadhish.droidgenesiskotlin.lib.application.MainApplication
import com.nyayadhish.droidgenesiskotlin.lib.preferrences.AppData
import com.nyayadhish.droidgenesiskotlin.lib.retrofit.APIService
import dagger.Component
import javax.inject.Singleton

/**
 * Created by Nikhil Nyayadhish
 */

@Singleton
@Component(modules = [APIModule::class])
interface APIComponent {
    val gson: Gson
    val appData: AppData
    val retroService: APIService.Companion

    val app: MainApplication
}
