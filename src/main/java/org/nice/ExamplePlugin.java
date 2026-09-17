package org.nice;

import com.google.inject.Provides;
import javax.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.events.GameStateChanged;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

@Slf4j
@PluginDescriptor(
	name = "Nice."
)
public class ExamplePlugin extends Plugin
{
	@Inject
	private Client client;

	@Inject
	private NiceConfig config;

	@Override
	protected void startUp() throws Exception
	{
		log.debug("Nice. started!");
	}

	@Override
	protected void shutDown() throws Exception
	{
		log.debug("Nice. stopped!");
	}

	@Subscribe
	public void onGameStateChanged(GameStateChanged gameStateChanged)
	{
		if (gameStateChanged.getGameState() == GameState.LOGGED_IN)
		{
		}
	}

	@Provides
	NiceConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NiceConfig.class);
	}
}
