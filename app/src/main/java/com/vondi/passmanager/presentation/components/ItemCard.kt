package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.vondi.passmanager.R
import com.vondi.passmanager.data.models.Item
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.FontSize
import com.vondi.passmanager.presentation.common.TextColor

@Composable
fun ItemCard(
    item: Item,
    onDelete: (Item) -> Unit,
    onClick: () -> Unit,
    onSelect: (Item) -> Unit
) {
    val login = item.login
    val password = item.password
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(item.logoUrl)
            .build()
    )
    val painterDefault = painterResource(id = R.drawable.lock)
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                onSelect(item)
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color(item.color),
            contentColor = TextColor
        )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                Image(
                    painter = if (painter.request.data == "") painterDefault else painter,
                    contentDescription = stringResource(R.string.iconsite),
                    modifier = Modifier
                        .size(40.dp)
                        .clip(RoundedCornerShape(60))
                        .fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = item.name,
                            fontSize = FontSize.Medium,
                            fontWeight = FontWeight.SemiBold,
                            color = MaterialTheme.colorScheme.primary
                        )
                        Spacer(modifier = Modifier.width(Dimens.ExtraSmall))
                        Text(
                            text = item.category,
                            fontSize = FontSize.ExtraSmall,
                            fontStyle = FontStyle.Italic
                        )
                    }
                    
                    Text(
                        text = "Login: $login",
                        fontSize = 14.sp,
                    )
                    Text(
                        text = "Password: " + buildAnnotatedString {
                            repeat(if (password.length > 15) 15 else password.length) {
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