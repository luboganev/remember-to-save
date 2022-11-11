package dev.luboganev.remembertosave

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.util.UUID

class MainViewModel : ViewModel() {

    private val _mutableLiveData = MutableLiveData(ViewData("", ""))
    val liveData: LiveData<ViewData> = _mutableLiveData

    init {
        viewModelScope.launch {
            DateTimeUseCase.flow.collect {
                _mutableLiveData.value = ViewData(
                    key = UUID.randomUUID().toString(),
                    dateTime = it,
                )
            }
        }
    }
}