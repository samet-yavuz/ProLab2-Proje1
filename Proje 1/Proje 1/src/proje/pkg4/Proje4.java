package proje.pkg4;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Proje4 extends JFrame {
    JFrame f1 = new JFrame();
    JTextField lab1 = new JTextField();
    JTextField lab2 = new JTextField();
    Random r=new Random();
    int random=r.nextInt(5);
    int random2=r.nextInt(13);
    Location konum2 =new Location();
    JTextField textField = new javax.swing.JTextField();
    final java.util.ArrayList shortestPath = new java.util.ArrayList();
    static ArrayList<Integer> altin = new ArrayList<Integer>();
    static ArrayList<Integer> mantar = new ArrayList<Integer>();
    static  Proje4 view = new Proje4();
    static int control=1;
    Characters wg =new Characters(73);
    static ArrayList<Integer> silincekler = new ArrayList<Integer>();

    static int saniye =0;
    static int saniye2 =0;
    static int asilSaniye =0;
    static String iconPath;
    String[] a;
    String[] b;
    String[] c;
    int maze[][]=new int[11][13];
    Azman azman = new Azman("A064","azman","enemy","C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\azman.png");
    Gargamel gargamel = new Gargamel("G076","gargamel","Enemy","C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\gargamel.png");
    Gozluklu gozluklu = new Gozluklu("G152","gozluklu","Enemy","C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\gozluklu.png");
    Tembel tembel = new Tembel("T120","tembel","Player","C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\tembel.png");
    Characters sirine = new Characters("S089","sirine","Player","C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\sirine.png");
    int kapiSayaci=1;
    Players puan=new Players();
    


public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                view.setVisible(true);

            }
        });
        
   Zaman.start();
   nesneler();
   view.readFile();
}
      
