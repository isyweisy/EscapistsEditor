package net.jselby.escapists.editor.utils;

import net.jselby.escapists.editor.EscapistsEditor;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;

/**
 * The SteamFinder discovers possible Steam paths, if required.
 *
 * @author j_selby
 */
public class SteamFinder {
    /**
     * Possible Steam locations. Prefixed by a drive.
     */
    private static final String[] POSSIBLE_LOCS = new String[] {

            // Windows Paths //
            "Program Files (x86)" + File.separator + "Steam" + File.separator,
            "Program Files" + File.separator + "Steam" + File.separator,
            "Steam" + File.separator,
            "SteamLibrary" + File.separator,
            "Games" + File.separator,
            "Games" + File.separator + "Steam" + File.separator,

            // Linux Paths //
            System.getProperty("user.home") + File.separator + ".steam" + File.separator + "steam" + File.separator,
            System.getProperty("user.home") + File.separator + ".steam" + File.separator + "root" + File.separator
    };

    /**
     * Discovers a steam installation.
     *
     * @return A steam installation, or null if one was not found.
     * @param editor The editor to use for generating prompts
     */
    public static File getSteamPath(EscapistsEditor editor) {
        // TODO: Support Mac.
        // Discover drive letters / filesystem root
        File[] drives = File.listRoots();

        // Check locations
        for (File drive : drives) {
            for (String possibleLoc : POSSIBLE_LOCS) {
                File steamDir = new File(drive, possibleLoc);

                if (steamDir.exists()) {
                    // Make sure that a steamapps exists here.
                    File steamFolder = new File(steamDir, "steamapps");
                    if (steamFolder.exists()) {
                        // Check that the Escapists is here
                        if (new File(steamDir,
                                "steamapps" + File.separator + "common" + File.separator + "The Escapists").exists()) {
                            return new File(steamDir,
                                    "steamapps" + File.separator + "common" + File.separator + "The Escapists");
                        }
                        if (new File(steamDir,
                                "common" + File.separator + "The Escapists").exists()) {
                            return new File(steamDir,
                                    "common" + File.separator + "The Escapists");
                        }
                        if (new File(steamDir,
                                "The Escapists").exists()) {
                            return new File(steamDir,
                                    "The Escapists");
                        }
                    }
                }
            }
        }

        // Ask user to find directory, if we are in a GUI.
        editor.dialog("No Stream installation detected. Please locate The Escapists in the following prompt.");
        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Directory";
            }
        });
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = chooser.showOpenDialog(null);
        if (JFileChooser.APPROVE_OPTION == result) {
            // Check for Escapists here
            File resultFile = chooser.getSelectedFile();
            File escapistsWin = new File(resultFile, "TheEscapists.exe");
            File escapistsLin64 = new File(resultFile, "bin64" + File.separator + "Chowdren");
            File escapistsLin32 = new File(resultFile, "bin32" + File.separator + "Chowdren");
            if (escapistsWin.exists()) {
                return resultFile;
            }
            else if (escapistsLin64.exists()) {
                return resultFile;
            }
            else if (escapistsLin32.exists()) {
                return resultFile;
            }
            else {
                EscapistsEditor.fatalError(new Exception("Invalid directory (No TheEscapists.exe)"));
            }
        }

        return null;
    }
}
