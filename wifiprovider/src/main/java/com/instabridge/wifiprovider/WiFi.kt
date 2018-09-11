package com.instabridge.wifiprovider

data class WiFi(val ssid: String, val bssid: String, val isInRange: Boolean)

interface WiFiProvider {
    fun inRangeWiFis() : Collection<WiFi>
    fun nearbyWiFis() : Collection<WiFi>
    fun updateWiFi() : Collection<WiFi>
}