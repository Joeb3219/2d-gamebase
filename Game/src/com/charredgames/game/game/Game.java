package com.charredgames.game.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

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
	public static final int _DESIREDTPS = 60;
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
	private World world;
	private Screen screen;
	
	private void tick(){
		keyboard.update();
		player.update();
	}
	
	private void render(){
		
		screen.clear();
		int xOffset = (player.getX()) - (_WIDTH/2);
		int yOffset = (player.getY()) - (_HEIGHT/2);
		System.out.println(xOffset + " " + player.getX());
		
		
		//screen.setOffset(xOffset, yOffset);
		world.render(xOffset, yOffset, screen);
		
		for(int i = 0; i < pixels.length; i++) pixels[i] = screen.pixels[i];
		
		g.drawImage(gameImage, 0, 0, window.getWidth(), window.getHeight(), null);
		
		drawHUD();
		
		buffer.show();
	}
	
	private void drawHUD(){
		int healthBarX = 2;
		int healthBarY = 2;
		int healthBarWidth = player.getMaxHealth() * 2 + 10;
		
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
	}
	
	private void init(){
		createBufferStrategy(3);
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
		screen = new Screen(_WIDTH, _HEIGHT);
		player = new Player(keyboard);

		System.out.println(Tile.AIR);
		world = new World();
		
	}
	
	public static void main(String[] args){
		Game game = new Game();
		
		window.add(game);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
		game.start();
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
