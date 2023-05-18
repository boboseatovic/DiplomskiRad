package hr.bornaseatovic.myapplication.main.features.calculation.map

sealed interface MapScreenIntents {
    object GoBack: MapScreenIntents
    object SearchAddress: MapScreenIntents
    object SearchLatLong: MapScreenIntents
    data class InputLatitude(val latitudeValue: String): MapScreenIntents
    data class InputLongitude(val longitudeValue: String): MapScreenIntents
    data class ChangeSearchValue(val searchValue: String): MapScreenIntents

    object DrawPolygon: MapScreenIntents

    object DoneDrawingPolygon: MapScreenIntents

}
