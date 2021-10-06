package ru.geekbrains.android2.chronometer.model

import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.geekbrains.android2.chronometer.model.data.ChronometerStateHolder
import ru.geekbrains.android2.chronometer.model.database.DataBase
import ru.geekbrains.android2.chronometer.model.database.TimestampProvider
import ru.geekbrains.android2.chronometer.utils.ChronometerStateCalculator
import ru.geekbrains.android2.chronometer.utils.ElapsedTimeCalculator
import ru.geekbrains.android2.chronometer.utils.TimestampMillisecondsFormatter

internal class DataSource(dataBase: TimestampProvider = DataBase) {

    private val chronometerStateHolder: ChronometerStateHolder =
        ChronometerStateHolder(
            ChronometerStateCalculator(
                dataBase,
                ElapsedTimeCalculator(dataBase)
            ),
            ElapsedTimeCalculator(dataBase),
            TimestampMillisecondsFormatter()
        )
    private val scope: CoroutineScope =
        CoroutineScope(
            Dispatchers.Main
                    + SupervisorJob()
        )

    private var job: Job? = null
    private val mutableTicker = MutableStateFlow("")
    val ticker: StateFlow<String> = mutableTicker

    fun start() {
        if (job == null) startJob()
        chronometerStateHolder.start()
    }

    private fun startJob() {
        scope.launch {
            while (isActive) {
                mutableTicker.value = chronometerStateHolder.getStringTimeRepresentation()
                delay(20)
            }
        }
    }

    fun pause() {
        chronometerStateHolder.pause()
        stopJob()
    }

    fun stop() {
        chronometerStateHolder.stop()
        stopJob()
        clearValue()
    }

    private fun stopJob() {
        scope.coroutineContext.cancelChildren()
        job = null
    }

    private fun clearValue() {
        mutableTicker.value = "00:00:000"
    }

}