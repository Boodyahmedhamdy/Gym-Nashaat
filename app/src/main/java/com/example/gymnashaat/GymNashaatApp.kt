package com.example.gymnashaat

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.gymnashaat.ui.components.InsertPlayerBottomSheet
import com.example.gymnashaat.ui.screens.AllPlayersScreen
import com.example.gymnashaat.ui.states.AllPlayersScreenUiState
import com.example.gymnashaat.ui.states.PlayerUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GymNashaatApp(
    modifier: Modifier = Modifier,
    state: AllPlayersScreenUiState,
    onClickFabToInsertPlayer: () -> Unit = {},
    onInsertPlayerClicked: (PlayerUiState) -> Unit = {},
    onDeleteAllPlayersClicked: () -> Unit = {},
    onBottomSheetDismissClicked: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = stringResource(R.string.all_players)) },
                actions = {
                    IconButton(onClick = { onDeleteAllPlayersClicked() }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = stringResource(R.string.delete_all_rows)
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onClickFabToInsertPlayer) {
                Icon(imageVector = Icons.Default.Add, contentDescription = stringResource(R.string.add_player))
            }
        }
    ) {

        val sheetState = rememberModalBottomSheetState()

        // show bottom sheet for player insertion
        if(state.showBottomSheet) {
            InsertPlayerBottomSheet(
                sheetState = sheetState,
                onClickDone = onInsertPlayerClicked,
                onDismissClicked = onBottomSheetDismissClicked
            )
        }

        // all players
        AllPlayersScreen(
            state = state,
            modifier=modifier.padding(it))
    }

}