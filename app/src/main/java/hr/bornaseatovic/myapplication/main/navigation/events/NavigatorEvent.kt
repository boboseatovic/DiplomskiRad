package hr.bornaseatovic.myapplication.main.navigation.events

import androidx.navigation.NavOptionsBuilder

sealed class NavigatorEvent {

    object GoBack: NavigatorEvent()

    class Directions(val destination: String, val builder: NavOptionsBuilder.() -> Unit) :
        NavigatorEvent()

//    class NavigateBackTo(val destination: String) : NavigatorEvent()

}