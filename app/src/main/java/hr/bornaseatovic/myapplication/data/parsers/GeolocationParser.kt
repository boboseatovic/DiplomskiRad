package hr.bornaseatovic.myapplication.data.parsers

import hr.bornaseatovic.myapplication.data.model.presentation.GeolocationPresentation
import hr.bornaseatovic.myapplication.data.model.remote.geolocation.GeolocationResponse

fun GeolocationResponse.toGeolocationPresentation() = GeolocationPresentation(
    administrativeArea = this.data?.get(0)?.administrativeArea ?: "",
    confidence = this.data?.get(0)?.confidence ?: 0,
    continent = this.data?.get(0)?.continent ?: "",
    country = this.data?.get(0)?.country ?: "",
    countryCode = this.data?.get(0)?.countryCode ?: "",
    county = this.data?.get(0)?.county ?: "",
    label = this.data?.get(0)?.label ?: "",
    latitude = this.data?.get(0)?.latitude ?: 0.0,
    locality = this.data?.get(0)?.locality ?: "",
    longitude = this.data?.get(0)?.longitude ?: 0.0,
    name = this.data?.get(0)?.name ?: "",
    neighbourhood = this.data?.get(0)?.neighbourhood ?: "",
    number = this.data?.get(0)?.number ?: "",
    postalCode = this.data?.get(0)?.postalCode ?: "",
    region = this.data?.get(0)?.region ?: "",
    regionCode = this.data?.get(0)?.regionCode ?: "",
    street = this.data?.get(0)?.street ?: "",
    type = this.data?.get(0)?.type ?: "",
)