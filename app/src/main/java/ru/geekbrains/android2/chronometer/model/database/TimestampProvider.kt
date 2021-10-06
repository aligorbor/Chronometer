package ru.geekbrains.android2.chronometer.model.database

interface TimestampProvider {
    fun getMilliseconds(): Long
}