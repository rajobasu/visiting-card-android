package com.community.jboss.visitingcard.data;


/**
 * This class encapsulates the Data of a visiting card.
 */
public class VisitingCard {
    private String name;
    private String email;

    public VisitingCard(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
