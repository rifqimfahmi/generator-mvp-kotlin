package <%= package %>.di.module

import android.content.Context
import android.support.v7.app.AppCompatActivity
import <%= package %>.di.ActivityContext
import <%= package %>.di.PerActivity
import <%= package %>.ui.splash.SplashMvpPresenter
import <%= package %>.ui.splash.SplashMvpView
import <%= package %>.ui.splash.SplashPresenter
import <%= package %>.util.rx.AppSchedulerProvider
import <%= package %>.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

@Module
class ActivityModule constructor(appCompatActivity: AppCompatActivity) {
    val mAppCompatActivity: AppCompatActivity = appCompatActivity

    @Provides
    @ActivityContext
    fun provideContext(): Context = mAppCompatActivity

    @Provides
    fun provideActivity(): AppCompatActivity = mAppCompatActivity

    @Provides
    fun provideScheduler(): SchedulerProvider = AppSchedulerProvider()

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable = CompositeDisposable()

    @Provides
    @PerActivity
    fun provideMvpPresenter(presenter: SplashPresenter<SplashMvpView>): SplashMvpPresenter<SplashMvpView> = presenter
}