package com.javable.daleks.logic;

import com.javable.daleks.models.GameMap;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Dalek;
import com.javable.daleks.models.objects.ObjectBase;
import com.javable.daleks.models.objects.Player;
import com.javable.daleks.models.objects.Scrap;

public class CollisionHandlerVisitor {

    private final GameMap map;

    public CollisionHandlerVisitor(GameMap map) {
        this.map = map;
    }


    // inWalk - whether I walked into someone or someone walked into me
    public static void handlePlayerCollision(Player player, ObjectBase other, boolean inWalk) {
        /*
        No matter what a player collides with he will die
         */
        player.kill();
    }
    public void handleDalekCollision(Dalek dalek, ObjectBase other, boolean inWalk) {
        /*
        Remove self from map if other is not player.
        Add scrap to map if needed.
         */
        if(!(other instanceof Player)) {
            map.removeDalek(dalek);
            if(inWalk)
                map.AddScrap(new Scrap(dalek.position));
        }

    }
    public void handleScrapCollision(Scrap scrap, ObjectBase other, boolean inWalk) {
        return;
    }

}
