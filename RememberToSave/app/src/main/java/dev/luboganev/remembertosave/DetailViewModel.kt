package dev.luboganev.remembertosave

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class DetailViewModel : ViewModel() {
    fun updateTime() {
        viewModelScope.launch {
            DateTimeUseCase.updateTime()
        }
    }
}