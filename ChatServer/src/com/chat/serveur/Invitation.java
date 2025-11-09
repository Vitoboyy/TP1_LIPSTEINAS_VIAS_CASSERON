package com.chat.serveur;

import com.commun.net.Connexion;
import com.chat.serveur.Serveur;

public class Invitation {
    String aliasInvite;
    String aliasHost;

    public Invitation(String aliasInvite, String aliasHost) {
        this.aliasInvite = aliasInvite;
        this.aliasHost = aliasHost;
    }
}
