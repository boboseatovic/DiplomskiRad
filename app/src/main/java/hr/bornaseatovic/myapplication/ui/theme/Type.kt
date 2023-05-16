package hr.bornaseatovic.myapplication.ui.theme

import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import hr.bornaseatovic.myapplication.R

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val AmaliaRegular_16: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.amalia_regular)),
            fontSize = 16.sp
        )
    }

val AmaliaRegular_15: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.amalia_regular)),
            fontSize = 15.sp
        )
    }

val AmaliaRegular_13_17: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.amalia_regular)),
            fontSize = 13.sp,
            lineHeight = 17.sp
        )
    }

val AmaliaBold_16: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.amalia_bold)),
            fontSize = 16.sp
        )
    }

val Montserrat: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat))
        )
    }

val Montserrat_ExtraLight: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat_extra_light))
        )
    }

val Montserrat_Light: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat_light))
        )
    }

val Montserrat_Thin: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.montserrat_thin))
        )
    }

val ZillaSlab: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.zilla_slab_regular))
        )
    }

val ZillaSlab_SemiBold: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.zilla_slab_semi_bold))
        )
    }

val Poppins_Regular: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_regular))
        )
    }

val Poppins_Regular_11: TextStyle
    @Composable
    get() {
        return TextStyle(
            fontFamily = FontFamily(Font(R.font.poppins_regular)),
            fontSize = 11.sp
        )
    }