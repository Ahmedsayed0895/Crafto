package org.example.project.presentation.mapper

import androidx.compose.ui.graphics.Color
import org.example.project.domain.entity.Category
import org.example.project.domain.entity.CraftsmanPersonalInfo
import org.example.project.domain.model.WorkImage
import org.example.project.presentation.model.CategoryUi
import org.example.project.presentation.model.ImageData
import org.example.project.presentation.model.CraftsmanPersonalInfoUiModel

fun CraftsmanPersonalInfoUiModel.toDomain(): CraftsmanPersonalInfo {
    return CraftsmanPersonalInfo(
        firstName = firstName,
        lastName = lastName,
        phoneNumber = phoneNumber,
        address = address
    )
}

fun List<ImageData>.toWorkImages(): List<WorkImage> {
    return map { imageData ->
        WorkImage(
            data = imageData.byteArray,
            fileName = imageData.fileName
        )
    }
}

fun Category.toUi(): CategoryUi =
    CategoryUi(id, title, Color(colorHex))