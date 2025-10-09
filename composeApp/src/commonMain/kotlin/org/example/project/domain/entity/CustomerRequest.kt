package org.example.project.domain.entity

data class CustomerRequest(
    val customerId: Long,
    val requestStatus: String,
    val issueTitle: String,
    val category: Category,
)