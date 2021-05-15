package org.bookmc.menu.gui

import club.sk1er.elementa.UIComponent
import club.sk1er.elementa.WindowScreen
import club.sk1er.elementa.components.UIBlock
import club.sk1er.elementa.components.UIContainer
import club.sk1er.elementa.components.UIText
import club.sk1er.elementa.constraints.SiblingConstraint
import club.sk1er.elementa.dsl.*
import club.sk1er.elementa.effects.OutlineEffect
import net.minecraft.client.Minecraft
import org.bookmc.loader.vessel.ModVessel
import org.bookmc.menu.gui.generated.GeneratedModDataComponent
import java.awt.Color

private const val DEFAULT_TEXT_CONTAINER_WIDTH = 15

class ModMenuScreen(vessels: List<ModVessel>) : WindowScreen() {
    private val selectedColor = Color(1, 165, 82)

    private val isWorldAvailable get() = Minecraft.getMinecraft().theWorld != null

    private val background get() = if (isWorldAvailable) Color(0, 0, 0, 50) else Color(27, 27, 27)

    init {
        val block = UIBlock(background).constrain {
            width = 100.percent()
            height = 100.percent()
        } childOf window

        with(block) {
            UIText("Mods").constrain {
                x = 15.pixels()
                y = 15.pixels()
                textScale = 2.pixels()
            } childOf this

            val modsContainer = UIContainer().constrain {
                x = 15.pixels()
                y = SiblingConstraint(8f)
                height = (vessels.size * 10).percent()
                width =
                    ((vessels.minByOrNull { it.name.length }?.name?.length
                        ?: DEFAULT_TEXT_CONTAINER_WIDTH) * 4).percent()
            } childOf this

            val modDataContainer = UIContainer().constrain {
                x = 20.percent()
                y = 10.percent()
                width = 75.percent()
                height = 80.percent()
            } effect OutlineEffect(Color.DARK_GRAY, 1f) childOf this

            var selected: UIComponent? = null

            vessels.forEachIndexed { index, modVessel ->
                val textColor = if (index == 0) selectedColor else Color.GRAY

                val text = UIText(modVessel.name).constrain {
                    color = textColor.toConstraint()
                    y = SiblingConstraint(8f)
                } childOf modsContainer

                text.onMouseClick {
                    if (selected != text) {
                        selected?.setColor(Color.GRAY.toConstraint())
                        text.setColor(selectedColor.toConstraint())

                        modDataContainer.clearChildren()
                        GeneratedModDataComponent(modVessel).constrain {
                            height = 100.percent()
                            width = 100.percent()
                        } childOf modDataContainer

                        selected = text
                    }
                }

                if (index == 0) {
                    GeneratedModDataComponent(modVessel).constrain {
                        height = 100.percent()
                        width = 100.percent()
                    } childOf modDataContainer

                    selected = text
                }
            }
        }
    }
}