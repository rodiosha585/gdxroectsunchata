package ru.rodyk.histgame;

import java.util.ArrayList;

public class Room {
    public ArrayList<ArrayList<Integer>> room = new ArrayList<>();
    public boolean isPassed;
    public int x1, x2, y1, y2;
    public Room(int x1_, int x2_, int y1_, int y2_) {
        for (int i = 0; i < 15; i++) {
            ArrayList<Integer> e = new ArrayList<>();
            this.room.add(e);
        }
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                this.room.get(i).add(0);
            }
        }
        this.room.get(0).set(0, 3);
        this.room.get(0).set(14, 3);
        this.room.get(14).set(0, 3);
        this.room.get(14).set(14, 3);
        this.room.get(7).set(7, 2);
        this.room.get(7).set(14, 89);
        this.room.get(7).set(8, 63);
        this.x1 = x1_;
        this.x2 = x2_;
        this.y1 = y1_;
        this.y2 = y2_;
    }
}
