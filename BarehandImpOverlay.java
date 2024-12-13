package net.runelite.client.plugins.barehandimp;

import net.runelite.api.Client;
import net.runelite.api.NPC;
import net.runelite.api.Skill;
import net.runelite.client.ui.overlay.Overlay;
import net.runelite.client.ui.overlay.OverlayLayer;
import net.runelite.client.ui.overlay.OverlayPosition;
import net.runelite.client.ui.overlay.OverlayPriority;

import javax.inject.Inject;
import java.awt.*;

public class BarehandImpOverlay extends Overlay {

    private final Client client;
    private final BarehandImpConfig config;

    @Inject
    public BarehandImpOverlay(Client client, BarehandImpConfig config) {
        this.client = client;
        this.config = config;
        setPosition(OverlayPosition.DYNAMIC);
        setLayer(OverlayLayer.ABOVE_SCENE);
        setPriority(OverlayPriority.HIGH);
    }

    @Override
    public Dimension render(Graphics2D graphics) {
        int hunterLevel = client.getBoostedSkillLevel(Skill.HUNTER);

        for (NPC npc : client.getNpcs()) {
            if (isBarehandCatchableImp(npc, hunterLevel)) {
                highlightImp(graphics, npc, config.highlightColor());
            }
        }
        return null;
    }

    private boolean isBarehandCatchableImp(NPC npc, int hunterLevel) {
        String npcName = npc.getName();
        if (npcName == null) {
            return false;
        }

        // Example logic for hunter levels required for different imps
        switch (npcName) {
            case "Baby impling":
                return hunterLevel >= 17;
            case "Young impling":
                return hunterLevel >= 22;
            case "Gourmet impling":
                return hunterLevel >= 28;
            case "Earth impling":
                return hunterLevel >= 36;
            case "Essence impling":
                return hunterLevel >= 42;
            case "Eclectic impling":
                return hunterLevel >= 50;
            case "Nature impling":
                return hunterLevel >= 58;
            case "Magpie impling":
                return hunterLevel >= 65;
            case "Ninja impling":
                return hunterLevel >= 74;
            case "Dragon impling":
                return hunterLevel >= 83;
            default:
                return false;
        }
    }

    private void highlightImp(Graphics2D graphics, NPC npc, Color color) {
        Shape convexHull = npc.getConvexHull(); // Returns a Shape
        if (convexHull != null) {
            graphics.setColor(color);
            graphics.setStroke(new BasicStroke(2));
            graphics.draw(convexHull); // Draws the Shape directly
        }
    }
}
