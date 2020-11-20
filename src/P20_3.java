import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class P20_3 {
    static class account{
        double initialamout;
        double interestrate;
        int year;

        account(double amount, double rate, int year){
            initialamout = amount;
            interestrate = rate;
            this.year = year;
        }

        public account() {
            initialamout = 0;
            interestrate = 0;
            year = 0;
        }

        double getamount(){return initialamout;};
        double getrate(){return interestrate;}
        double getyear(){return year;}

        void setInitialamout(double amount){initialamout = amount;}
        void setInterestrate(double rate){interestrate = rate;}
        void setYear(int year){this.year = year;}

        double cauculate(){
            return initialamout*(1+interestrate/100);
        }
    }

    static class myFrame extends JFrame {
        final static int FRAME_WIDTH = 300;
        final static int FRAME_HEIGHT = 400;

        final static int AREA_ROW = 10;
        final static int AREA_COLUME = 30;
        account myaccount = new account();

        JLabel AmountLable;
        JLabel RateLable;
        JLabel yearLable;
        JTextField AmountextField;
        JTextField RatextField;
        JTextField YeartextField;
        JButton mybutton;
        JTextArea myTextArea;

        myFrame(){
            myTextArea = new JTextArea(AREA_ROW, AREA_ROW);
            myTextArea.setText("");
            myTextArea.setEditable(false);

            createTextfield();
            createButton();
            createPanel();

            setSize(FRAME_WIDTH, FRAME_HEIGHT);
        }

        void createTextfield(){
            AmountLable = new JLabel("Initial amount: " + myaccount.getamount());
            RateLable = new JLabel("Annual rate: " + myaccount.getrate());
            yearLable = new JLabel("Number of years: " + myaccount.getyear());
            final int FIELDWIDTH = 10;
            AmountextField = new JTextField(FIELDWIDTH);
            AmountextField.setText("1000.0");
            RatextField = new JTextField(FIELDWIDTH);
            RatextField.setText("5.0");
            YeartextField = new JTextField(FIELDWIDTH);
            YeartextField.setText("5");
        }

        void createPanel(){
            JPanel panel = new JPanel();
            panel.add(AmountLable);
            panel.add(AmountextField);
            panel.add(RateLable);
            panel.add(RatextField);
            panel.add(yearLable);
            panel.add(YeartextField);
            panel.add(mybutton);
            JScrollPane myScroll = new JScrollPane(myTextArea);
            panel.add(myScroll);
            add(panel);
        }

        class Addlistener implements ActionListener{

            @Override
            public void actionPerformed(ActionEvent e) {
                double amount = Double.parseDouble(AmountextField.getText());
                double rate = Double.parseDouble(RatextField.getText());
                int year = Integer.parseInt(YeartextField.getText());
                myaccount.setInitialamout(amount);
                myaccount.setInterestrate(rate);
                for(int i = 0; i < year; i ++){
                    myTextArea.append(String.format("%.3f", myaccount.cauculate()) + "\n");
                    myaccount.setInitialamout(myaccount.cauculate());
                }
            }
        }

        void createButton(){
            mybutton = new JButton("Cosume all");
            ActionListener listern = new Addlistener();
            mybutton.addActionListener(listern);
        }
    }

    public static void main(String[] args){
        JFrame frame = new myFrame();
        frame.setTitle("Mycars");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}