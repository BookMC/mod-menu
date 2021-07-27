package org.bookmc.menu.comparator;

import org.bookmc.loader.api.vessel.ModVessel;

import java.util.Comparator;

public class ModVesselComparator implements Comparator<ModVessel> {
    public static Comparator<ModVessel> INSTANCE = new ModVesselComparator();

    @Override
    public int compare(ModVessel o1, ModVessel o2) {
        return Integer.compare(o1.getName().length(), o2.getName().length());
    }
}
