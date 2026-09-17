package org.nice;

import javax.inject.Inject;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;

import net.runelite.client.ui.PluginPanel;

public class NicePanel extends PluginPanel
{
    private final Damage69ScreenshotPlugin plugin;

    @Inject
    public NicePanel(Damage69ScreenshotPlugin plugin)
    {
        this.plugin = plugin;

        setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel label = new JLabel("Screenshot settings");

        JButton chooseFolderButton = new JButton("Choose screenshot folder");

        chooseFolderButton.addActionListener(e ->
                plugin.chooseScreenshotDirectory()
        );

        panel.add(label, BorderLayout.NORTH);
        panel.add(chooseFolderButton, BorderLayout.CENTER);

        add(panel, BorderLayout.NORTH);
    }
}