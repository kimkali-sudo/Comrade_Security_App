package dev.korryr.comradealert.ui.feutures.auth.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.InputTransformation.Companion.keyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.korryr.comradealert.ui.theme.orange28

@Composable
fun ComradeTextField(
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    value : String = "",
    placeholder : String = "",
    isPassword: Boolean = false,
    showPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
    keyboardActions: KeyboardActions? = null,
    onDone: () -> Unit = {},

    ){

    var passwordVisible by rememberSaveable { mutableStateOf(showPassword) }
    val softwareKeyboard = LocalSoftwareKeyboardController.current
    val focusManager = LocalFocusManager.current

    val textFieldColors = OutlinedTextFieldDefaults.colors(
        // Border colors
        focusedBorderColor = orange28,
        unfocusedBorderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.2f),
        errorBorderColor = Color.Red.copy(alpha = 0.8f),

        // Container colors
        focusedContainerColor = Color.White,
        unfocusedContainerColor = Color.White,
        errorContainerColor = Color.Red.copy(alpha = 0.05f),

        // Text colors
        focusedTextColor = MaterialTheme.colorScheme.onSurface,
        unfocusedTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.7f),
        errorTextColor = Color.Red,

        // Label colors
        focusedLabelColor = orange28,
        unfocusedLabelColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        errorLabelColor = Color.Red,

        // Icon colors
        focusedLeadingIconColor = orange28,
        unfocusedLeadingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        errorLeadingIconColor = Color.Red,

        focusedTrailingIconColor = orange28,
        unfocusedTrailingIconColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f),
        errorTrailingIconColor = Color.Red,

        // Cursor color
        cursorColor = orange28,

        // Selection colors
        selectionColors = TextSelectionColors(
            handleColor = orange28,
            backgroundColor = orange28.copy(alpha = 0.2f)
        )
    )

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center,
    ){
        OutlinedTextField(
            value = value,
            shape = androidx.compose.material3.MaterialTheme.shapes.medium,
            onValueChange = {
                onValueChange(it)
            },
            modifier = modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text = placeholder)
            },
            visualTransformation = when {
                isPassword && !passwordVisible -> PasswordVisualTransformation()
                else -> VisualTransformation.None
            },
            colors = textFieldColors,
            keyboardActions = keyboardActions ?: KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Next)
                },
                onDone = {
                    softwareKeyboard?.hide()
                    onDone()
                }
            ),
            singleLine = true,
            maxLines = 1,
            keyboardOptions = when {
                isPassword && passwordVisible -> keyboardOptions.copy(keyboardType = KeyboardType.Text)
                isPassword -> keyboardOptions.copy(keyboardType = KeyboardType.Password)
                else -> keyboardOptions
            },




            )
    }
}