package net.runelite.client.plugins.barehandimp;

import net.runelite.client.config.*;
import java.awt.Color;

@ConfigGroup("barehandimp")
public interface BarehandImpConfig extends Config
{
    @ConfigItem(
            keyName = "highlightColor",
            name = "Highlight Color",
            description = "Set the color of the highlight for Imps",
            position = 0
    )
    default Color highlightColor()
    {
        return Color.YELLOW;
    }

}
