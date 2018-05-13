package <%= package %>.di.module

import android.app.Service
import dagger.Module

@Module
class ServiceModule(service: Service) {
    val mService = service
}