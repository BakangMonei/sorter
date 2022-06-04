/**
 * GUI Class
 * Sorting Method
 * Counting method
 * Timer
 * Reading using Buffered reader
 * Writing using File writer
 */
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.StringTokenizer;

public class Sort extends JPanel {
    private JButton btnSort;
    private JButton btncnt;
    private JButton displayWords;

    private JTextField searchtxt;
    private JButton search;
    private JTextField countTxt;
    private JTextArea display;
    private JButton btnSave;
    private JButton btnExit;
    private JButton btnStop;
    private JButton btnReset;
    private JLabel hr;
    private JLabel min;
    private JLabel mill;
    private JLabel secs;
    private JLabel jcomp16;
    private JLabel jcomp17;
    private JLabel jcomp18;
    private JLabel jcomp19;
    static int mills = 0;
    static int minutes = 0;
    static int hrs = 0;
    static int sec = 0;
    static boolean state = true;

    public Sort() {
        //construct components
        btnSort = new JButton("Sort");
        btncnt = new JButton("Count Words");
        displayWords = new JButton("Open File");
        search = new JButton("Search");
        searchtxt = new JTextField(5);
        countTxt = new JTextField(5);
        display = new JTextArea(5, 5);
        btnSave = new JButton("Save");
        btnExit = new JButton("Exit");
        btnStop = new JButton("Stop");
        btnReset = new JButton("Reset");
        hr = new JLabel("00");
        min = new JLabel("00");
        mill = new JLabel("00");
        secs = new JLabel("00");
        jcomp16 = new JLabel("hr");
        jcomp17 = new JLabel("min");
        jcomp18 = new JLabel("sec");
        jcomp19 = new JLabel("milli");

        //adjust size of the frame and set layout
        setPreferredSize(new Dimension(701, 552));
        setLayout(null);

        //adding components
        add(btnSort);
        add(btncnt);
        add(displayWords);
        add(searchtxt);
        add(search);
        add(countTxt);
        add(display);
        add(btnSave);
        add(btnExit);
        add(btnStop);
        add(btnReset);
        add(hr);
        add(min);
        add(mill);
        add(secs);
        add(jcomp16);
        add(jcomp17);
        add(jcomp18);
        add(jcomp19);

        //set component bounds (only needed by Absolute Positioning)
        btnSort.setBounds(495, 305, 130, 40);
        btncnt.setBounds(55, 65, 110, 30);
        displayWords.setBounds(495, 210, 130, 40);
        searchtxt.setBounds(100, 10, 500, 30);
        search.setBounds(10, 10, 80, 30);
        countTxt.setBounds(180, 65, 230, 30);
        display.setBounds(55, 100, 355, 435);
        btnSave.setBounds(495, 390, 130, 45);
        btnExit.setBounds(495, 480, 130, 45);
        btnStop.setBounds(430, 125, 90, 30);
        btnReset.setBounds(565, 125, 90, 30);
        hr.setBounds(465, 65, 30, 25);
        min.setBounds(535, 65, 35, 25);
        mill.setBounds(660, 65, 35, 25);
        secs.setBounds(600, 65, 30, 25);
        jcomp16.setBounds(465, 95, 25, 20);
        jcomp17.setBounds(535, 90, 30, 25);
        jcomp18.setBounds(600, 90, 25, 25);
        jcomp19.setBounds(660, 90, 30, 20);

        search.setEnabled(false);
        searchtxt.disable();


        btnExit.addActionListener(e ->
                System.exit(0));

        btncnt.addActionListener(e -> {
            if (display.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Please Select Text File");
            } else {
                try {
                    String line;
                    int count = 0;
                    BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\cse20-018\\Desktop\\Sorter\\Sort.txt"));//reading from a text file

                    while ((line = br.readLine()) != null) {
                        StringTokenizer st = new StringTokenizer(line, ",");
                        while (st.hasMoreTokens()) {//<<--------checks if there are values in nextline

                            count++;
                            st.nextToken();
                        }
                        countTxt.setText("There are " + count + " words in a file");//displaying the total number of words in a textfile

                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        });

        displayWords.addActionListener(e -> {

            JFileChooser j = new JFileChooser();
            j.showOpenDialog(null);
            File f = j.getSelectedFile();
            String fileName = f.getAbsolutePath();

            try {
                FileReader fr = new FileReader(fileName);
                BufferedReader br = new BufferedReader(fr);
                display.read(br, null);
                br.close();
                display.requestFocus();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });

        btnSave.addActionListener(e -> {
            try {

                if (display.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Text Area Empty");
                } else {

                    FileWriter fw = new FileWriter("C:\\Users\\cse20-018\\Desktop\\Sorter\\SortedFile.txt");//<<write to text file
                    BufferedWriter br2 = new BufferedWriter(fw);
                    display.write(br2);
                    br2.close();

                    JOptionPane.showMessageDialog(null, "File saved in Desktop as SortedFile.txt");//<<---show message
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        });

        btnSort.addActionListener(e -> {
            sort();// call sort method
        });

        btnReset.addActionListener(e -> {
            state = false;
            mills = 0;
            hrs = 0;
            sec = 0;
            minutes = 0;

            hr.setText("00 : ");
            min.setText("00 : ");
            secs.setText("00 :");
            mill.setText("00 ");

        });
    }
    /*
    Sort method
     */
    public void sort() {
        String[] words = display.getText().split("\n");
        if (display.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Text Area Empty");

        } else if (words.length < 20) {
            JOptionPane.showMessageDialog(null, "Words less than 20");//display message if words are less than 20 in a file

        } else {
            timer(); //call the start timer method
            display.setText(""); // clear the textfield so that we can display the sorted words
            /**bubble sorting algorithm <------
             *
             * **/
            for (int x = 0; x < words.length; x++) {
                for (int y = 0; y < words.length - 1; y++) {
                    if (words[y].compareTo(words[y + 1]) > 0) {
                        String a = words[y];
                        words[y] = words[y + 1];
                        words[y + 1] = a;
                    }
                }
            }
            int i = 0;
            while (i < 20) { //<--print 20 words
                display.append(words[i] + "\n");//display sorted words <---- in textfield
                i++;
            }
            stop(); // call the stop timer method to stop the counter
        }
    }
    public void stop() {
        state = false;
    }
    public void timer() {
        state = true;
        Thread t = new Thread() {

            public void run() {
                for (; ; ) {

                    if (state) {

                        try {
                            sleep(1);
                            if (mills > 1000) {
                                mills = 0;
                                sec++;
                            }
                            if (sec > 60) {
                                mills = 0;
                                sec = 0;
                                minutes++;
                            }
                            if (minutes > 60) {
                                mills = 0;
                                sec = 0;
                                minutes = 0;
                                hrs++;
                            }
                            mill.setText(" : " + mills);
                            mills++;

                            secs.setText(" : " + sec);
                            min.setText(" : " + minutes);
                            hr.setText("" + hrs);

                        } catch (Exception e) {

                        }


                    } else {

                        break;
                    }
                }

            }
        };
        t.start();
    }

}
