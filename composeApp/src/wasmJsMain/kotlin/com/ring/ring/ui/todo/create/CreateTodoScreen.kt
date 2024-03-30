package com.ring.ring.ui.todo.create

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


@Composable
fun CreateTodoScreen(
    viewModel: CreateTodoViewModel = remember { CreateTodoViewModel() },
    toTodoListScreen: () -> Unit,
) {
    CreateTodoScreen(
        uiState = CreateTodoViewModel.rememberCreateTodoUiState(viewModel),
        updater = viewModel,
        toTodoListScreen = toTodoListScreen,
    )

    LaunchedEffect(Unit) {
        viewModel.toTodoListEvent.collect {
            toTodoListScreen()
        }
    }
}

data class CreateTodoUiState(
    val title: String,
    val description: String,
    val done: Boolean,
    val deadline: Deadline,
    val isShowDatePicker: Boolean,
) {
    data class Deadline(
        val dateMillis: Long
    ) {
        fun formatString(): String {
            return convertLongToDateString(dateMillis)
        }

        private fun convertLongToDateString(timestamp: Long): String {
            val instant = Instant.fromEpochMilliseconds(timestamp)
            val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())

            return "${localDateTime.year}-${
                localDateTime.monthNumber.toString().padStart(2, '0')
            }-${localDateTime.dayOfMonth.toString().padStart(2, '0')}"
        }

        companion object {
            fun currentTime(): Deadline {
                return Deadline(Clock.System.now().toEpochMilliseconds())
            }
        }
    }
}

interface CreateTodoUiUpdater {
    fun onBack()
    suspend fun saveTodo()
    fun setTitle(title: String)
    fun setDescription(description: String)
    fun setDone(done: Boolean)
    fun setDeadline(dateMillis: Long)
    fun showDatePicker()
    fun dismissDatePicker()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTodoScreen(
    uiState: CreateTodoUiState,
    updater: CreateTodoUiUpdater,
    toTodoListScreen: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Create Todo") },
                navigationIcon = {
                    IconButton(onClick = toTodoListScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        }
    ) { padding ->
        Content(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp),
            uiState = uiState,
            updater = updater,
        )
    }
}

@Composable
private fun Content(
    modifier: Modifier,
    uiState: CreateTodoUiState,
    updater: CreateTodoUiUpdater
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            TitleTextField(uiState, updater)
            DescriptionTextField(uiState, updater)
            DoneCheckBox(uiState, updater)
            DeadlineField(updater, uiState)
            Spacer(modifier = Modifier.height(8.dp))
            CreateButton(updater)
        }
        DeadlineDatePicker(uiState, updater)
    }
}

@Composable
private fun TitleTextField(
    uiState: CreateTodoUiState,
    updater: CreateTodoUiUpdater
) {
    OutlinedTextField(
        value = uiState.title,
        onValueChange = updater::setTitle,
        label = { Text("Title") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DescriptionTextField(
    uiState: CreateTodoUiState,
    updater: CreateTodoUiUpdater
) {
    OutlinedTextField(
        value = uiState.description,
        onValueChange = updater::setDescription,
        label = { Text("Description") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DoneCheckBox(
    uiState: CreateTodoUiState,
    updater: CreateTodoUiUpdater
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = uiState.done,
            onCheckedChange = updater::setDone,
        )
        Text("Done")
    }
}

@Composable
private fun DeadlineField(
    updater: CreateTodoUiUpdater,
    uiState: CreateTodoUiState
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = updater::showDatePicker),
    ) {
        Icon(
            Icons.Filled.DateRange,
            contentDescription = null,
        )
        Text(uiState.deadline.formatString())
    }
}

@Composable
private fun ColumnScope.CreateButton(
    updater: CreateTodoUiUpdater,
    scope: CoroutineScope = rememberCoroutineScope()
) {
    Button(
        onClick = {
            scope.launch { updater.saveTodo() }
        },
        modifier = Modifier.align(Alignment.End)
    ) {
        Text("Create")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeadlineDatePicker(
    uiState: CreateTodoUiState,
    updater: CreateTodoUiUpdater,
) {
    val state = rememberDatePickerState()
    if (uiState.isShowDatePicker) {
        DatePickerDialog(
            onDismissRequest = updater::dismissDatePicker,
            confirmButton = {
                Text("Set", modifier = Modifier.padding(16.dp).clickable {
                    state.selectedDateMillis?.let { updater.setDeadline(it) }
                    updater.dismissDatePicker()
                })
            }
        ) {
            DatePicker(state)
        }
    }
}
