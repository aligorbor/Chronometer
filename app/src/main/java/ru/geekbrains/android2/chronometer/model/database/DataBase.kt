package ru.geekbrains.android2.chronometer.model.database

object DataBase : TimestampProvider {
    override fun getMilliseconds(): Long {
        return System.currentTimeMillis()
    }
}