package ie.wit.cgd.bunnyhop;
import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import ie.wit.cgd.bunnyhop.game.WorldController;
import ie.wit.cgd.bunnyhop.game.WorldRenderer;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.assets.AssetManager;
import ie.wit.cgd.bunnyhop.game.Assets;

public class BunnyHopMain implements ApplicationListener
{
	private static final String TAG = BunnyHopMain.class.getName();

	private WorldController worldController;
	private WorldRenderer worldRenderer;
	private boolean paused;

	@Override public void create () 
	{
		// Set Libgdx log level to DEBUG
		Gdx.app.setLogLevel(Application.LOG_DEBUG);
		Assets.instance.init(new AssetManager());               // Load assets

		// Initialize controller and renderer
		worldController = new WorldController();
		worldRenderer = new WorldRenderer(worldController);
		//Game world is active on start
		paused = false;
	}
	@Override public void render () 
	{
		if(!paused)		//Do not update game world while paused
		{
			// Update game world by the time that has passed since last rendered frame.
			worldController.update(Gdx.graphics.getDeltaTime());
		}

		// Sets the clear screen color to: Cornflower Blue
		Gdx.gl.glClearColor(0x64/255.0f, 0x95/255.0f, 0xed/255.0f, 0xff/255.0f);

		// Clears the screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Render game world to screen
		worldRenderer.render();
	}
	@Override public void resize (int width, int height)
	{
		worldRenderer.resize(width, height);
	}
	@Override public void pause () 
	{ 
		paused = true;	
	}
	@Override public void resume () 
	{
		paused = false;
		Assets.instance.init(new AssetManager());
	}
	@Override public void dispose () 
	{ 
		worldRenderer.dispose();
		Assets.instance.dispose();
	}
}