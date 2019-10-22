package com.minigame.colorboard.board;

public enum Color {
    Red,
    Blue;

    public boolean isRed() {
        return this == Red;
    }

    public boolean isBlue() {
        return this == Blue;
    }

    public String toString() {
        return this.name();
    }
}
