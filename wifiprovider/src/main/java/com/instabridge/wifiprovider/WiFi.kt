package com.instabridge.wifiprovider

import java.io.Serializable

data class WiFi(val ssid: String = "", val rssi: Int, val hasPassword: Boolean) : Serializable {

    var frequency: Int? = null

    var bssid: String? = null
}