/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package occult;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 *
 *  @author Tønnes-Skole-Pc2
 */
public class Main implements ActionListener, KeyListener, MouseListener {   
    //Class Objects
    public static Main main;
    InformationGetter infoGetter;
    Rendering rendering;
    Utility util;
    
    //Integers
    int width;
    int height;
    int introWidth;
    int gameScene = 0; //IMPLEMENT WAY TO CHANGE THIS <-----------  Basiclly done. Somewhat.
    int identifier = 0; //IMPLEMENT WAY TO CHANGE THIS <---------
    int sentence = 0; //IMPLEMENT WAT TO CHANGE THIS <--------
    int currentStoryPart = 0; //IMPLEMENT WAY TO CHANGE THIS <---------  
    int itemIdentifier = 0; //IMPLEMENT WAY TO CHANGE THIS <---------
    int storyIdentifier = 0; //IMPLEMENT WAY TO CHANGE THIS <---------
    
    //Strings
    String currentSpeech;
    String currentIntro;
    String reasonAreYouSure; //IMPLEMENT WAY TO CHANGE THIS <----------
    String filePath;
    
    //Booleans
    Boolean fullscreen = false; //<---- Don't use. It doesn't work with every component.
    Boolean canInteract = true; //<---- IMPLEMENT
    Boolean showAreYouSure = false;
    Boolean showInventory = false; 
    
    //Find a better way to do this.
    Boolean isBorderRendered, isAreYouSureRendered, isSpeechBoxRendered, isItemsRendered, isBackgroundRendered, isPortraitRendered, isInventoryRendered;
    Boolean doItem1Exist, doItem2Exist, doItem3Exist, doItem4Exist;    
    //Two Portraits? <--------
    //More than one item? <-----------
    
    //Images
    Image imgResized;
    Image image;
    
    //Fonts
    Font Arial_15 = new Font("Arial", 0, 15);
    Font Arial_20 = new Font("Arial", 0, 20);
    Font Arial_30 = new Font("Arial", 0, 30);
    Font Arial_20_Bold = new Font("Arial", 1 ,20);
    Font Arial_30_Bold = new Font("Arial", 1, 30);  
    Font BoxyB_20 = new Font("Boxy bold", 0, 20);
    
    //Other
    JFrame frame;
    Graphics2D g;
    
