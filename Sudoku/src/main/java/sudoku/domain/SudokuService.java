package sudoku.domain;

import de.sfuhrm.sudoku.QuadraticArrays;
import sudoku.dao.DbStatisticsDao;
import sudoku.dao.FileGameDao;
import sudoku.dao.FileSettingsDao;
import sudoku.messages.Language;
import sudoku.messages.Message;

public class SudokuService {

    private final FileGameDao gameDao;
    private final FileSettingsDao settingsDao;
    private final DbStatisticsDao statisticsDao;

    private final Game game;
    private final Settings settings;
    private final Statistics statistics;

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

    public Game getGame() {
        return this.game;
    }

    public Settings getSettings() {
        return this.settings;
    }

    public Statistics getStatistics() {
        return this.statistics;
    }

    public void newGame() {
        game.getPuzzle().setPuzzle(settings.getDifficulty());
        game.initMasks();
        game.setGameTime(0);
    }

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

    public boolean loadGame() {
        String[] loadData;
        byte[][] sudokuMatrix;
        boolean[][] masks = new boolean[9][9], originalMasks = new boolean[9][9];

        try {
            loadData = gameDao.loadFile();
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

    public void applySettings(Language language, Difficulty difficulty, boolean showMistakes) {
        Message.setLanguage(language);
        settings.setDifficulty(difficulty);
        settings.setShowMistakes(showMistakes);

        saveSettings();
    }

    public boolean saveSettings() {
        try {
            settingsDao.saveFile(Message.getLanguage().toString(),
                    settings.getDifficulty().toString(), String.valueOf(settings.isShowMistakes()));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

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

    public void insertGame(int time) {
        try {
            statisticsDao.insertGame(time);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        loadStatistics();
    }

    public void loadStatistics() {
        int[] data = new int[4];
        try {
            data = statisticsDao.getAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            statistics.setAll(0, 0, 0, 0);
        }
        statistics.setAll(data[0], data[1], data[2], data[3]);
    }

}
