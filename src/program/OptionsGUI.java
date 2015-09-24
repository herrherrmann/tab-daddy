package program;
/** @version 0.1 | 23.02.2011
  * @author Sebastian Herrmann
  */
  
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;

public class OptionsGUI extends JFrame {

  // icons:
  final Icon icoSave = new ImageIcon("src/icons/accept.png");
  final Icon icoCancel = new ImageIcon("src/icons/cancel.png");
  final Icon icoFolder = new ImageIcon("src/icons/open.png");
  final Icon icoColumns = new ImageIcon("src/icons/columns.png");

  // components:
  private JLabel lblFolders = new JLabel("Ordner");
  private JLabel lblFolderImages = new JLabel("Bilder:");
  private JTextField tfdFolderImages = new JTextField(Options.getFolderImages());
  private JLabel lblFolderSounds = new JLabel("Sounds:");
  private JTextField tfdFolderSounds = new JTextField(Options.getFolderSounds());
  private JLabel lblFolderSystem = new JLabel("System:");
  private JTextField tfdFolderSystem = new JTextField(Options.getFolderSystem());
  
  private JSeparator separatorMiscellaneous = new JSeparator(JSeparator.HORIZONTAL);
  private JLabel lblMiscellaneous = new JLabel("Sonstiges");
  private JPanel pnlColumnCountStandard = new JPanel();
  private JLabel lblColumnCountStandard = new JLabel("Standard-Spalten:");
  private SpinnerModel model = new SpinnerNumberModel(Options.getColumnCountStandard(),1,4,1);
  private JSpinner spnColumnCountStandard = new JSpinner(model);
  
  private JSeparator separatorOptions = new JSeparator(JSeparator.HORIZONTAL);
  private JPanel pnlOptions = new JPanel();
  private JButton btnSave = new JButton("Speichern", icoSave);
  private JButton btnCancel = new JButton("Abbrechen", icoCancel);

  public OptionsGUI (String title) {
    super(title);
    setSize(250,175);
    setResizable(false);
    setLocationRelativeTo(null);
    
    setLayout(new GridBagLayout());
    GridBagConstraints layout = new GridBagConstraints();
    
    layout.fill = GridBagConstraints.HORIZONTAL;
    lblFolders.setFont(new Font("Sans-Serif", Font.BOLD, 14));
    layout.gridx = 0;
    layout.gridy = 0;
    add(lblFolders, layout);
    
    lblFolderImages.setIcon(icoFolder);
    layout.gridx = 0;
    layout.gridy = 1;
    add(lblFolderImages, layout);
    layout.gridx = 1;
    layout.gridy = 1;
    add(tfdFolderImages, layout);
    
    lblFolderSounds.setIcon(icoFolder);
    layout.gridx = 0;
    layout.gridy = 2;
    add(lblFolderSounds, layout);
    layout.gridx = 1;
    layout.gridy = 2;
    add(tfdFolderSounds, layout);
    
    lblFolderSystem.setIcon(icoFolder);
    layout.gridx = 0;
    layout.gridy = 3;
    add(lblFolderSystem, layout);
    layout.gridx = 1;
    layout.gridy = 3;
    add(tfdFolderSystem, layout);
    
    layout.gridx = 0;
    layout.gridy = 4;
    layout.gridwidth = 2;
    add(separatorMiscellaneous, layout);
    
    lblMiscellaneous.setFont(new Font("Sans-Serif", Font.BOLD, 14));
    layout.gridx = 0;
    layout.gridy = 5;
    add(lblMiscellaneous, layout);
    
    lblColumnCountStandard.setIcon(icoColumns);
    pnlColumnCountStandard.setLayout(new BorderLayout());
    pnlColumnCountStandard.add(lblColumnCountStandard, BorderLayout.WEST);
    pnlColumnCountStandard.add(spnColumnCountStandard, BorderLayout.EAST);
    layout.gridx = 0;
    layout.gridy = 6;
    layout.gridwidth = 2;
    add(pnlColumnCountStandard, layout);
    
    layout.gridx = 0;
    layout.gridy = 8;
    layout.gridwidth = 2;
    add(separatorOptions, layout);
    
    pnlOptions.setLayout(new BorderLayout());
    pnlOptions.add(btnSave, BorderLayout.WEST);
    pnlOptions.add(btnCancel, BorderLayout.EAST);
    layout.gridx = 0;
    layout.gridy = 9;
    layout.gridwidth = 2;
    add(pnlOptions, layout);
    
    
    // listeners:
    addWindowListener(new WindowAdapter() {
      public void windowClosing (WindowEvent evt){ dispose(); }
    });
    
    btnSave.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        // should check availability of the folders (try-catch or handling in Options.java)
        Options.setFolderImages( tfdFolderImages.getText() );
        Options.setFolderSounds( tfdFolderSounds.getText() );
        Options.setFolderSystem( tfdFolderSystem.getText() );
        Options.setColumnCountStandard( Integer.parseInt(spnColumnCountStandard.getValue().toString()) );
        dispose();
      }
    });
    btnCancel.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        dispose();
      }
    });
    
    
    setVisible(true);
  }

  public static void main(String[] args) {
    new OptionsGUI("Einstellungen");
  }
}
