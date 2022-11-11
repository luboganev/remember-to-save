package dev.luboganev.remembertosave

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    onNavigateToDetail: () -> Unit,
) {
    val myViewDataWithDefault by viewModel.liveData.observeAsState("initial")
    val myViewData by viewModel.liveData.observeAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
    ) {
        Button(onClick = {
            onNavigateToDetail()
        }) {
            Text(text = "Open detail")
        }

        ObserveAsStateExperiment(label = "observeAsState(\"initial\")") {
            RememberExperiment(myViewDataWithDefault)
        }
        Spacer(modifier = Modifier.height(8.dp))
        ObserveAsStateExperiment(label = "observeAsState(\"initial\")") {
            myViewData?.let {
                RememberExperiment(it)
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        ObserveAsStateExperiment(label = "observeAsState(\"initial\")") {
            RememberExperiment(myViewData)
        }

        Button(onClick = {
            viewModel.updateTime()
        }) {
            Text(text = "Update time")
        }
    }
}

@Composable
fun RememberExperiment(dateTime: String?) {
    val rememberCheckedState = remember(dateTime) {
        mutableStateOf(false)
    }
    val rememberSaveableCheckedState = rememberSaveable(dateTime) {
        mutableStateOf(false)
    }
    val rememberSaveableWithKeyCheckedState = rememberSaveable(dateTime, key = dateTime) {
        mutableStateOf(false)
    }
    Column {
        Text(text = dateTime ?: "null")
        RememberExperimentRow(
            label = "remember",
            checkState = rememberCheckedState
        )
        RememberExperimentRow(
            label = "rememberSaveable",
            checkState = rememberSaveableCheckedState
        )
        RememberExperimentRow(
            label = "rememberSaveable with key",
            checkState = rememberSaveableWithKeyCheckedState
        )
    }
}