package hr.bornaseatovic.myapplication.common.domain.validation

data class ValidationUseCases(
    val requiredValidation: RequiredValidation,
    val numberValidation: NumberValidation
)