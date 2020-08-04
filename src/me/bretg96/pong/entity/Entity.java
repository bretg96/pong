package me.bretg96.pong.entity;

import me.bretg96.pong.Pong;

public class Entity
{
    private int posx = 0;
    private int posy = 0;
    public void render(int[] pixels)
    {
        for (int i = 0; i < Pong.WIDTH*Pong.HEIGHT; i++) {
            pixels[i] = 0;

        }


    }
}
