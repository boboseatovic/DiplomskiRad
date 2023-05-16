package hr.bornaseatovic.myapplication.main.features.calculation.map

sealed interface MapScreenIntents {
    object GoBack: MapScreenIntents

    data class InputLatitude(val latitudeValue: String): MapScreenIntents
    data class InputLongitude(val longitudeValue: String): MapScreenIntents
}
