package net.jselby.escapists.editor.elements;

import net.jselby.escapists.editor.mapping.Map;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * A simple zone selection GUI
 */
public class ZoneSelectionGUI extends JFrame {
    private RenderView oldView;

    public ZoneSelectionGUI(MapRendererComponent renderer, Map map, int x1, int y1, int x2, int y2) throws IOException {
        String[] zones = {"Rollcall", "YourCell", "SHU", "Cells1", "Cells2", "Cells3", "Cells4",
                "Cells5", "Cells6", "Cells7", "Cells8", "Cells9", "Cells10", "Cells11", "Cells12",
                "Cells13", "Cells14", "Cells15", "Laundry", "Kitchen", "Canteen", "Gym", "Showers",
                "Woodshop", "Metalshop", "Tailorshop", "Deliveries", "Janitor", "Gardening",
                "Safe1", "Safe2", "Safe3"};
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20));
        final JLabel descriptor = new JLabel("Select a zone:");
        descriptor.setAlignmentX(CENTER_ALIGNMENT);
        add(descriptor);

        add(Box.createVerticalStrut(20));

        final JComboBox zonesSelector = new JComboBox(zones);
        zonesSelector.setAlignmentX(CENTER_ALIGNMENT);
        zonesSelector.setMaximumSize(new Dimension(150, 30));
        add(zonesSelector);

        add(Box.createVerticalStrut(20));

        final JButton create = new JButton("Create");
        create.setAlignmentX(CENTER_ALIGNMENT);
        create.setMaximumSize(new Dimension(150, 30));
        add(create);

        // Button actions
        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                zonesSelector.setEditable(false);
                create.setEnabled(false);

                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String value = x1 + "_" + y1 + "_" + x2 + "_" + y2;
                        map.set("Zones." + (String) zonesSelector.getSelectedItem(), value);

                        oldView.setEnabled(true);
                        renderer.refresh();
                        dispose();
                    }
                });
            }
        });

        setSize(200, 200);
        setResizable(false);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (oldView != null) {
                    oldView.setEnabled(true);
                }
            }
        });

        setLocationRelativeTo(null);
        setTitle("Create Zone");
        setIconImage(ImageIO.read(getClass().getResource("/icon.png")));
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException |
                InstantiationException |
                IllegalAccessException |
                UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    public void setOldView(RenderView oldView) {
        this.oldView = oldView;
    }

    public RenderView getOldView() {
        return oldView;
    }
}
