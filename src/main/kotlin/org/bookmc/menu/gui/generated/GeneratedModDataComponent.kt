package org.bookmc.menu.gui.generated

import gg.essential.elementa.UIComponent
import gg.essential.elementa.components.UIImage
import gg.essential.elementa.components.UIText
import gg.essential.elementa.constraints.CenterConstraint
import gg.essential.elementa.constraints.SiblingConstraint
import gg.essential.elementa.dsl.childOf
import gg.essential.elementa.dsl.constrain
import gg.essential.elementa.dsl.pixels
import gg.essential.elementa.dsl.toConstraint
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import org.bookmc.loader.api.vessel.ModVessel
import java.awt.Color

class GeneratedModDataComponent(mod: ModVessel) : UIComponent() {
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

        UIText("Version: ${mod.version}").constrain {
            x = CenterConstraint()
            y = SiblingConstraint()
            color = Color.GRAY.toConstraint()
        } childOf this
    }
}