package controleur;

import com.chat.client.ClientChat;
import vue.PanneauChat;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurChatPrive extends EcouteurChatPublic {
    private String alias;
    public EcouteurChatPrive(String alias, ClientChat clientChat, PanneauChat panneauChat) {
        super(clientChat, panneauChat);
        this.alias = alias;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String action = evt.getActionCommand();
        if ("ACCEPTER".equals(action)) {
            clientChat.envoyer("TTT " + alias);
            return;
        } else if ("REFUSER".equals(action)) {
            clientChat.envoyer("DECLINE " + alias);
            return;
        }


        if (evt.getSource() instanceof JTextField) {
            JTextField text = (JTextField) evt.getSource();
            String msg = text.getText().trim();

            if (msg.isEmpty()) {
                return;
            }

            if ("QUIT".equals(msg)) {
                clientChat.envoyer("QUIT " + alias);
            } else if ("ABANDON".equals(msg)) {
                clientChat.envoyer("ABANDON");
            } else {
                clientChat.envoyer("PRV " + alias + " " + msg);
                panneauChat.ajouter("MOI>> " + msg);
            }
            text.setText("");
        }
    }
}