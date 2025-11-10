import org.example.project.di.NetworkModule
import org.example.project.di.ViewModelModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.ksp.generated.module

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        config?.invoke(this)
        modules(
            CraftoModule().module,
            ViewModelModule().module,
            NetworkModule().module
        )
    }
}