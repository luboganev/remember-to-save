package dev.luboganev.remembertosave

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel = viewModel(),
    onNavigateToDetail: () -> Unit,
) {
    val myViewData by viewModel.liveData.observeAsState()
    Surface(
        modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            myViewData?.let {
                Clock(
                    key = it.key,
                    dateTime = it.dateTime,
                )
            }
            Button(onClick = {
                onNavigateToDetail()
            }) {
                Text(text = "Open detail")
            }
        }
    }
}

@Composable
fun Clock(
    key: String,
    dateTime: String,
) {
    val rememberStateDateTime by remember(key) {
        mutableStateOf("The remembered time is:\n$dateTime")
    }
    val rememberSaveableStateDateTime by rememberSaveable(key) {
        mutableStateOf("The remembered saveable time is:\n$dateTime")
    }
    Column {
        Text(text = dateTime)
        Text(text = rememberStateDateTime)
        Text(text = rememberSaveableStateDateTime)
    }
}