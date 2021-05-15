package org.bookmc.menu.gui.generated

import club.sk1er.elementa.UIComponent
import club.sk1er.elementa.components.UIImage
import club.sk1er.elementa.components.UIText
import club.sk1er.elementa.constraints.CenterConstraint
import club.sk1er.elementa.constraints.SiblingConstraint
import club.sk1er.elementa.dsl.childOf
import club.sk1er.elementa.dsl.constrain
import club.sk1er.elementa.dsl.pixels
import club.sk1er.elementa.dsl.toConstraint
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import org.bookmc.loader.vessel.ModVessel
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