package hr.bornaseatovic.myapplication.ui.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import hr.bornaseatovic.myapplication.ui.states.PrimaryButtonState
import hr.bornaseatovic.myapplication.ui.theme.*

@Composable
fun PrimaryButton(
    modifier: Modifier = Modifier,
    text: String,
    buttonState: PrimaryButtonState = PrimaryButtonState.NormalButton,
    onClick: () -> Unit
) {
    when (buttonState) {
        is PrimaryButtonState.NormalButton -> {
            NormalButton(text = text, modifier = modifier) {
                onClick()
            }
        }
        is PrimaryButtonState.DisabledButton -> {
            DisabledButton(text = buttonState.disabledText, modifier = modifier) {
                onClick()
            }
        }
        is PrimaryButtonState.LoadingButton -> {
            LoadingButton(modifier = modifier) {
                onClick()
            }
        }
    }
}

@Composable
fun NormalButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val isPressed by interactionSource.collectIsPressedAsState()

    val backgroundColor = if (isPressed) Gold else Yellow

    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
        elevation = null,
        shape = RoundedCornerShape(2.dp),
        interactionSource = interactionSource,
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
        Text(
            text = text,
            style = AmaliaBold_16,
            color = OffBlack,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun DisabledButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Gainsboro),
        elevation = null,
        shape = RoundedCornerShape(2.dp),
        contentPadding = PaddingValues(vertical = 14.dp),
        enabled = false
    ) {
        Text(
            text = text,
            style = AmaliaBold_16,
            color = Color.White,
        )
    }
}

@Composable
fun LoadingButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = {
            onClick()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Yellow),
        elevation = null,
        shape = RoundedCornerShape(2.dp),
        contentPadding = PaddingValues(vertical = 14.dp)
    ) {
        //TODO: animated loading icon
    }
}