public Proje4(){
        setTitle("Smurfs Game");
        setSize(1400, 925);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
public void backTo(){
    sirine.setYol(Dijikstra.printPath(Characters.enKisaYol(), Dijikstra.dijikstraAlgoritmasi(Characters.enKisaYol(),(azman.getX()/75)+(azman.getY()/75)*13),(azman.getX()/75)+(azman.getY()/75)*13 ,((konum2.getX()/75)+(konum2.getY()/75)*13)));
    sirine.setYol2(Dijikstra.printPath(Characters.enKisaYol(), Dijikstra.dijikstraAlgoritmasi(Characters.enKisaYol(),(gargamel.getX()/75)+(gargamel.getY()/75)*13),(gargamel.getX()/75)+(gargamel.getY()/75)*13 ,((konum2.getX()/75)+(konum2.getY()/75)*13)));
    if(azman.getX()-1==konum2.getX() && azman.getY()-1==konum2.getY()){
        puan.setSkor(puan.getSkor()-5); azman.setX(doorsLoc(azman.getDoor())%13*75+1);azman.setY(doorsLoc(azman.getDoor())/13*75+1);
    sirine.setYol(Dijikstra.printPath(Characters.enKisaYol(), Dijikstra.dijikstraAlgoritmasi(Characters.enKisaYol(),(azman.getX()/75)+(azman.getY()/75)*13),(azman.getX()/75)+(azman.getY()/75)*13 ,((konum2.getX()/75)+(konum2.getY()/75)*13)));
        if (puan.getSkor()<=0)endGame();
    }
    if((gargamel.getX()-1)==konum2.getX() && (gargamel.getY()-1==konum2.getY())){
        puan.setSkor(puan.getSkor()-15); gargamel.setX(doorsLoc(gargamel.getDoor())%13*75+1);gargamel.setY(doorsLoc(gargamel.getDoor())/13*75+1);kapiSayaci=0;
    sirine.setYol2(Dijikstra.printPath(Characters.enKisaYol(), Dijikstra.dijikstraAlgoritmasi(Characters.enKisaYol(),(gargamel.getX()/75)+(gargamel.getY()/75)*13),(gargamel.getX()/75)+(gargamel.getY()/75)*13 ,((konum2.getX()/75)+(konum2.getY()/75)*13)));
        if (puan.getSkor()<=0)endGame();
    }
}
public void endGame(){
    if(puan.getSkor()>0){
        f1.setSize(400,100);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.add(lab1);
        lab1.setBounds(50, 50, 500, 100);
        lab1.setFont(new Font("TimesRoman",Font.BOLD,25));
        lab1.setText("You Won !!!?!   \n"+"   Your Score:"+puan.PuaniGoster());f1.setVisible(true);lab1.setVisible(true);
        view.dispose();
    }
    else if(puan.getSkor()<=0){
        f1.setSize(400,100);
        f1.setLocationRelativeTo(null);
        f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f1.add(lab2);
        lab2.setBounds(50, 50, 550, 100);
        lab2.setFont(new Font("TimesRoman",Font.BOLD,25));
        lab2.setText("You Lose !!!?!\n");f1.setVisible(true);lab2.setVisible(true);
        view.dispose();
    }
   
}
    @Override
public void paint(Graphics g) {
        super.paint(g);

        g.translate(50, 50);
        
        // draw the maze
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[0].length; col++) {
                Color color;
                switch (maze[row][col]) {
                    case 1:
                        color = Color.WHITE;
                        break;
                    default:
                        color = Color.BLACK;
                        break;
                     
                }
                g.setColor(color);
                g.fillRect(75 * col, 75 * row, 75, 75);
                g.setColor(Color.BLACK);
                g.drawRect(75 * col, 75 * row, 75, 75);

            }
            g.setFont(new Font("TimesRoman",1,37));
                g.drawString("A",250,50);
                g.drawString("B",775,50);
                g.drawString("C",25,425);
                g.drawString("D",250,800);
                g.setFont(new Font("TimesRoman",1,40));
                g.drawString("Your Score : "+String.valueOf(puan.getSkor()),1000,175);
        }
        
        
        getContentPane().setLayout(null);

        
        Icon goal = new javax.swing.ImageIcon(sirine.getIkonYolu());
        goal.paintIcon(getContentPane(),g,976,526);
        
          for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                           altin.set(i, 0);altin.set(i+1, 0);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    } }
          if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }
        
        
        sirine.setYol(Dijikstra.printPath(Characters.enKisaYol(), Dijikstra.dijikstraAlgoritmasi(Characters.enKisaYol(),(azman.getX()/75)+(azman.getY()/75)*13),(azman.getX()/75)+(azman.getY()/75)*13 ,((konum2.getX()/75)+(konum2.getY()/75)*13)));
        sirine.setYol2(Dijikstra.printPath(Characters.enKisaYol(), Dijikstra.dijikstraAlgoritmasi(Characters.enKisaYol(),(gargamel.getX()/75)+(gargamel.getY()/75)*13),(gargamel.getX()/75)+(gargamel.getY()/75)*13 ,((konum2.getX()/75)+(konum2.getY()/75)*13)));
        g.setColor(new Color(255, 127, 0, 127));
    for(int i=0;i<sirine.getYol().size();i++)
    {
        g.fillRect((sirine.getYol().get(i)%13)*75+1,(sirine.getYol().get(i)/13)*75+1 , 73, 73);
    }
            g.setColor(new Color(188, 0, 0, 127));
    for(int i=0;i<sirine.getYol2().size();i++)
    {
        g.fillRect((sirine.getYol2().get(i)%13)*75+1,(sirine.getYol2().get(i)/13)*75+1 , 73, 73);
    }
        Icon e1 = new javax.swing.ImageIcon(gargamel.getIkonYolu());
        e1.paintIcon(getContentPane(),g,gargamel.getX()+15,gargamel.getY()+1);
        Icon e2 = new javax.swing.ImageIcon(azman.getIkonYolu());
        e2.paintIcon(getContentPane(),g,azman.getX()+1,azman.getY()+1);
        
       Icon p1 = new javax.swing.ImageIcon(iconPath);
        p1.paintIcon(getContentPane(),g,konum2.getX()+1,konum2.getY()+1); 

        
if(saniye>=random){
        Icon c1 = new javax.swing.ImageIcon("C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\coin.png");
        c1.paintIcon(getContentPane(),g,altin.get(0)+1,altin.get(1)+1);
        Icon c2 = new javax.swing.ImageIcon("C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\coin.png");
        c2.paintIcon(getContentPane(),g,altin.get(2)+1,altin.get(3)+1);
        Icon c3 = new javax.swing.ImageIcon("C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\coin.png");
        c3.paintIcon(getContentPane(),g,altin.get(4)+1,altin.get(5)+1);
        Icon c4 = new javax.swing.ImageIcon("C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\coin.png");
        c4.paintIcon(getContentPane(),g,altin.get(6)+1,altin.get(7)+1);
        Icon c5 = new javax.swing.ImageIcon("C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\coin.png");
        c5.paintIcon(getContentPane(),g,altin.get(8)+1,altin.get(9)+1);
}
if(saniye>random+5){
    for(int i=0;i<altin.size();i+=2){
    altin.set(i, 0);altin.set(i+1, 0);}
}
if(saniye>random2+7){
    mantar.set(0, 0);mantar.set(1, 0);
}
        if(saniye2>=random2){
        Icon m1 = new javax.swing.ImageIcon("C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\images\\mushroom.png");
        m1.paintIcon(getContentPane(),g,mantar.get(0)+1,mantar.get(1)+1);}
        
                g.setColor(Color.BLACK);
                g.fillRect(0,0,75,75);
   
