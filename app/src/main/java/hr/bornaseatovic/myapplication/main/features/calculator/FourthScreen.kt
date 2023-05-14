package hr.bornaseatovic.myapplication.main.features.calculator

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ContentTransform
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
@Composable
fun FourthScreen(
    onBack: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize().background(Color.Black).clickable(
        interactionSource = MutableInteractionSource(),
        onClick = {
            onBack()
        },
        indication = null
    )) {

            Text(
                text = "Heeeeeej!",
                color = Color.White,
                fontSize = 20.sp,
                modifier = Modifier
                    .align(
                        Alignment.Center
                    )
            )


    }
}