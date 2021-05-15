package org.bookmc.menu.gui.generated

import club.sk1er.elementa.UIComponent
import club.sk1er.elementa.components.UIText
import club.sk1er.elementa.constraints.CenterConstraint
import club.sk1er.elementa.constraints.SiblingConstraint
import club.sk1er.elementa.dsl.childOf
import club.sk1er.elementa.dsl.constrain
import club.sk1er.elementa.dsl.pixels
import club.sk1er.elementa.dsl.toConstraint
import org.bookmc.loader.vessel.ModVessel
import java.awt.Color

class GeneratedModDataComponent(mod: ModVessel) : UIComponent() {
    init {
        UIText(mod.name).constrain {
            x = CenterConstraint()
            y = 5.pixels()
            textScale = 1.5.pixels()
        } childOf this

        if (mod.description != null) {
            UIText(mod.description).constrain {
                x = CenterConstraint()
                y = SiblingConstraint(5f)
                color = Color.GRAY.toConstraint()
            } childOf this
        }

        UIText("Created by ${mod.authors.joinToString()}").constrain {
            x = CenterConstraint()
            y = SiblingConstraint(5f)
            color = Color.GRAY.toConstraint()
        } childOf this
    }
}