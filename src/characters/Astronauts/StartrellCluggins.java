package characters.Astronauts;

import javafx.scene.image.Image;

public class StartrellCluggins extends DefenderTower{
	
	public StartrellCluggins() {
		super(HEALTH_STARTRELL_CLUGGINS, ATTACK_SPEED_STARTRELL_CLUGGINS, 
				DAMAGE_STARTRELL_CLUGGINS, new Image(STARTRELL_CLUGGINS_IMAGE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
	}
}