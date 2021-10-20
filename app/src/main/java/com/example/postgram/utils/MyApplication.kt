package com.example.postgram.utils

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

//Annotation for marking the Application class where the Dagger components should be generated
@HiltAndroidApp
class MyApplication : Application()