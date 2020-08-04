package me.bretg96.pong;

import me.bretg96.pong.entity.Paddle;
import me.bretg96.pong.input.KeyBoardListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;


public class Pong extends Canvas implements Runnable
{	
	public static final int HEIGHT = 700, WIDTH = 1000;
	private KeyBoardListener keyboardlistener;
	private Paddle bluePaddle;
	private Paddle redPaddle;


	private BufferedImage image;
	private int[] pixels;
	
	public Pong() {

		this.image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt) this.image.getRaster().getDataBuffer()).getData();
		this.keyboardlistener = new KeyBoardListener();
		this.addKeyListener(this.keyboardlistener);
		this.bluePaddle = new Paddle();
		this.redPaddle = new Paddle();
	}


	
	@Override
	public void run()
	{
		while (true) 
		{
			BufferStrategy buff = this.getBufferStrategy();
			if (buff == null) 
			{
				this.createBufferStrategy(3);
				continue;
				
				
			}

			this.render();

			//creating graphics
			Graphics drawGraphics = buff.getDrawGraphics();
			drawGraphics.fillRect(0,0,WIDTH,HEIGHT);
			drawGraphics.drawImage(this.image,0,0,WIDTH,HEIGHT,null);
			drawGraphics.dispose();
			buff.show();



		}
	}
	private int vel = (HEIGHT/2)-(100/2);

	private void render()
	{
		for (int i = 0; i < WIDTH*HEIGHT; i++) {
			this.pixels[i] = 0;

		}
		rect(25,100, 0, vel, 0xff0000);
		rect(25, 100, WIDTH-25, vel, 0x0000ff);
		if (this.keyboardlistener.isPressed(KeyEvent.VK_W)) //move paddle up
		{
			vel-=2;

		}
		if (this.keyboardlistener.isPressed(KeyEvent.VK_S)) //move paddle up
		{
			vel+=2;

		}
	}
	public void rect(int W, int H, int locx, int locy, int color)
	{
		for (int x = locx;  x < W+locx; x++) {

			//int height_in_inches = (y*WIDTH)+x;
			for (int y = locy; y < H+locy; y++) {
				this.pixels[x + (y * WIDTH)] = color;
			}
		}

	}
}
