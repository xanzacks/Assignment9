import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import java.util.Stack;

public class P20_1 {
    public static class DrawRectangle extends JComponent{
        int x;
        int y;

        void setxy(){
            Random rand = new Random();
            x = rand.nextInt(getWidth());
            y = rand.nextInt(getHeight());
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g;
            if (x == 0 && y == 0) {
                setxy();
            }
            Rectangle2D.Double rectangle = new Rectangle2D.Double(x, y, 30, 30);
            g2.draw(rectangle);
            g2.setColor(Color.PINK);
            g2.fill(rectangle);
        }
    }

    public static void main(String[] args){
        Stack<DrawRectangle> myRecs = new Stack<DrawRectangle>();
        final int width = 300;
        final int height = 400;
        JMenuItem increase, decrease;
        increase = new JMenuItem("increase");
        decrease = new JMenuItem("decrease");
        JFrame frame = new JFrame("Ellipse");
        frame.setSize(width,height);
        JPanel panel = new JPanel();
        panel.setSize(width, 10);
        JSlider slider = new JSlider(0, 50, 0);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int num = slider.getValue();
                int size = myRecs.size();
                if(num > size){
                    for(int i = 0; i < num - size; i++){
                        DrawRectangle rectangle = new DrawRectangle();
                        myRecs.push(rectangle);
                    }
                    for(DrawRectangle temp: myRecs){
                        panel.add(temp);
                        frame.add(panel);
                        frame.add(temp);
                        frame.setVisible(true);
                    }
                }
                else{
                    for(int i = 0; i < size - num; i++){
                        frame.remove(myRecs.pop());
                    }
                    frame.repaint();
                }
            }
        });
        panel.add(slider);
        JMenuBar Menubar = new JMenuBar();
        JMenu operate = new JMenu("operation");
        operate.add(increase);
        operate.add(decrease);
        Menubar.add(operate);
        frame.add(Menubar);
        //panel.add(slider);
        frame.getContentPane().add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setJMenuBar(Menubar);
    }
}
