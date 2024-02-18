package com.example.gymnashaat.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnashaat.R
import com.example.gymnashaat.ui.states.PlayerUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertPlayerBottomSheet(
    sheetState: SheetState,
    modifier: Modifier = Modifier,
    onClickDone: (playerToBeAdded: PlayerUiState) -> Unit = {},
    onDismissClicked: () -> Unit = {}
) {
    ModalBottomSheet(
        onDismissRequest = { onDismissClicked() },
        sheetState = sheetState,
        modifier = modifier
    ) {
        BottomSheetContent(onClickCreatePlayer = onClickDone)
    }
}

/*
     size must be at least 11
     must start with 01
     */
private fun isValidPhoneNumber(phoneNumber: String): Boolean {
    return phoneNumber.startsWith("01") && phoneNumber.length == 11
}
private fun isValidPlayerName(name: String): Boolean {
    return name.isNotEmpty() && name.isNotBlank()
}

@Composable
fun BottomSheetContent(
    onClickCreatePlayer: (PlayerUiState) -> Unit,
    modifier: Modifier = Modifier
) {
    var playerName by remember { mutableStateOf("") }
    var playerPhoneNumber by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = modifier
    ) {
        Text(text = errorMessage, color = MaterialTheme.colorScheme.error)

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Name")
            TextField(
                value = playerName,
                onValueChange = {playerName = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                singleLine = true
            )
        }

        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = "Phone")
            TextField(
                value = playerPhoneNumber,
                onValueChange = {playerPhoneNumber = it},
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                singleLine = true
            )
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = {
                if(isValidPlayerName(playerName) && isValidPhoneNumber(playerPhoneNumber)) {
                    val player = PlayerUiState(name = playerName, phoneNumber = playerPhoneNumber)
                    onClickCreatePlayer(player)
                } else {
                    errorMessage = "inValid Input, recheck name and phone number"
                }
            }) {
                Text(text = stringResource(R.string.create_player))
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showSystemUi = true, showBackground = true)
@Composable
fun InsertPlayerBottomSheetPreview() {
    InsertPlayerBottomSheet(
        rememberModalBottomSheetState()
    )
}