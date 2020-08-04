package me.bretg96.pong;

import me.bretg96.pong.entity.Paddle;
import me.bretg96.pong.input.KeyBoardListener;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.Random;


public class Pong extends Canvas implements Runnable
{	
	public static final int HEIGHT = 700, WIDTH = 1000;
	private static boolean inPlay;
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
		this.bluePaddle = new Paddle(0x0000ff);
		this.redPaddle = new Paddle(0xff0000);
		this.inPlay = true;
	}


	
	@Override
	public void run()
	{

		while (Pong.inPlay)
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
	private int vel2 = (HEIGHT/2)-(100/2);
	private int xBallVel = (WIDTH-25)/2;
	private int yBallVel = (HEIGHT-25)/2;
	private int xBallDir = -1;
	private int yBallDir = -1;
	private int diff = 1;

	private void render()
	{
		for (int i = 0; i < WIDTH*HEIGHT; i++) {
			this.pixels[i] = 0;

		}
		rect(25,100, 0, vel, 0xff0000);
		rect(25, 100, WIDTH-25, vel2, 0x0000ff);
		ball(10, 10, xBallVel, yBallVel, 0xFFFFFF);

		xBallVel+=xBallDir;
		yBallVel+=yBallDir;
		if (yBallVel > HEIGHT-50)
		{
			yBallDir*=-1;
		}
		if (yBallVel < 50)
		{
			yBallDir*=-1;
		}
		/*
		if (xBallVel > WIDTH-25)
		{
			xBallDir*=-1;
		}
		if (xBallVel < 10)
		{
			xBallDir*=-1;
		}
		 */

		if ((yBallVel<vel+100 && yBallVel> vel-50) && (xBallVel == 35)) {
			xBallDir *=-1;
			diff++;
		}
		if ((yBallVel<vel2+100 && yBallVel> vel2-50) && (xBallVel == WIDTH-35)) {
			xBallDir *=-1;
			diff++;

		}

		if (xBallVel<10 || xBallVel>WIDTH-25) {
			Pong.inPlay = false;
		}




		if (this.keyboardlistener.isPressed(KeyEvent.VK_W)) //move paddle up
		{
			vel -= 2;
			if (vel < 50) {
				vel = HEIGHT - 100;
			}


		}
		if (this.keyboardlistener.isPressed(KeyEvent.VK_S)) //move paddle down
		{
			vel += 2;
			if (vel > HEIGHT - 100) {
				vel = 100;
			}

		}
			if (this.keyboardlistener.isPressed(KeyEvent.VK_UP)) //move paddle up
			{
				vel2 -= 2;
				if (vel2 < 50) {
					vel2 = HEIGHT - 100;
				}
			}
			if (this.keyboardlistener.isPressed(KeyEvent.VK_DOWN)) //move paddle down
			{
				vel2 += 2;
				if (vel2 > HEIGHT - 100) {
					vel2 = 100;
				}
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
	public void ball(int W, int H, int locx, int locy, int color)
	{
		for (int x = locx;  x < W+locx; x++) {

			//int height_in_inches = (y*WIDTH)+x;
			for (int y = locy; y < H+locy; y++) {
				this.pixels[x + (y * WIDTH)] = color;
			}
		}

	}
}
