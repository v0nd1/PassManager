package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.vondi.passmanager.R
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.Size


@Composable
fun PassSearchBar(

) {
    val maxLines = 1
    var text by remember { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it.filter {
                it.isLetterOrDigit()
            }
        },
        modifier = Modifier
            .padding(horizontal = Dimens.Medium)
            .padding(top = Dimens.Big, bottom = Dimens.Small)
            .height(Size.Medium)
            .fillMaxWidth(),
        trailingIcon = {
            IconButton(
                onClick = { }
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        },
        shape = RoundedCornerShape(20),
        maxLines = maxLines
    )

}