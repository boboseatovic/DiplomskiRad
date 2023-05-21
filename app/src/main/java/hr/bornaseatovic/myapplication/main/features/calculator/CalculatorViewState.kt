package hr.bornaseatovic.myapplication.main.features.calculator

data class CalculatorViewState(
    val text: String = "",
    val geoSirina: String = "46",
    val geoDuzina: String = "16",
    val povrsinaIstocnogKrova: String = "13",
    val nagibIstocnogKrova: String = "32",
    val povrsinaJunznogKrova: String = "23",
    val nagibJunznogKrova: String = "32",
    val povrsinaZapadnogKrova: String = "14",
    val nagibZapadnogKrova: String = "32",
    val potrosnja1mjesec: String = "950",
    val potrosnja2mjesec: String = "900",
    val potrosnja3mjesec: String = "920",
    val potrosnja4mjesec: String = "880",
    val potrosnja5mjesec: String = "800",
    val potrosnja6mjesec: String = "750",
    val potrosnja7mjesec: String = "800",
    val potrosnja8mjesec: String = "820",
    val potrosnja9mjesec: String = "850",
    val potrosnja10mjesec: String = "900",
    val potrosnja11mjesec: String = "950",
    val potrosnja12mjesec: String = "980",
    val mjesecnaPotrosnja: MutableList<String> = mutableListOf(
        "","","","","","","","","","","","",
    ),
    val monthlyProduction: List<Double>? = null,
    val yearlyProduction: Double = 0.0,
    val monthlyConsumption: List<Double> = listOf(0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0,0.0),
    val completeConsumption: Double = 0.0
)
