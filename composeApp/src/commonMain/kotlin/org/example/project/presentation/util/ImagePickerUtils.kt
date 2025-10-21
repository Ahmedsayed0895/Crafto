package org.example.project.presentation.util

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import com.mohamedrejeb.calf.core.LocalPlatformContext
import com.mohamedrejeb.calf.io.getName
import com.mohamedrejeb.calf.io.readByteArray
import com.mohamedrejeb.calf.picker.FilePickerFileType
import com.mohamedrejeb.calf.picker.FilePickerSelectionMode
import com.mohamedrejeb.calf.picker.rememberFilePickerLauncher
import kotlinx.coroutines.launch
import org.example.project.presentation.model.ImageData
import org.example.project.util.AppConstants
import org.example.project.util.AppLogger
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

class ImagePickerLauncher(
    private val launch: () -> Unit
) {
    fun launch() = launch.invoke()
}

@OptIn(ExperimentalTime::class)
@Composable
fun rememberImagePicker(
    singleSelection: Boolean = true,
    onImagesSelected: (List<ImageData>) -> Unit,
    onError: (String) -> Unit = {}
): ImagePickerLauncher {
    val scope = rememberCoroutineScope()
    val context = LocalPlatformContext.current

    val launcher = rememberFilePickerLauncher(
        type = FilePickerFileType.Image,
        selectionMode = if (singleSelection) {
            FilePickerSelectionMode.Single
        } else {
            FilePickerSelectionMode.Multiple
        }
    ) { files ->
        scope.launch {
            try {
                AppLogger.d("ImagePicker", " Files selected: ${files.size}")

                val imageDataList = files.mapNotNull { file ->
                    try {
                        val originalFileName = file.getName(context)
                        AppLogger.d("ImagePicker", "Processing file: $originalFileName")

                        val byteArray = file.readByteArray(context)
                        AppLogger.d("ImagePicker", "  Size: ${byteArray.size} bytes")

                        // Basic validation
                        if (byteArray.size > AppConstants.FileUpload.MAX_FILE_SIZE) {
                            onError("Image is too large. Maximum size is ${AppConstants.FileUpload.MAX_FILE_SIZE_MB}MB")
                            AppLogger.e("ImagePicker", "   File too large!")
                            return@mapNotNull null
                        }

                        // CRITICAL: Ensure proper file extension
                        val fileName = ensureProperFileName(originalFileName, byteArray)
                        AppLogger.d("ImagePicker", "  Final fileName: $fileName")

                        // Validate extension
                        val extension = fileName.substringAfterLast('.', "").lowercase()
                        if (extension !in AppConstants.FileUpload.ALLOWED_IMAGE_TYPES) {
                            AppLogger.e("ImagePicker", "   Invalid extension: $extension")
                            onError("Invalid image format. Please select JPG or PNG")
                            return@mapNotNull null
                        }

                        AppLogger.d("ImagePicker", "   File validated successfully")

                        ImageData(
                            uri = fileName,
                            fileName = fileName,
                            byteArray = byteArray
                        )
                    } catch (e: Exception) {
                        AppLogger.e("ImagePicker", "   Error processing file: ${e.message}")
                        e.printStackTrace()
                        null
                    }
                }

                if (imageDataList.isNotEmpty()) {
                    AppLogger.d("ImagePicker", " Successfully processed ${imageDataList.size} images")
                    onImagesSelected(imageDataList)
                } else {
                    AppLogger.d("ImagePicker", "⚠ No valid images processed")
                }
            } catch (e: Exception) {
                AppLogger.e("ImagePicker", " Failed to pick images: ${e.message}")
                onError("Failed to pick images")
            }
        }
    }

    return remember {
        ImagePickerLauncher { launcher.launch() }
    }
}

@OptIn(ExperimentalTime::class)
private fun ensureProperFileName(originalFileName: String?, byteArray: ByteArray): String {
    // If we have a filename with valid extension, use it
    if (!originalFileName.isNullOrBlank()) {
        val extension = originalFileName.substringAfterLast('.', "").lowercase()
        if (extension in AppConstants.FileUpload.ALLOWED_IMAGE_TYPES) {
            return originalFileName
        }

        val detectedExt = detectImageFormat(byteArray)
        val baseFileName = originalFileName.substringBeforeLast('.', originalFileName)
        return "$baseFileName.$detectedExt"
    }

    val extension = detectImageFormat(byteArray)
    return "image_${Clock.System.now().toEpochMilliseconds()}.$extension"
}


private fun detectImageFormat(byteArray: ByteArray): String {
    if (byteArray.size < 4) return "jpg"

    // PNG signature: 89 50 4E 47
    if (byteArray[0] == 0x89.toByte() &&
        byteArray[1] == 0x50.toByte() &&
        byteArray[2] == 0x4E.toByte() &&
        byteArray[3] == 0x47.toByte()) {
        return "png"
    }

    // JPEG signature: FF D8 FF
    if (byteArray[0] == 0xFF.toByte() &&
        byteArray[1] == 0xD8.toByte() &&
        byteArray[2] == 0xFF.toByte()) {
        return "jpg"
    }

    return "jpg"
}