package ru.geekbrains.android2.chronometer.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.geekbrains.android2.chronometer.model.Repository

internal class MainViewModel(
    private val repository: Repository = Repository()
) : ViewModel() {

    val liveData: MutableLiveData<AppState> = MutableLiveData()

    fun start1() = repository.start1()

    fun pause1() = repository.pause1()

    fun stop1() = repository.stop1()

    fun start2() = repository.start2()

    fun pause2() = repository.pause2()

    fun stop2() = repository.stop2()

    init {
        viewModelScope.launch {
            repository.ticker1.collect { data ->
                liveData.value = AppState.Chronometer1(data)
            }
        }
        viewModelScope.launch {
            repository.ticker2.collect { data ->
                liveData.value = AppState.Chronometer2(data)
            }
        }
    }
}