package ru.geekbrains.android2.chronometer.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import ru.geekbrains.android2.chronometer.R
import ru.geekbrains.android2.chronometer.viewmodel.AppState
import ru.geekbrains.android2.chronometer.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView = findViewById<TextView>(R.id.text_time)
        val textView2 = findViewById<TextView>(R.id.text_time2)

        val viewModel: MainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.liveData.observe(
            this,
            { appState ->
                when (appState) {
                    is AppState.Chronometer1 -> textView.text = appState.data
                    is AppState.Chronometer2 -> textView2.text = appState.data
                }
            }
        )

        findViewById<Button>(R.id.button_start).setOnClickListener {
            viewModel.start1()
        }
        findViewById<Button>(R.id.button_pause).setOnClickListener {
            viewModel.pause1()
        }
        findViewById<Button>(R.id.button_stop).setOnClickListener {
            viewModel.stop1()
        }
        findViewById<Button>(R.id.button_start2).setOnClickListener {
            viewModel.start2()
        }
        findViewById<Button>(R.id.button_pause2).setOnClickListener {
            viewModel.pause2()
        }
        findViewById<Button>(R.id.button_stop2).setOnClickListener {
            viewModel.stop2()
        }
    }
}