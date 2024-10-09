package net.runelite.client.plugins.implinghighlighter;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;
import javax.inject.Inject;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class ImplingHighlightOverlay extends Overlay {

    private final Client client;
    private final IimplingHighlight plugin;

    @Inject
    public ImplingHighlightOverlay(Client client, IimplingHighlight plugin) {
        this.client = client;
        this.plugin = plugin;
        setPosition(OverlayPosition.DYNAMIC);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        client.getNpcs().stream()
                .filter(this::shouldHighlightNpc)
                .map(NPC::getCanvasTilePoly)
                .forEach(poly -> drawPoly(graphics, poly));
        return null;
    }

    private boolean shouldHighlightNpc(NPC npc) {
        return npc != null && npc.getName() != null && plugin.shouldHighlight(npc.getName());
    }

    private void drawPoly(Graphics2D graphics, Polygon poly) {
        if (poly != null) {
            graphics.setColor(Color.RED);
            graphics.draw(poly);
            graphics.setColor(new Color(255, 0, 0, 50)); // Semi-transparent fill
            graphics.fill(poly);
        }
    }
}
