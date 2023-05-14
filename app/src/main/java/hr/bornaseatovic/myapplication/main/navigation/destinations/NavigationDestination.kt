package hr.bornaseatovic.myapplication.main.navigation.destinations

import androidx.navigation.NamedNavArgument

fun interface NavigationDestination {

    operator fun invoke() = destination()

    fun destination(): String
    val route: String
        get() = destination()
    val arguments: List<NamedNavArgument>
        get() = emptyList()
}