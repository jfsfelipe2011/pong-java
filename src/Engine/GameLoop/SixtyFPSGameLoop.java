package Engine.GameLoop;

import Interface.GameLoop;
import Interface.Playable;

public class SixtyFPSGameLoop implements GameLoop {
    private boolean isRunning;
    private Playable playable;

    public SixtyFPSGameLoop() {
        this.isRunning = true;
    }

    @Override
    public void setPlayable(Playable playable) {
        this.playable = playable;
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double timer = System.currentTimeMillis();

        double delta = 0;
        int frames = 0;

        while (this.isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / (1000000000 / 60.0);
            lastTime = now;

            if (delta >= 1) {
                this.playable.tick();
                this.playable.render();

                delta--;
                frames++;
            }

            if (System.currentTimeMillis() - timer >= 1000) {
                System.out.println("FPS: " + frames);
                frames = 0;
                timer += 1000;
            }
        }
    }

    @Override
    public void stopRun() {
        this.isRunning = false;
    }
}
