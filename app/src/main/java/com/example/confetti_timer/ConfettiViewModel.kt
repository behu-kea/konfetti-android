package com.example.confetti_timer

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ConfettiViewModel: ViewModel() {
    var confettiKey by mutableStateOf(0)
    var timeToWait by mutableStateOf(0)

    fun onButtonClicked() {
//        runBlocking {
//            delay(timeToWait.toLong() * 1000)
//            // Trigger the confetti by incrementing the key
//            confettiKey++
//        }
        viewModelScope.launch {
            delay(timeToWait.toLong() * 1000) // Non-blocking delay
            confettiKey++

        }
    }

    fun onTimeChanged(newTime: String) {
        if(newTime.isNotEmpty()) timeToWait = newTime.toInt()
    }
}