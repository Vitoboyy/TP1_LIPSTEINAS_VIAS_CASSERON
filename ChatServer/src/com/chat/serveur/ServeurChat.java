package com.chat.serveur;

import com.commun.net.Connexion;
import  java.util.Iterator;
import java.util.Vector;

/**
 * Cette classe �tend (h�rite) la classe abstraite Serveur et y ajoute le n�cessaire pour que le
 * serveur soit un serveur de chat.
 *
 * @author Abdelmoum�ne Toudeft (Abdelmoumene.Toudeft@etsmtl.ca)
 * @version 1.0
 * @since 2023-09-15
 */
public class ServeurChat extends Serveur {

    private Vector<String> historique = new Vector<>();
    private Vector<Invitation> invit = new Vector<>();
    private Vector<SalonPrive> prives = new Vector<>();

    /**
     * Cr�e un serveur de chat qui va �couter sur le port sp�cifi�.
     *
     * @param port int Port d'�coute du serveur
     */
    public ServeurChat(int port) {
        super(port);
    }

    @Override
    public synchronized boolean ajouter(Connexion connexion) {
        String hist = this.historique();
        if ("".equals(hist)) {
            connexion.envoyer("OK");
        }
        else {
            connexion.envoyer("HIST " + hist);
        }
        return super.ajouter(connexion);
    }
    /**
     * Valide l'arriv�e d'un nouveau client sur le serveur. Cette red�finition
     * de la m�thode h�rit�e de Serveur v�rifie si le nouveau client a envoy�
     * un alias compos� uniquement des caract�res a-z, A-Z, 0-9, - et _.
     *
     * @param connexion Connexion la connexion repr�sentant le client
     * @return boolean true, si le client a valid� correctement son arriv�e, false, sinon
     */
    @Override
    protected boolean validerConnexion(Connexion connexion) {

        String aliasFourni = connexion.getAvailableText().trim();
        char c;
        int taille;
        boolean res = true;
        if ("".equals(aliasFourni)) {
            return false;
        }
        taille = aliasFourni.length();
        for (int i=0;i<taille;i++) {
            c = aliasFourni.charAt(i);
            if ((c<'a' || c>'z') && (c<'A' || c>'Z') && (c<'0' || c>'9')
                    && c!='_' && c!='-') {
                res = false;
                break;
            }
        }
        if (!res)
            return false;
        for (Connexion cnx:connectes) {
            if (aliasFourni.equalsIgnoreCase(cnx.getAlias())) { //alias d�j� utilis�
                res = false;
                break;
            }
        }
        if (!res)
            return false;
        connexion.setAlias(aliasFourni);
        return true;
    }

    /**
     * Retourne la liste des alias des connect�s au serveur dans une cha�ne de caract�res.
     *
     * @return String cha�ne de caract�res contenant la liste des alias des membres connect�s sous la
     * forme alias1:alias2:alias3 ...
     */
    public String list() {
        String s = "";
        for (Connexion cnx:connectes)
            s+=cnx.getAlias()+":";
        return s;
    }
    /**
     * Retourne la liste des messages de l'historique de chat dans une cha�ne
     * de caract�res.
     *
     * @return String cha�ne de caract�res contenant la liste des alias des membres connect�s sous la
     * forme message1\nmessage2\nmessage3 ...
     */
    public String historique() {
        String s = "";
        for (String hist:historique) {
            s+=hist+"\n";
        }
        return s;
    }

    public void envoyerATousSauf(String str, String aliasExpediteur){
        String msg = aliasExpediteur +" >> "+str;
        for (Connexion cnx:connectes){
            if (!cnx.getAlias().equals(aliasExpediteur)){
                cnx.envoyer(msg);
            }
        }
    }

    public void ajouterHistorique(String msg, String aliasExp){
        String s = aliasExp+" >> "+msg;
        historique.add(s);
    }

    public void invite(String aliasInvite, String aliasHost){
        Iterator<Invitation> iterator = invit.iterator();
        boolean invitationTrouvee = false;

        while (iterator.hasNext()) {
            Invitation inv = iterator.next();

            if (inv.aliasInvite.equals(aliasHost) && inv.aliasHost.equals(aliasInvite)) {
                prives.add(new SalonPrive(aliasInvite, aliasHost));
                iterator.remove();
                invitationTrouvee = true;
                break;
            }
        }

        if (invitationTrouvee) {
            for(Connexion cnx:connectes){
                if(cnx.getAlias().equals(aliasInvite)){
                    cnx.envoyer("JOINOK "+aliasHost);
                }
            }
            for(Connexion cnx:connectes){
                if(cnx.getAlias().equals(aliasHost)){
                    cnx.envoyer("JOINOK "+aliasInvite);
                }
            }
            return;
        }

        Invitation temp = new Invitation(aliasInvite, aliasHost);
        invit.add(temp);

        for(Connexion cnx:connectes){
            if(cnx.getAlias().equals(aliasInvite)){
                cnx.envoyer("JOIN "+aliasHost);
            }
        }
    }

    public void decline(String aliasInvite, String aliasHost){
        Iterator<Invitation> iterator = invit.iterator();

        while (iterator.hasNext()) {
            Invitation inv = iterator.next();

            if ((inv.aliasInvite.equals(aliasInvite) && inv.aliasHost.equals(aliasHost))||(inv.aliasInvite.equals(aliasHost) && inv.aliasHost.equals(aliasInvite))) {
                invit.remove(inv);
                iterator.remove();


                for(Connexion cnx:connectes){
                    if(cnx.getAlias().equals(aliasHost)){
                        cnx.envoyer("DECLINE "+ aliasInvite);
                        return;
                    }
                }
                break;
            }
        }
    }

    public String inv(String aliasInvite){

        Iterator<Invitation> iterator = invit.iterator();
        String s = "";
        while (iterator.hasNext()) {
            Invitation inv = iterator.next();

            if (inv.aliasInvite.equals(aliasInvite)){
                    s+=inv.aliasHost+":";
            }
        }
        return s;
    }

    public void prv(String aliasHost, String aliasInvite, String msg){
        SalonPrive temp = new SalonPrive(aliasInvite, aliasHost);
        Iterator<SalonPrive> iterator = prives.iterator();

        while (iterator.hasNext()) {
            SalonPrive prive1 = iterator.next();

            if(prive1.equals(temp)){
                String s = aliasHost+" (>>) "+msg;
                for (Connexion cnx:connectes){
                    if(cnx.getAlias().equals(aliasInvite)){
                        cnx.envoyer(s);
                        return;
                    }
                }
                break;
            }
        }
    }

    public void quit(String aliasHost, String aliasInvite){
        SalonPrive temp = new SalonPrive(aliasInvite, aliasHost);
        Iterator<SalonPrive> iterator = prives.iterator();

        while (iterator.hasNext()) {
            SalonPrive prive = iterator.next();
            if(prive.equals(temp)){
                prives.remove(prive);
                for(Connexion cnx:connectes){
                    if(cnx.getAlias().equals(aliasInvite)){
                        cnx.envoyer("QUIT "+aliasHost);
                        return;
                    }
                }
                break;
            }
        }
    }
}
