import org.example.project.domain.entity.Category

fun org.example.project.data.dto.Category.toDomain(): Category{
    return Category(
        id = id?.toInt() ?:0,
        title = categoryName?:"",
        color = categoryColor?:""
    )
}

fun Category.toDto(): org.example.project.data.dto.Category{
    return org.example.project.data.dto.Category(
        id = id.toString(),
        categoryName = title,
        categoryColor = color
    )
}
