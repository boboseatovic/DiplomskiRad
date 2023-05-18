package hr.bornaseatovic.myapplication.common.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import hr.bornaseatovic.myapplication.common.domain.validation.NumberValidation
import hr.bornaseatovic.myapplication.common.domain.validation.RequiredValidation
import hr.bornaseatovic.myapplication.common.domain.validation.ValidationUseCases

@Module
@InstallIn(ViewModelComponent::class)
object ValidationModule {

    @ViewModelScoped
    @Provides
    fun providesValidationUseCases(): ValidationUseCases {
        return ValidationUseCases(
            RequiredValidation(),
            NumberValidation()
        )
    }
}