    public Main() {        
        rendering = new Rendering();
        util = new Utility();        
        infoGetter = new InformationGetter();
        
        frame = new JFrame("Occult"); 
        //frame.setLayout(null);
        frame.setVisible(true);
        frame.add(rendering);
        frame.addKeyListener(this);
        frame.addMouseListener(this);                 
        getInformation();
        
//        width = screenSize.width/2+screenSize.width/4;
//        height = screenSize.height/2+screenSize.height/4;
        width = 1440;
        height = 810;
        
        frame.setBackground(Color.white);   
        frame.setSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);       
    }
    
    public static void main(String[] args) {            
        main = new Main();
    }
    
    //Unsure of the outcome of using final.
    public final void getInformation() {                 
        infoGetter.getCharacterArray(); 
        infoGetter.getStoryArray(storyIdentifier);    
        infoGetter.getItemArray();           
        
        currentSpeech = infoGetter.getSentence(currentStoryPart);       
    }
    
    //Potentially create a new class, to contain every render methods.
    public void render(Graphics2D g) {                 
        resetRenderingBooleans();

        //For Testing
        System.out.println("Debug: Render");
        
        width = util.getWindowSize(frame).width-18;
        height = util.getWindowSize(frame).height;
        
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);         
        
        if (fullscreen == true) {
            util.fullScreen(frame); //Should not use.
        }
        
        //Conflict on reset. REPAINT();
        switch (gameScene) {
            //Intro Scene
            case -3: 
                renderGameSceneIntro(g);
                break;
            //About/Settings/Help or something
            case -2: 
                renderGameSceneNEG2(g);
                break;
            //Select Character
            case -1: 
                renderCharacterSelection(g);
                break;
            //Start Screen
            case 0: 
                renderGameScene0(g);
                break;
            //Scene 1
            case 1: 
                renderGameScene1(g);
                break;
            //Scene 2
            case 2: 
                renderGameScene2(g);
                break;
        }
    }
    
    //Reseting the rendering tests.
    public void resetRenderingBooleans() {
        isBorderRendered = isInventoryRendered = isBackgroundRendered = isSpeechBoxRendered = isItemsRendered = isPortraitRendered = isAreYouSureRendered = false;
        doItem1Exist = doItem2Exist = doItem3Exist = doItem4Exist = false;
    }
    
    //Rendering ->  
    //Scenes
    public void renderGameSceneNEG2(Graphics2D g) {
        g.setFont(Arial_30);
        g.drawString("HELP", 100, 100);
        g.drawString("or SETTINGS", 100, 150);
        g.drawString("or maybe ABOUT", 100, 200);
        g.drawString("Coming soon..." , 100, 400);
    }
    
    public void renderCharacterSelection(Graphics2D g) {
        //Background
        filePath = "Resources\\Backgrounds\\Sky_1440x810.png";
        image = util.getImage(filePath);
        g.drawImage(image, 0,0, frame);
        
        //Character select box
        filePath = "Resources\\Other\\CharacterSelectionScreen_1440x810_withButton.png";
        image = util.getImage(filePath);
        g.drawImage(image, 0,0, frame);
    }
    
    public void renderGameSceneIntro(Graphics2D g) {
        canInteract = false; //<--- Find better way. <-----------------------
        //IMPLEMENT CREDITS ROLL or something. <-------------
        introWidth = width/2-220;
        g.setColor(Color.red);
        g.setFont(Arial_30);
        g.drawString("OCCULT", introWidth+150, 100);
        g.drawString("Made by Tonnes Roren", introWidth, 250);
        g.drawString("Made in JAVA 8", introWidth, 300);
        g.drawString("UiA - 2018", introWidth, 350);
        g.drawString("More Lines...", introWidth, 400);
        g.drawString("Skip with ENTER", introWidth, 500);
    } 
    
    public void renderGameScene0(Graphics2D g) {
        canInteract = true;
        filePath = "Resources\\Other\\TitleScreenTitle_UW.png";
        image = util.getImage(filePath);
        imgResized = util.resizeImage(image, 430, 430, g);
        g.drawImage(imgResized, -0, -40, frame);
        
        g.setFont(Arial_30_Bold);
        g.drawString("New Game (Space)", 50, 270); 
        g.drawString("Select Character (Q)", 50, 320);
        g.drawString("About (E)", 50, 370);
        g.drawString("Exit (Esc)", 50, 420); 
    }
    
    public void renderGameScene1(Graphics2D g) {
        canInteract = true; //<--- Find better way. <---------------------
        //Do something here.
        //No story elements are made.
        renderBackground(g);
        renderBorder(g);
        renderSpeechBox(g);
        renderPortraitBox(g);
        renderItems(g);
//        renderTest(g); //<---- GIF RENDERING TEST
//        renderTestAnimation(g); //<- --- Animation RENDERING TEST (Does not work.)
        if (showAreYouSure == true) {
            renderAreYouSure(g, reasonAreYouSure);
        } 
        if (showInventory == true) {
            renderInventory(g);
        }
    }
    
    public void renderGameScene2(Graphics2D g) {
        canInteract = true; //<--- Find better way.
        //Do something here.
        renderBackground(g);             
        renderBorder(g);
        renderSpeechBox(g);
        renderPortraitBox(g);
        renderItems(g);   
    }
    
    //Components
    public void renderSpeechBox(Graphics2D g) {       
        filePath = "Resources\\Other\\SpeechBox_1440x136.png";
        image = util.getImage(filePath);
        g.drawImage(image, -3, height-168, frame);
        
        g.setFont(Arial_30);
        g.setColor(Color.white);
        if (currentSpeech.contains("I'm")) {
             g.drawString(currentSpeech + " " + infoGetter.getFullName(identifier) + ".", 80, height-120);
        }
        else {
            g.drawString(currentSpeech, 80, height-120);
        }
        
        isSpeechBoxRendered = true;
    }
    
    public void renderPortraitBox(Graphics2D g) {
        g.setFont(Arial_15);
        g.setColor(Color.black); 
        
        //Portrait back Image
        filePath = "Resources\\Other\\Portrait_304x145.png";
        image = util.getImage(filePath);
        imgResized = util.resizeImage(image, 304, 141, g);
        g.drawImage(imgResized, 1, 1, frame);
     
        //Portrait Image
        filePath = infoGetter.getPortraitImgAdress(identifier);
        image = util.getImage(filePath);
        imgResized = util.resizeImage(image, 100, 120, g);
        g.drawImage(imgResized, 18, 15, frame);            
        g.drawString(infoGetter.getFullName(identifier), 139, 28);
        
        //Flag Image
        filePath = infoGetter.getCountryImgAdress(identifier);
        image = util.getImage(filePath);
        imgResized = util.resizeImage (image, 41, 22, g);       
        g.drawImage(imgResized, 256, 42, frame);     
        g.drawString(infoGetter.getCountry(identifier), 139, 56);
        
        g.drawString(infoGetter.getOccupation(identifier), 139, 84);
        
        isPortraitRendered = true;
    }
    
    public void renderBackground(Graphics2D g) {
        //Background Image
        if (gameScene == 1) {
            filePath = "Resources\\Backgrounds\\Gallery_UF_300x128.png";
            image = util.getImage(filePath);
            imgResized = util.resizeImage(image, width-137, height-150, g);
            g.drawImage(imgResized, 72,32, frame);
        }
        if (gameScene == 2) {
            filePath = "Resources\\Backgrounds\\Scene2_1440x810_UW2.png";
            image = util.getImage(filePath);
            g.drawImage(image, 0, -20, frame);
        }
        
        isBackgroundRendered = true;
    }
    
    public void renderBorder(Graphics2D g) {
        //Border Image
        filePath = "Resources\\Other\\BorderOne_1440x810.png";
        image = util.getImage(filePath);
        imgResized = util.resizeImage(image, 1434, 781, g);
        g.drawImage(imgResized, 0, 0, frame);        
        
        isBorderRendered = true;
    }
    
    public void renderItems(Graphics2D g) {
        //Items
        //IMPLEMENT WAY TO CHANGE ITEMS <------------------      
        
        //Item 1
        if (itemIdentifier == 0) { //Unsure if this work
            filePath = infoGetter.getItemImage(itemIdentifier);
            image = util.getImage(filePath);
            g.drawImage(image, 336, 20, frame);
            doItem1Exist = true;
        }
        //Item 2
        if (itemIdentifier == 1) {
            filePath = infoGetter.getItemImage(itemIdentifier);
            image = util.getImage(filePath);
            g.drawImage(image, 466, 20, frame);
            doItem2Exist = true;
        }
        //Item 3
        if (itemIdentifier == 2) {
            filePath = infoGetter.getItemImage(itemIdentifier);
            image = util.getImage(filePath);
            g.drawImage(image, 586, 20, frame);
            doItem3Exist = true;
        }
        //Item 4
        if (itemIdentifier == 3) {
            filePath = infoGetter.getItemImage(itemIdentifier);
            image = util.getImage(filePath);      
            g.drawImage(image, 716, 20, frame);
            doItem4Exist = true;
        }     
        
        isItemsRendered = true;
    }
    
    public void renderInventory(Graphics2D g) {
        //Inventory Screen UNFINISHED <---------------------
        g.setColor(Color.red);
        g.fillRect(100, 100, 100, 100);
        
        isInventoryRendered = true;
    }
    
    public void renderAreYouSure(Graphics2D g, String what) {        
        g.setFont(Arial_20);
        
        //Are you sure box image
        filePath = "Resources\\Other\\AreYouSure_1440x810_Brown.png";
        image = util.getImage(filePath);
        g.drawImage(image, 0, 0, frame);

        g.setColor(Color.white);
        g.drawString("Do you want to " + what + "?", width-860, height-445);
        
        isAreYouSureRendered = true;
    }
    
    //Use for something.
    public void renderTest(Graphics2D g) {
        g.drawString("GIF", 100 ,100);
        filePath = "Resources\\Other\\120430.gif";
        image = util.getAnimatedGif(filePath);
        g.drawImage(image, 50, 50, frame);       
    }
    
