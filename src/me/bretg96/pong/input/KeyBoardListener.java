package me.bretg96.pong.input;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyBoardListener implements KeyListener
{
    private boolean [] keyPressed = new boolean[65536];
    public boolean isPressed(int keycode) {
        return this.keyPressed[keycode];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        int code = e.getKeyCode();
        if (code>0 && code < keyPressed.length) {
            keyPressed[code] = true;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code>0 && code < keyPressed.length) {
            keyPressed[code] = false;
        }
    }
}
