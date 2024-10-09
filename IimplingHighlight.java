package net.runelite.client.plugins.implinghighlighter;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Skill;
import net.runelite.api.events.GameTick;
import net.runelite.api.events.NpcSpawned;
import net.runelite.api.events.GameObjectSpawned;
import net.runelite.api.events.GraphicsObjectCreated;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.components.PanelComponent;
import javax.inject.Inject;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

@PluginDescriptor(
        name = "Imp Highlighter"
)
public class IimplingHighlight extends Plugin
{
    private static final Map<String, Integer> IMPLING_LEVELS = new HashMap<>();

    static {
        IMPLING_LEVELS.put("Baby impling", 27);
        IMPLING_LEVELS.put("Young impling", 32);
        IMPLING_LEVELS.put("Gourmet impling", 38);
        IMPLING_LEVELS.put("Earth impling", 46);
        IMPLING_LEVELS.put("Essence impling", 52);
        IMPLING_LEVELS.put("Eclectic impling", 60);
        IMPLING_LEVELS.put("Nature impling", 68);
        IMPLING_LEVELS.put("Magpie impling", 75);
        IMPLING_LEVELS.put("Ninja impling", 84);
        IMPLING_LEVELS.put("Crystal impling", 90);
        IMPLING_LEVELS.put("Dragon impling", 93);
        IMPLING_LEVELS.put("Lucky impling", 99);
    }

    @Inject
    private Client client;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private ImplingHighlightOverlay overlay;

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
    public void onGameTick(GameTick event)
    {
        // Update state on each game tick if needed
    }

    @Subscribe
    public void onNpcSpawned(NpcSpawned event)
    {
        // Highlight imps here
    }

    @Subscribe
    public void onGameObjectSpawned(GameObjectSpawned event)
    {
        // Handle game objects if needed
    }

    @Subscribe
    public void onGraphicObjectCreated(GraphicsObjectCreated event)
    {
        // Handle graphic objects if needed
    }

    public boolean shouldHighlight(String impName)
    {
        int hunterLevel = client.getRealSkillLevel(Skill.HUNTER);
        Integer requiredLevel = IMPLING_LEVELS.get(impName);
        return requiredLevel != null && hunterLevel >= requiredLevel;
    }
}
