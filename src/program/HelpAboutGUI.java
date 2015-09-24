package program;
/** @version 1.0 | 2011-02-18
  * @author Sebastian Herrmann
  */
import java.awt.*;
import java.awt.Desktop.*;
import java.awt.event.*;
import java.awt.image.*;
import java.net.*;
import javax.swing.*;

public class HelpAboutGUI extends Frame {

  private ImageIcon imgAbout = new ImageIcon(Options.getFolderSystem()+"about.jpg");
  private JLabel lblImgAbout = new JLabel();
  private JLabel lblAbout = new JLabel();
  private Button btnClose = new Button("Schlieﬂen");

  public HelpAboutGUI (String title) {
    // frame initialisation
    super(title);
    setResizable(false);
    
    setLayout(new GridBagLayout());
    GridBagConstraints layout = new GridBagConstraints();

    // components:
    lblImgAbout.setIcon(imgAbout);
    layout.gridx = 0;
    layout.gridy = 0;
    add(lblImgAbout, layout);
    
    lblAbout.setText("<html>Version: 0.1<br />Entwickler: Sebastian Herrmann<br />Kontakt: sebastian@herrherrmann.net</html>");
    layout.fill = GridBagConstraints.HORIZONTAL;
    layout.insets = new Insets(0, 10, 0, 10);
    layout.gridx = 0;
    layout.gridy = 2;
    add(lblAbout, layout);
    
    layout.fill = GridBagConstraints.NONE;
    layout.insets = new Insets(10, 0, 10, 0);
    layout.gridx = 0;
    layout.gridy = 3;
    add(btnClose, layout);

    // listeners:
    addWindowListener(new WindowAdapter() {
      public void windowClosing (WindowEvent evt){dispose();}
    });


    btnClose.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        dispose();
      }
    });

    pack();
    setVisible(true);
    setLocationRelativeTo(null);
  }
  
  public static void main(String[] args) {
    new HelpAboutGUI("‹ber TabDaddy");
  }
}