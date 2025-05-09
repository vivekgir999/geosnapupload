package com.example.geosnapupload.utils

import android.content.Context
import android.net.Uri
import com.google.api.services.drive.Drive
import com.google.api.services.drive.model.File
import java.io.InputStream

class DriveUploader(private val driveService: Drive) {

    fun uploadFileToFolder(context: Context, uri: Uri, folderId: String? = null): String? {
        return try {
            val fileMetadata = File().apply {
                name = "GeoSnap_${System.currentTimeMillis()}.jpg"
                folderId?.let { parents = listOf(it) }
            }

            val inputStream: InputStream = context.contentResolver.openInputStream(uri)!!
            val mediaContent = com.google.api.client.http.InputStreamContent("image/jpeg", inputStream)

            val file = driveService.files().create(fileMetadata, mediaContent)
                .setFields("id")
                .execute()

            file.id
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}