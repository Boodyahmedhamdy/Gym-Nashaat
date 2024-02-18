package com.example.gymnashaat.ui.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.gymnashaat.ui.components.PlayerCardListItem
import com.example.gymnashaat.ui.states.AllPlayersScreenUiState

@Composable
fun AllPlayersScreen(
    state: AllPlayersScreenUiState,
    modifier: Modifier = Modifier
) {
    // if any error happened
    if(state.error.isNotEmpty()) {
        Text(text = state.error, color = MaterialTheme.colorScheme.error)
    }

    // loading animation
    if(state.isLoading) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
    } else {
        LazyColumn(modifier = modifier) {
            // check for empty state
            if (state.players.isEmpty()) {
                item { Text(text = "No Players Yet!\n add them using + button") }
            } else {
                items(state.players) {
                    PlayerCardListItem(state = it)
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AllPlayersScreenPreview() {

}