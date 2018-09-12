package com.instabridge.wifiprovider

interface WifiProvider {
    fun onInRangeUpdate(items: List<WiFi>)
    fun onNearbyUpdate(items: List<WiFi>)
}