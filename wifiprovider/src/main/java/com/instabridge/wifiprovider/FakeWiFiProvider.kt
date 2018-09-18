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
                wifiProvider.onCloseByUpdate(generateFakeWiFis(random.nextInt(10), -70, 0, generateRandomBoolen()))
            } else {
                wifiProvider.onFarAwayUpdate(generateFakeWiFis(random.nextInt(10), -120, -71, generateRandomBoolen()))
            }
        }
    }

    override fun start() {
        countDownTimer.start()
    }

    override fun stop() {
        countDownTimer.onFinish()
    }

    private fun generateFakeWiFis(numberOfWifis: Int, minRssi: Int, maxRssi: Int, hasPassword: Boolean): List<WiFi> {
        val list = mutableListOf<WiFi>()

        for (i in 1..numberOfWifis) {
            list.add(WiFi(UUID.randomUUID().toString(), generateRandomRssi(minRssi, maxRssi), generateRandomBoolen()))
        }

        return list
    }

    private fun generateRandomRssi(min: Int, max: Int): Int {
        return Random().nextInt(max - min + 1) + min
    }

    private fun generateRandomBoolen(): Boolean {
        return Random().nextBoolean()
    }
}