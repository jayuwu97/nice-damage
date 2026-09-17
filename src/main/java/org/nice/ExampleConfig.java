package org.nice;

import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;

@ConfigGroup("nice69")
public interface ExampleConfig extends Config
{
	@ConfigItem(
			keyName = "overheadText",
			name = "Show overhead text",
			description = "Show 'Nice.' above your character when you deal 69 damage"
	)
	default boolean overheadText()
	{
		return true;
	}

	@ConfigItem(
			keyName = "chatMessage",
			name = "Show chat message",
			description = "Show 'Nice.' in the chatbox when you deal 69 damage"
	)
	default boolean chatMessage()
	{
		return true;
	}
}
