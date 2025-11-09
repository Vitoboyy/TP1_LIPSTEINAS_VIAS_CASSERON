package com.chat.serveur;

public class SalonPrive {
    private  String aliasInvite;
    private String aliasHost;

    @Override
    public boolean equals(Object obj) {
        SalonPrive p = (SalonPrive ) obj;
        return (aliasInvite.equals(p.getAliasInvite())
        || aliasInvite.equals(p.getAliasHost()))
        && (aliasHost.equals(p.getAliasHost())
        || aliasHost.equals(p.getAliasInvite()));
    }

    public String getAliasInvite() {
        return aliasInvite;
    }

    public void setAliasInvite(String aliasInvite) {
        this.aliasInvite = aliasInvite;
    }

    public String getAliasHost() {
        return aliasHost;
    }

    public void setAliasHost(String aliasHost) {
        this.aliasHost = aliasHost;
    }

    public SalonPrive(String aliasInvite, String aliasHost) {
        this.aliasInvite = aliasInvite;
        this.aliasHost = aliasHost;
    }
}
