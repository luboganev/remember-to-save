package dev.luboganev.remembertosave

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUseCase {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    private val _mutableStateFlow = MutableStateFlow(simpleDateFormat.format(Date()))

    val flow: Flow<String> = _mutableStateFlow

    fun updateTime() {
        _mutableStateFlow.value = simpleDateFormat.format(Date())
    }
}