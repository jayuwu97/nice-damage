package org.nice;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import net.runelite.client.callback.ClientThread;
import java.awt.Graphics2D;
import net.runelite.client.ui.DrawManager;
import lombok.extern.slf4j.Slf4j;
import com.google.inject.Provides;
import net.runelite.api.ChatMessageType;
import net.runelite.api.Client;
import net.runelite.api.events.HitsplatApplied;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.util.Filepath;

import javax.inject.Inject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
@PluginDescriptor(
        name = "69. Nice.",
        description = "Takes a screenshot whenever you deal 69 damage. Save location: runelite/plugin-data/nice-damage/screenshots.",
        internalName = "nice-damage"
)
public class Damage69ScreenshotPlugin extends Plugin
{
    @Inject
    private Client client;

    @Inject
    private ScheduledExecutorService scheduledExecutorService;

    @Inject
    private DrawManager drawManager;

    @Inject
    private ClientThread clientThread;

    @Inject
    private NiceConfig config;

    private Filepath screenshotDirectory;

    @Provides
    NiceConfig provideConfig(ConfigManager configManager)
    {
        return configManager.getConfig(NiceConfig.class);
    }

    @Override
    protected void startUp()
    {
        log.info("===== 69 PLUGIN LOADED =====");

        try
        {
            screenshotDirectory = getPluginDirectory().join("screenshots");
        }
        catch (Exception e)
        {
            log.error("Failed to set screenshot directory", e);
        }
    }

    @Subscribe
    public void onHitsplatApplied(HitsplatApplied event)
    {
        if (event.getHitsplat().getAmount() == 69)
        {
            log.debug("===== 69 DAMAGE DETECTED =====");

            if (config.overheadText())
            {
                client.getLocalPlayer().setOverheadText("Nice.");
            }

            if (config.chatMessage())
            {
                client.addChatMessage(
                        ChatMessageType.PUBLICCHAT,
                        client.getLocalPlayer().getName(),
                        "Nice.",
                        null
                );
            }

            drawManager.requestNextFrameListener(image ->
            {
                BufferedImage bufferedImage = new BufferedImage(
                        image.getWidth(null),
                        image.getHeight(null),
                        BufferedImage.TYPE_INT_ARGB
                );

                Graphics2D graphics = bufferedImage.createGraphics();
                graphics.drawImage(image, 0, 0, null);
                graphics.dispose();

                takeScreenshot(bufferedImage);
            });

            scheduledExecutorService.schedule(() ->
                            clientThread.invokeLater(() ->
                                    client.getLocalPlayer().setOverheadText(null)
                            ),
                    3,
                    TimeUnit.SECONDS
            );
        }
    }

    private void takeScreenshot(BufferedImage image)
    {
        try
        {
            String timestamp = new SimpleDateFormat(
                    "yyyy-MM-dd_HH-mm-ss-SSS"
            ).format(new Date());

            Filepath folder = screenshotDirectory;
            folder.createDirectories();

            Filepath file = folder.join(
                    "nice_" + timestamp + ".png"
            );

            try (java.io.OutputStream outputStream = file.openOutputStream())
            {
                ImageIO.write(image, "png", outputStream);
            }

            log.debug("NICE SCREENSHOT: {}", file);
        }
        catch (Exception e)
        {
            log.error("Failed to take screenshot", e);
        }
    }
}

