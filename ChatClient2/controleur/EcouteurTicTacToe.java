package controleur;

import com.chat.client.ClientChat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcouteurTicTacToe implements ActionListener {

    private ClientChat clientChat;
    private String symbole;

    public EcouteurTicTacToe(ClientChat clientChat, String symbole) {
        this.clientChat = clientChat;
        this.symbole = symbole;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof JButton) {
            JButton boutonClique = (JButton) e.getSource();
            String position = boutonClique.getActionCommand();
            if (position != null && position.length() == 2){
                char ligne = position.charAt(0);
                char colonne = position.charAt(1);
                if (clientChat != null && symbole != null) {
                    clientChat.envoyer("COUP " + symbole + " " + ligne + " " + colonne);
                }
            }
        }
    }
}
