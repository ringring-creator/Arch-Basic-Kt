package com.ring.ring.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class KeyValueSessionDataSource(
    private val settings: Settings
) {
    fun save(key: String, value: Long) {
        settings[key] = value
    }

    fun save(key: String, value: String) {
        settings[key] = value
    }
}