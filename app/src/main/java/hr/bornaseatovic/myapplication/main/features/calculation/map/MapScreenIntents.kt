package hr.bornaseatovic.myapplication.main.features.calculation.map

sealed interface MapScreenIntents {
    object GoBack: MapScreenIntents

    data class Search(val searchValue: String): MapScreenIntents
}
