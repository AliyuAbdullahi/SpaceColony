package com.lek.arcade.entities.dialpad

interface DialpadDirectionListener {
    fun onUpSelected()
    fun onDownSelected()
    fun onLeftSelected()
    fun onRightSelected()
    fun onTwoInputSelected(first: DirectionButton, second: DirectionButton)
    fun clear()
}
