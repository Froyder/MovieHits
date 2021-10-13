package com.example.poplibexamapp

import io.reactivex.Scheduler

interface CustomSchedulersInterface {

    fun background(): Scheduler
    fun mainThread(): Scheduler

}