if(saniye2==20){
    
    random=r.nextInt(5);random2=r.nextInt(13);
    saniye=0;saniye2=0;nesneler();
}}
public void azmanLoc(){
backTo();
if(sirine.getYol().size()>=2){
azman.setX((sirine.getYol().get(1)%13)*75+1);
azman.setY((sirine.getYol().get(1)/13)*75+1);
}}
public void gargamelLoc(){
kapiSayaci=1;backTo();
if(kapiSayaci==1){
if(sirine.getYol2().size()>=2){
gargamel.setX((sirine.getYol2().get(1)%13)*75+1);
gargamel.setY((sirine.getYol2().get(1)/13)*75+1);
if(sirine.getYol2().size()>2){
gargamel.setX((sirine.getYol2().get(2)%13)*75+1);
gargamel.setY((sirine.getYol2().get(2)/13)*75+1);}
}}}
public void readFile()
{
            try {
                File file =new File("C:\\Users\\yavuz\\Desktop\\NetBeansProjects\\Proje 4\\src\\proje\\pkg4\\harita.txt");
                Scanner reader = new Scanner(file);
                int i = 0;
                String p = reader.nextLine();String e1 = reader.nextLine();String e2 = reader.nextLine();
                for (;i<=3;) {
                    while (i != 4) {
                        p = p.replaceAll("\\p{Punct}", " ");
                        e1 = e1.replaceAll("\\p{Punct}", " ");
                        e2 = e2.replaceAll("\\p{Punct}", " ");
                        if(i%2==1){
                        a = p.split(" ", 8);
                        b = e1.split(" ", 8);
                        c = e2.split(" ", 8);

                        }
                        i++;
                    }
                }if(a[1].compareToIgnoreCase(gozluklu.getAd())==0)
                {
                    control=0;
                    iconPath=gozluklu.getIkonYolu();
                }
                else if(a[1].compareToIgnoreCase(tembel.getAd())==0){
                    control=1;
                    iconPath=tembel.getIkonYolu();
                }
                
                if(b[1].compareToIgnoreCase(azman.getAd())==0)
                {
                azman.setX(doorsLoc(b[3])%13*75+1);azman.setY(doorsLoc(b[3])/13*75+1);
                azman.setDoor(c[3]);
                }
                else if(b[1].compareToIgnoreCase(gargamel.getAd())==0){
                gargamel.setX(doorsLoc(b[3])%13*75+1);gargamel.setY(doorsLoc(b[3])/13*75+1);
                gargamel.setDoor(b[3]);
                }
                if(c[1].compareToIgnoreCase(azman.getAd())==0)
                {
                azman.setX(doorsLoc(c[3])%13*75+1);azman.setY(doorsLoc(c[3])/13*75+1);
                azman.setDoor(c[3]);
                }
                else if(c[1].compareToIgnoreCase(gargamel.getAd())==0){
                gargamel.setX(doorsLoc(c[3])%13*75+1);gargamel.setY(doorsLoc(c[3])/13*75+1);
                gargamel.setDoor(c[3]);
                }
                
                for (int row = 0; row < 11; row++) {
                    for (int col = 0; col < 13; col++) {
                        maze[row][col]=reader.nextInt();
                    }
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Proje4.class.getName()).log(Level.SEVERE, null, ex);
            }
}
public int doorsLoc(String door){
if(door.compareToIgnoreCase("A")==0){
    return 3;
}else if(door.compareToIgnoreCase("B")==0){
    return 10;
 }else if(door.compareToIgnoreCase("C")==0){
    return 65;
}else {
    return 133;
}
}
public static void nesneler(){
    
         Obj dene=new Obj();
         int x=0;
         int y=0;
       int sayamac=0;
        for (int i = 0; i < 10; i += 2) {
            x = dene.getX();
            y = dene.getY();

            if (!ara(x, y) ) {

                    i -= 2;

            } else {
                for (int j = 0; j <altin.size(); j+=2) {
                    if(altin.get(j)==x&&altin.get(j+1)==y)
                      sayamac++;
                }

                if(sayamac==0){
                    altin.add(i, x);
                    altin.add(i+1, y);

                }
                else {
                    sayamac=0;
                    i-=2;
                    continue;
                }


            }
        }
        x = dene.getX();
        y = dene.getY();
        int sayac=0;
        for (int g = 0; g <10 ; g++) {
            x = dene.getX();
            y = dene.getY();
            if(ara(x,y))
            {
                for (int j = 0; j <altin.size(); j+=2) {
                    if(altin.get(j)==x &&altin.get(j+1)==y)
                        sayac++;
                }

                if(sayac==0){
                    mantar.add(0, x);
                    mantar.add(1, y);
                    break;
                    
                }
                else{
                    sayac=0;
                    g-=1;
                    continue;
                }
            }
         }
        
}
 @Override
