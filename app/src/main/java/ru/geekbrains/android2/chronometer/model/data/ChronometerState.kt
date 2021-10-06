package ru.geekbrains.android2.chronometer.model.data

sealed class ChronometerState {

    data class Paused(
        val elapsedTime: Long
    ) : ChronometerState()

    data class Running(
        val startTime: Long,
        val elapsedTime: Long
    ) : ChronometerState()
}
