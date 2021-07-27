package org.bookmc.menu.mixins.client.gui;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiMainMenu;
import net.minecraft.client.gui.GuiScreen;
import org.bookmc.loader.api.vessel.ModVessel;
import org.bookmc.loader.impl.Loader;
import org.bookmc.menu.comparator.ModVesselComparator;
import org.bookmc.menu.gui.ModMenuScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;

@Mixin(GuiMainMenu.class)
public class MixinGuiMainMenu extends GuiScreen {
    @Inject(method = "addSingleplayerMultiplayerButtons", at = @At("RETURN"))
    private void addSingleplayerMultiplayerButtons(int p_73969_1_, int p_73969_2_, CallbackInfo ci) {
        // Replace the Realms button for our button :)
        for (GuiButton button : buttonList) {
            if (button.id == 14) {
                buttonList.remove(button);
                break;
            }
        }

        this.buttonList.add(new GuiButton(14, this.width / 2 - 100, p_73969_1_ + p_73969_2_ * 2, "Mods"));
    }

    @Inject(method = "actionPerformed", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/GuiMainMenu;switchToRealms()V"), cancellable = true)
    private void actionPerformed(CallbackInfo ci) {
        List<ModVessel> vesselList = new ArrayList<>(Loader.getModVessels());
        vesselList.sort(ModVesselComparator.INSTANCE);
        mc.displayGuiScreen(new ModMenuScreen(vesselList));
        // Cancel out all other invocations by returning. This means only our code will be called >:)
        ci.cancel();
    }
}