//  Does not work.  
//    public void renderTestAnimation(Graphics2D g) {
//        int xTestPos = 0; 
//        boolean is = true;
//
//        while (is && xTestPos < 1000) {
//            xTestPos += 1;
//            g.drawRect(xTestPos, width, width, height);
//            System.out.println(xTestPos);            
//        }
//    }
    
    //Mouse and Keyboard Inputs ->   
    //Keyboard inputs
    @Override
    public void actionPerformed(ActionEvent e) {  
        rendering.repaint();
    }
    
    @Override
    public void keyTyped(KeyEvent e) {       
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyID = e.getKeyCode();
        
        if(keyID == KeyEvent.VK_ESCAPE) {
            if (gameScene != 0 && showInventory == false && showAreYouSure == false) {
                gameScene = 0;
            }
            else if(gameScene == 0) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
            else if(showInventory == true) {
                showInventory = false;
            }
            else if(showAreYouSure == true) {
                showAreYouSure = false;
            }
        }
        
        //Intro Scene
        if(keyID == KeyEvent.VK_SPACE && gameScene == 0) {
            gameScene = -3; 
        }
        
        //Game Scene 1
        if (keyID == KeyEvent.VK_ENTER && gameScene == -3) {           
            gameScene = 1; 
        }
        
        //Select Charater
        if(keyID == KeyEvent.VK_Q && gameScene == 0) {
            gameScene = -1; 
        }
        
        //About
        if (keyID == KeyEvent.VK_E && gameScene == 0) {
            gameScene = -2; 
        }
        
        //Inventory     
        if (keyID == KeyEvent.VK_I) {
            if (gameScene == 1 && showInventory == false) {
                showInventory = true;    
            }
            else if (showInventory == true) {
                showInventory = false;
            }            
        }
        rendering.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {    
    }

    // > = Greater than
    // < = Less than
    
    //Mouse inputs
    @Override
    public void mouseClicked(MouseEvent e) {                
        //canInteract should be added.
        //Interactive stuff part.       
        if (canInteract == true) {
            int posX=e.getX();
            int posY=e.getY();

            
            System.out.println("Debug: " + "X-" + posX+", Y-" + +posY);            
            if (gameScene > 0) {
                if (gameScene ==1) {
                    if (isAreYouSureRendered == false) {               
                        //Paintings and other background elements.
                        if (posX>998 && posY>130 && posX<1170 && posY<477) { //Portrait Panting
                            currentSpeech = "It's a painting of the duke.";   
                            System.out.println("Debug: Duke Painting");
                        }
                        if (posX>732 && posY>136 && posX<964 && posY<292) { //Grey Tree Panting
                            currentSpeech = "It's some odd painting of a tree?";
                            System.out.println("Debug: Tree Painting");
                        }
                        if (posX>1204 && posY>129 && posX<1342 && posY<300) { //Round Painting
                            currentSpeech = "I don't really know what this is.";
                            System.out.println("Debug: Mirror Painting");
                        }

                        //Are you sure part
                        if (posX>475 && posY>146 && posX<700 && posY<613) {
                            System.out.println("Debug: AreYouSure");
                            reasonAreYouSure = "go through the door";
                            showAreYouSure = true;
                        }                
                    }
                }

                //Items part
                if (isItemsRendered == true) {
                    if (doItem1Exist == true && posX>315 && posY>32 && posX<430 && posY<123) { //Item 1
                        System.out.println("Debug: Item 1");
                        currentSpeech = "It's my " + infoGetter.getItem(itemIdentifier) + ".";
                    }
                    if (doItem2Exist == true && posX>430 && posY>32 && posX<556 && posY<123) { //Item 2
                        System.out.println("Debug: Item 2");
                        currentSpeech = "It's item 2!";
                    }
                    if (doItem3Exist == true && posX>556 && posY>32 && posX<682 && posY<123) { //Item 3
                        System.out.println("Debug: Item 3");
                        currentSpeech = "It's item 3!";            
                    }
                    if (doItem4Exist == true && posX>682 && posY>32 && posX<807 && posY<123) { //Item 4
                        System.out.println("Debug: Item 4");
                        currentSpeech = "It's item 4!";
                    }
                }   
            }
            //Are you sure part 2
            if (isAreYouSureRendered == true) {
                if (posX>575 && posY>430 && posX<685 && posY<490 && reasonAreYouSure.equals("go through the door")) {
                    //Yes
                    gameScene = 2;
                } 
                else if (posX>575 && posY>430 && posX<685 && posY<490 && reasonAreYouSure.equals("exit the game")) {
                    //Yes
                    gameScene = 0;
                }
                if (posX>760 && posY>430 && posX<870 && posY<490) {
                    //No
                    showAreYouSure = false;
                }
            }            
        }
        rendering.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {       
    }

    @Override
    public void mouseEntered(MouseEvent e) {   
//        int posX=e.getX();
//        int posY=e.getY();  
//        
//        if (posX>0 && posY>0 && posX<100 && posY<100) {
//            System.out.println("INSIDE");
//            //Use with booleans
//        } else {
//            System.out.println("OUTSIDE");
//            //Use with booleans
//        }
//        rendering.repaint();
    }

    @Override
    public void mouseExited(MouseEvent e) {  
        //Use with Entered above.
    }
}