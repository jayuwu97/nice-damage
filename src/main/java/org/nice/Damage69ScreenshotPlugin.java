package org.nice;

import lombok.extern.slf4j.Slf4j;
import com.google.inject.Provides;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;

import javax.inject.Inject;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@PluginDescriptor(
        name = "69. Nice.",
        description = "Takes a screenshot whenever you deal 69 damage"
)
public class Damage69ScreenshotPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ExampleConfig config;

    @Provides
    ExampleConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(ExampleConfig.class);
    }

    @Override
    protected void startUp()
    {
        log.info("===== 69 PLUGIN LOADED =====");
    }

    @Subscribe
    public void onHitsplatApplied(HitsplatApplied event)
    {
        if (event.getHitsplat().getAmount() == 69)
        {
            log.debug("===== 69 DAMAGE DETECTED =====");

            // Show "nice" above your character
            if (config.overheadText())
            {
                client.getLocalPlayer().setOverheadText("Nice.");
            }

            // Show "nice" in the chatbox
            if (config.chatMessage())
            {
                client.addChatMessage(
                        ChatMessageType.PUBLICCHAT,
                        client.getLocalPlayer().getName(),
                        "Nice.",
                        null
                );
            }

            new Thread(() ->
            {
                try
                {
                    log.debug("===== WAITING 100MS =====");
                    Thread.sleep(100);
                    log.debug("===== TAKING SCREENSHOT =====");
                    takeScreenshot();

                    //Remove overhead text after another 3 seconds
                    Thread.sleep(3000);
                    client.getLocalPlayer().setOverheadText(null);
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
            }).start();
        }
    }

    private void takeScreenshot()
    {
        try
        {
            Robot robot = new Robot();

            Rectangle window = client.getCanvas().getBounds();
            Point location = client.getCanvas().getLocationOnScreen();

            window.setLocation(location);

            BufferedImage image = robot.createScreenCapture(window);

            String timestamp = new SimpleDateFormat(
                    "yyyy-MM-dd_HH-mm-ss-SSS"
            ).format(new Date());

            File folder = new File(
                    System.getProperty("user.home"),
                    ".runelite/screenshots/nice"
            );

            folder.mkdirs();

            File file = new File(
                    folder,
                    "nice_" + timestamp + ".png"
            );

            ImageIO.write(image, "png", file);

            log.debug("NICE SCREENSHOT: {}", file);
        }
        catch (Exception e)
        {
            log.error("Failed to take screenshot", e);
        }
    }
}

