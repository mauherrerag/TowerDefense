package characters.Aliens;

import characters.BoardCharacter;
import game.SpriteAnimation;
import game.View;
import javafx.animation.Animation;
import javafx.application.Platform;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class Enemy extends BoardCharacter {
	// Default values for characters

	// File paths for the images of each Enemy
	public static final String LITTLEGREENMEN_IMAGE = "file:assets/aliens/little-green-man-attack.gif";
	public static final String GRUNT_IMAGE 			= "file:assets/aliens/grunt-walk.gif";
	public static final String SPRINTER_IMAGE 		= "file:assets/aliens/sprinter-walk.gif";
	public static final String TANK_IMAGE 			= "file:assets/aliens/tank-walk.gif";
	public static final String MANHUNTER_IMAGE 		= "file:assets/aliens/manhunter-attack.gif";
	public static final String GARGANTUA_IMAGE 		= "file:assets/aliens/gargantua-walk/gargantua-10.png";

	protected static final int IMAGE_WIDTH = 80;
	protected static final int IMAGE_HEIGHT = 80;
	protected static final int OFFSET_X = 0;
    protected static final int OFFSET_Y = 0;
	
	// LittleGreenMen
	protected static final int HEALTH_LITTLE_GREEN_MEN = 100;
	protected static final int ATTACK_SPEED_LITTLE_GREEN_MEN = 300;
	protected static final int DAMAGE_LITTLE_GREEN_MEN = 1;

	// Grunt
	protected static final int HEALTH_GRUNT = 100;
	protected static final int ATTACK_SPEED_GRUNT = 400;
	protected static final int DAMAGE_GRUNT = 2;
    
	// Sprinter
	protected static final int HEALTH_SPRINTER = 100;
	protected static final int ATTACK_SPEED_SPRINTER = 600;
	protected static final int DAMAGE_SPRINTER = 4;
	
	// Tank
	protected static final int HEALTH_TANK = 100;
	protected static final int ATTACK_SPEED_TANK = 200;
	protected static final int DAMAGE_TANK = 6;
    
	// ManHunter
	protected static final int HEALTH_MAN_HUNTER = 100;
	protected static final int ATTACK_SPEED_MAN_HUNTER = 450;
	protected static final int DAMAGE_MAN_HUNTER = 7;

	// Gargantua
	protected static final int HEALTH_GARGANTUA = 100;
	protected static final int ATTACK_SPEED_GARGANTUA = 500;
	protected static final int DAMAGE_GARGANTUA = 10;
	
	
	// Class variables
	private StackPane stackPane;
	private boolean isAttacking;
	
	// Animations
	private ImageView walkView;
	private ImageView attackView;
	private ImageView dieView;
	private Animation walkAnimation;
	private Animation attackAnimation;
	private Animation dieAnimation;
	
	public static final String WALK_ID = "walk";
	public static final String ATTACK_ID = "attack";
	public static final String DIE_ID = "die";

	// Constructor
	protected Enemy(int health, int attackSpeed, int damage, Image sprite) {
		super(health, attackSpeed, damage, sprite);
		isAttacking = false;
	}
	
	public void setStackPane() {
		stackPane = new StackPane();
//		stackPane.setStyle("-fx-border-color: black");
		
		// Add all views but only make walk animation visible
		walkView.setVisible(true);
		walkView.setId(WALK_ID);
		attackView.setVisible(false);
		attackView.setId(ATTACK_ID);
		dieView.setVisible(false);
		dieView.setId(DIE_ID);
		stackPane.getChildren().addAll(walkView, attackView, dieView);
	}
	
	public StackPane getStackPane() {
		return stackPane;
	}
	
	public void playAlienMunchNoise() {
		// TODO: for Trey, have a bullet noise for each defender's ammo,
		// each time a piece of ammo is fired
		/*MediaPlayer munchNoise;
		String resource = new File(munchNoiseFile.mp3).toURI().toString();
		munchNoise = new MediaPlayer(new Media(resource));
		munchNoise.play();*/
	}
	
	public boolean isAttacking() {
		return isAttacking;
	}
	
	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}
	
	public void move() {
		double xPos = stackPane.getTranslateX();
		
		double movement = xPos - ((double)this.getAttackSpeed() / 100.0);
		this.setCol(calculateCol(xPos));
//		Platform.runLater(() -> stackPane.setTranslateX((movement))); // Negative value will be alien speed
		stackPane.setTranslateX(movement); // Negative value will be alien speed
	}
	
	private int calculateCol(double xPos) {
		double x = xPos - View.COLUMN_OFFSET;
		return (int) x / View.GP_CELL_SIZE;
//		characterPane.setTranslateX((GP_CELL_SIZE * message.getCol()) + COLUMN_OFFSET);
	}
	
	public void setWalkView(ImageView walkView) {
		this.walkView = walkView;
	}
	
	public ImageView getWalkView() {
		return walkView;
	}
	
	public void setAttackView(ImageView attackView) {
		this.attackView = attackView;
	}
	
	public ImageView getAttackView() {
		return attackView;
	}
	
	public void setDieView(ImageView dieView) {
		this.dieView = dieView;	
	}
	
	public ImageView getDieView() {
		return dieView;
	} 
	
	public void triggerAnimation(String animationId) {
		for (Node node : stackPane.getChildren()) {
			ImageView view = (ImageView) node;
			if (view.getId().equals(animationId)) {
				view.setVisible(true);
			} else {
				view.setVisible(false);
			}
		}
	}
	
	public ImageView generateAnimation(Image spriteImage, int count, int columns, int spriteWidth, int spriteHeight, int animationTime, String animationId) {
		ImageView view = new ImageView(spriteImage);
		view.setViewport(new Rectangle2D(
				OFFSET_X, 
				OFFSET_Y, 
				spriteWidth, 
				spriteHeight
			));
		view.setFitWidth(IMAGE_WIDTH);
		view.setFitHeight(IMAGE_HEIGHT);
		Animation animation = new SpriteAnimation(view, Duration.millis(animationTime), 
				count,
				columns, 
				OFFSET_X, 
				OFFSET_Y, 
				spriteWidth, 
				spriteHeight
			);
		
		if (animationId.equals(WALK_ID)) {
			walkAnimation = animation;
		} else if (animationId.equals(ATTACK_ID)) {
			attackAnimation = animation;
		} else if (animationId.equals(DIE_ID)) {
			dieAnimation = animation;
		}
		animation.setCycleCount(Animation.INDEFINITE);
		animation.play();
		return view;
	}

	public void updateAnimationSpeed(int speed) {
		walkAnimation.setRate(speed);
		attackAnimation.setRate(speed);
		dieAnimation.setRate(speed);
	}
	
}
