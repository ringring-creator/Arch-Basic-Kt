package com.ring.ring.ui.todo.edit


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
fun EditTodoScreen(
    todoId: Long,
    viewModel: EditTodoViewModel = remember { EditTodoViewModel() },
    toTodoListScreen: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    EditTodoScreen(
        uiState = EditTodoViewModel.rememberEditTodoUiState(viewModel),
        updater = viewModel,
        toTodoListScreen = toTodoListScreen,
        snackBarHostState = snackBarHostState,
    )

    SetupSideEffect(viewModel, toTodoListScreen, todoId, snackBarHostState)
}

@Composable
private fun SetupSideEffect(
    viewModel: EditTodoViewModel,
    toTodoListScreen: () -> Unit,
    todoId: Long,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.toTodoListEvent.collect {
            toTodoListScreen()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getTodo(todoId = todoId)
    }
    LaunchedEffect(Unit) {
        viewModel.editErrorEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to edit",
                withDismissAction = true,
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.deleteErrorEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to delete",
                withDismissAction = true,
            )
        }
    }
    LaunchedEffect(Unit) {
        viewModel.getTodoErrorEvent.collect {
            val snackBarResult = snackBarHostState.showSnackbar(
                message = "Failed to get todo list",
                actionLabel = "Retry",
                withDismissAction = true,
            )
            when (snackBarResult) {
                SnackbarResult.Dismissed -> {}
                SnackbarResult.ActionPerformed -> {
                    viewModel.getTodo(todoId)
                }
            }
        }
    }
}

data class EditTodoUiState(
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

interface EditTodoUiUpdater {
    suspend fun editTodo()
    suspend fun deleteTodo()
    fun setTitle(title: String)
    fun setDescription(description: String)
    fun setDone(done: Boolean)
    fun setDeadline(dateMillis: Long)
    fun showDatePicker()
    fun dismissDatePicker()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTodoScreen(
    uiState: EditTodoUiState,
    updater: EditTodoUiUpdater,
    toTodoListScreen: () -> Unit,
    snackBarHostState: SnackbarHostState,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Edit Todo") },
                navigationIcon = {
                    IconButton(onClick = toTodoListScreen) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null)
                    }
                }
            )
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
    ) { padding ->
        Content(
            Modifier
                .padding(padding)
                .padding(16.dp), uiState, updater
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier,
    uiState: EditTodoUiState,
    updater: EditTodoUiUpdater,
    datePickerState: DatePickerState = rememberDatePickerState(),
    scope: CoroutineScope = rememberCoroutineScope(),
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
            TitleTextField(uiState.title, updater::setTitle)
            DescriptionTextField(uiState.description, updater::setDescription)
            DoneCheckBox(uiState.done, updater::setDone)
            DeadlineField(uiState.deadline.formatString(), updater::showDatePicker)
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.align(Alignment.End),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                EditButton { scope.launch { updater.editTodo() } }
                DeleteButton { scope.launch { updater.deleteTodo() } }
            }
        }
        DeadlineDatePicker(
            uiState.isShowDatePicker,
            datePickerState,
            updater::dismissDatePicker,
            updater::setDeadline,
        )
    }
}

@Composable
private fun TitleTextField(
    title: String,
    setTitle: (String) -> Unit
) {
    OutlinedTextField(
        value = title,
        onValueChange = setTitle,
        label = { Text("Title") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DescriptionTextField(
    description: String,
    setDescription: (String) -> Unit
) {
    OutlinedTextField(
        value = description,
        onValueChange = setDescription,
        label = { Text("Description") },
        modifier = Modifier.fillMaxWidth()
    )
}

@Composable
private fun DoneCheckBox(
    done: Boolean,
    setDone: (Boolean) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = done,
            onCheckedChange = setDone,
        )
        Text("Done")
    }
}

@Composable
private fun DeadlineField(
    deadline: String,
    showDatePicker: () -> Unit,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable(onClick = showDatePicker),
    ) {
        Icon(
            Icons.Filled.DateRange,
            contentDescription = null,
        )
        Text(deadline)
    }
}

@Composable
private fun EditButton(
    edit: () -> Unit,
) {
    Button(
        onClick = edit,
    ) { Text("Edit") }
}

@Composable
private fun DeleteButton(
    delete: () -> Unit,
) {
    Button(
        onClick = delete,
    ) { Text("Delete") }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DeadlineDatePicker(
    isShowDatePicker: Boolean,
    datePickerState: DatePickerState,
    dismissDatePicker: () -> Unit,
    setDeadline: (Long) -> Unit,
) {
    if (isShowDatePicker) {
        DatePickerDialog(
            onDismissRequest = dismissDatePicker,
            confirmButton = {
                Text("Set", modifier = Modifier.padding(16.dp).clickable {
                    datePickerState.selectedDateMillis?.let { setDeadline(it) }
                    dismissDatePicker()
                })
            }
        ) {
            DatePicker(datePickerState)
        }
    }
}