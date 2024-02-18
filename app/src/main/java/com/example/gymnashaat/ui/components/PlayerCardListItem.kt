package com.example.gymnashaat.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.gymnashaat.ui.states.PlayerUiState

@Composable
fun PlayerCardListItem(
    state: PlayerUiState,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier.fillMaxWidth().padding(8.dp)) {

        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = state.imageId),
                contentDescription = state.name,
                modifier = Modifier
                    .size(120.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .weight(0.3f)
                    .padding(8.dp)
            )


            Column(Modifier.weight(0.7f)){
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Text(
                        text = state.name,
                        style = MaterialTheme.typography.headlineSmall
                    )

                    Text(
                        text = state.phoneNumber,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }

}


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PlayerCardListItemPreview() {
    val state = PlayerUiState(name = "boody", phoneNumber = "01125747163")

    Column {
        PlayerCardListItem(state)
        PlayerCardListItem(state)
        PlayerCardListItem(state)
    }


}