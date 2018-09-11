package com.instabridge.wifiprovider

import org.junit.Assert
import org.junit.Test

class FakeWiFiProviderTest {

    @Test(expected = IllegalArgumentException::class)
    fun exceptionIfNumberOfWifisSmallerThan1() {
        FakeWiFiProvider(0).inRangeWiFis()[0]

    }

    @Test
    fun inRangeWiFis() {
        val fakeWiFiProvider = FakeWiFiProvider()

        Assert.assertTrue(fakeWiFiProvider.inRangeWiFis().filter { it.isInRange } == fakeWiFiProvider.inRangeWiFis())
    }

    @Test
    fun nearbyWiFis() {
        val fakeWiFiProvider = FakeWiFiProvider()

        Assert.assertTrue(fakeWiFiProvider.nearbyWiFis().filter { !it.isInRange } == fakeWiFiProvider.nearbyWiFis())
    }
}