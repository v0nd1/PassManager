package com.vondi.passmanager.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.room.util.query
import com.vondi.passmanager.R
import com.vondi.passmanager.domain.event.ItemEvent
import com.vondi.passmanager.presentation.common.Dimens
import com.vondi.passmanager.presentation.common.Size
import com.vondi.passmanager.presentation.common.TextColor


@Composable
fun PassSearchBar(
    onEvent: (ItemEvent) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it.filter { char -> char.isLetterOrDigit() }
            active = text.isNotEmpty()
            onEvent(ItemEvent.UpdateSearchQuery(query = text))
        },
        modifier = Modifier
            .padding(horizontal = Dimens.Medium)
            .padding(top = Dimens.Big, bottom = Dimens.Small)
            .height(Size.MediumPlus)
            .fillMaxWidth(),
        trailingIcon = {
            if (active) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = stringResource(R.string.clear_query),
                    modifier = Modifier.clickable {
                        text = ""
                        active = false
                        onEvent(ItemEvent.UpdateSearchQuery(""))
                    },
                    tint = TextColor
                )
            }
        },
        leadingIcon = {
            IconButton(onClick = { }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_icon)
                )
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            localFocusManager.clearFocus()
        }),
        shape = RoundedCornerShape(20),
        singleLine = true
    )

}