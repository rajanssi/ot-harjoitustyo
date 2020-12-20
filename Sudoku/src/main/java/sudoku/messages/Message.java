package sudoku.messages;

/**
 * Contains all the String for UI messages in Finnish and in English.
 */
public class Message {

    private static Language language;

    // Menu scene messages
    /**
     * String for continuing an existing game.
     *
     * @return String in Finnish or English
     */
    public static final String continueGame() {
        if (checkLanguage()) {
            return "Jatka peliä";
        }
        return "Continue game";
    }

    /**
     * String for starting a new game.
     * 
     * @return String in Finnish or English
     */
    public static final String newGame() {
        if (checkLanguage()) {
            return "Uusi peli";
        }
        return "New game";
    }

    /**
     * String for settings.
     * 
     * @return String in Finnish or English
     */
    public static final String settings() {
        if (checkLanguage()) {
            return "Asetukset";
        }
        return "Settings";
    }

    /**
     * String for statistics.
     * 
     * @return String in Finnish or English
     */
    public static final String statistics() {
        if (checkLanguage()) {
            return "Tilastoja";
        }
        return "Statistics";
    }

    /**
     * String for quitting.
     * 
     * @return String in Finnish or English
     */
    public static final String quit() {
        if (checkLanguage()) {
            return "Sulje";
        }
        return "Quit";
    }

    /**
     * String for returning to main menu.
     * 
     * @return String in Finnish or English
     */
    public static final String backToMenu() {
        if (checkLanguage()) {
            return "Päävalikkoon";
        }
        return "Menu";
    }

    // Game scene messages
    /**
     * String for congratulation after winning a game.
     * 
     * @return String in Finnish or English
     */
    public static final String congrats() {
        if (checkLanguage()) {
            return "Onnea!";
        }
        return "Congratulations! You won the game.";
    }

    /**
     * String for message in after winning a game.
     * 
     * @return String in Finnish or English
     */
    public static final String winMessage() {
        if (checkLanguage()) {
            return "Voitit pelin. Haluatko pelata uudestaan?";
        }
        return "Congratulations. Play again?";
    }

    // Settings scene messages
    /**
     * String for choosing a language.
     * 
     * @return String in Finnish or English
     */
    public static final String language() {
        if (checkLanguage()) {
            return "Valitse kieli";
        }
        return "Select language";
    }

    /**
     * String for Finnish.
     * 
     * @return String in Finnish or English
     */
    public static final String languageFi() {
        if (checkLanguage()) {
            return "Suomi";
        }
        return "Finnish";
    }

    /**
     * String for English.
     * 
     * @return String in Finnish or English
     */
    public static final String languageEn() {
        if (checkLanguage()) {
            return "Englanti";
        }
        return "English";
    }

    /**
     * String for choosing a difficulty.
     * 
     * @return String in Finnish or English
     */
    public static final String difficulty() {
        if (checkLanguage()) {
            return "Vaikeustaso";
        }
        return "Difficulty";
    }

    /**
     * String for Easy difficulty.
     * 
     * @return String in Finnish or English
     */
    public static final String difficultyEasy() {
        if (checkLanguage()) {
            return "Helppo";
        }
        return "Easy";
    }

    /**
     * String for Hard difficulty.
     * 
     * @return String in Finnish or English
     */
    public static final String difficultyHard() {
        if (checkLanguage()) {
            return "Vaikea";
        }
        return "Hard";
    }

    /**
     * String for showing mistakes.
     * 
     * @return String in Finnish or English
     */
    public static final String showMistakes() {
        if (checkLanguage()) {
            return "Näytä virheet";
        }
        return "Show mistakes";
    }

    /**
     * String for applying settings.
     * 
     * @return String in Finnish or English
     */
    public static final String apply() {
        if (checkLanguage()) {
            return "Hyväksy";
        }
        return "Apply";
    }

    // Statistics scene messages
    /**
     * String for games played.
     * 
     * @return String in Finnish or English
     */
    public static final String gamesPlayed() {
        if (checkLanguage()) {
            return "Pelejä pelattu";
        }
        return "Games played";
    }

    /**
     * String for average length.
     * 
     * @return String in Finnish or English
     */
    public static final String avgLength() {
        if (checkLanguage()) {
            return "Keskimääräinen pelin kesto";
        }
        return "Average game length";
    }

    /**
     * String for maximum duration.
     * 
     * @return String in Finnish or English
     */
    public static final String maxLength() {
        if (checkLanguage()) {
            return "Pisin pelin kesto";
        }
        return "Longest game length";
    }

    /**
     * String for minimum duration.
     * 
     * @return String in Finnish or English
     */
    public static final String minLength() {
        if (checkLanguage()) {
            return "Lyhin pelin kesto";
        }
        return "Shortest game length";
    }
    
    /**
     * Sets the language of the application to the specified language.
     * 
     * @param language Either Finnish or English. 
     */
    public static void setLanguage(Language language) {
        Message.language = language;
    }

    /**
     * Returns the current language of the application.
     * 
     * @return Language value
     */
    public static Language getLanguage() {
        return language;
    }

    private static boolean checkLanguage() {
        return language.equals(Language.FINNISH);
    }

}
