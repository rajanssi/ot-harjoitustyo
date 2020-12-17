package sudoku.domain;

import sudoku.dao.FileSettingsDao;
import sudoku.messages.Language;
import sudoku.messages.Message;

public class Settings {

    private Difficulty difficulty;
    private boolean showMistakes;
    private final FileSettingsDao fileSettingsDao;

    public Settings(FileSettingsDao fileSettingsDao) {
        this.fileSettingsDao = fileSettingsDao;

        // If settings can't be loaded, then set default settings
        if (!loadSettings()) {
            Message.setLanguage(Language.FINNISH);
            this.difficulty = Difficulty.EASY;
            this.showMistakes = true;
        }
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public boolean isShowMistakes() {
        return showMistakes;
    }

    public void applySettings(Language language, Difficulty difficulty, boolean showMistakes) {
        Message.setLanguage(language);
        this.difficulty = difficulty;
        this.showMistakes = showMistakes;

        saveSettings();
    }

    public boolean saveSettings() {
        try {
            fileSettingsDao.saveFile(Message.getLanguage().toString(), difficulty.toString(), String.valueOf(showMistakes));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean loadSettings() {
        try {
            String[] loadFile = fileSettingsDao.loadFile();

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
                this.difficulty = Difficulty.EASY;
            } else if (loadFile[1].equals(Difficulty.HARD.toString())) {
                this.difficulty = Difficulty.HARD;
            } else {
                return false;
            }

            // Check mistakes
            if (loadFile[2].equals("true")) {
                this.showMistakes = true;
            } else if (loadFile[2].equals("false")) {
                this.showMistakes = false;
            } else {
                return false;
            }
        } catch (Exception e) {
            System.out.println("Settings error: " + e.getMessage());
            return false;
        }
        return true;
    }
}
