package ru.geekbrains.android2.chronometer.model.data

import ru.geekbrains.android2.chronometer.utils.ChronometerStateCalculator
import ru.geekbrains.android2.chronometer.utils.ElapsedTimeCalculator
import ru.geekbrains.android2.chronometer.utils.TimestampMillisecondsFormatter

class ChronometerStateHolder(
    private val chronometerStateCalculator: ChronometerStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMillisecondsFormatter: TimestampMillisecondsFormatter
) {

    var currentState: ChronometerState = ChronometerState.Paused(0)
        private set

    fun start() {
        currentState = chronometerStateCalculator.calculateRunningState(currentState)
    }

    fun pause() {
        currentState = chronometerStateCalculator.calculatePausedState(currentState)
    }

    fun stop() {
        currentState = ChronometerState.Paused(0)
    }

    fun getStringTimeRepresentation(): String {
        val elapsedTime = when (val currentState = currentState) {
            is ChronometerState.Paused -> currentState.elapsedTime
            is ChronometerState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMillisecondsFormatter.format(elapsedTime)
    }
}