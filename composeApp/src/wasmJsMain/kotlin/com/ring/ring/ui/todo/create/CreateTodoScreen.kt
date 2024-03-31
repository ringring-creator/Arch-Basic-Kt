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
import com.ring.ring.ui.common.ArchBasicDatePicker
import com.ring.ring.util.DateUtil
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock


@Composable
fun CreateTodoScreen(
    viewModel: CreateTodoViewModel = remember { CreateTodoViewModel() },
    toTodoListScreen: () -> Unit,
) {
    val snackBarHostState = remember { SnackbarHostState() }

    CreateTodoScreen(
        uiState = viewModel.rememberCreateTodoUiState(),
        updater = viewModel,
        toTodoListScreen = toTodoListScreen,
        snackBarHostState = snackBarHostState,
    )

    SetupSideEffect(viewModel, toTodoListScreen, snackBarHostState)
}

@Composable
private fun SetupSideEffect(
    viewModel: CreateTodoViewModel,
    toTodoListScreen: () -> Unit,
    snackBarHostState: SnackbarHostState
) {
    LaunchedEffect(Unit) {
        viewModel.toTodoListEvent.collect {
            toTodoListScreen()
        }
    }
    LaunchedEffect(Unit) {
        viewModel.saveTodoErrorEvent.collect {
            snackBarHostState.showSnackbar(
                message = "Failed to create",
                withDismissAction = true,
            )
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
            return DateUtil.format(dateMillis)
        }

        companion object {
            fun currentTime(): Deadline {
                return Deadline(Clock.System.now().toEpochMilliseconds())
            }
        }
    }
}

interface CreateTodoUiUpdater {
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
    snackBarHostState: SnackbarHostState,
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
        },
        snackbarHost = { SnackbarHost(snackBarHostState) },
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Content(
    modifier: Modifier,
    uiState: CreateTodoUiState,
    updater: CreateTodoUiUpdater,
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
            DeadlineField(uiState.deadline, updater::showDatePicker)
            Spacer(modifier = Modifier.height(8.dp))
            CreateButton {
                scope.launch { updater.saveTodo() }
            }
        }
        ArchBasicDatePicker(
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
    setDone: (Boolean) -> Unit,
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
    deadline: CreateTodoUiState.Deadline,
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
        Text(deadline.formatString())
    }
}

@Composable
private fun ColumnScope.CreateButton(create: () -> Unit) {
    Button(
        onClick = create,
        modifier = Modifier.align(Alignment.End)
    ) {
        Text("Create")
    }
}