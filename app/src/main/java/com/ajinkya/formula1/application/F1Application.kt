package com.ajinkya.formula1.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * @HiltAndroidApp This annotation will generate a base class that the annotated class should extend,
 * either directly or via the Hilt Gradle Plugin.
 *
 * This base class will take care of injecting members into the Android class
 * as well as handling instantiating the proper Hilt components at the right point in the lifecycle.
 */
@HiltAndroidApp
class F1Application : Application()
