package ru.geekbrains.android2.chronometer.viewmodel

sealed class AppState {
    data class Chronometer1(val data: String) : AppState()
    data class Chronometer2(val data: String) : AppState()
}
