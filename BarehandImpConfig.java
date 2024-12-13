package net.runelite.client.plugins.barehandimp;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import java.awt.Color;

@ConfigGroup("barehandimp")
public interface BarehandImpConfig extends Config {

    @ConfigItem(
            keyName = "highlightColor",
            name = "Highlight Color",
            description = "The color to highlight imps you can catch"
    )
    default Color highlightColor() {
        return Color.GREEN;
    }
}
