package hr.bornaseatovic.myapplication.main.navigation

import androidx.navigation.NavOptionsBuilder
import hr.bornaseatovic.myapplication.main.navigation.events.Navigator
import hr.bornaseatovic.myapplication.main.navigation.events.NavigatorEvent
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NavigationManager @Inject constructor(
    private val externalScope: CoroutineScope
) : Navigator{

    var navigationEvents = MutableSharedFlow<NavigatorEvent>()

    private val handler = CoroutineExceptionHandler { _, exception ->
        println("CoroutineExceptionHandler got $exception")
    }

    override fun goBack() {
        externalScope.launch(handler) {
            navigationEvents.emit(NavigatorEvent.GoBack)
        }
    }

    override fun navigate(route: String, builder: NavOptionsBuilder.() -> Unit) {
        externalScope.launch(handler) {
            navigationEvents.emit(NavigatorEvent.Directions(route, builder))
        }
    }

//    override fun navigateBackTo(destination: String) {
//        externalScope.launch(handler) {
//            navigationEvents.emit(NavigatorEvent.NavigateBackTo(destination))
//        }
//    }

}