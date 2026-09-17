package org.nice;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class ExamplePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(
				ExamplePlugin.class,
				Damage69ScreenshotPlugin.class
		);

		RuneLite.main(args);
	}
}