package com.example.geosnapupload.utils

import android.content.Context
import android.os.Environment
import java.io.File

object FileUtils {
    fun getOutputDirectory(context: Context): File {
        val mediaDir = context.externalMediaDirs.firstOrNull()?.let {
            File(it, "GeoSnapUploads").apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else context.filesDir
    }
}