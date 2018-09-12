package com.instabridge.wifiprovider

interface WifiProvider {
    fun onCloseByUpdate(items: List<WiFi>)
    fun onFarAwayUpdate(items: List<WiFi>)
}