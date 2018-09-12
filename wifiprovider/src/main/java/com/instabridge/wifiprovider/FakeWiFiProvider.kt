package com.instabridge.wifiprovider

import android.os.CountDownTimer
import java.util.*
import java.util.concurrent.TimeUnit

class FakeWiFiProvider(private val wifiProvider: WifiProvider) : WifiComponent {

    private val countDownTimer = object : CountDownTimer(Long.MAX_VALUE, TimeUnit.SECONDS.toMillis(5)) {
        override fun onFinish() {
            cancel()
        }

        override fun onTick(p0: Long) {
            val random = Random()
            if (random.nextBoolean()) {
                wifiProvider.onCloseByUpdate(generateFakeWiFis(random.nextInt(10)))
            } else {
                wifiProvider.onFarAwayUpdate(generateFakeWiFis(random.nextInt(10)))
            }
        }
    }

    override fun start() {
        countDownTimer.start()
    }

    override fun stop() {
        countDownTimer.onFinish()
    }

    private fun generateFakeWiFis(numberOfWifis: Int) : List<WiFi> {
        val list = mutableListOf<WiFi>()

        for (i in 1..numberOfWifis) {
            list.add(WiFi(UUID.randomUUID().toString()))
        }

        return list
    }
}