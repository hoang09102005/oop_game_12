package gamestates;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import main.Game;
import ui.MenuButton;
import ui.QSButton;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {

    private MenuButton[] buttons = new MenuButton[4];
    private QSButton qs =  new QSButton((int) (Game.GAME_WIDTH  - 70 * Game.SCALE) ,  (int) (Game.GAME_HEIGHT - 70 * Game.SCALE) ,  Gamestate.QS);
    private BufferedImage backgroundImg, backgroundImgPink, backgroundImglng;
    private int menuX, menuY, menuWidth, menuHeight;
    private boolean menuflag = false  ; 

    public Menu(Game game) {
        super(game);
        loadButtons();
        loadBackground();
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND_IMG);
        backgroundImglng = LoadSave.GetSpriteAtlas(LoadSave.LEGEND);

    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BACKGROUND);
        menuWidth = (int) (backgroundImg.getWidth() * Game.SCALE *0.85);
        menuHeight = (int) (backgroundImg.getHeight() * Game.SCALE *0.9);
        menuX = Game.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (0* Game.SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH / 2, (int) (155 * Game.SCALE), 0, Gamestate.PLAYER_SELECTION);
        buttons[1] = new MenuButton(Game.GAME_WIDTH / 2, (int) (215 * Game.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(Game.GAME_WIDTH / 2, (int) (275 * Game.SCALE), 3, Gamestate.CREDITS);
        buttons[3] = new MenuButton(Game.GAME_WIDTH / 2, (int) (330 * Game.SCALE), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        qs.update();
        for (MenuButton mb : buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {
        if(!menuflag){      
        g.drawImage(backgroundImglng, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        }
        else{
        g.drawImage(backgroundImgPink, 0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT, null);
        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        qs.draw(g);
        for (MenuButton mb : buttons)
            mb.draw(g);
        }
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (isIn(e, qs)) {
            qs.setMousePressed(true);
        }
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                mb.setMousePressed(true);
            }
        }   
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (isIn(e, qs)) {
            if (qs.isMousePressed())
                qs.applyGamestate();
            if (qs.getState() == Gamestate.PLAYING)
                game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
        }
        for (MenuButton mb : buttons) {
            if (isIn(e, mb)) {
                if (mb.isMousePressed())
                    mb.applyGamestate();
                if (mb.getState() == Gamestate.PLAYING)
                    game.getAudioPlayer().setLevelSong(game.getPlaying().getLevelManager().getLevelIndex());
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        qs.resetBools();
        for (MenuButton mb : buttons)
            mb.resetBools();

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        qs.setMouseOver(false);
        for (MenuButton mb : buttons)
            mb.setMouseOver(false);
        
        if (isIn(e, qs)) {
            qs.setMouseOver(true);
        }
        for (MenuButton mb : buttons)
            if (isIn(e, mb)) {
                mb.setMouseOver(true);
                break;
            }

    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ESCAPE:         

                break;
            case KeyEvent.VK_ENTER:
                menuflag = true;
                System.out.println("eureka");
                break;
            default:
                break;
        }
       

    }

}