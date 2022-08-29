package com.company.model.player;

import com.company.model.piece.figure.Team;

public class Player {
    private final String name;
    protected final Team team;

    public Player(String name, Team team) {
        this.name = name;
        this.team = team;
    }

    public Player(Team team) {
        this(null, team);
    }

    public String getName() {
        if(name == null) {
            return team.name();
        }
        return String.format("%s [%s]", name, team.name());
    }

    public Team getTeam() {
        return team;
    }

}
