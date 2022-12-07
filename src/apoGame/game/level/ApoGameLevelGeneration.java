package apoGame.game.level; 

import apoGame.game.level.ApoGameLevel; 

public  class  ApoGameLevelGeneration {
	
	public static final ApoGameLevel getLevel(int level) {
		if (level == 0) {
			return ApoGameLevelGeneration.getFirstLevel();
		} else if (level == 1) {
			return ApoGameLevelGeneration.getSecondLevel();
		} else if (level == 2) {
			return ApoGameLevelGeneration.getThirdLevel();
		} else if (level == 3) {
			return ApoGameLevelGeneration.getFourthLevel();
		} else if (level == 4) {
			return ApoGameLevelGeneration.getFifthLevel();
		} else if (level == 5) {
			return ApoGameLevelGeneration.getSixLevel();
		} else if (level == 6) {
			return ApoGameLevelGeneration.getSevenLevel();
		} else if (level == 7) {
			return ApoGameLevelGeneration.getEightLevel();
		} else if (level == 8) {
			return ApoGameLevelGeneration.getNineLevel();
		} else if (level == 9) {
			return ApoGameLevelGeneration.getTenLevel();
		} else if (level == 10) {
			return ApoGameLevelGeneration.getElevenLevel();
		} else if (level == 11) {
			return ApoGameLevelGeneration.getTwelveLevel();
		} else if (level == 12) {
			return ApoGameLevelGeneration.getThirteenLevel();
		} else if (level == 13) {
			return ApoGameLevelGeneration.getFourteenLevel();
		} else if (level == 14) {
			return ApoGameLevelGeneration.getFifteenLevel();
		} else if (level == 15) {
			return ApoGameLevelGeneration.getSixteenLevel();
		} else if (level == 16) {
			return ApoGameLevelGeneration.getSeventeenLevel();
		} else if (level == 17) {
			return ApoGameLevelGeneration.getEighteenLevel();
		} else if (level == 18) {
			return ApoGameLevelGeneration.getNineteenLevel();
		} else if (level == 19) {
			return ApoGameLevelGeneration.getTwentyLevel();
		} else if (level == 20) {
			return ApoGameLevelGeneration.getTwentyOneLevel();
		} else if (level == 21) {
			return ApoGameLevelGeneration.getTwentyTwoLevel();
		} else if (level == 22) {
			return ApoGameLevelGeneration.getTwentyThreeLevel();
		} else if (level == 23) {
			return ApoGameLevelGeneration.getTwentyFourLevel();
		} else if (level == 24) {
			return ApoGameLevelGeneration.getTwentyFifeLevel();
		} else if (level == 25) {
			return ApoGameLevelGeneration.getTwentySixLevel();
		} else if (level == 26) {
			return ApoGameLevelGeneration.getTwentySevenLevel();
		} else if (level == 27) {
			return ApoGameLevelGeneration.getTwentyEightLevel();
		} else if (level == 28) {
			return ApoGameLevelGeneration.getTwentyNineLevel();
		} else if (level == 29) {
			return ApoGameLevelGeneration.getThirtyLevel();
		} else if (level == 30) {
			return ApoGameLevelGeneration.getThirtyOneLevel();
		}
		return null;
	}

	
	
