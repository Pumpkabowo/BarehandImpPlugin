package net.runelite.client.plugins.barehandimp;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.api.Client;
import net.runelite.api.Skill;
import net.runelite.api.coords.WorldPoint;
import net.runelite.api.NPC;
import net.runelite.api.Point;
import net.runelite.api.Tile;
import net.runelite.api.events.GameStateChanged; // Correct import
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.OverlayUtil;
import java.awt.*;

@PluginDescriptor(
        name = "Barehand Imp",
        description = "Highlights Imps that the player can barehand catch",
        tags = {"imp", "hunter", "highlight"}
)
public class BarehandImpPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private BarehandImpOverlay overlay;

    @Inject
    private OverlayManager overlayManager;

    @Provides
    BarehandImpConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(BarehandImpConfig.class);
    }

    @Override
    protected void startUp() throws Exception
    {
        overlayManager.add(overlay);
    }

    @Override
    protected void shutDown() throws Exception
    {
        overlayManager.remove(overlay);
    }

    @Subscribe
    public void onGameStateChanged(GameStateChanged event)
    {
        // Add logic for handling game state changes, ensuring the overlay is managed properly
        if (isPlayerInPuroPuro())
        {
            overlayManager.remove(overlay);
        }
        else
        {
            overlayManager.add(overlay);
        }
    }

    private boolean isPlayerInPuroPuro()
    {
        WorldPoint playerLocation = client.getLocalPlayer().getWorldLocation();
        return playerLocation != null && playerLocation.getRegionID() == 10307; // Puro-Puro region ID
    }
}

