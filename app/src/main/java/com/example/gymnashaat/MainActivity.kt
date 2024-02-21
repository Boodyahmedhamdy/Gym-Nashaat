package com.example.gymnashaat

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gymnashaat.data.db.DatabaseProvider
import com.example.gymnashaat.mappers.toFirestoreObject
import com.example.gymnashaat.ui.theme.GymNashaatTheme
import com.example.gymnashaat.ui.viewmodels.AllPlayersScreenViewModel
import com.example.gymnashaat.ui.viewmodels.AllPlayersScreenViewModelFactory
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GymNashaatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val db = DatabaseProvider.provideDatabase(applicationContext)
                    val playerDao = db.playerDao()
                    val viewmodel: AllPlayersScreenViewModel = viewModel(
                        factory = AllPlayersScreenViewModelFactory(playerDao)
                    )
                    val state = viewmodel.state.collectAsState()
                    val firestoreDb = Firebase.firestore

                    // offline
                    GymNashaatApp(
                        state = state.value,
                        // passed to sheet to perform actual insertion in database
                        onInsertPlayerClicked = {
                            try {
                                viewmodel.insertPlayer(it)
                                // firebase firestore addition
                                firestoreDb.collection("players")
                                    .add(it.toFirestoreObject())
                                    .addOnSuccessListener { documentReference ->
                                        Log.d(ContentValues.TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
                                    }.addOnFailureListener {e ->
                                        Log.w(ContentValues.TAG, e)
                                    }
                            } catch(e: Exception) {
                                println("PLAYER-INSERTION-ERROR: some thing went wrong")
                                println(e)
                            }
                            viewmodel.hideBottomSheet()
                        },

                        // clear database
                        onDeleteAllPlayersClicked = { viewmodel.deleteAllPlayers() },

                        // to hide sheet
                        onBottomSheetDismissClicked = { viewmodel.hideBottomSheet() },

                        // to show sheet
                        onClickFabToInsertPlayer = { viewmodel.showBottomSheet() }
                    )

                }
            }
        }
    }
}
