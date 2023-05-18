package hr.bornaseatovic.myapplication.common.domain.validation

class NumberValidation {
    operator fun invoke(input: String): String {
        return  try {
            input.toDouble()
            ""
        } catch (e: NumberFormatException) {
            "Polje prima samo numeričke znakove"
        }
    }
}