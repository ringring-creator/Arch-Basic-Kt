package com.ring.ring.data

import com.russhwolf.settings.Settings
import com.russhwolf.settings.get
import com.russhwolf.settings.set

class KeyValueSessionDataSource(
    private val settings: Settings
) {
    fun getLong(key: String): Long? {
        return settings[key]
    }

    fun getString(key: String): String? {
        return settings[key]
    }

    fun save(key: String, value: Long) {
        settings[key] = value
    }

    fun save(key: String, value: String) {
        settings[key] = value
    }
}