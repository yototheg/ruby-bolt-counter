package com.yototheg;

import net.runelite.api.MenuAction;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.ui.overlay.OverlayMenuEntry;
import net.runelite.client.ui.overlay.infobox.Counter;

import java.awt.image.BufferedImage;

public class RubyBoltSpecCounter extends Counter {
    private final String name;

    public RubyBoltSpecCounter(BufferedImage image, Plugin plugin, int count, String name) {
        super(image, plugin, count);
        this.name = name;
        getMenuEntries().add(new OverlayMenuEntry(MenuAction.RUNELITE_INFOBOX, "Reset", "Ruby Bolt Counter"));
    }

    @Override
    public String getTooltip()
    {
        return name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
