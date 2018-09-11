package com.instabridge.wifiprovider

import android.support.v4.content.LocalBroadcastManager
import java.util.*

class FakeWiFiProvider(numberOfWifis: Int = 100) : WiFiProvider {
    private var fakeWiFis : List<WiFi>

    init {
        if (numberOfWifis <= 0) throw IllegalArgumentException("number of wifis should be higher than 0")
        fakeWiFis = generateFakeWiFis(numberOfWifis)
    }

    override fun inRangeWiFis(): List<WiFi> {
        return fakeWiFis.filter { it.isInRange }
    }

    override fun nearbyWiFis(): List<WiFi> {
        return fakeWiFis.filter { !it.isInRange }
    }

    override fun updateWiFi(): Collection<WiFi> {
        return fakeWiFis.shuffled().take(1)
    }

    private fun generateFakeWiFis(numberOfWifis: Int) : List<WiFi> {
        val list = mutableListOf<WiFi>()

        for (i in 1..numberOfWifis) {
            list.add(WiFi(UUID.randomUUID().toString(), UUID.randomUUID().toString(), Random().nextBoolean()))
        }

        return list
    }
}