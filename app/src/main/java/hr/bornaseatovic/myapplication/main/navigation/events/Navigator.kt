package hr.bornaseatovic.myapplication.main.navigation.events

import androidx.navigation.NavOptionsBuilder

interface Navigator {

    fun goBack()

    fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit = { launchSingleTop = true })

//    fun navigateBackTo(destination: String)
}