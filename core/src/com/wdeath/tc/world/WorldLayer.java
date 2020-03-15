package com.wdeath.tc.world;

public class WorldLayer {

    private int[][] blocks;
    private int width, height;

    public WorldLayer(int width, int height){
        this.width = width;
        this.height = height;
        blocks = new int[width][height];
    }

    public void set(int id, int x, int y){
        blocks[x][y] = id;
    }

    public int get(int x, int y){
        return blocks[x][y];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
