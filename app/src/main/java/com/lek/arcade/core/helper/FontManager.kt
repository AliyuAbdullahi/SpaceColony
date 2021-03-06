package com.lek.arcade.core.helper

import android.content.Context
import android.graphics.Typeface

object FontManager {

    fun getTypeForFont(context: Context, font: Font): Typeface {
        return Typeface.createFromAsset(context.assets, font.path)
    }

    enum class Font(val path: String) {
        SPACE_QUEST_XJ4O("fonts/SpaceQuest-Xj4o.ttf"),
        SPACE_QUEST_XJ40_ITALLIC("fonts/SpaceQuestItalic-60Rx.ttf"),
        SPACE_QUEST_Y0Y3("fonts/SpaceQuest-yOY3.ttf"),
        GLADIATOR_SPORT("fonts/GladiatorSport-O82O.ttf"),
        GLADIATOR_SPORT_BOLD("fonts/GladiatorSportBold-ZP4q.ttf"),
        GLADIATOR_SPORT_BOLD_ITALLICS("fonts/GladiatorSportBoldItalic-3BWX.ttf"),
        GLADIATOR_SPORT_ITALLIC("fonts/GladiatorSportItalic-xgKj.ttf"),
        SQUIRK("fonts/Squirk-RMvV.ttf")
    }
}
