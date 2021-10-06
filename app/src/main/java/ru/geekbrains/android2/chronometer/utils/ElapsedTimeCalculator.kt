package ru.geekbrains.android2.chronometer.utils

import ru.geekbrains.android2.chronometer.model.data.ChronometerState
import ru.geekbrains.android2.chronometer.model.database.TimestampProvider

class ElapsedTimeCalculator(
    private val timestampProvider: TimestampProvider,
) {

    fun calculate(state: ChronometerState.Running): Long {
        val currentTimestamp = timestampProvider.getMilliseconds()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else {
            0
        }
        return timePassedSinceStart + state.elapsedTime
    }
}
