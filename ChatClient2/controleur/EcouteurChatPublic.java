package controleur;

import com.chat.client.ClientChat;
import vue.PanneauChat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurChatPublic implements ActionListener {
    protected ClientChat clientChat;
    protected PanneauChat panneauChat;

    public EcouteurChatPublic(ClientChat clientChat, PanneauChat panneauChat) {
        this.clientChat = clientChat;
        this.panneauChat = panneauChat;
    }
    @Override
    public void actionPerformed(ActionEvent evt) {
       if(evt.getSource() instanceof JTextField) {
           JTextField text = (JTextField) evt.getSource();
           String msg  = text.getText();

           if (!msg.isEmpty()) {
               clientChat.envoyer("MSG " + msg);
               panneauChat.ajouter("MOI >> " + msg);
               text.setText("");
           }
       }

    }
}