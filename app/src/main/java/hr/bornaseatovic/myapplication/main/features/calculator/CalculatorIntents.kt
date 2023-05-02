package hr.bornaseatovic.myapplication.main.features.calculator

sealed interface CalculatorIntents {
    object PressAButton: CalculatorIntents
    data class PromjeniGeoSirina(val sirina: String): CalculatorIntents
    data class PromjeniGeoDuzina(val duzina: String): CalculatorIntents
    data class PromjeniPovrsinaIstocnogKrova(val povrsina: String): CalculatorIntents
    data class PromjeniPovrsinaJuznogKrova(val povrsina: String): CalculatorIntents
    data class PromjeniPovrsinaZapadnogKrova(val povrsina: String): CalculatorIntents
    data class PromjeniNagibIstocnogKrova(val nagib: String): CalculatorIntents
    data class PromjeniNagibJuznogKrova(val nagib: String): CalculatorIntents
    data class PromjeniNagibZapadnogKrova(val nagib: String): CalculatorIntents

}
