@file:OptIn(ExperimentalComposeUiApi::class)

package hr.bornaseatovic.myapplication.ui.components


import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import hr.bornaseatovic.myapplication.R
import hr.bornaseatovic.myapplication.ui.states.CalculatorFieldState
import hr.bornaseatovic.myapplication.ui.theme.*
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CalculatorField(
    modifier: Modifier = Modifier,
    label: String = "",
    value: String = "",
    unit: String? = null,
    info: Boolean = false,
    readOnly: Boolean = false,
    infoAction: (() -> Unit)? = null,
    calculatorFieldState: CalculatorFieldState = CalculatorFieldState.Normal,
    onFocusLost: () -> Unit = {},
    onInputFieldChange: (String) -> Unit = {}
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusLocalManager = LocalFocusManager.current

    val borderColor = remember {
        mutableStateOf(Manatee)

    }
    LaunchedEffect(key1 = calculatorFieldState, block = {
        Log.i("testfocus", "CalculatorField: ")
        when (calculatorFieldState) {
            is CalculatorFieldState.Disabled -> borderColor.value = LavenderGray
            is CalculatorFieldState.Error -> borderColor.value = Crimson
            is CalculatorFieldState.Normal -> borderColor.value = Manatee
        }
    })

    Log.i("testfocus", "CalculatorField: rerender")

    val coroutineScope = rememberCoroutineScope()

    var wasInFocus by remember {
        mutableStateOf(false)
    }

    val bringIntoViewRequester = remember {
        BringIntoViewRequester()
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .bringIntoViewRequester(bringIntoViewRequester)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = label,
                    style = AmaliaRegular_15,
                    color = MediumGray,
                    modifier = Modifier
                        .weight(if (info) 0.8f else 1f)
                )
                if (info) {
                    Box(modifier = Modifier.weight(0.2f)) {

                        Icon(painterResource(id = R.drawable.calculator_info),
                            contentDescription = "calculator icon",
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .clickable {
                                    infoAction?.let {
                                        it()
                                    }
                                }
                                .align(Alignment.CenterStart)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .weight(1f)
                    .height(41.dp)
                    .border(2.dp, borderColor.value, RoundedCornerShape(4.dp))
                    .background(MaterialTheme.colors.background)
                    .padding(top = 9.dp, bottom = 12.dp, start = 16.dp, end = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                BasicTextField(
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged {
                            if (calculatorFieldState == CalculatorFieldState.Normal || calculatorFieldState is CalculatorFieldState.Error) {
                                if (it.isFocused) {
                                    Log.i("testfocus", "in focus")
                                    wasInFocus = true
                                    coroutineScope.launch {
                                        bringIntoViewRequester.bringIntoView()
                                    }
                                    borderColor.value = OffBlack
                                } else {
                                    borderColor.value = Manatee
                                    coroutineScope.launch {
                                        if (wasInFocus) {
                                            wasInFocus = false
                                            if (calculatorFieldState is CalculatorFieldState.Error)
                                                borderColor.value = Crimson
                                            onFocusLost()
                                            Log.i("testfocus", "focus lost")
                                        }
                                    }
                                }
                            }
                        },
                    value = value,
                    singleLine = true,
                    onValueChange = {
                        onInputFieldChange(it)
                    },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = {
                        keyboardController?.hide()
                        focusLocalManager.clearFocus(true)
                    }),
                    textStyle = AmaliaRegular_16.copy(
                        letterSpacing = (-1).sp,
                        color = if (calculatorFieldState == CalculatorFieldState.Disabled) LightGray else Color.Black
                    ),
                    enabled = (calculatorFieldState != CalculatorFieldState.Disabled) && !readOnly

                )

                unit?.let {
                    Text(
                        text = it,
                        style = AmaliaRegular_16,
                        letterSpacing = (-1).sp,
                        color = Manatee
                    )
                }

            }


        }
        if (calculatorFieldState is CalculatorFieldState.Error) {
            Text(
                text = calculatorFieldState.errorMessage,
                color = Crimson,
                style = AmaliaRegular_13_17,
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .padding(top = 8.dp)
                    .fillMaxWidth()
            )
        }
    }
}

