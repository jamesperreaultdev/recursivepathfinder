import java.util.Map;
import java.lang.*;
import java.util.TreeMap;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;
import java.util.*;

public class recursiviepathfindingmain{
    final int width = 1000;
    final int height = 700;

    static int lowestcost = 100000;
    static int [][] matrix = new int [10][10];
    public JFrame frame;
    public Canvas canvas;
    public JPanel panel;
    public BufferStrategy bufferStrategy;
    static String path = "";
    static TreeMap<Integer, String> paths = new TreeMap<Integer, String>();

    public static void main(String[] args){
        recursiviepathfindingmain ex =  new recursiviepathfindingmain();
    }

    public recursiviepathfindingmain() {
        int xpos = 0;
        int ypos = 0;
        int cost = 0;
        Random rand = new Random();
        for(int i = 0; i < 10;i++){
            for(int ii = 0; ii < 10;ii++){
                matrix[i][ii] = rand.nextInt(50);
                System.out.println(matrix[i][ii]);
            }
        }
        recursivepathfinder(matrix,xpos,ypos,cost,path,paths);
        Map.Entry<Integer,String> first = paths.entrySet().iterator().next();
        Integer key = first.getKey();
        System.out.print(paths.get(key));
        String dakey = paths.get(key);
        setUpGraphics(dakey);
    }
    //setting up graphics
    private void setUpGraphics(String p){
        frame = new JFrame("WOOOHOOOHO");

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(width,height));

        canvas = new Canvas();
        canvas.setBounds(0,0,width,height);
        canvas.setIgnoreRepaint(true);

        panel.add(canvas);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);

        canvas.createBufferStrategy(2);
        bufferStrategy = canvas.getBufferStrategy();
        canvas.requestFocus();

        run(p);

    }
    //pathfinding
    public static void recursivepathfinder(int[][] para,int xpos,int ypos,int cost, String path,TreeMap paths) {
        //check if it's over
        if (ypos == para.length - 1 && xpos == para[ypos].length - 1) {
            paths.put(cost, path);
            if (cost < recursiviepathfindingmain.lowestcost) {
                recursiviepathfindingmain.lowestcost = cost;
            }
            return;
        }


        //go diagonal right and check it out

        if (cost < recursiviepathfindingmain.lowestcost) {
            if (xpos < para[ypos].length - 1 && ypos < para.length - 1) {
                recursivepathfinder(para, xpos = xpos + 1, ypos = ypos + 1, cost +=  cost + para[ypos][xpos], path = path.concat("2"), paths);
            }
        }


        //go right and check it out
        if (cost < recursiviepathfindingmain.lowestcost) {
            if (xpos < para[ypos].length - 1) {
                recursivepathfinder(para, xpos = xpos + 1, ypos, cost += cost + para[ypos][xpos], path = path.concat("1"), paths);
            }
        }


        //go down and check it out

        if (cost < recursiviepathfindingmain.lowestcost) {
            if (ypos < para.length - 1) {
                System.out.println(para.length);
                recursivepathfinder(para, xpos, ypos = ypos + 1, cost += cost + para[ypos][xpos], path = path.concat("0"), paths);
            }
        }

    }
        //}

    private void render(String p){
        Graphics2D g = (Graphics2D) bufferStrategy.getDrawGraphics();
        g.drawRect(150,10, 600, 600);

        g.setColor(Color.BLACK);
        int xpossquare = 90;
        int ypossquare = 10;
        int xposcircle  = 150;
        int yposcircle = 10;
        for(int i = 0;i<matrix.length;i++){
            ypossquare = 10;
            xpossquare = xpossquare+60;
            for(int ii = 0; ii<matrix[i].length;ii++){
                g.drawRect(xpossquare,ypossquare,60,60);
                g.drawString(String.valueOf(matrix[i][ii]), xpossquare, ypossquare+60 );
                ypossquare = ypossquare+60;
            }
     }

        g.drawOval(xposcircle,yposcircle,60,60);
        for (int xd = 0; xd < p.length(); xd++){
            if(p.charAt(xd) == '2'){
                xposcircle+=60;
                yposcircle+=60;
                g.drawOval(xposcircle,yposcircle,60,60);
        }
            if (p.charAt(xd) == '1') {
                xposcircle+=60;
                g.drawOval(xposcircle,yposcircle,60,60);
            }
            if(p.charAt(xd) == '0'){
                yposcircle+=60;
                g.drawOval(xposcircle,yposcircle,60,60);

            }
    }


        g.dispose();
        bufferStrategy.show();
    }
    public void run(String p) {
        while(true){
            render(p);

        }

    }
}
