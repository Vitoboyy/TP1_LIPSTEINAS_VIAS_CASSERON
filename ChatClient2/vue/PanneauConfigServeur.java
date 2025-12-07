package vue;

import javax.swing.*;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class PanneauConfigServeur extends JPanel {
    private JTextField txtAdrServeur, txtNumPort;

    public PanneauConfigServeur(String adr, int port) {
        this.setLayout(new java.awt.GridLayout(2, 2, 5, 5));

        txtAdrServeur = new JTextField(adr);
        txtNumPort = new JTextField(String.valueOf(port)); // Conversion du int en String

        this.add(new JLabel("Adresse IP :", JLabel.RIGHT));
        this.add(txtAdrServeur);

        this.add(new JLabel("Port :",JLabel.RIGHT));
        this.add(txtNumPort);
    }
    public String getAdresseServeur() {
        return txtAdrServeur.getText();
    }
    public String getPortServeur() {
        return txtNumPort.getText();
    }
}
