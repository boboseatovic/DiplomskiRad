package hr.bornaseatovic.myapplication.common.domain.validation

class RequiredValidation {
    operator fun invoke(input: String): String {
        return if (input.isBlank()) "Obvezno polje!"
        else ""
    }
}