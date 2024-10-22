package com.example.wizardsofhogwarts

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp

/**
 * Application class for the Wizards of Hogwarts app.
 * This class is responsible for initializing Dagger Hilt for dependency injection.
 */
@HiltAndroidApp
class WizardsOfHogwartsApplication : MultiDexApplication() {

}
