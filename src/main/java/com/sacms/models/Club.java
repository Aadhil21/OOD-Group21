package com.sacms.models;

public class Club {
    private String name;
    private Advisor advisor;

    public Club(String name, Advisor advisor) {
        this.name = name;
        this.advisor = advisor;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }

    public Advisor getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Advisor advisor) {
        this.advisor = advisor;
    }
}
