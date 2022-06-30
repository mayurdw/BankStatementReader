package com.mayurdw.bankstatementreader

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class BankStatementReaderApp : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(MyDebugTree())
        }
    }

    inner class MyDebugTree : Timber.DebugTree() {
        override fun createStackElementTag(element: StackTraceElement): String {
            return String.format(
                "[%s:%s]",
                element.fileName,
                element.lineNumber
            )
        }
    }
}