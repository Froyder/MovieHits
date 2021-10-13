package com.example.poplibexamapp

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class CustomSchedulers : CustomSchedulersInterface {

    override fun background(): Scheduler = io.reactivex.schedulers.Schedulers.newThread()

    override fun mainThread(): Scheduler = AndroidSchedulers.mainThread()
}