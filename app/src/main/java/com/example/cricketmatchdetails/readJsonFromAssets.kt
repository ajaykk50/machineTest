package com.example.cricketmatchdetails

import android.content.Context
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset

fun readJsonFromAssets(context: Context, fileName: String): JSONObject? {
    try {
        val inputStream = context.assets.open(fileName)
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val jsonString = buffer.toString(Charset.defaultCharset())
        return JSONObject(jsonString)
    } catch (e: IOException) {
        e.printStackTrace()
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}
