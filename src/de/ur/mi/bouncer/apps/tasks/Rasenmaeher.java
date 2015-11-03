package de.ur.mi.bouncer.apps.tasks;

import de.ur.mi.bouncer.apps.BouncerApp;
import de.ur.mi.bouncer.world.FieldColor;

public class Rasenmaeher extends BouncerApp {
	private static final int ROWS = 15;
	
	@Override
	public void bounce() {
		loadMap("Rasenmaeher");
	    while(bouncer.canMoveLeft()) {
            checkRows();
            bouncer.turnLeft();
            bouncer.move();
            bouncer.turnLeft();
            checkRows();
            turnRight();
            bouncer.move();
            turnRight();
        }
        checkRows();
    }
    private void checkRows()
    {
        while(bouncer.canMoveForward())
        {
            checkField();
        }
    }

    private void checkField() {

        if(bouncer.isOnFieldWithColor(FieldColor.GREEN))
        {
            lawnmoving();
        }
        else
        {
            bouncer.move();
        }
    }

    private void lawnmoving()
    {
        bouncer.clearFieldColor();
    }

    private void turnRight()
    {
        bouncer.turnLeft();
        bouncer.turnLeft();
        bouncer.turnLeft();
    }

}
