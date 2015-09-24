package program;
/** @version 0.1 | 23.02.2011
  * @author Sebastian Herrmann
  */

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.awt.Desktop.*;
import java.net.*;
import javax.swing.*;
import javax.swing.UIManager.*;

public class TabDaddyGUI extends JFrame {
  // test objects:
  Chord aDur = new Chord("A-Dur","A-Dur.jpg","A-Dur.wav");
  Chord bDur = new Chord("B-Dur / H-Dur","B-Dur.jpg");
  Chord cDur = new Chord("C","C.jpg");
  Chord dDur = new Chord("D-Dur","D-Dur.jpg","D-Dur.wav");
  Chord eDur = new Chord("E-Dur","E-Dur.jpg");

  // icons:
  final Icon icoNew = new ImageIcon("src/icons/new.png");
  final Icon icoOpen = new ImageIcon("src/icons/open.png");
  final Icon icoSave = new ImageIcon("src/icons/save.png");
  final Icon icoSettings = new ImageIcon("src/icons/settings.png");
  final Icon icoQuit = new ImageIcon("src/icons/quit.png");
  
  final Icon icoAdd = new ImageIcon("src/icons/add.png");
  final Icon icoColumns = new ImageIcon("src/icons/columns.png");
  final Icon icoSoundOff = new ImageIcon("src/icons/sound_off.png");
  final Icon icoSoundOn = new ImageIcon("src/icons/sound_on.png");
  
  final Icon icoWebsite = new ImageIcon("src/icons/website.png");
  final Icon icoAbout = new ImageIcon("src/icons/about.png");

  // menu objects:
  private JMenuBar mnuMenu = new JMenuBar();
  private JMenu mnuFile = new JMenu("Datei");
  private JMenuItem mnuFileNew = new JMenuItem("Neues Set", KeyEvent.VK_N);
  private JMenuItem mnuFileOpen = new JMenuItem("Set Öffnen...", KeyEvent.VK_O);
  private JMenuItem mnuFileSave = new JMenuItem("Set Speichern...", KeyEvent.VK_S);
  private JMenuItem mnuFileSettings = new JMenuItem("Einstellungen", KeyEvent.VK_COMMA);
  private JMenuItem mnuFileQuit = new JMenuItem("Beenden", KeyEvent.VK_Q);
  private JMenu mnuChords = new JMenu("Akkorde");
  private JMenuItem mnuChordsManager = new JMenuItem("Hinzufügen/Entfernen...");
  private JMenu mnuChordsColumns = new JMenu("Spaltenanzahl");
  private JRadioButtonMenuItem mnuChordsColumns1 = new JRadioButtonMenuItem("1", true);
  private JRadioButtonMenuItem mnuChordsColumns2 = new JRadioButtonMenuItem("2", false);
  private JRadioButtonMenuItem mnuChordsColumns3 = new JRadioButtonMenuItem("3", false);
  private JRadioButtonMenuItem mnuChordsColumns4 = new JRadioButtonMenuItem("4", false);
  private ButtonGroup bgpChordsColumns = new ButtonGroup();
  private JCheckBoxMenuItem mnuChordsPreview = new JCheckBoxMenuItem("Audiovorschau", false);
  private JMenu mnuHelp = new JMenu("Hilfe");
  private JMenuItem mnuHelpWebsite = new JMenuItem("Website");
  private JMenuItem mnuHelpAbout = new JMenuItem("Über...", KeyEvent.VK_F1);

  // separator:
  private JSeparator separator = new JSeparator(JSeparator.VERTICAL);
  //lblSeparator.setBackground(Color.black);

  // column 1 objects:
  // private Panel pnlColumn1 = new Panel();
  private ImageIcon imgChord1 = new ImageIcon(Options.getBlankImage());
  private JLabel lblImgChord1 = new JLabel();
  private JLabel lblChordName1 = new JLabel();
  private JButton btnPreview1 = new JButton();
  private List lstChords1 = new List();
  
