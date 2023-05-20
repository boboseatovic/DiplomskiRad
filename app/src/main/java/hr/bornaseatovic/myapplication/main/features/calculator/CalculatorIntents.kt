package hr.bornaseatovic.myapplication.main.features.calculator

sealed interface CalculatorIntents {
    data class PromjeniGeoSirina(val sirina: String): CalculatorIntents
    data class PromjeniGeoDuzina(val duzina: String): CalculatorIntents
    data class PromjeniPovrsinaIstocnogKrova(val povrsina: String): CalculatorIntents
    data class PromjeniPovrsinaJuznogKrova(val povrsina: String): CalculatorIntents
    data class PromjeniPovrsinaZapadnogKrova(val povrsina: String): CalculatorIntents
    data class PromjeniNagibIstocnogKrova(val nagib: String): CalculatorIntents
    data class PromjeniNagibJuznogKrova(val nagib: String): CalculatorIntents
    data class PromjeniNagibZapadnogKrova(val nagib: String): CalculatorIntents

    data class PromjeniMjesecnaPotrosnja(val mjesec: Int, val potrosnja: String): CalculatorIntents

    data class Promjeni1Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni2Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni3Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni4Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni5Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni6Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni7Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni8Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni9Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni10Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni11Mjesec(val potrosnja: String): CalculatorIntents
    data class Promjeni12Mjesec(val potrosnja: String): CalculatorIntents

    object IzracunajPVGIS: CalculatorIntents

    object InitThirdScreen: CalculatorIntents
    object NavigateToFourthScreen : CalculatorIntents

}
