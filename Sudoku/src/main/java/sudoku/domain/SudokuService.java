package sudoku.domain;

import de.sfuhrm.sudoku.QuadraticArrays;
import sudoku.dao.DbStatisticsDao;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.messages.Language;
import sudoku.messages.Message;

/**
 * Handles communication between UI, file/database access and game logic. 
 */
public class SudokuService {

    private final FileGameDao gameDao;
    private final FileSettingsDao settingsDao;
    private final DbStatisticsDao statisticsDao;

    private final Game game;
    private final Settings settings;
    private final Statistics statistics;

    /**
     * Takes in data access objects and initializes game, settings and statistics
     * classes. 
     * 
     * @param gameDao Data access object for loading and saving game files
     * @param settingsDao Data access object for loading and saving settings
     * @param statisticsDao Data access object that handles data of played games
     */
    public SudokuService(FileGameDao gameDao, FileSettingsDao settingsDao, DbStatisticsDao statisticsDao) {
        this.gameDao = gameDao;
        this.settingsDao = settingsDao;
        this.statisticsDao = statisticsDao;

        this.settings = new Settings();
        this.game = new Game(settings.getDifficulty());
        this.statistics = new Statistics();

        // If settings can't be loaded, then set default settings
        if (!loadSettings()) {
            Message.setLanguage(Language.FINNISH);
            settings.setDifficulty(Difficulty.EASY);
            settings.setShowMistakes(true);
        }

        // If game can't be loaded, then set up a new game
        if (!loadGame()) {
            newGame();
        }

        loadStatistics();
    }

    /**
     * Returns Game object created in SudokuService class.
     * 
     * @return game object
     */
    public Game getGame() {
        return this.game;
    }

    /** 
     * Returns Settings object created in SudokuService class.
     * 
     * @return Settings object
     */
    public Settings getSettings() {
        return this.settings;
    }
    
    /**
     * Returns Statistics object created in SudokuService class.
     * 
     * @return Statistics object
     */
    public Statistics getStatistics() {
        return this.statistics;
    }

    /**
     * Sets up a new game from scratch based on the settings given to by the 
     * user in settings.
     */
    public void newGame() {
        game.getPuzzle().setPuzzle(settings.getDifficulty());
        game.initMasks();
        game.setGameTime(0);
    }   

    /**
     * Saves current game to a file.
     * 
     * @param time Takes in the game time from a timer 
     * @return true on success
     */
    public boolean saveGame(int time) {
        byte[][] originalMasks = new byte[9][9];
        byte[][] gameMasks = new byte[9][9];

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                gameMasks[x][y] = (byte) (game.getMask(x, y) ? 1 : 0);
                originalMasks[x][y] = (byte) (game.getOriginalMask(x, y) ? 1 : 0);
            }
        }

        try {
            gameDao.saveFile(game.getPuzzle().getGame(), gameMasks, originalMasks, time, settings.getDifficulty());
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    /**
     * Loads a saved game from a file.
     * 
     * @return true on success
     */
    public boolean loadGame() {
        byte[][] sudokuMatrix;
        boolean[][] masks = new boolean[9][9], originalMasks = new boolean[9][9];
        try {
            String[] loadData = gameDao.loadFile();
            sudokuMatrix = QuadraticArrays.parse(loadData[0].split(";"));
            int index = 0;
            for (int x = 0; x < 9; x++) {
                for (int y = 0; y < 9; y++) {
                    masks[x][y] = loadData[1].charAt(index) == '1';
                    originalMasks[x][y] = loadData[2].charAt(index) == '1';
                    index++;
                }
            }
            game.setGameTime(Integer.parseInt(loadData[3]));
            if (loadData[4].equals(Difficulty.EASY.toString())) {
                game.setDifficulty(Difficulty.EASY);
            } else {
                game.setDifficulty(Difficulty.HARD);
            }
        } catch (Exception e) {
            return false;
        }
        game.getPuzzle().setPuzzle(sudokuMatrix, originalMasks);
        game.setMasks(masks);
        return true;
    }

    /**
     * Applies settings to language and game and then saves them to a file.
     * 
     * @param language Users choice of language, English or Finnish
     * @param difficulty Users choice of difficulty, Easy or Hard
     * @param showMistakes indicates whether incorrectly placed cells are shown
     * on the Sudoku scene
     */
    public void applySettings(Language language, Difficulty difficulty, boolean showMistakes) {
        Message.setLanguage(language);
        settings.setDifficulty(difficulty);
        settings.setShowMistakes(showMistakes);

        saveSettings();
    }

    /**
     * Saves settings to a file.
     * 
     * @return true on success
     */
    public boolean saveSettings() {
        try {
            settingsDao.saveFile(Message.getLanguage().toString(),
                    settings.getDifficulty().toString(), String.valueOf(settings.isShowMistakes()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Loads settings from a file.
     * 
     * @return true on success
     */
    public boolean loadSettings() {
        try {
            String[] loadFile = settingsDao.loadFile();

            // Check language
            if (loadFile[0].equals(Language.ENGLISH.toString())) {
                Message.setLanguage(Language.ENGLISH);
            } else if (loadFile[0].equals(Language.FINNISH.toString())) {
                Message.setLanguage(Language.FINNISH);
            } else {
                return false;
            }

            // Check difficulty
            if (loadFile[1].equals(Difficulty.EASY.toString())) {
                settings.setDifficulty(Difficulty.EASY);
            } else if (loadFile[1].equals(Difficulty.HARD.toString())) {
                settings.setDifficulty(Difficulty.HARD);
            } else {
                return false;
            }

            // Check mistakes
            if (loadFile[2].equals("true")) {
                settings.setShowMistakes(true);
            } else if (loadFile[2].equals("false")) {
                settings.setShowMistakes(false);
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Inserts a game that has been completed to a database.
     * 
     * @param time The time that it took to complete a game
     * @return true on success
     */
    public boolean insertGame(int time) {
        try {
            statisticsDao.insertGame(time);
        } catch (Exception e) {
            return false;
        }

        loadStatistics();
        return true;
    }

    /**
     * Loads game statistics from a database.
     * 
     * @return true on success
     */
    public boolean loadStatistics() {
        int[] data;
        try {
            data = statisticsDao.getAll();
        } catch (Exception e) {
            statistics.setAll(0, 0, 0, 0);
            return false;
        }
        statistics.setAll(data[0], data[1], data[2], data[3]);
        return true;
    }
    
}
