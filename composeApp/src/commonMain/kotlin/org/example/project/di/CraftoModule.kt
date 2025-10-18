import org.example.project.domain.repository.LocationRepository
import org.koin.core.annotation.Module
import org.koin.dsl.module

@Module
class CraftoModule {
    val module = module {
        single<LocationRepository> { LocationRepositoryImpl(get()) }
    }
}