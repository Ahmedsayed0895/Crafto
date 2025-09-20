package org.example.project.data.mapper

import org.example.project.data.model.CraftsmanPortfolioLocalDto
import org.example.project.domain.entity.CraftsmanPortfolio

fun CraftsmanPortfolio.toLocalDto() = CraftsmanPortfolioLocalDto(
    photoUrls = photoUrls,
    description = description,
    localPhotoPaths = photoPaths
)

fun CraftsmanPortfolioLocalDto.toDomain() = CraftsmanPortfolio(
    photoUrls = photoUrls,
    description = description,
    photoPaths = localPhotoPaths
)