  public TabDaddyGUI (String title) {
    // frame initialisation
    super(title);
    setSize(200,300);
    setResizable(false);
    setLocationRelativeTo(null);

    setLayout(new GridBagLayout());
    GridBagConstraints layout = new GridBagConstraints();

    // menus:
    mnuFile.add(mnuFileNew); // file
    mnuFile.add(mnuFileOpen);
    mnuFile.add(mnuFileSave);
    mnuFile.addSeparator();
    mnuFile.add(mnuFileSettings);
    mnuFile.addSeparator();
    mnuFile.add(mnuFileQuit);
    mnuMenu.add(mnuFile);
    mnuChords.add(mnuChordsManager); // chords
    mnuChords.addSeparator();
    mnuChords.add(mnuChordsColumns);
    bgpChordsColumns.add(mnuChordsColumns1);
    bgpChordsColumns.add(mnuChordsColumns2);
    bgpChordsColumns.add(mnuChordsColumns3);
    bgpChordsColumns.add(mnuChordsColumns4);
    mnuChordsColumns.add(mnuChordsColumns1);
    mnuChordsColumns.add(mnuChordsColumns2);
    mnuChordsColumns.add(mnuChordsColumns3);
    mnuChordsColumns.add(mnuChordsColumns4);
    mnuChords.add(mnuChordsPreview);
    mnuMenu.add(mnuChords);
    mnuHelp.add(mnuHelpWebsite); // help
    mnuHelp.addSeparator();
    mnuHelp.add(mnuHelpAbout);
    mnuMenu.add(mnuHelp);
    
    // tooltips and icons:
    mnuFileNew.setToolTipText("Erstellt ein neues Set von Akkorden. (STRG+N)");
    mnuFileNew.setIcon(icoNew); // file
    mnuFileOpen.setIcon(icoOpen);
    mnuFileSave.setIcon(icoSave);
    mnuFileSettings.setIcon(icoSettings);
    mnuFileQuit.setIcon(icoQuit);
    mnuChordsManager.setIcon(icoAdd); // chords
    mnuChordsColumns.setIcon(icoColumns);
    setPreviewIcon();
    mnuHelpWebsite.setIcon(icoWebsite); // help
    mnuHelpAbout.setIcon(icoAbout);
    
    setJMenuBar(mnuMenu);

    // column 1 components:
    lblImgChord1.setIcon(imgChord1);
    // lblImgChord1.setSize(175,150);
    layout.gridx = 0;
    layout.gridy = 0;
    add(lblImgChord1, layout);
    
    lblChordName1.setFont(new Font("Sans-Serif", Font.BOLD, 20));
    lblChordName1.setText("Bitte auswählen:");
    layout.fill = GridBagConstraints.HORIZONTAL;
    layout.gridx = 0;
    layout.gridy = 1;
    add(lblChordName1, layout);
    
    createChordList(lstChords1);
    layout.fill = GridBagConstraints.HORIZONTAL;
    layout.gridx = 0;
    layout.gridy = 2;
    add(lstChords1, layout);
    
    // separator:
    layout.gridx = 1;
    layout.gridy = 0;
    layout.gridheight = 3;
    add(separator, layout);


    // listeners:
    addWindowListener(new WindowAdapter() {
      public void windowClosing (WindowEvent evt){System.exit(0);}
    });
    
    lstChords1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        lblImgChord1.setIcon(new ImageIcon(Chord.getImageById(lstChords1.getSelectedIndex()+1)));
        lblChordName1.setText(Chord.getNameById(lstChords1.getSelectedIndex()+1));
        if(Options.getPreviewActive() == true) {
          try {
            Chord.playSoundById(lstChords1.getSelectedIndex()+1);
          }
          catch (Exception e) {
            lblChordName1.setText("Soundfehler");
          }
        }
      }
    });
    
    // file menu:
    mnuFileQuit.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        System.exit(0);
      }
    });
    mnuFileSettings.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        new OptionsGUI("Einstellungen");
      }
    });
    
    // chords menu:
    mnuChordsPreview.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        if(mnuChordsPreview.getState() == true /* state before change */) {
          Options.setPreviewActive(true);
        } else {
          Options.setPreviewActive(false);
        }
        setPreviewIcon();
      }
    });
    
    // help menu:
    mnuHelpWebsite.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        try {
          Desktop.getDesktop().browse(new URI("http://herrherrmann.net/"));
        }
        catch (Exception /* IOException, URISyntaxException */ e) {
          lblChordName1.setText("URL-Fehler");
        }
      }
    });
    mnuHelpAbout.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        new HelpAboutGUI("Über TabDaddy");
      }
    });
    
    //pack();
    setVisible(true);
  }
  
  private void setPreviewIcon() {
    if(mnuChordsPreview.getState() == false) {
      mnuChordsPreview.setIcon(icoSoundOff);
    } else {
      mnuChordsPreview.setIcon(icoSoundOn);
    }
  }
  
  private void createChordList(List list) {
    list.removeAll();
    for (int x=1; x <= Chord.getChordCount(); x++) {
      list.add(Chord.getNameById(x));
    }
  }

  public static void main (String[] args) {
    new TabDaddyGUI("TabDaddy");
  }
}