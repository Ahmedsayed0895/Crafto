import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompilationTask

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.google.services)
    alias(libs.plugins.firebase.crashlytics)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
            freeCompilerArgs += listOf("-Xbinary=bundleId=org.example.project.Crafto")
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            implementation(libs.firebase.analytics)
            implementation(project.dependencies.platform(libs.firebase.bom))
            implementation(libs.firebase.crashlytics.ktx)

            implementation(libs.ktor.client.okhttp)
            implementation(libs.ktor.client.cio)
            implementation(libs.ktor.client.android)

        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodelCompose)
            implementation(libs.androidx.lifecycle.runtimeCompose)
            //adaptive
            implementation(libs.adaptive)
            implementation(libs.adaptive.layout)
            implementation(libs.adaptive.navigation)
            //koin
            api(libs.koin.core)
            api(libs.koin.annotations)
            implementation(libs.koin.compose)
            implementation(libs.koin.compose.viewmodel)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.serialization.kotlinx.json)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.logging)

            implementation(libs.bundles.coil)


        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }

    sourceSets.named("commonMain").configure {
        kotlin.srcDir("build/generated/ksp/metadata/commonMain/kotlin")
    }

}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
}

project.tasks.withType(KotlinCompilationTask::class.java).configureEach {
    if (name != "kspCommonMainKotlinMetadata") {
        dependsOn("kspCommonMainKotlinMetadata")
    }
}


android {
    namespace = "org.example.project"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.example.project"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

}

dependencies {
    debugImplementation(compose.uiTooling)
    add("kspCommonMainMetadata", libs.koin.ksp.compiler)
}

