package com.example.ghautham.fblaquiz;

public class Leader implements Comparable<Leader> {

    private String name;
    private int score;

    public Leader(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Leader o) {
        return o.getScore() - this.score;
    }
}
