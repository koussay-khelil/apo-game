package apoGame.game;

import org.apogames.entity.ApoButton;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.apogames.ApoGameConstants;

import apoGame.ApoGameImages;
import apoGame.ApoGamePanel;

public class ApoGameButtons {

	private ApoGamePanel game;

	private Font font;

	private String text, function;

	int width, height, x, y;

	public ApoGameButtons(ApoGamePanel game) {
		this.game = game;
	}

	private void init__wrappee__Menu() {
		this.game.setButtons(new HashMap<String, ApoButton>());
	}

	private void init__wrappee__WhiteMenu() {
		init__wrappee__Menu();

		font = new Font(Font.SANS_SERIF, Font.BOLD, 30);
		text = "X";
		function = ApoGameMenu.QUIT;
		width = 45;
		height = 45;
		x = ApoGameConstants.GAME_WIDTH - 15 - width;
		y = ApoGameConstants.GAME_HEIGHT - 1 * height - 1 * 15;
		this.game.getButtons().put(ApoGameMenu.QUIT, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 255),
				x, y, width, height, function));

		text = "play";
		function = ApoGameMenu.START;
		width = 250;
		height = 60;
		x = ApoGameConstants.GAME_WIDTH / 2 - width / 2;
		y = 30;
		this.game.getButtons().put(ApoGameMenu.START, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, true, font, 10),
				x, y, width, height, function));

		text = "level";
		function = ApoGameGame.MENU;
		width = 150;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH - 1 * 15 - 1 * width;
		y = ApoGameConstants.GAME_HEIGHT - 1 * height - 1 * 5;
		this.game.getButtons().put(ApoGameGame.MENU, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, true, font, 10),
				x, y, width, height, function));
	}

	private void init__wrappee__Credits() {
		init__wrappee__WhiteMenu();

		text = "C";
		function = ApoGameMenu.CREDITS;
		width = 45;
		height = 45;
		x = 15;
		y = ApoGameConstants.GAME_HEIGHT - 1 * height - 1 * 15;
		this.game.getButtons().put(ApoGameMenu.CREDITS, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 255),
				x, y, width, height, function));

		text = ApoGameCredits.MENU_STRING;
		function = ApoGameCredits.MENU;
		width = 150;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH - 1 * 15 - 1 * width;
		y = ApoGameConstants.GAME_HEIGHT - 1 * height - 1 * 15;
		this.game.getButtons().put(ApoGameCredits.MENU, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, true, font, 10),
				x, y, width, height, function));
	}

	private void init__wrappee__Options() {
		init__wrappee__Credits();

		text = "options";
		function = ApoGameMenu.OPTIONS;
		width = 250;
		height = 60;
		x = ApoGameConstants.GAME_WIDTH / 2 - width / 2;
		y = ApoGameConstants.GAME_HEIGHT - 30 - height;
		this.game.getButtons().put(ApoGameMenu.OPTIONS, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, true, font, 10),
				x, y, width, height, function));

		text = ApoGameOptions.MENU_STRING;
		function = ApoGameOptions.MENU;
		width = 150;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH - 1 * 15 - 1 * width;
		y = ApoGameConstants.GAME_HEIGHT - 1 * height - 1 * 15;
		this.game.getButtons().put(ApoGameOptions.MENU, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, true, font, 10),
				x, y, width, height, function));

		text = "";
		function = ApoGameOptions.MUSIC;
		width = 40;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH / 2;
		y = 70;
		this.game.getButtons().put(ApoGameOptions.MUSIC, new ApoGameOptionsButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 10),
				x, y, width, height, function, false));

		text = "";
		function = ApoGameOptions.SOUND;
		width = 40;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH / 2;
		y = 115;
		this.game.getButtons().put(ApoGameOptions.SOUND, new ApoGameOptionsButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 10),
				x, y, width, height, function, true));
	}

	private void init__wrappee__LevelChooser() {
		init__wrappee__Options();

		text = "menu";
		function = ApoGameLevelChooser.MENU;
		width = 150;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH - 1 * 15 - 1 * width;
		y = ApoGameConstants.GAME_HEIGHT - 1 * height - 1 * 15;
		this.game.getButtons().put(ApoGameLevelChooser.MENU, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, true, font, 10),
				x, y, width, height, function));
	}

	private void init__wrappee__Editor() {
		init__wrappee__LevelChooser();

		text = "editor";
		function = ApoGameMenu.EDITOR;
		width = 250;
		height = 60;
		x = ApoGameConstants.GAME_WIDTH / 2 - width / 2;
		y = ApoGameConstants.GAME_HEIGHT / 2 + 70;
		this.game.getButtons().put(ApoGameMenu.EDITOR, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), ,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), true, false, font, 10),
				x, y, width, height, function));
	}

	public void init() {
		init__wrappee__Editor();

		text = ApoGameEditor.MENU_STRING;
		function = ApoGameEditor.MENU;
		width = 150;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH - 1 * 10 - 1 * width;
		y = ApoGameConstants.GAME_HEIGHT - 1 * height - 1 * 5;
		this.game.getButtons().put(ApoGameEditor.MENU, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 10),
				x, y, width, height, function));

		font = new Font(Font.SANS_SERIF, Font.BOLD, 20);
		text = "test";
		function = ApoGameEditor.TEST;
		width = 75;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH - 1 * 10 - 1 * width;
		y = ApoGameConstants.GAME_HEIGHT - 2 * height - 2 * 5;
		this.game.getButtons().put(ApoGameEditor.TEST, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), ,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 10),
				x, y, width, height, function));

		text = "upload";
		function = ApoGameEditor.UPLOAD;
		width = 75;
		height = 40;
		x = ApoGameConstants.GAME_WIDTH - 1 * 10 - 2 * width;
		y = ApoGameConstants.GAME_HEIGHT - 2 * height - 2 * 5;
		this.game.getButtons().put(ApoGameEditor.UPLOAD, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 10),
				x, y, width, height, function));

		text = " < ";
		function = ApoGameEditor.LEFT_LAYER;
		width = 30;
		height = 30;
		x = ApoGameConstants.LEVEL_WIDTH + 1 * 10 + 10;
		y = 50;
		this.game.getButtons().put(ApoGameEditor.LEFT_LAYER, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 10),
				x, y, width, height, function));

		text = " > ";
		function = ApoGameEditor.RIGHT_LAYER;
		width = 30;
		height = 30;
		x = ApoGameConstants.GAME_WIDTH - 5 - width;
		y = 50;
		this.game.getButtons().put(ApoGameEditor.RIGHT_LAYER, new ApoButton(
				this.game.getImages().getButtonImageSimple(width * 3, height, text, new Color(0, 0, 0, 0), Color.BLACK,
						Color.BLACK, new Color(255, 255, 0, 128), new Color(255, 0, 0, 128), false, false, font, 10),
				x, y, width, height, function));

		text = "";
		function = ApoGameEditor.PLAYER;
		BufferedImage iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_PLAYER, Color.YELLOW,
				Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.LEVEL_WIDTH + 10 + 30;
		y = 90 + 0 * height + 0 * 5;
		this.game.getButtons().put(ApoGameEditor.PLAYER, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.FINISH;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_FINISH, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.GAME_WIDTH - width - 25;
		y = 90 + 0 * height + 0 * 5;
		this.game.getButtons().put(ApoGameEditor.FINISH, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.FIXED;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_FIXED, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.LEVEL_WIDTH + 10 + 30;
		y = 90 + 1 * height + 1 * 5;
		this.game.getButtons().put(ApoGameEditor.FIXED, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.UP;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_UP, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.GAME_WIDTH - width - 25;
		y = 90 + 1 * height + 1 * 5;
		this.game.getButtons().put(ApoGameEditor.UP, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.DOWN;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_DOWN, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.LEVEL_WIDTH + 10 + 30;
		y = 90 + 2 * height + 2 * 5;
		this.game.getButtons().put(ApoGameEditor.DOWN, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.LEFT;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_LEFT, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.GAME_WIDTH - width - 25;
		y = 90 + 2 * height + 2 * 5;
		this.game.getButtons().put(ApoGameEditor.LEFT, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.RIGHT;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_RIGHT, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.LEVEL_WIDTH + 10 + 30;
		y = 90 + 3 * height + 3 * 5;
		this.game.getButtons().put(ApoGameEditor.RIGHT, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.VISIBLE_TRUE;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_VISIBLE_TRUE, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.GAME_WIDTH - width - 25;
		y = 90 + 3 * height + 3 * 5;
		this.game.getButtons().put(ApoGameEditor.VISIBLE_TRUE,
				new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.VISIBLE_FALSE;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_VISIBLE_FALSE, Color.YELLOW,
				Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.LEVEL_WIDTH + 10 + 30;
		y = 90 + 4 * height + 4 * 5;
		this.game.getButtons().put(ApoGameEditor.VISIBLE_FALSE,
				new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.STEP;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_STEP, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.GAME_WIDTH - width - 25;
		y = 90 + 4 * height + 4 * 5;
		this.game.getButtons().put(ApoGameEditor.STEP, new ApoButton(iEditorImage, x, y, width, height, function));

		function = ApoGameEditor.STEP_FINISH;
		iEditorImage = ApoGameImages.getImageSimpleEditor(ApoGameImages.ORIGINAL_STEP_FINISH, Color.YELLOW, Color.RED);
		width = iEditorImage.getWidth() / 3;
		height = iEditorImage.getHeight();
		x = ApoGameConstants.LEVEL_WIDTH + 10 + 30;
		y = 90 + 5 * height + 5 * 5;
		this.game.getButtons().put(ApoGameEditor.STEP_FINISH,
				new ApoButton(iEditorImage, x, y, width, height, function));

	}

}
