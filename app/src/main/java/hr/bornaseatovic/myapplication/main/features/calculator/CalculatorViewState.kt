package hr.bornaseatovic.myapplication.main.features.calculator

data class CalculatorViewState(
    val text: String = "",
    val geoSirina: String = "",
    val geoDuzina: String = "",
    val povrsinaIstocnogKrova: String = "",
    val nagibIstocnogKrova: String = "",
    val povrsinaJunznogKrova: String = "",
    val nagibJunznogKrova: String = "",
    val povrsinaZapadnogKrova: String = "",
    val nagibZapadnogKrova: String = "",
    val potrosnja1mjesec: String = "",
    val potrosnja2mjesec: String = "",
    val potrosnja3mjesec: String = "",
    val potrosnja4mjesec: String = "",
    val potrosnja5mjesec: String = "",
    val potrosnja6mjesec: String = "",
    val potrosnja7mjesec: String = "",
    val potrosnja8mjesec: String = "",
    val potrosnja9mjesec: String = "",
    val potrosnja10mjesec: String = "",
    val potrosnja11mjesec: String = "",
    val potrosnja12mjesec: String = "",
    val mjesecnaPotrosnja: MutableList<String> = mutableListOf(
        "","","","","","","","","","","","",
    ),
    val monthlyProduction: List<Double>? = null,
)
