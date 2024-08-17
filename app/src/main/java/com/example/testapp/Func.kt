package com.example.testapp

import android.app.Activity
import android.view.WindowManager
import java.io.IOException
import java.io.InputStream

fun Activity.fullscreenTransparentStatusNavigation(){
    window.setFlags(
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
        WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
}

fun Activity.readAssets(fileNamed: String): String{
    /**fileNamed= "testonly.txt"**/
    return try {
        var myOutput= ""
        val fileInputStream: InputStream = assets.open(fileNamed)
        val size: Int = fileInputStream.available()
        val buffer = ByteArray(size)
        fileInputStream.read(buffer)
        myOutput = String(buffer)

        myOutput
    } catch (e: IOException) {
        return e.toString()
    }
}