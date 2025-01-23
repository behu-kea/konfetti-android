package com.example.confetti_timer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TimerViewModel: ViewModel() {
    var textToChange by mutableStateOf("Før ændring");
}