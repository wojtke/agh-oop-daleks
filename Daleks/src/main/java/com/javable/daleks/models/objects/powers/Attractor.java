package com.javable.daleks.models.objects.powers;

import com.javable.daleks.logic.ImageLoader;
import com.javable.daleks.models.Position;
import com.javable.daleks.models.objects.Destination;
import com.javable.daleks.models.objects.PowerUp;
import javafx.scene.image.Image;

public class Attractor extends PowerUp implements Destination {

    public Attractor(com.javable.daleks.models.Position position) {
        super(position);
    }

    @Override
    public Image getImage(ImageLoader loader) {
        return null;
    }
}
