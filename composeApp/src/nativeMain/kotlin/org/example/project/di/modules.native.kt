package org.example.project.di

import org.example.project.Platform


class NativePlatform : Platform {
    override val name: String = "Native"
}

fun getPlatform(): Platform = NativePlatform()