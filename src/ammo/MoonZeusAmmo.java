package ammo;

import characters.Astronauts.DefenderTower;
import characters.Astronauts.MoonZeus;
import javafx.scene.image.Image;

public class MoonZeusAmmo extends Ammo{

	public MoonZeusAmmo(DefenderTower dt, Image sprite) {
		super(new MoonZeus(), new Image(MOON_ZEUS_AMMO_SPRITE, SPRITE_WIDTH, SPRITE_HEIGHT, false, false));
		// TODO Auto-generated constructor stub
	}

}
