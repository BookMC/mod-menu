package org.bookmc.menu.gui.generated

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.components.UIText
import gg.essential.elementa.components.UIWrappedText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.*
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import org.bookmc.loader.api.vessel.ModVessel
import java.awt.Color

class ModComponent(mod: ModVessel) : UIComponent() {
    init {
        val clazz = mod.config?.let { Class.forName(it) }

        UIText(mod.name).constrain {
            x = CenterConstraint()
            y = 5.pixels()
            textScale = 1.5.pixels()
        } childOf this

        if (mod.config != null) {
            UIImage.ofResource("/assets/modmenu/icons/cog.png").constrain {
                x = 0.pixels(true)
                height = 16.pixels()
                width = 16.pixels()
            }.onMouseClick {
                if (clazz != null) {
                    val instance = clazz.newInstance()
                    if (instance !is GuiScreen) {
                        return@onMouseClick
                    }
                    Minecraft.getMinecraft().displayGuiScreen(instance)
                }
            } childOf this
        }

        if (mod.description != null) {
            UIWrappedText(mod.description, centered = true).constrain {
                x = CenterConstraint()
                y = SiblingConstraint(5f)
                width = 65.percentOfWindow()
                color = Color.GRAY.toConstraint()
            } childOf this
        }

        UIWrappedText("Created by ${mod.authors.joinToString()}", centered = true).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(5f)
            width = 65.percentOfWindow()
            color = Color.GRAY.toConstraint()
        } childOf this

        UIWrappedText("Version: ${mod.version}", centered = true).constrain {
            x = CenterConstraint()
            y = SiblingConstraint(5f)
            width = 65.percentOfWindow()
            color = Color.GRAY.toConstraint()
        } childOf this
    }
}