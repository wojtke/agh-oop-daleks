package com.javable.daleks.logic;

import com.javable.daleks.Settings;
import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.Player;
import com.javable.daleks.models.objects.Scrap;

import java.io.IOException;

public class CollisionHandlerVisitor {

    private final GameMap map;

    public CollisionHandlerVisitor(GameMap map) {
        this.map = map;
    }


    // inWalk - whether I walked into someone or someone walked into me
    public void handlePlayerCollision() {
        /*
        No matter what a player collides with he will die
         */
        try {
            ViewManager.SetScene(Settings.GameOverView);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void handleDalekCollision(Dalek dalek, ObjectBase other, boolean inWalk) {
        /*
        Remove self from map if other is not player.
        Add scrap to map if needed.
         */
        if (!(other instanceof Player)) {
            map.removeDalek(dalek);
            if (inWalk)
                map.addScrap(new Scrap(dalek.position));
        }

    }
}
