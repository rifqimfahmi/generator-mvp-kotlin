package <%= package %>.util.rx

import io.reactivex.Scheduler

/*
 * Created by Rifqi Mulya Fahmi on 13/02/18.
 */

interface SchedulerProvider {
    fun io(): Scheduler
    fun ui(): Scheduler
    fun computation(): Scheduler
}