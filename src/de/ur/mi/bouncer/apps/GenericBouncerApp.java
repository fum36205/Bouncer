package de.ur.mi.bouncer.apps;

import de.ur.mi.bouncer.Bouncer;
import de.ur.mi.bouncer.events.DefaultEventBus;
import de.ur.mi.bouncer.events.EventBus;
import de.ur.mi.bouncer.events.OnWorldChangedListener;
import de.ur.mi.bouncer.stacktrace.StackTraceFilter;
import de.ur.mi.bouncer.ui.GraphicsContext;
import de.ur.mi.bouncer.ui.WorldScene;
import de.ur.mi.bouncer.world.TwoDimensionalWorld;
import de.ur.mi.bouncer.world.loader.WorldLoader;
import de.ur.mi.graphicsapp.GraphicsApp;

public abstract class GenericBouncerApp<T extends Bouncer> extends GraphicsApp
		implements GraphicsContext, OnWorldChangedListener {
	private static final long serialVersionUID = 4728854238050763260L;
	private final AppConfiguration appConfig;
	private WorldLoader worldLoader;
	protected T bouncer;
	private TwoDimensionalWorld world;
	private WorldScene worldScene;
	private int windowSize;

	public GenericBouncerApp() {
		this(new WorldLoader());
	}

	public GenericBouncerApp(WorldLoader worldLoader) {
		super();
		appConfig = new AppConfiguration();
		this.worldLoader = worldLoader;
	}

	public void setWorldLoader(WorldLoader worldLoader) {
		this.worldLoader = worldLoader;
	}

	@Override
	public void setup() {
		super.setup();
		setupApplet();
		world = TwoDimensionalWorld.emptyWorld();
		EventBus eventBus = newEventBus();
		setupBouncer(eventBus);
		eventBus.addOnWordChangedListener(this);
		worldScene = new WorldScene(world, bouncer, windowSize, appConfig);
		startBounceThread();
	}

	public void setupBouncer(EventBus eventBus) {
		bouncer = createBouncer();
		bouncer.setEventBus(eventBus);
	}

	private EventBus newEventBus() {
		return new DefaultEventBus(newStackTraceFilter());
	}
	
	protected StackTraceFilter newStackTraceFilter() {
		return StackTraceFilter.forClasses(this.getClass());
	}

	private void setupApplet() {
		windowSize = appConfig.windowSizeFor(displayHeight);
		size(windowSize, windowSize);
		smooth(appConfig.smoothLevel());
	}

	public abstract T createBouncer();

	private void startBounceThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				bounce();
			}
		}).start();
	}

	public abstract void bounce();

	public final void loadLocalMap(String mapName) {
		world = worldLoader.loadLocalMap(mapName);
		if (worldScene != null) {
			worldScene.setWorld(world);
		}
		bouncer.placeInWorld(world);
	}

	public final void loadOnlineMap(String mapName) {
		world = worldLoader.loadOnlineMap(mapName);
		if (worldScene != null) {
			worldScene.setWorld(world);
		}
		worldScene.setWorld(world);
		bouncer.placeInWorld(world);
	}

	public void onWorldChanged() {
		try {
			Thread.sleep(1000 / appConfig.frameRate());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		world.purgeCollisions();
	}

	@Override
	public void draw() {
		super.draw();
		worldScene.draw(this);
	}

	public void rectModeCorner() {
		rectMode(CORNER);
	}

}
