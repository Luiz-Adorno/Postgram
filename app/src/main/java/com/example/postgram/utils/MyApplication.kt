package com.example.postgram.utils

/**
 * Created by Luiz Adorno on October 21, 2021
 * Linkedln profile https://www.linkedin.com/in/luiz-adorno/
 */

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Annotation for marking the Application class where the Dagger components should be generated
@HiltAndroidApp
class MyApplication : Application()