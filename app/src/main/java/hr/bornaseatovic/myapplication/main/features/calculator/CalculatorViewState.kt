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
)
