package com.example.wizardsofhogwarts

import android.app.Application
import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WizardsOfHogwartsApplication : MultiDexApplication() {
}