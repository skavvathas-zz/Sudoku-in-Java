/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ece326.hw3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class Homework3 implements ActionListener{
    JFrame frame;
    JMenuBar mb;
    JMenu x2;
    JMenuItem m1, m2, m3;
    JPanel board, menu, panel1, panel2, panel3, panel4, panel5, panel6, panel7, panel8, panel9;
    JButton [][]button = new JButton[9][9]; 
    JButton but;
    JButton menu1, menu2, menu3, menu4, menu5, menu6, menu7, menu8, menu9, menuE, menuU, menuR, br;
    JCheckBox menuCheck;
    int [][]array =  new int[9][9];
    int [][]sol = new int[9][9];
    int [][]arrayInitial = new int[9][9];
    int [][]undo = new int[70][3];
    char [][]arrayColor = new char[9][9];
    int xA, yA, un=0, sud=0;
    

    Homework3() {
        frame = new JFrame();
        board = new JPanel();
        menu = new JPanel();
        panel1 = new JPanel(new GridLayout(3,3));
        panel2 = new JPanel(new GridLayout(3,3));
        panel3 = new JPanel(new GridLayout(3,3));
        panel4 = new JPanel(new GridLayout(3,3));
        panel5 = new JPanel(new GridLayout(3,3));
        panel6 = new JPanel(new GridLayout(3,3));
        panel7 = new JPanel(new GridLayout(3,3));
        panel8 = new JPanel(new GridLayout(3,3));
        panel9 = new JPanel(new GridLayout(3,3));
        
        

        //getContentPane()
        frame.getContentPane().setBackground(Color.MAGENTA);
        frame.setTitle("Sudoku");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(510, 610);
        frame.setLayout(null);    
        frame.setVisible(true);
        
        board.setBounds(15, 50, 450, 300);
        board.setBackground(Color.MAGENTA);
        board.setVisible(true);
        frame.add(board);
        
        mb = new JMenuBar();
     
        // create a menu
        x2 = new JMenu("Menu");
  
        // create menuitems
        m1 = new JMenuItem("Easy");
        m1.addActionListener(this);
        m2 = new JMenuItem("Intermediate");
        m2.addActionListener(this);
        m3 = new JMenuItem("Expert");
        m3.addActionListener(this);
  
        // add menu items to menu
        x2.add(m1);
        x2.add(m2);
        x2.add(m3);
  
        // add menu to menu bar
        mb.add(x2);
  
        // add menubar to frame
        frame.setJMenuBar(mb);

        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){

                button[x][y] = new JButton ();
                button[x][y].setVisible(true);
                array[x][y] = 0;
                sol[x][y] = 0;
                arrayColor[x][y] = 'w';
                button[x][y].addActionListener(this);
                button[x][y].addKeyListener(new KeyListener(){
                    @Override
                    public void keyTyped(KeyEvent ke) {}

                    @Override
                    public void keyPressed(KeyEvent ke) {
                        keyword(ke);
                    }

                    @Override
                    public void keyReleased(KeyEvent ke) {}
                });
                button[x][y].setBackground(Color.WHITE);
                //board.add(button[x][y]);
                if(x < 3 && y < 3){
                    panel1.add(button[x][y]);
                }
                else if(x >= 3 && x < 6 && y < 3){
                    panel2.add(button[x][y]);
                }
                else if(x >= 6 && y < 3){
                    panel3.add(button[x][y]);
                }
                else if(x < 3 && y >= 3 && y < 6){
                    panel4.add(button[x][y]);
                }
                else if(x >= 3 && x < 6 && y >= 3 && y < 6){
                    panel5.add(button[x][y]);
                }
                else if(x >= 6 && y >= 3 && y < 6){
                    panel6.add(button[x][y]);
                }
                else if(x < 3 && y >= 6){
                    panel7.add(button[x][y]);
                }
                else if(x >= 3 && x < 6 && y >= 6){
                    panel8.add(button[x][y]);
                }
                else if(x >= 6 && y >= 6){
                    panel9.add(button[x][y]);
                }               
            }
        }
        
        board.add(panel1);        
        board.add(panel2);
        board.add(panel3);
        board.add(panel4);
        board.add(panel5);
        board.add(panel6);
        board.add(panel7);
        board.add(panel8);
        board.add(panel9);
        
        board.setLayout (new GridLayout(3,3,2,2));  // *******************
        
        // Create menu panel
        menu.setBackground(Color.CYAN);
        
        //menu.setLayout (new GridLayout(2,9));
        menu.setBounds(55, 365, 350, 130);
        
        menu1 = new JButton("1");
        menu1.addActionListener(this);
        menu.add(menu1);
        menu2 = new JButton("2");
        menu2.addActionListener(this);
        menu.add(menu2);
        menu3 = new JButton("3");
        menu3.addActionListener(this);
        menu.add(menu3);
        menu4 = new JButton("4");
        menu4.addActionListener(this);
        menu.add(menu4);
        menu5 = new JButton("5");
        menu5.addActionListener(this);
        menu.add(menu5);
        menu6 = new JButton("6");
        menu6.addActionListener(this);
        menu.add(menu6);
        menu7 = new JButton("7");
        menu7.addActionListener(this);
        menu.add(menu7);
        menu8 = new JButton("8");
        menu8.addActionListener(this);
        menu.add(menu8);
        menu9 = new JButton("9");
        menu9.addActionListener(this);
        menu.add(menu9);
        
        try {
            Image img = ImageIO.read(getClass().getResource("eraser.png"));
            Image newimg = img.getScaledInstance(26, 17,  java.awt.Image.SCALE_SMOOTH);
            menuE = new JButton();
            menuE.setIcon(new ImageIcon(newimg));
            menuE.setPreferredSize(new Dimension(42, 25));
            menuE.addActionListener(this);
            menu.add(menuE);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        try {
            Image img = ImageIO.read(getClass().getResource("undo.png"));
            Image newimg = img.getScaledInstance(26, 17,  java.awt.Image.SCALE_SMOOTH);
            menuU = new JButton();
            ImageIcon icon = new ImageIcon(newimg);
            //icon.setPreferredSize(new Dimension(25,25));
            menuU.setIcon(icon);
            menuU.setPreferredSize(new Dimension(42, 25));
            menuU.addActionListener(this);
            menu.add(menuU);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        try {
            Image img = ImageIO.read(getClass().getResource("rubik.png"));
            Image newimg = img.getScaledInstance(26, 17,  java.awt.Image.SCALE_SMOOTH);
            menuR = new JButton();
            menuR.setIcon(new ImageIcon(newimg));
            menuR.setPreferredSize(new Dimension(42, 25));
            menuR.addActionListener(this);
            menu.add(menuR);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
        menuCheck = new JCheckBox("Verify against solution");
        menuCheck.addActionListener(this);
        menu.add(menuCheck); 
        disable();
        frame.add(menu);
        
    }
    
    // Make Yellow the buttons with the same value with num (we click on it)
    public void makeYellow(int num){
        
        for(int x=0; x < 9; x++){
            for(int y = 0; y < 9; y++){
                if(array[x][y] == num){
                    if(arrayColor[x][y] == 'w'){
                        arrayColor[x][y] = 'a'; // white(before)-yellow(now)
                    }
                    else if(arrayColor[x][y] == 'g'){
                        arrayColor[x][y] = 'b'; // grey(before)-yellow(now)
                    }
                    
                    button[x][y].setBackground(Color.yellow);
                }
            }
        }
        
    }
    
    public void ifYellow(){
        for(int x=0; x < 9; x++){
            for(int y = 0; y < 9; y++){
               
                if(arrayColor[x][y] == 'a' || arrayColor[x][y] == 'c' || arrayColor[x][y] == 'k'){
                    arrayColor[x][y] = 'w'; // white(before)-yellow(now) || white(before)-red(now)
                    button[x][y].setBackground(Color.white);
                }
                else if(arrayColor[x][y] == 'b' || arrayColor[x][y] == 'd'){
                    arrayColor[x][y] = 'g'; // grey(before)-yellow(now) || grey(before)-red(now)
                    button[x][y].setBackground(Color.lightGray);
                }
            }
        }
    }
    
    public int canIAdd(int x, int y, int num){
        int k = 0;
        
        for(int i=0; i < 9; i++){
            if(array[x][i] == num){
                button[x][i].setBackground(Color.RED);
                if(arrayColor[x][i] == 'w'){
                    arrayColor[x][i] = 'c'; // white(before)-red(now)
                }
                else if(arrayColor[x][i] == 'g'){
                    arrayColor[x][i] = 'd'; // grey(before)-red(now)
                }
                k++;
            }
        }
        
        for(int i=0; i < 9; i++){
            if(array[i][y] == num){
                button[i][y].setBackground(Color.RED);
                if(arrayColor[i][y] == 'w'){
                    arrayColor[i][y] = 'c'; // white(before)-red(now)
                }
                else if(arrayColor[i][y] == 'g'){
                    arrayColor[i][y] = 'd'; // grey(before)-red(now)
                }
                k++;
            }
        }
        
        if(box(x,y,num) == 1){
            return 1;
        }
        
        return k;
    }
    
    public int box(int x, int y, int num){
        
        if(x < 3 && y < 3){
            if(boxIsRed(0,0,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x >= 3 && x < 6 && y < 3){
            if(boxIsRed(3,0,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x >= 6 && y < 3){
            if(boxIsRed(6,0,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x < 3 && y >= 3 && y < 6){
            if(boxIsRed(0,3,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x >= 3 && x < 6 && y >= 3 && y < 6){
            if(boxIsRed(3,3,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x >= 6 && y >= 3 && y < 6){
            if(boxIsRed(6,3,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x < 3 && y >= 6){
            if(boxIsRed(0,6,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x >= 3 && x < 6 && y >= 6){
            if(boxIsRed(3,6,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        else if(x >= 6 && y >= 6){
            if(boxIsRed(6,6,num) == 1){
                return 1;
            }
            else{
                return 0;
            }
        }
        
        return 0;
    }
    
    public boolean Solver(int width, int height){
        int x, y, num;
        
        for(y = 0; y < width; y++){
            for(x = 0; x < height; x++){
                if(sol[x][y] == 0){
                    for(num = 1; num <= 9; num++){ 
                        if(isOkay(x, y, num)){
                            sol[x][y] = num;

                            if(Solver(width, height)){
                                return true;
                            } 
                            else{ 
                                sol[x][y] = 0;
                            }
                       }
                    }
                return false;
                }
            }
        }
        return true;
    }
    
    private boolean isInBox(int x, int y, int num){
        int i, j, k, h;

        k = x - x%3;
        h = y - y%3;
        
        for(i = h; i < h+3; i++){
            for(j = k; j < k+3; j++){
                if(sol[x][y] == num){
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isInColumn(int col, int number){
        int i;
        
        for(i = 0; i < 9; i++){
            if(sol[col][i] == number){
                return true;
            }
        }

        return false;
    }
    
    private boolean isInRow(int row, int num){
        int i;
        
        for(i = 0; i < 9; i++){
            if(sol[i][row] == num){
                return true;
            }
        }
        return false;
    }
	
    private boolean isOkay(int x, int y, int number){
        return !isInRow(y, number)  &&  !isInColumn(x, number)  &&  !isInBox(x, y, number);
    }
    
    /*public int[][] solvedSodoku(){
        return array;
    }*/

    public void print(){
        int x, y;
        
        for(y = 0; y < 9; y++) {
            for(x = 0; x < 9; x++) {
                System.out.print(" " + sol[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public int boxIsRed(int x, int y, int num){
        int i, j;
        
        for(i = x; i < x+3; i++){
            for(j = y; j < y+3; j++){
                if(array[i][j] == num){
                    button[i][j].setBackground(Color.red);
                    if(arrayColor[i][j] == 'w'){
                        arrayColor[i][j] = 'c'; // white(before)-red(now)
                    }
                    else if(arrayColor[i][j] == 'g'){
                        arrayColor[i][j] = 'd'; // grey(before)-red(now)
                    }
                    return 1;
                }
            }
        }
        
        return 0;
    }

    public StringBuilder urlTostring(URL url) throws IOException{
        int i=0;
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder cont = new StringBuilder();
        String inputLine;

        try{
            while((inputLine = in.readLine()) != null) {
                if(i != 0){
                    cont.append(" ");
                }
                cont.append(inputLine);
                i++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return cont;
        
    }
    
    public void enable(){
        menu1.setEnabled(true);
        menu2.setEnabled(true);
        menu3.setEnabled(true);
        menu4.setEnabled(true);
        menu5.setEnabled(true);
        menu6.setEnabled(true);
        menu7.setEnabled(true);       
        menu8.setEnabled(true);
        menu9.setEnabled(true);
        menuU.setEnabled(true);
        menuE.setEnabled(true);
        menuR.setEnabled(true);
        menuCheck.setEnabled(true);
    }
    
    public void disable(){
        menu1.setEnabled(false);
        menu2.setEnabled(false);
        menu3.setEnabled(false);
        menu4.setEnabled(false);
        menu5.setEnabled(false);
        menu6.setEnabled(false);
        menu7.setEnabled(false);       
        menu8.setEnabled(false);
        menu9.setEnabled(false);
        menuU.setEnabled(false);
        menuE.setEnabled(false);
        menuR.setEnabled(false);
        menuCheck.setEnabled(false);
    }
    
    public static void main(String[] args) throws MalformedURLException, IOException {
        
        Homework3 hom = new Homework3();
    }

    @Override
    @SuppressWarnings("UseSpecificCatch")
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == m1 || e.getSource() == m2 || e.getSource() == m3){
            StringBuilder cont = null;
            int i = 0;
            
            if(e.getSource() == m1){
                try {
                    URL url = new URL("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=easy");
                    System.out.println("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=easy");
                    enable();
                    sud = 0;
                    cont = urlTostring(url);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else if(e.getSource() == m2){
                try {
                    URL url = new URL("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=intermediate");
                    System.out.println("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=intermediate");
                    enable();
                    sud = 0;
                    cont = urlTostring(url);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            else{
                try {
                    URL url = new URL("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=expert");
                    System.out.println("http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=expert");
                    enable();
                    sud = 0;
                    cont = urlTostring(url);
                } catch (MalformedURLException ex) {
                    Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Homework3.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            System.out.println(cont);
            for(int y = 0; y < 9; y++){
                
                for(int x = 0; x < 9; x++){
                    if(cont.charAt(i) == '.' || cont.charAt(i) == ' '){
                        arrayInitial[x][y] = 0;
                        array[x][y] = 0;
                        sol[x][y] = 0;
                        button[x][y].setText(" ");
                        button[x][y].setBackground(Color.WHITE);
                    }
                    else{
                        char str;
                        str = cont.charAt(i);
                        arrayInitial[x][y] = Character.getNumericValue(str);
                        sud++;
                        array[x][y] = Character.getNumericValue(str);
                        sol[x][y] = Character.getNumericValue(str);
                        arrayColor[x][y] = 'g'; //grey
                        button[x][y].setText(Character.toString(str));
                        button[x][y].setBackground(Color.lightGray);
                        //button[x][y] = new JButton(str.toString());
                    }
                    
                    //button[x][y]
                    i++;
                }
                i = (y+1)*9+(y+1);
            }
           
        }
        
        if(e.getSource() == menu1){
            ifYellow();
            if(canIAdd(xA, yA, 1) == 0){
                undo[un][0] = 1;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("1");
                array[xA][yA] = 1;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        if(e.getSource() == menu2){
            ifYellow();
            if(canIAdd(xA, yA, 2) == 0){
                undo[un][0] = 2;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("2");
                array[xA][yA] = 2;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
             
        }
        if(e.getSource() == menu3){
            ifYellow();
            if(canIAdd(xA, yA, 3) == 0){
                undo[un][0] = 3;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("3");
                array[xA][yA] = 3;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
            
        }
        if(e.getSource() == menu4){
            ifYellow();
            if(canIAdd(xA, yA, 4) == 0){
                undo[un][0] = 4;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("4");
                array[xA][yA] = 4;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        if(e.getSource() == menu5){
            ifYellow();
            if(canIAdd(xA, yA, 5) == 0){
                undo[un][0] = 5;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("5");
                array[xA][yA] = 5;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        if(e.getSource() == menu6){
            ifYellow();
            if(canIAdd(xA, yA, 6) == 0){
                undo[un][0] = 6;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("6");
                array[xA][yA] = 6;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        if(e.getSource() == menu7){
            if(canIAdd(xA, yA, 7) == 0){
                undo[un][0] = 7;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("7");
                array[xA][yA] = 7;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        if(e.getSource() == menu8){
            ifYellow();
            if(canIAdd(xA, yA, 8) == 0){
                undo[un][0] = 8;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("8");
                array[xA][yA] = 8;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        if(e.getSource() == menu9){
            ifYellow();
            if(canIAdd(xA, yA, 9) == 0){
                undo[un][0] = 9;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("9");
                array[xA][yA] = 9;
                sud++;
                ifYellow(); 
            }
            but.setBackground(Color.WHITE); 
        }
        if(e.getSource() == menuE){
            if(sud > 0){
                but.setText(" ");
                array[xA][yA] = 0;
                sud--;
                ifYellow();
                but.setBackground(Color.WHITE); 
            }
        }
        if(e.getSource() == menuU){ // undo
            int xU, yU;
            ifYellow();
            if(un > 0){
                un--;
                xU = undo[un][1];
                yU = undo[un][2];
                array[xU][yU] = 0;
                button[xU][yU].setText(" ");
            }
        }
        if(e.getSource() == menuR){
            ifYellow();
            if(Solver(9,9)) {
                System.out.println("Sudoku solved");
                print();
                for(int y = 0; y < 9; y++){
                    for(int x = 0; x < 9; x++){
                        char a = (char)(sol[x][y] + '0');
                        button[x][y].setText(Character.toString(a));
                    }
                }
                disable();
            } 
            else{
                System.out.println("Unsolvable");
            }
        }
        if(e.getSource() == menuCheck){
            if(Solver(9,9)) {
                System.out.println("Sudoku solved");
                //sol = solve.solvedSodoku();
                print();
                for(int y = 0; y < 9; y++){
                    for(int x = 0; x < 9; x++){
                        if(array[x][y] != sol[x][y] && array[x][y] != 0){
                            button[x][y].setBackground(Color.BLUE);
                            arrayColor[x][y] = 'k'; // blue , k-->blue
                        }
                    }
                }
            } 
            else{
                System.out.println("Unsolvable");
            }
        }        
        
        int num = -1;
        for(int y = 0; y < 9; y++){
            for(int x = 0; x < 9; x++){
                
                if(e.getSource() == button[x][y]){
                    ifYellow();
                    but = button[x][y];
                    xA = x;
                    yA = y;
                    num = array[x][y];
                    if(num != 0){
                        makeYellow(num);
                    }else{
                        button[x][y].setBackground(Color.YELLOW); 
                        arrayColor[x][y] = 'a';
                    }
                    break;
                }
                //if()
            }
        }
    }
    
    public void keyword(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_1){
            ifYellow();
            if(canIAdd(xA, yA, 1) == 0){
                undo[un][0] = 1;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("1");
                array[xA][yA] = 1;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_2){
            ifYellow();
            if(canIAdd(xA, yA, 2) == 0){
                undo[un][0] = 2;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("2");
                array[xA][yA] = 2;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_3){
            ifYellow();
            if(canIAdd(xA, yA, 3) == 0){
                undo[un][0] = 3;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("3");
                array[xA][yA] = 3;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_4){
            ifYellow();
            if(canIAdd(xA, yA, 4) == 0){
                undo[un][0] = 4;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("4");
                array[xA][yA] = 4;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_5){
            ifYellow();
            if(canIAdd(xA, yA, 5) == 0){
                undo[un][0] = 5;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("5");
                array[xA][yA] = 5;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_6){
            ifYellow();
            if(canIAdd(xA, yA, 6) == 0){
                undo[un][0] = 6;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("6");
                array[xA][yA] = 6;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_7){
            ifYellow();
            if(canIAdd(xA, yA, 7) == 0){
                undo[un][0] = 7;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("7");
                array[xA][yA] = 7;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_8){
            ifYellow();
            if(canIAdd(xA, yA, 8) == 0){
                undo[un][0] = 8;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("8");
                array[xA][yA] = 8;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_9){
            ifYellow();
            if(canIAdd(xA, yA, 9) == 0){
                undo[un][0] = 9;
                undo[un][1] = xA;
                undo[un][2] = yA;
                un++;
                but.setText("9");
                array[xA][yA] = 9;
                sud++;
                ifYellow();
            }
            but.setBackground(Color.WHITE); 
        }
        
        if(e.getKeyCode() == KeyEvent.VK_BACK_SPACE || e.getKeyCode() == KeyEvent.VK_DELETE){
            if(sud > 0){
                but.setText(" ");
                array[xA][yA] = 0;
                sud--;
                ifYellow();
                but.setBackground(Color.WHITE); 
            }
        }
        
    }

}


