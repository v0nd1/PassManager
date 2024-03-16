package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.Item

@Composable
fun ItemCard(
    item: Item,
    onClick: (ItemEvent) -> Unit
){
    Card(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Row {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Iconsite"
                )
                Column {
                    Text(
                        text = item.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = item.login,
                        fontSize = 14.sp
                    )
                    Text(
                        text = item.password,
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item.url,
                        fontSize = 14.sp
                    )

                }
            }

            IconButton(onClick = {

            }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete item")
            }


        }
    }

}