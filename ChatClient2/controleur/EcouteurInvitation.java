package controleur;

import com.chat.client.ClientChat;
import vue.PanneauInvitations;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class EcouteurInvitation implements ActionListener{
    private ClientChat clientChat;
    private PanneauInvitations panneauInvitations;

    public EcouteurInvitation(ClientChat clientChat, PanneauInvitations panneauInvitations) {
        this.clientChat = clientChat;
        this.panneauInvitations = panneauInvitations;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        List<String> selectedAliases = panneauInvitations.getElementsSelectionnes();

        if (selectedAliases == null || selectedAliases.isEmpty()) {
            return;
        }
        for (String alias : selectedAliases) {
            if ("ACCEPTER".equals(action)) {
                clientChat.envoyer("JOIN " + alias);
            }else if ("REFUSER".equals(action)) {
                clientChat.envoyer("DECLINE " + alias);
            }
            panneauInvitations.retirerInvitationRecue(alias);
        }

    }
}
