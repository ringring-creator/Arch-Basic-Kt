package com.ring.ring.ui.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArchBasicDatePicker(
    isShowDatePicker: Boolean,
    datePickerState: DatePickerState,
    dismissDatePicker: () -> Unit,
    setDate: (Long) -> Unit,
) {
    if (isShowDatePicker) {
        DatePickerDialog(
            onDismissRequest = dismissDatePicker,
            confirmButton = {
                Text("Set", modifier = Modifier.padding(16.dp).clickable {
                    datePickerState.selectedDateMillis?.let { setDate(it) }
                    dismissDatePicker()
                })
            }
        ) {
            DatePicker(datePickerState)
        }
    }
}