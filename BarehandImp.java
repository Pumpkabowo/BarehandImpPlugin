package net.runelite.client.plugins.barehandimp;

import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import com.google.inject.Provides; 
import javax.inject.Inject;

@PluginDescriptor(
        name = "Barehand Imp Highlighter",
        description = "Highlights imps you can barehand catch based on Hunter level",
        tags = {"hunter", "imps", "highlight"}
)
public class BarehandImpPlugin extends Plugin {

    @Inject
    private BarehandImpConfig config;

    @Override
    protected void startUp() throws Exception {
        // Plugin startup logic
    }

    @Override
    protected void shutDown() throws Exception {
        // Plugin shutdown logic
    }

    @Provides
    BarehandImpConfig provideConfig(ConfigManager configManager) {
        return configManager.getConfig(BarehandImpConfig.class);
    }
}
