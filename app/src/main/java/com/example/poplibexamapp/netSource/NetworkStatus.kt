package com.example.poplibexamapp.netSource

import android.content.Context

import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class NetworkStatus @Inject constructor(context: Context) : NetworkStatusInterface {
    val netStatusSubject: BehaviorSubject<Boolean> = BehaviorSubject.create()

    init {
        netStatusSubject.onNext(false)
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val request = NetworkRequest.Builder().build()
        connectivityManager.registerNetworkCallback(request, object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                netStatusSubject.onNext(true)
            }

            override fun onUnavailable() {
                netStatusSubject.onNext(false)
            }

            override fun onLost(network: Network) {
                netStatusSubject.onNext(false)
            }
        })
    }

    override fun isOnline() = netStatusSubject
    override fun isOnlineSingle(): Single<Boolean> = netStatusSubject.first(false)
}