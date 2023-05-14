package hr.bornaseatovic.myapplication.main.features.home

sealed interface HomeScreenIntents {
    object PressCalculateNew: HomeScreenIntents

    object OpenMap: HomeScreenIntents
    object CloseMap: HomeScreenIntents
}
