package ru.geekbrains.android2.chronometer.utils

import ru.geekbrains.android2.chronometer.model.data.ChronometerState
import ru.geekbrains.android2.chronometer.model.database.TimestampProvider

class ChronometerStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
) {

    fun calculateRunningState(oldState: ChronometerState): ChronometerState.Running =
        when (oldState) {
            is ChronometerState.Running -> oldState
            is ChronometerState.Paused -> {
                ChronometerState.Running(
                    startTime = timestampProvider.getMilliseconds(),
                    elapsedTime = oldState.elapsedTime
                )
            }
        }

    fun calculatePausedState(oldState: ChronometerState): ChronometerState.Paused =
        when (oldState) {
            is ChronometerState.Running -> {
                val elapsedTime = elapsedTimeCalculator.calculate(oldState)
                ChronometerState.Paused(elapsedTime = elapsedTime)
            }
            is ChronometerState.Paused -> oldState
        }
}