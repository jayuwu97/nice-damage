package org.nice;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("nice69")
public interface NiceConfig extends Config
{
	@ConfigItem(
			keyName = "screenshot",
			name = "Screenshot",
			description = "Take a screenshot whenever you deal 69 damage - Save location: .runelite/plugin-data/nice-damage/screenshots",
			position = 0
	)
	default boolean screenshot()
	{
		return true;
	}

	@ConfigItem(
			keyName = "overheadText",
			name = "Show overhead text",
			description = "Show 'Nice.' above your character whenever you deal 69 damage",
			position = 1
	)
	default boolean overheadText()
	{
		return true;
	}

	@ConfigItem(
			keyName = "chatMessage",
			name = "Show chat message",
			description = "Show 'Nice.' in the chatbox whenever you deal 69 damage",
			position = 2
	)
	default boolean chatMessage()
	{
		return true;
	}
}
