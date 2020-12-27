package com.lek.arcade.ui.home

import com.lek.arcade.core.helper.ViewDimention
import com.lek.arcade.ui.BaseViewModel

class HomeScreenViewModel :
    BaseViewModel<HomeScreenViewModel.HomeScreenState, HomeScreenViewModel.HomeScreenEvent>() {

    fun goToGameHost() {
        updateEvent(HomeScreenEvent.StartGame)
    }

    fun updateDimention(width: Int, height: Int) {
        ViewDimention.width = width
        ViewDimention.height = height
    }

    fun goToGameHostClicked() {
        updateEvent(HomeScreenEvent.StartGameClicked)
    }

    object HomeScreenState

    sealed class HomeScreenEvent {
        object StartGame: HomeScreenEvent()
        object StartGameClicked: HomeScreenEvent()
    }
}
