package sudoku.messages;

public class Message {

    private static Language language;

    // Menu scene messages
    public static final String CONTINUE() { if (checkLanguage()) { return "Jatka peliä"; } return "Continue game";}
    public static final String NEWGAME() { if (checkLanguage()) {return "Uusi peli";} return "New game"; }
    public static final String SETTINGS() { if (checkLanguage()) {return "Asetukset";} return "Settings"; }
    public static final String STATISTICS() { if (checkLanguage()) {return "Tilastoja";} return "Statistics"; }
    public static final String QUIT() { if (checkLanguage()) {return "Sulje";} return "Quit"; }
    public static final String BACKTOMENU() { if (checkLanguage()) {return "Päävalikkoon";} return "Menu"; }

    // Game scene messages
    public static final String CONGRATS() { if (checkLanguage()) {return "Onnea!";} return "Congratulations! You won the game."; }
    public static final String WINMESSAGE() { if (checkLanguage()) { return "Voitit pelin. Haluatko pelata uudestaan?"; } return "Congratulations. Play again?"; }

    // Settings scene messages
    public static final String LANGUAGE() { if (checkLanguage()) { return "Valitse kieli"; } return "Select language"; }
    public static final String LANGUAGE_FI() { if (checkLanguage()) { return "Suomi"; } return "Finnish"; }
    public static final String LANGUAGE_EN() { if (checkLanguage()) { return "Englanti"; } return "English"; }
    public static final String DIFFICULTY() { if (checkLanguage()) { return "Vaikeustaso"; } return "Difficulty"; }
    public static final String DIFFICULTY_E() { if (checkLanguage()) { return "Helppo"; } return "Easy"; }
    public static final String DIFFICULTY_H() { if (checkLanguage()) { return "Vaikea"; } return "Hard"; }
    public static final String SHOWMISTAKES() { if (checkLanguage()) { return "Näytä virheet"; } return "Show mistakes"; }
    public static final String APPLY(){ if (checkLanguage()){ return "Hyväksy"; } return "Apply"; }
    
    // Statistics scene messages
    public static final String GAMESPLAYED() { if (checkLanguage()) { return "Pelejä pelattu"; } return "Games played"; }
    public static final String AVGLENGTH() { if (checkLanguage()) { return "Keskimääräinen pelin kesto"; } return "Average game length"; }
    public static final String MAXLENGTH() { if (checkLanguage()) { return "Pisin pelin kesto"; } return "Longest game length"; }
    public static final String MINLENGTH() { if (checkLanguage()) { return "Lyhin pelin kesto"; } return "Shortest game length"; }
            
    public static void setLanguage(Language language) {
        Message.language = language;
    }

    public static Language getLanguage() {
        return language;
    }

    private static boolean checkLanguage() {
        return language.equals(Language.FINNISH);
    }

}
