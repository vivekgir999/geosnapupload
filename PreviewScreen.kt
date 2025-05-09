package com.example.geosnapupload.ui

import android.content.Context
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RectangleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.example.geosnapupload.utils.DriveUploader

@Composable
fun PreviewScreen(navController: NavController, capturedImageUri: Uri, context: Context) {
    var isUploading by remember { mutableStateOf(false) }
    var uploadStatus by remember { mutableStateOf("Waiting for action...") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text("Preview Photo", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .border(1.dp, Color.Gray, RectangleShape)
        ) {
            // Display captured image
            Image(
                painter = rememberImagePainter(data = capturedImageUri),
                contentDescription = null,
                modifier = Modifier.fillMaxSize()
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedButton(onClick = { navController.popBackStack() }) {
                Text("Retake")
            }
            Button(onClick = {
                isUploading = true
                val driveUploader = DriveUploader(getDriveService(context))
                val fileId = driveUploader.uploadFileToFolder(context, capturedImageUri)
                uploadStatus = if (fileId != null) "Upload Successful! File ID: $fileId" else "Upload Failed!"
                isUploading = false
            }) {
                Text("Upload")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        if (isUploading) {
            CircularProgressIndicator()
        } else {
            Text(uploadStatus, fontSize = 14.sp, color = Color.Gray)
        }
    }
}