protected void processKeyEvent(KeyEvent ke) {
Gold gold=new Gold();
        if (ke.getID() != KeyEvent.KEY_PRESSED) {
            repaint();
            return;
        }

        // for double speed delete
        switch (control){
            case 1:
            switch (ke.getKeyCode()) {

            case KeyEvent.VK_RIGHT:
                    if ((konum2.getX() == 900) && (konum2.getY() == 525)) {
                        view.dispose();endGame(); 
                    }
                if (ara(konum2.getX() + 75, konum2.getY())) {
                konum2.setX(konum2.getX() + 75);
                    
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                           altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    } 
                }
                if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }azmanLoc();gargamelLoc();backTo();}
                break;
            case KeyEvent.VK_LEFT:
            if (ara(konum2.getX() - 75, konum2.getY())) {
                    konum2.setX(konum2.getX() - 75);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                             altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);;
                        }
                    }
                    if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
               azmanLoc();gargamelLoc();backTo();}
                break;
            case KeyEvent.VK_UP:
             if (ara(konum2.getX(), konum2.getY() - 75)) {
                    konum2.setY(konum2.getY() - 75);
                    
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
            azmanLoc();gargamelLoc();backTo();}    
                break;
            case KeyEvent.VK_DOWN:
              if (ara(konum2.getX(), konum2.getY() + 75)) {
                    konum2.setY(konum2.getY() + 75);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
                azmanLoc();gargamelLoc();backTo();}
                break;
            default:
                break;

        }
                break;
            case 0:
                        switch (ke.getKeyCode()) {

            case KeyEvent.VK_RIGHT:


                
                if (ara(konum2.getX() + 150, konum2.getY()) && ara(konum2.getX() + 75, konum2.getY())) {
                    konum2.setX(konum2.getX() + 150);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if((altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY())||(altin.get(i)==konum2.getX()-75 && altin.get(i+1)==konum2.getY())){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if((mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY())||(mantar.get(0)==konum2.getX()-75 && mantar.get(1)==konum2.getY())){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
               azmanLoc();gargamelLoc();backTo();}
               else if(ara(konum2.getX() + 75, konum2.getY())){
                        if(!ara(konum2.getX() + 150, konum2.getY())){
                            konum2.setX(konum2.getX() + 75);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
                azmanLoc();gargamelLoc();backTo();
                        }
                    }
                break;
            case KeyEvent.VK_LEFT:
            if (ara(konum2.getX() - 150, konum2.getY()) && ara(konum2.getX() - 75, konum2.getY())) {
                    konum2.setX(konum2.getX() - 150);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if((altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY())||(altin.get(i)==konum2.getX()+75 && altin.get(i+1)==konum2.getY())){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if((mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY())||(mantar.get(0)==konum2.getX()+75 && mantar.get(1)==konum2.getY())){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
                azmanLoc();gargamelLoc();backTo();}
            else if(ara(konum2.getX() - 75, konum2.getY())){
                        if(!ara(konum2.getX() - 150, konum2.getY())){
                            konum2.setX(konum2.getX() - 75);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
                azmanLoc();gargamelLoc();backTo();
                        }
                    }
                break;
            case KeyEvent.VK_UP:
             if (ara(konum2.getX(), konum2.getY() - 150) && ara(konum2.getX(), konum2.getY() - 75)) {
                    konum2.setY(konum2.getY() - 150);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if((altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY())||(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()+75)){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if((mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY())||(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()+75)){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
             azmanLoc();gargamelLoc();backTo();}
             
             else if(ara(konum2.getX(), konum2.getY() - 75)){
                        if(!ara(konum2.getX(), konum2.getY() - 150)){
                            konum2.setY(konum2.getY() - 75);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
                azmanLoc();gargamelLoc();backTo();
                        }
                    }
                break;
            case KeyEvent.VK_DOWN:
              if (ara(konum2.getX(), konum2.getY() + 150) && ara(konum2.getX(), konum2.getY() + 75)) {
                    konum2.setY(konum2.getY() + 150);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}
                        if((altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY())||(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()-75)){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if((mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY())||(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()-75)){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }
               azmanLoc();gargamelLoc();backTo();}
             else if(ara(konum2.getX(), konum2.getY() + 75)){
                        if(!ara(konum2.getX(), konum2.getY() + 150)){
                            konum2.setY(konum2.getY() + 75);
                    for(int i=0;i<altin.size();i+=2){if(saniye>random+5){altin.set(i, 0);altin.set(i+1, 0);}if(saniye>=random&&saniye<random+5){
                        if(altin.get(i)==konum2.getX() && altin.get(i+1)==konum2.getY()){
                              altin.set(i, 0);altin.set(i+1, 0);gold.setAltin(gold.getAltin()-1);
                            puan.setSkor(puan.getSkor()+5);
                        }
                    }
                    if(mantar.get(0)==konum2.getX() && mantar.get(1)==konum2.getY()){
                        mantar.set(0, 0);mantar.set(1, 0);
                        puan.setSkor(puan.getSkor()+50);
                        }}
                azmanLoc();gargamelLoc();backTo();
                        }
                    }
                break;
            default:
                break;

        }
                break;
        }
        if (((konum2.getX() == 900) && (konum2.getY() == 525))) {
         view.dispose();
         endGame();
                    }
  repaint();

    }
public static boolean ara(int xco, int yco) {

        switch (yco) {

            case 0:
                if (xco == 0 || xco == 75 || xco == 150 || xco == 225 || xco == 300 || xco == 375 || xco == 450 || xco == 525 || xco == 600 || xco == 675 || xco == 750 || xco == 825 || xco == 900)
                    return false;
                break;
            case 75*1:
                if (xco == 0 || xco ==5* 75 || xco ==12* 75)
                    return false;
                break;
            case 75*2:
                if (xco == 0 || xco ==2*75 || xco == 9*75 || xco == 10*75||xco==12*75 )
                    return false;
                break;
            case 75*3:
                if (xco == 0 || xco == 5*75 || xco == 75*7 || xco == 75*10|| xco ==75*12 )
                    return false;
                break;
            case 75*4:
                if (xco == 0 || xco == 2*75 || xco == 75*4 || xco == 75*5|| xco == 75*7|| xco == 75*9|| xco == 75*10|| xco ==75*12 )
                    return false;
                break;
            case 75*5:
                if (xco == 0 || xco == 2*75 ||xco == 75*7|| xco == 75*9|| xco ==75*12 )
                    return false;
                break;
            case 75*6:
                if (xco == 0 || xco == 2*75 ||xco == 75*3|| xco == 75*7|| xco ==75*12 )
                    return false;
                break;
            case 75*7:
                if (xco == 0 || xco == 2*75  ||  xco == 75*13)
                    return false;
                break;
            case 75*8:
                if (xco == 0 || xco == 2*75 ||xco == 75*4|| xco == 75*5|| xco == 75*6|| xco == 75*7|| xco == 75*8|| xco ==75*12 )
                    return false;
                break;
            case 75*9:
                if (xco == 0 ||  xco ==75*12)
                    return false;
                break;
            case 75*10:
                if (xco == 0 || xco == 75 || xco == 150 || xco == 225 || xco == 300 || xco == 375 || xco == 450 || xco == 525 || xco == 600 || xco == 675 || xco == 750 || xco == 825 || xco == 900)
                    return false;
                break;
        }
        return true;
    }

    public int getSaniye() {
        return saniye;
    }

    public void setSaniye(int saniye) {
        this.saniye = saniye;
    }
}

class  Zaman {

Proje4 prj=new Proje4();
    static Timer myTimer=new Timer();
    static TimerTask task=new TimerTask() {
        @Override
        public void run() {
            Proje4.saniye+=1;Proje4.saniye2+=1;Proje4.asilSaniye+=1;
            Proje4.view.repaint();
            System.out.println("Saniye: "+Proje4.asilSaniye);
        }
    };

    public static void start(){

        myTimer.scheduleAtFixedRate(task,1000,1000);
        
    }
}

