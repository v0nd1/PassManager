package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.domain.model.Item
import com.vondi.passmanager.ui.theme.Black
import com.vondi.passmanager.ui.theme.DarkGreen
import com.vondi.passmanager.ui.theme.Green
import com.vondi.passmanager.ui.theme.LightGreen
import com.vondi.passmanager.ui.theme.White

@Composable
fun ItemCard(
    item: Item,
    onDelete: (Item) -> Unit,
    onClick: () -> Unit,
    onSelect: (Item) -> Unit
){
    val login = item.login
    val password = item.password

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onSelect(item)
                onClick()
            },
        colors = CardDefaults.cardColors(
           containerColor = if (isSystemInDarkTheme()) DarkGreen else LightGreen,
            contentColor = if (isSystemInDarkTheme()) White else Black
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ){
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Rounded.AccountCircle,
                    contentDescription = "Iconsite",
                    modifier = Modifier.size(40.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = item.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSystemInDarkTheme()) Green else DarkGreen
                    )
                    Text(
                        text = "Login: $login",
                        fontSize = 14.sp,
                    )
                    Text(
                        text = "Password: " + buildAnnotatedString {
                            repeat(if (password.length > 15 ) 15 else password.length) {
                                append("*")
                            }
                        },
                        fontSize = 14.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = item.url,
                        fontSize = 14.sp,
                        fontStyle = FontStyle.Italic
                    )

                }
            }
            IconButton(onClick = {
                onDelete(item)
            }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete item"
                )
            }
        }
    }

}