package dev.luboganev.remembertosave

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MovableContent
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dev.luboganev.remembertosave.ui.theme.RememberToSaveTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun App() {
    RememberToSaveTheme {
        val navController = rememberNavController()
        NavHost(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colors.primaryVariant)
                .padding(8.dp),
            navController = navController,
            startDestination = "main"
        ) {
            composable("main") {
                MainScreen {
                    navController.navigate("detail")
                }
            }
            composable("detail") {
                DetailScreen {
                    navController.popBackStack()
                }
            }
        }
    }
}

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = viewModel(),
    onClose: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            viewModel.updateTime()
        }) {
            Text(text = "Update time")
        }
        Button(onClick = {
            onClose()
        }) {
            Text(text = "Back")
        }
    }
}

class DetailViewModel : ViewModel() {
    fun updateTime() {
        viewModelScope.launch {
            DateTimeUseCase.updateTime()
        }
    }
}

class MainViewModel : ViewModel() {
    val liveData: LiveData<String> = DateTimeUseCase.flow.asLiveData()
    fun updateTime() {
        viewModelScope.launch {
            DateTimeUseCase.updateTime()
        }
    }
}

object DateTimeUseCase {

    private val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)

    private val _mutableStateFlow = MutableStateFlow(simpleDateFormat.format(Date()))

    val flow: Flow<String> = _mutableStateFlow

    fun updateTime() {
        _mutableStateFlow.value = simpleDateFormat.format(Date())
    }
}

@Composable
fun ObserveAsStateExperiment(label: String, content: @Composable () -> Unit) {
    Card(modifier = Modifier.background(MaterialTheme.colors.surface)) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = label, fontStyle = FontStyle.Italic)
            content()
        }
    }
}

@Composable
fun RememberExperimentRow(
    label: String,
    checkState: MutableState<Boolean>
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier.weight(1.0f), text = label)
        Switch(
            checked = checkState.value,
            onCheckedChange = { checkState.value = it })
    }
}