	private static final ApoGameLevel getFirstLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,0,0,0,0,0,0},
				{2,0,0,0,0,0,0,0,1,9},
				{0,0,0,0,0,0,0,0,0,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Move the player with the arrow keys";
		instructions[1] = "Reach the finish in every level";
		String levelName = "FIRST";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getSecondLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,1,1,1,1,0,0,0},
				{2,1,0,0,1,0,0,0,1,0},
				{0,1,1,0,0,0,1,0,1,9},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Find a way through the labyrinth";
		instructions[1] = "It's easy, isn't it?";
		String levelName = "LABYRINTH";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getThirdLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,1,1,0,0,0,0},
				{2,0,1,1,1,1,1,1,0,9},
				{0,0,1,0,0,0,0,1,0,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "What to do now? Perhaps you will";
		instructions[1] = "find a way through the walls.";
		String levelName = "THROUGH";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getFourthLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,1,0,0,0,1,1,0,0},
				{0,0,1,1,0,0,0,2,0,9},
				{0,0,0,0,0,0,0,0,0,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,0,0,0,0,0,1,0},
				{0,9,0,0,1,0,1,2,1,0},
				{0,0,0,0,1,0,0,0,1,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "I see double?";
		instructions[1] = "That's crazy!";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getFifthLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,3,3,0,3,0,0},
				{2,0,3,0,0,0,0,3,0,9},
				{0,0,3,0,3,3,0,0,0,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Change your vertical direction";
		instructions[1] = "And the yellow tiles will go up";
		String levelName = "SURPRISE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getSixLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,3,0,0,3,0,0},
				{2,0,0,0,0,0,0,3,0,9},
				{0,0,0,0,3,0,0,0,0,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,0,0,3,0,0,0,0},
				{2,0,3,0,0,0,0,0,0,9},
				{0,0,3,0,0,3,0,0,0,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "It looks like the previous level or?";
		instructions[1] = "But it feels different";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE SURPRISE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getSevenLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,1,0,0,0,1,0,0,0,1},
				{2,1,3,1,3,0,0,1,3,9},
				{0,0,3,1,3,1,1,1,3,1},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Go up and down";
		instructions[1] = "I am dizzy";
		String levelName = "GOING UP";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	

	private static final ApoGameLevel getEightLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,3,0,0,0,0,0},
				{2,0,3,0,3,0,3,0,1,9},
				{0,0,3,0,0,0,3,0,1,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,0,0,1,0,0,0,0},
				{2,0,0,1,0,0,0,3,0,9},
				{0,1,0,0,0,0,0,3,0,1},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "You have to catch the right";
		instructions[1] = "PHASE";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE PHASE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getNineLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{1,1,0,1,4,4,0,4,4,0},
				{9,1,0,0,0,0,1,0,4,2},
				{0,0,4,1,4,4,0,0,0,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Change your vertical direction";
		instructions[1] = "And the red tiles will go down";
		String levelName = "GO DOWN";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	

	private static final ApoGameLevel getTenLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,0,0,0,0,0,0},
				{2,3,0,1,0,3,0,3,0,9},
				{0,3,0,1,0,3,0,3,0,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,4,0,4,0,1,0,4,0},
				{2,0,4,0,4,0,1,0,4,9},
				{0,0,0,0,0,0,0,0,0,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "Oh they doubled the fun";
		instructions[1] = "Its like a wave";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getElevenLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{1,4,0,0,3,1,4,0,0,1},
				{2,0,3,4,3,4,4,1,3,9},
				{1,1,1,4,0,0,0,3,3,1},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "You need some help?";
		instructions[1] = "Flip is the magic word";
		String levelName = "FLIP";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwelveLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,4,0,0,0,1,4,0,0,0},
				{2,4,0,0,0,4,4,4,0,9},
				{0,0,0,0,0,0,0,1,0,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,3,1,0,0,0,3,0},
				{2,0,3,3,3,0,0,0,3,9},
				{0,0,1,0,0,0,0,0,0,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "That level blows my mind";
		instructions[1] = "Yours too?";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getThirteenLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,1,0,1,0,7,0,0,1},
				{2,0,7,0,1,0,1,7,7,9},
				{0,0,1,0,7,0,1,0,0,1},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Change your vertical direction";
		instructions[1] = "and they switch on and off";
		String levelName = "ION";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getFourteenLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,1,0,0,0,1,0,7,7,0},
				{2,0,7,1,0,7,7,0,1,9},
				{0,7,0,7,0,1,0,0,7,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,1,0,0,7,1,0,7,1,0},
				{0,9,7,1,0,7,7,2,1,0},
				{0,0,0,1,0,1,0,0,1,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "Io a io a io";
		instructions[1] = "The path is ionized";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getFifteenLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,1,0,7,0,3,0,1,0,1},
				{2,7,3,1,4,3,1,7,4,9},
				{0,1,0,7,4,1,7,7,4,1},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Slowly, it is tricky";
		instructions[1] = "More than 4 different kind of objects";
		String levelName = "DECEPTION";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getSixteenLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{8,0,8,1,8,0,1,8,8,1},
				{2,0,7,7,0,3,7,3,7,8},
				{7,7,3,1,0,0,4,1,8,9},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,8,1,8,0,0,0,1,0},
				{0,9,7,2,0,3,3,1,1,0},
				{0,0,3,1,4,0,0,0,1,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "So near but also so far away";
		instructions[1] = "";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getSeventeenLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{1,3,7,1,3,7,3,1,0,1},
				{2,7,0,0,7,0,7,0,4,9},
				{0,3,7,1,3,7,0,1,4,1},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "It just a jump to the left";
		instructions[1] = "And then a step to the right";
		String levelName = "SIDESTEP";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getEighteenLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,1,0,0,8,1,8,0,7,0},
				{2,8,7,1,0,7,7,0,8,9},
				{0,7,0,7,8,8,1,7,8,1},
		};
		byte[][] secondLevel = new byte[][] {
				{0,7,0,0,8,1,1,0,7,0},
				{9,7,1,1,0,0,0,2,8,0},
				{0,7,0,0,7,1,1,7,7,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "That's easy or?";
		instructions[1] = "Stop what's that ...";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getNineteenLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,8,7,0,7,8,0,0},
				{2,0,7,8,0,8,7,0,0,9},
				{0,0,7,0,7,8,0,8,0,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Like a flashlight";
		instructions[1] = "On / off ... on / off";
		String levelName = "ONOFF";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	
	private static final ApoGameLevel getTwentyLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,8,0,7,0,1,0,0,0,1},
				{2,1,4,7,3,1,4,3,1,9},
				{0,1,4,1,8,0,8,1,7,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Now it is getting difficult";
		instructions[1] = "But not for you or?";
		String levelName = "Highjump";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwentyOneLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,1,0,0,8,1,1,0,7,0},
				{2,0,7,1,0,7,7,0,8,9},
				{0,7,0,7,8,1,1,0,7,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,0,0,3,4,0,8,0},
				{9,0,3,3,0,0,7,2,1,7},
				{0,0,0,0,7,3,4,0,8,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "Thats easy or?";
		instructions[1] = "Stop whats that ...";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	
	private static final ApoGameLevel getTwentyTwoLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,1,7,7,0,1,0,3,0,7},
				{2,1,3,1,8,3,4,1,1,9},
				{0,8,8,1,8,3,8,3,0,7},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Sometimes I'm might going crazy";
		instructions[1] = "Hihihihihi";
		String levelName = "BACKDOOR";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwentyThreeLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,0,5,1,0,0,1},
				{2,0,0,0,0,5,0,0,0,9},
				{0,0,0,0,0,0,1,5,5,1},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,1,0,0,0,0,0,0},
				{2,1,5,0,4,0,0,0,0,9},
				{0,1,0,0,4,0,0,0,0,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "The magenta tile goes up when";
		instructions[1] = "you change your direction horizontally";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	
	private static final ApoGameLevel getTwentyFourLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,1,5,5,5,5,5,5,5,1},
				{2,0,0,0,0,0,0,3,0,9},
				{0,1,0,7,0,4,0,3,0,1},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Now there is a magenta wall";
		instructions[1] = "I hope I can pass it";
		String levelName = "The WALL";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwentyFifeLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,0,0,1,0,5,7,0,0},
				{2,1,5,0,0,4,0,0,5,9},
				{0,0,0,0,0,4,0,3,5,1},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,1,1,0,0,7,0,0},
				{2,0,0,1,0,4,5,3,5,9},
				{0,1,0,7,0,4,0,0,5,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "You are stuck?";
		instructions[1] = "Did you try to run backwards?";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwentySixLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,5,6,3,4,5,6,0,0},
				{2,0,0,0,0,0,0,0,0,9},
				{0,0,5,6,3,4,5,6,0,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Pink Tiles? Goes down when";
		instructions[1] = "you change your direction horizontally";
		String levelName = "PINKY";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwentySevenLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,7,6,0,0,0,5,0,0,0},
				{2,1,0,5,4,1,0,6,3,9},
				{0,0,0,0,0,7,0,0,0,1},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0,0,0,4,8,0,0,3,1},
				{2,1,6,0,0,1,5,0,0,9},
				{0,8,0,5,0,0,0,6,0,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "It's impossible!";
		instructions[1] = "Or wait ... perhaps when I ...";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwentyEightLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{3,0,4,0,3,8,4,0,3,0},
				{2,1,4,5,1,1,4,5,1,9},
				{3,6,7,0,3,6,7,0,3,6},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "So many different colors";
		instructions[1] = "These colors are very calming or?";
		String levelName = "Colored";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	
	
	private static final ApoGameLevel getTwentyNineLevel() {
		byte[][][] level = new byte[2][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0,10,11,0, 0,0, 0, 0,0},
				{2,0, 0,11,0, 0,0, 0,11,9},
				{0,0,10,11,0, 0,0, 0, 1,0},
		};
		byte[][] secondLevel = new byte[][] {
				{0,0, 0, 0,0, 3,8, 1, 0,0},
				{2,0, 0, 0,0,10,6, 6,11,9},
				{0,0, 0, 0,0, 3,0,10, 1,0},
		};
		level[0] = firstLevel;
		level[1] = secondLevel;

		String[] instructions = new String[3];
		instructions[0] = "Step on the * tile and the o tiles";
		instructions[1] = "will change their visible";
		instructions[2] = "Lamer will press 'c'";
		String levelName = "DOUBLE COMBINE";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	

	private static final ApoGameLevel getThirtyLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,0, 8, 6, 3, 0,10, 1, 1,0},
				{2,1, 5, 6, 3, 5, 6,10,11,9},
				{0,0, 5,10,11, 5, 0, 1, 1,0},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "Damn heavy level!";
		instructions[1] = "Can you beat it?";
		String levelName = "MINDBLOW";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}

	

	private static final ApoGameLevel getThirtyOneLevel() {
		byte[][][] level = new byte[1][3][10];
		byte[][] firstLevel = new byte[][] {
				{0,5,10,11, 0, 5, 1, 1, 1, 1},
				{2,0, 6, 0, 7, 8,10,10,11, 9},
				{0,5, 6, 0, 6, 0, 1, 1, 1, 1},
		};
		level[0] = firstLevel;
		String[] instructions = new String[2];
		instructions[0] = "This level will blow your mind";
		instructions[1] = "Can you beat it?";
		String levelName = "FINAL LEVEL";
		ApoGameLevel simpleLevel = new ApoGameLevel(level, instructions, levelName);
		return simpleLevel;
	}


}
