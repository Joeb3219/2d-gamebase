package com.charredgames.game.game;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

import org.lwjgl.opengl.Display;

import com.charredgames.game.game.entity.Player;
import com.charredgames.game.game.graphics.Colour;
import com.charredgames.game.game.graphics.Screen;
import com.charredgames.game.game.graphics.Tile;
import com.charredgames.game.game.world.World;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = 1456505934897275335L;
	public static final int _WIDTH = 600;
	public static final int _HEIGHT = _WIDTH / 16 * 9;
	public static int _SCALE = 2;
	public static final int _DESIREDTPS = 30;
	public static String title = "CharredGames game ";
	
	private static boolean isRunning = false;
	private static JFrame window;
	private static Graphics g;
	private static BufferStrategy buffer;
	private static BufferedImage gameImage = new BufferedImage(_WIDTH, _HEIGHT, BufferedImage.TYPE_INT_RGB);
	private static int[] pixels = ((DataBufferInt) gameImage.getRaster().getDataBuffer()).getData();
	private Thread gameThread;
	
	private Keyboard keyboard;
	private Player player;
	public static World world;
	private Screen screen;
	
	private void tick(){
		keyboard.update();
		player.update();
		
		if(Mouse.button == 1){
			int x = Mouse.x;
			int y = Mouse.y;
			System.out.println(world.getTile(x, y).getName() + " @ " + x + ", " + y);
		}
	}
	
	private void render(){
		
		screen.clear();
		int xOffset = (player.getX()) - (_WIDTH/2);
		int yOffset = (player.getY()) - (_HEIGHT/2);		
		
		if(yOffset < 0) yOffset = 1;
		
		screen.setOffset(xOffset, yOffset);
		world.render(player.getX(), screen);
		player.render(screen);
		
		for(int i = 0; i < pixels.length; i++) pixels[i] = screen.pixels[i];
		
		g.drawImage(gameImage, 0, 0, window.getWidth(), window.getHeight(), null);
		
		
		//drawHUD();
		
		//Draw diagnostics stuff
		//g.setColor(Colour.HUD_HEALTH_DIVIDER.getColour());
		int currentChunkId = world.getChunkId(player.getX());
		g.drawString("In chunk " + currentChunkId + " (x-start: " + (currentChunkId * 16 * 8) + ", end: " + ((currentChunkId * 16 * 8) + (16 * 8)) + " ) out of " + world.getTotalChunks() + " chunks total.", 5, 55);
		g.drawString("Player: pos(" + player.getX() + " x, " + player.getY() + " y)" + " standingOn(" + world.getTile(player.getX(), player.getY()).getName() + " (" + world.getTile(player.getX(), player.getY()).isSolid() + ") )", 5, 70);
		
		buffer.show();
	}
	
	private void drawHUD(){
		int healthBarX = 2;
		int healthBarY = 2;
		int healthBarWidth = player.getMaxHealth() * 2 + 10;
		int inventoryBarX = window.getWidth() - 358;
		int inventoryBarY = 2;
		
		//Draw health bar
		g.setColor(Colour.HUD_BG.getColour());
		g.fillRect(healthBarX, healthBarY, healthBarWidth, 35);
		g.setColor(Color.WHITE);
		g.drawString("Health:", ((healthBarWidth - healthBarX) - g.getFontMetrics().stringWidth("Health:")) / 2 + healthBarX, healthBarY + g.getFontMetrics().getHeight() - 2);
		for(int i = 0; i < player.getHealth(); i ++){
			int xPos = healthBarX + 5 + (i * 5);
			g.setColor(Colour.HUD_HEALTH_COLOUR.getColour());
			g.fillRect(xPos + 2, healthBarY + 20, 5, 10);
			g.setColor(Colour.HUD_HEALTH_DIVIDER.getColour());
			g.fillRect(xPos + 5, healthBarY + 20, 2, 10);
		}
		g.setColor(Colour.HUD_INVENTORY_DIVIDER.getColour());
		g.fillRect(inventoryBarX, inventoryBarY, 348, 40);
		int spotPos = inventoryBarX + 6;
		g.setColor(Colour.HUD_INVENTORY_SLOT.getColour());
		for(int i = 0; i < 9; i++){
			g.fillRect(spotPos, 6, 32, 32);
			spotPos += (6 + 32);
		}
		g.setColor(Colour.HUD_HEALTH_DIVIDER.getColour());
	}
	
	private void init(){
		createBufferStrategy(2);
		buffer = getBufferStrategy();
		g = buffer.getDrawGraphics();
		for(int i = 0; i < pixels.length; i++) pixels[i] = 0xFF448844;
	}
	
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double nanoSeconds = 1000000000.0 / _DESIREDTPS;
		double delta = 0;
		int frames = 0, ticks = 0;
		requestFocus();
		while(isRunning){
			long now = System.nanoTime();
			delta+= (now - lastTime) / nanoSeconds;
			lastTime = now;
			while(delta >= 1){
				tick();
				ticks++;
				delta--;
			}
			render();
			frames++;
			if((System.currentTimeMillis() - timer) > 1000){
				timer += 1000;
				window.setTitle(title + " (" + ticks + " TPS, " + frames + "FPS)");
				ticks = 0;
				frames = 0;
			}
		}
		stop();
	}
	
	public Game(){
		setPreferredSize(new Dimension(_WIDTH * _SCALE, _HEIGHT * _SCALE));
		window = new JFrame();
		keyboard = new Keyboard();
		addKeyListener(keyboard);
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		screen = new Screen(_WIDTH, _HEIGHT);
		player = new Player(keyboard);

		if(Tile.AIR instanceof Tile) //This is used to instantiate the Tile class. Stupid way to do it, but works. Yoloswag.
		world = new World();
		
	}
	
	public static void main(String[] args){
		Game game = new Game();
		
		game.setIgnoreRepaint(true);
		/*try {
			Display.setDisplayMode(new DisplayMode(_WIDTH * _SCALE, _HEIGHT * _SCALE));
			Display.setTitle(title);
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, _WIDTH * _SCALE, _HEIGHT * _SCALE, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		
		loop();*/
		
		window.add(game);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		System.setProperty("sun.java2d.opengl", "true");
		
		game.start();
	}
	
	private static void loop(){
		while(!Display.isCloseRequested()){
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_LINES);
				glVertex2i(50, 50);
				glVertex2i(100, 100);
			glEnd();
			
			Display.update();
			Display.sync(60);
		}
		Display.destroy();
	}
	
	private void start(){
		isRunning = true;
		init();
		gameThread = new Thread(this, "Game");
		gameThread.start();
	}
	
	private void stop(){
		try {
			gameThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
