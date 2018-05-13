package <%= package %>.di.component

import <%= package %>.di.PerService
import <%= package %>.di.module.ServiceModule
import dagger.Component

@PerService
@Component(dependencies = [ApplicationComponent::class], modules = [ServiceModule::class])
interface ServiceComponent {
    // Inject your service here
}