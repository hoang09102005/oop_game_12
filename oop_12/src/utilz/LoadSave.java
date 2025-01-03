package utilz;

import entities.PlayerCharacter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import javax.imageio.ImageIO;

public class LoadSave {
    //Image of 3 main players
    public static final String PLAYER_HERO = "player_hero.png";
    public static final String PLAYER_SAMURAI = "player_samurai.png";
    public static final String PLAYER_WARRIOR = "player_warrior.png";
    //Enemies
//    public static final String CRABBY_SPRITE = "crabby_sprite.png";
    public static final String GORDON = "Gordon(96x96).png";
    public static final String WOLF = "Wolf(64x64).png";
    public static final String SNAKE = "black_enemies(64x64).png";

    public static final String QS_menu = "QS_menu.png";
    public static final String QS_BUTTON = "QSimg.png";
    public static final String QS1_img = "ENTER.png";
    public static final String QS_ENTER= "ENTER_BUTTON.png";

    public static final String LEGEND= "legend.png";
    
    
    public static final String LEVEL_ATLAS = "outside_sprites.png";
    public static final String MENU_BUTTONS = "button_atlas.png";
    public static final String MENU_BACKGROUND = "menubk.png";
    public static final String PAUSE_BACKGROUND = "pause_menu.png";
    public static final String SOUND_BUTTONS = "sound_button.png";
    public static final String URM_BUTTONS = "urm_buttons.png";
    public static final String VOLUME_BUTTONS = "volume_buttons.png";
    public static final String MENU_BACKGROUND_IMG = "background.png";
    public static final String PLAYING_BG_IMG = "playing_bg_img.png";
    public static final String LAYER_IMG = "layer_img.png";
    public static final String SMALL_CLOUDS = "small_clouds.png";
    public static final String STATUS_BAR = "health_power_bar.png";
    public static final String COMPLETED_IMG = "completed_sprite.png";
    public static final String POTION_ATLAS = "potions_sprites.png";
    public static final String CONTAINER_ATLAS = "objects_sprites.png";
    public static final String TRAP_ATLAS = "trap_atlas.png";
    public static final String CANNON_ATLAS = "cannon_atlas.png";
    public static final String CANNON_BALL = "ball.png";
    public static final String DEATH_SCREEN = "deathscreen.png";
    public static final String OPTIONS_MENU = "options_background1.png";
    public static final String QUESTION_ATLAS = "question_atlas.png";
    public static final String EXCLAMATION_ATLAS = "exclamation_atlas.png";
    public static final String CREDITS = "credits_list(change).png";
    public static final String GRASS_ATLAS = "grass_atlas.png";
    public static final String DECOR_ONE_ATLAS = "decor_one_atlas.png";
    public static final String DECOR_TWO_ATLAS = "decor_two_atlas.png";
    public static final String GAME_COMPLETED = "game_completed(change).png";
    public static final String RAIN_PARTICLE = "rain_particle.png";
    public static final String LAVA_TOP = "lava_atlas_animation.png";
    public static final String LAVA_BOTTOM = "lava.png";
    public static final String SHIP = "ship.png";


    public static BufferedImage[][] loadAnimations(PlayerCharacter pc) {
        BufferedImage img = LoadSave.GetSpriteAtlas(pc.playerAtlas);
        BufferedImage[][] animations = new BufferedImage[pc.rowA][pc.colA];
        for (int j = 0; j < animations.length; j++)
            for (int i = 0; i < animations[j].length; i++)
                animations[j][i] = img.getSubimage(i * pc.spriteW, j * pc.spriteH, pc.spriteW, pc.spriteH);

        return animations;
    }


    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = null;
        
        try {
            // In ra đường dẫn đầy đủ để kiểm tra
            String fullPath = "/" + fileName;
            System.out.println("Attempting to load resource: " + fullPath);
            
            // Thử lấy input stream
            is = LoadSave.class.getResourceAsStream(fullPath);
            
            // Kiểm tra xem input stream có null không
            if (is == null) {
                System.err.println("ERROR: Cannot find resource - " + fullPath);
                // In ra danh sách các tài nguyên để kiểm tra
             //   printAvailableResources();
                return null;
            }
            
            // Đọc ảnh
            img = ImageIO.read(is);
            
            // Kiểm tra xem ảnh có được load thành công không
            if (img == null) {
                System.err.println("ERROR: Failed to read image - " + fullPath);
            }
            
        } catch (IOException e) {
            System.err.println("IOException when loading: " + fileName);
            e.printStackTrace();
        } finally {
            // Đóng input stream an toàn
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        return img;
    }


    public static BufferedImage[] GetAllLevels() {
        URL url = LoadSave.class.getResource("/lvls");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++)
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png"))
                    filesSorted[i] = files[j];

            }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];

        for (int i = 0; i < imgs.length; i++)
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return imgs;
    }

}