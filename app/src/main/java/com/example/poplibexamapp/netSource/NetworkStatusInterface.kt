package com.example.poplibexamapp.netSource

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single

interface NetworkStatusInterface {
    fun isOnline(): Observable<Boolean>
    fun isOnlineSingle(): Single<Boolean>
}