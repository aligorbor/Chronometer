package ru.geekbrains.android2.chronometer.model

import kotlinx.coroutines.flow.StateFlow

internal class Repository(
    private val dataSource1: DataSource = DataSource(),
    private val dataSource2: DataSource = DataSource()
) {

    val ticker1: StateFlow<String> = dataSource1.ticker

    fun start1() = dataSource1.start()

    fun pause1() = dataSource1.pause()

    fun stop1() = dataSource1.stop()

    val ticker2: StateFlow<String> = dataSource2.ticker

    fun start2() = dataSource2.start()

    fun pause2() = dataSource2.pause()

    fun stop2() = dataSource2.stop()
}