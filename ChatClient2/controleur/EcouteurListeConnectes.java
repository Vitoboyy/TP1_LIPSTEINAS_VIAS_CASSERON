package controleur;

import com.chat.client.ClientChat;
import javax.swing.JList;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Abdelmoumène Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-11-01
 */
public class EcouteurListeConnectes extends MouseAdapter {

    private ClientChat clientChat;
    public EcouteurListeConnectes(ClientChat clientChat) {
        this.clientChat = clientChat;
    }

    @Override
    public void mouseClicked(MouseEvent evt) {
        if (evt.getClickCount() == 2) {
            JList<String> list = (JList<String>) evt.getSource();
            String alias = list.getSelectedValue();
            if (alias != null) {
                clientChat.envoyer("JOIN " + alias);
            }
        }
    }
}
