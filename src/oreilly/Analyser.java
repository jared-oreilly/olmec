/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author jared.oreilly
 */
public class Analyser
{

    private ArrayList<Report> reports;
    private ArrayList<Phase> phases;
    private int numReports;
    private int numPhases;
    private OlmecUI parent;
    //private String toDisplay;
    private boolean[] display;
    private Color[] colArr;
    private Color[] colArro;
    //private String[] displayDescArr;
    private final int numDisplayOptions = 5;
    private final int x = 200, y = 200;
    private int min, max;

    public Analyser(String filename, String toDisplay, OlmecUI g) throws FileNotFoundException
    {
        reports = new ArrayList<Report>();
        phases = new ArrayList<Phase>();
        numReports = 0;
        numPhases = 0;
        parent = g;
        //make boolean array to check if display or not
        display = new boolean[numDisplayOptions];
        for (int i = 0; i < numDisplayOptions; i++)
        {
            display[i] = false;
            if (toDisplay.contains(i + ""))
            {
                display[i] = true;
            }
        }
        colArr = new Color[]
        {
            Color.red, Color.blue, new Color(0, 153, 0), new Color(255, 102, 0), new Color(102, 0, 153), Color.orange
        };
        colArro = new Color[]
        {
            new Color(255, 0, 0, 127), new Color(0, 0, 255, 127), new Color(0, 153, 0, 127), new Color(255, 102, 0, 127), new Color(102, 0, 153, 127), Color.orange
        };
//        displayDescArr = new String[]
//        {
//            "Min", "Median", "P95", "P99", "Max"
//        };

        //put file into arraylist
        Scanner scFile = new Scanner(new File("../../mayan/Mayan/gen/runs/" + filename + ".txt"));
        ArrayList<String> compArr = new ArrayList<String>();
        while (scFile.hasNextLine())
        {
            compArr.add(scFile.nextLine());
        }
        scFile.close();

        //print out arraylist
//            for (int i = 0; i < compArr.size(); i++)
//            {
//                System.out.println(compArr.get(i));
//            }
        parent.appendToFeedback("Starting report capture:");
        //extract report info
        for (int i = 0; i < compArr.size(); i++)
        {
            try
            {
                if (compArr.get(i).substring(0, 6).equals("Report"))
                {
                    String h = compArr.get(i).trim();
                    //System.out.println("---" + h);
                    i++;
                    String sl = compArr.get(i).trim();
                    sl = sl.substring(21);
                    //System.out.println("---" + sl);
                    i++;
                    String sc = compArr.get(i).trim();
                    sc = sc.substring(21);
                    //System.out.println("---" + sc);
                    i++;
                    String rc = compArr.get(i).trim();
                    rc = rc.substring(21);
                    //System.out.println("---" + rc);
                    i++;
                    String rps = compArr.get(i).trim();
                    //System.out.println(rps.indexOf(":")+2);
                    rps = rps.substring(10);
                    //System.out.println("---" + rps);
                    i++;
                    i++;
                    String min = compArr.get(i).trim();
                    min = min.substring(5);
                    //System.out.println("---" + min);
                    i++;
                    String max = compArr.get(i).trim();
                    max = max.substring(5);
                    //.out.println("---" + max);
                    i++;
                    String med = compArr.get(i).trim();
                    med = med.substring(8);
                    //System.out.println("---" + med);
                    i++;
                    String p95 = compArr.get(i).trim();
                    p95 = p95.substring(5);
                    //System.out.println("---" + p95);
                    i++;
                    String p99 = compArr.get(i).trim();
                    p99 = p99.substring(5);
                    //System.out.println("---" + p99);
                    i++;
                    String codes = "";
                    i++;
                    String nextLine = compArr.get(i).trim();
                    while (!nextLine.equals(""))
                    {
                        codes += nextLine + ",";
                        i++;
                        nextLine = compArr.get(i).trim();
                    }
                    codes = codes.substring(0, codes.length() - 1);
                    //System.out.println(codes);
                    Report temp = new Report(h, Integer.parseInt(sl), Integer.parseInt(sc), Integer.parseInt(rc), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), codes);
                    reports.add(temp);
                    numReports++;
                    //System.out.println(temp);

                } else if (compArr.get(i).substring(0, 14).equals("Summary report"))
                {
                    String h = compArr.get(i).trim();
                    //System.out.println("---" + h);
                    i++;
                    String sl = compArr.get(i).trim();
                    sl = sl.substring(21);
                    //System.out.println("---" + sl);
                    i++;
                    String sc = compArr.get(i).trim();
                    sc = sc.substring(21);
                    //System.out.println("---" + sc);
                    i++;
                    String rc = compArr.get(i).trim();
                    rc = rc.substring(21);
                    //System.out.println("---" + rc);
                    i++;
                    String rps = compArr.get(i).trim();
                    //System.out.println(rps.indexOf(":")+2);
                    rps = rps.substring(10);
                    //System.out.println("---" + rps);
                    i++;
                    i++;
                    String min = compArr.get(i).trim();
                    min = min.substring(5);
                    //System.out.println("---" + min);
                    i++;
                    String max = compArr.get(i).trim();
                    max = max.substring(5);
                    //.out.println("---" + max);
                    i++;
                    String med = compArr.get(i).trim();
                    med = med.substring(8);
                    //System.out.println("---" + med);
                    i++;
                    String p95 = compArr.get(i).trim();
                    p95 = p95.substring(5);
                    //System.out.println("---" + p95);
                    i++;
                    String p99 = compArr.get(i).trim();
                    p99 = p99.substring(5);
                    //System.out.println("---" + p99);
                    i++;

                    String scenarioCounts = "";
                    i++;
                    String nextLineScen = compArr.get(i).trim();
                    while (!nextLineScen.equals("Codes:"))
                    {
                        scenarioCounts += nextLineScen + ",";
                        i++;
                        nextLineScen = compArr.get(i).trim();
                    }
                    scenarioCounts = scenarioCounts.substring(0, scenarioCounts.length() - 1);

                    String codes = "";
                    i++;
                    String nextLineCodes = compArr.get(i).trim();
                    while (!nextLineCodes.equals(""))
                    {
                        codes += nextLineCodes + ",";
                        i++;
                        nextLineCodes = compArr.get(i).trim();
                    }
                    codes = codes.substring(0, codes.length() - 1);

                    //System.out.println(codes);
                    Report temp = new Report(h, Integer.parseInt(sl), Integer.parseInt(sc), Integer.parseInt(rc), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), scenarioCounts, codes);
                    reports.add(temp);
                    numReports++;
                    //System.out.println(temp);
                } else if (compArr.get(i).substring(0, 7).equals("Started"))
                {
                    String s = compArr.get(i);
                    int pn = Integer.parseInt(s.substring(14, s.indexOf(",")));
                    int d = Integer.parseInt(s.substring(s.indexOf(":") + 2, s.indexOf("s @")));
                    String td = s.substring(s.indexOf("@") + 2);
                    Phase temp = new Phase(pn, d, td);
                    System.out.println(temp);
                    phases.add(temp);
                    numPhases++;
                }
            } catch (StringIndexOutOfBoundsException e)
            {

            }
        }

        parent.appendToFeedback("Reports captured!");

        //reports arraylist now filled
//        for (int i = 0; i < reports.size(); i++)
//        {
//            System.out.println(reports.get(i));
//        }
        drawGraph();

    }

    public void drawGraph()
    {
        parent.appendToFeedback("Initializing graph and grid:");
        //create frame with settings
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1300, 700);

        //add x and y axes
        min = (int) getMinFromReports();
        max = (int) getMaxFromReports();
        JLabel n2 = new JLabel("Time in ms");
        frame.add(n2, BorderLayout.WEST);
        JLabel n1 = new JLabel(min + "");
        frame.add(n1, BorderLayout.PAGE_END);
        JLabel n3 = new JLabel(max + "");
        frame.add(n3, BorderLayout.PAGE_START);

        //create panel and gridlayout with it
        //int x = max - min, y = numReports - 1;
        //int x = max - min, y = 200;
        //x = max - min;
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setSize(900, 700);
        panel.setBounds(0, 0, 900, 1100);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(x, y));

        //panel.getLayout().
        frame.add(panel);
        //frame.pack();

        //make label array to fill the grid, empty cells
        JLabel lblArr[][] = new JLabel[x][y];
        for (int i = 0; i < x; i++)
        {
            for (int j = 0; j < y; j++)
            {
                lblArr[i][j] = new JLabel("");
                panel.add(lblArr[i][j]);
            }
        }

        parent.appendToFeedback("Graph and grid initialized!");

        drawPhaseLines(lblArr);

        drawGraph(lblArr);

        parent.appendToFeedback("Rendering graph (please be patient):");
        //show frame
        //frame.setResizable(false);
        frame.setVisible(true);
        parent.appendToFeedback("Graph rendered!");
        //frame.pack();
        //System.out.println("hi");
    }

    public void drawPhaseLines(JLabel lblArr[][])
    {
        //int totalPhase = getTotalPhaseTime();
        int totalTime = (numReports - 1) * 10;
        //System.out.println(totalPhase + " / " + totalTime);
        int num = y / totalTime; //scale unit / s
        //System.out.println("unit/s = " + num);
        //(y / (numReports - 1)) * i is 0, 10, 20, 30

        int last = 0;
        for (int i = 0; i < numPhases; i++)
        {
            Phase cur = phases.get(i);
            //System.out.println("--" + last + " / " + totalTime);
            int blah = num * last;
            //System.out.println("=> " + blah);
            for (int j = 0; j < x; j++)
            {
                //System.out.println(j + " " + blah);
                lblArr[j][blah].setOpaque(true);
                lblArr[j][blah].setBackground(Color.lightGray);
                //lblArr[j][blah].setText("^");
                //lblArr[j][blah].setForeground(Color.gray);
            }
            last += cur.getDuration();
        }
        //System.out.println("--" + last + " / " + totalTime);
        int blah = num * last;
        //System.out.println("=> " + blah);
        for (int j = 0; j < x; j++)
        {
            //System.out.println(j + " " + blah);
            lblArr[j][blah].setOpaque(true);
            lblArr[j][blah].setBackground(Color.lightGray);
            //lblArr[j][blah].setText("^");
            //lblArr[j][blah].setForeground(Color.gray);
        }

    }

    public void drawGraph(JLabel lblArr[][])
    {
        parent.appendToFeedback("Starting to draw graph:");

        Report cur = reports.get(0);
        //draw first point if need to display
        if (display[0])
        {
            try
            {
                //System.out.println("(" + cur.getMin() + ", 1) -> (" + transX(cur.getMin()) + "," + transY(1) + ")");
                lblArr[transX(cur.getMin())][transY(1)].setOpaque(true);
                lblArr[transX(cur.getMin())][transY(1)].setBackground(colArr[0]);
                lblArr[transX(cur.getMin())][transY(1)].addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        System.out.println("Yay you clicked me");
                    }

                });
                //lblArr[transX(cur.getMin())][0].setText("^");
                //lblArr[transX(cur.getMin())][0].setForeground(colArr[0]);
            } catch (ArrayIndexOutOfBoundsException e)
            {
                lblArr[transX(cur.getMin()) - 1][transY(1)].setOpaque(true);
                lblArr[transX(cur.getMin()) - 1][transY(1)].setBackground(colArr[0]);
                //lblArr[transX(cur.getMin()) - 1][0].setText("^");
                //lblArr[transX(cur.getMin()) - 1][0].setForeground(colArr[0]);
            }
        }
        if (display[1])
        {
            lblArr[transX(cur.getMedian())][transY(1)].setOpaque(true);
            lblArr[transX(cur.getMedian())][transY(1)].setBackground(colArr[1]);
            //lblArr[transX(cur.getMedian())][0].setText("^");
            //lblArr[transX(cur.getMedian())][0].setForeground(colArr[1]);
        }
        if (display[2])
        {
            //System.out.println(transX(cur.getP95()) + " " + transY(1));
            lblArr[transX(cur.getP95())][transY(1)].setOpaque(true);
            lblArr[transX(cur.getP95())][transY(1)].setBackground(colArr[2]);
            //lblArr[transX(cur.getP95()][0].setText("^");
            //lblArr[transX(cur.getP95()][0].setForeground(colArr[2]);
        }
        if (display[3])
        {
            lblArr[transX(cur.getP99())][transY(1)].setOpaque(true);
            lblArr[transX(cur.getP99())][transY(1)].setBackground(colArr[3]);
            //lblArr[transX(cur.getP99()][0].setText("^");
            //lblArr[transX(cur.getP99()][0].setForeground(colArr[3]);
        }
        if (display[4])
        {
            lblArr[transX(cur.getMax())][transY(1)].setOpaque(true);
            lblArr[transX(cur.getMax())][transY(1)].setBackground(colArr[4]);
            //lblArr[transX(cur.getMax())][0].setText("^");
            //lblArr[transX(cur.getMax())][0].setForeground(colArr[4]);
        }

        //for each report, draw the lines if they should be drawn
        for (int i = 1; i < numReports - 2; i++)
        {
            cur = reports.get(i);
            //draw a connecting line
            for (int j = 0; j < 5; j++)
            {
                if (display[j])
                {
                    drawLine(lblArr, i, j);
                }
            }

            //draw the next point
            if (display[0])
            {
                try
                {
                    //System.out.println("MIN: (" + cur.getMin() + ", " + (i + 1) + ") -> (" + transX(cur.getMin()) + "," + transY(i + 1) + ")");
                    lblArr[transX(cur.getMin())][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getMin())][transY(i + 1)].setBackground(colArr[0]);
                    //lblArr[x - (((int) cur.getMin()) - min)][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getMin()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[0]);
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    lblArr[transX(cur.getMin()) - 1][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getMin()) - 1][transY(i + 1)].setBackground(colArr[0]);
                    //lblArr[x - (((int) cur.getMin()) - min) - 1][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getMin()) - min) - 1][(y / (numReports - 1)) * i].setForeground(colArr[0]);
                }
            }
            if (display[1])
            {
                try
                {
                    //System.out.println("MEDIAN: (" + cur.getMedian() + ", " + (i + 1) + ") -> (" + transX(cur.getMedian()) + "," + transY(i + 1) + ")");
                    lblArr[transX(cur.getMedian())][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getMedian())][transY(i + 1)].setBackground(colArr[1]);
                    //lblArr[x - (((int) cur.getMedian()) - min)][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getMedian()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[1]);
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    lblArr[transX(cur.getMedian()) - 1][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getMedian()) - 1][transY(i + 1)].setBackground(colArr[1]);
                    //lblArr[x - (((int) cur.getMedian()) - min) - 1][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getMedian()) - min) - 1][(y / (numReports - 1)) * i].setForeground(colArr[1]);
                }
            }
            if (display[2])
            {
                try
                {
                    //System.out.println("P95: (" + cur.getP95() + ", " + (i + 1) + ") -> (" + transX(cur.getP95()) + "," + transY(i + 1) + ")");
                    lblArr[transX(cur.getP95())][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getP95())][transY(i + 1)].setBackground(colArr[2]);
                    //lblArr[transX(cur.getP95() - min)][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[transX(cur.getP95() - min)][(y / (numReports - 1)) * i].setForeground(colArr[2]);
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    lblArr[transX(cur.getP95()) - 1][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getP95()) - 1][transY(i + 1)].setBackground(colArr[2]);
                    //lblArr[x - (((int) cur.getP95()) - min) - 1][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getP95()) - min) - 1][(y / (numReports - 1)) * i].setForeground(colArr[2]);
                }
            }
            if (display[3])
            {
                try
                {
                    lblArr[transX(cur.getP99())][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getP99())][transY(i + 1)].setBackground(colArr[3]);
                    //lblArr[transX(cur.getP99() - min)][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[transX(cur.getP99() - min)][(y / (numReports - 1)) * i].setForeground(colArr[3]);
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    lblArr[transX(cur.getP99()) - 1][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getP99()) - 1][transY(i + 1)].setBackground(colArr[3]);
                    //lblArr[x - (((int) cur.getP99()) - min) - 1][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getP99()) - min) - 1][(y / (numReports - 1)) * i].setForeground(colArr[3]);
                }
            }
            if (display[4])
            {
                try
                {
                    lblArr[transX(cur.getMax())][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getMax())][transY(i + 1)].setBackground(colArr[4]);
                    //lblArr[x - (((int) cur.getMax()) - min)][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getMax()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[4]);
                } catch (ArrayIndexOutOfBoundsException e)
                {
                    lblArr[transX(cur.getMax()) - 1][transY(i + 1)].setOpaque(true);
                    lblArr[transX(cur.getMax()) - 1][transY(i + 1)].setBackground(colArr[4]);
                    //lblArr[x - (((int) cur.getMax()) - min) - 1][(y / (numReports - 1)) * i].setText("^");
                    //lblArr[x - (((int) cur.getMax()) - min) - 1][(y / (numReports - 1)) * i].setForeground(colArr[4]);
                }
            }

        }
        parent.appendToFeedback("Graph drawn!");
        //lblArr[0][0].setText("^");
    }

    public int transX(double in)
    {
        //old = return x - (((int) in) - min);
        //System.out.println("--" + in);
        //in is the ms
        //to get it to right level, in - min
        //then, calc percentage of (max-min), so (in-min)/(max-min)
        //then, times that into x, so ((in-min)/(max-min))*x
        int r = x - ((int) (((Math.round(in) - min) / (double) (max - min)) * x));
        if(r < 0)
        {
            return 0;
        }
        else if(r >= x)
        {
            return x-1;
        }
        else
        {
            return r;
        }

    }

    public int transY(int i)
    {
        return (y / (numReports - 1)) * i;
    }

    public void drawLine(JLabel lblArr[][], int i, int caseOf)
    {
        int old1, new1;
        switch (caseOf)
        {
            case 0:
                old1 = transX(reports.get(i - 1).getMin());
                new1 = transX(Math.round(reports.get(i).getMin()));
                break;
            case 1:
                old1 = transX(reports.get(i - 1).getMedian());
                new1 = transX(reports.get(i).getMedian());
                break;
            case 2:
                old1 = transX(reports.get(i - 1).getP95());
                new1 = transX(reports.get(i).getP95());
                break;
            case 3:
                old1 = transX(reports.get(i - 1).getP99());
                new1 = transX(reports.get(i).getP99());
                break;
            case 4:
                old1 = transX(reports.get(i - 1).getMax());
                new1 = transX(reports.get(i).getMax());
                break;
            default:
                old1 = transX(reports.get(i - 1).getMedian());
                new1 = transX(reports.get(i).getMedian());
        }

        int old2 = (int) ((y / (numReports - 1)) + (double) y / (numReports - 1) * (i - 1));
        int new2 = (int) ((y / (numReports - 1)) + (double) y / (numReports - 1) * i);
        //System.out.println("(" + old1 + "," + old2 + ") to (" + new1 + "," + new2 + ")");

        int dist1 = (new1 - old1);
        int dist2 = new2 - old2;
        //System.out.println("d1: " + dist1 + " d2: " + dist2);
        for (int j = 1; j < dist2; j++)
        {
            //System.out.println((old1 + (dist1 / dist2) * (j)) + " " + (old2 + j));
            try
            {
                lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setOpaque(true);
                lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setBackground(colArro[caseOf]);
                //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setText("^");
                //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setForeground(colArr[caseOf]);
                //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setVerticalAlignment(SwingConstants.TOP);

            } catch (ArrayIndexOutOfBoundsException e)
            {
                lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j) - 1)][old2 + j].setOpaque(true);
                lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j) - 1)][old2 + j].setBackground(colArro[caseOf]);
                //lblArr[old1 + (dist1 / dist2) * (j) - 1][old2 + j].setText("^");
                //lblArr[old1 + (dist1 / dist2) * (j) - 1][old2 + j].setForeground(colArr[caseOf]);
                //lblArr[old1 + (dist1 / dist2) * (j) - 1][old2 + j].setVerticalAlignment(SwingConstants.TOP);
            }
        }
        //System.out.println("----------------------------");
    }

    public double getMinFromReports()
    {
        //find lowest needed
        int j = 0;
        while (!display[j])
        {
            j++;
        }
        //j is at thing we should use for min
        double min = 0;
        switch (j)
        {
            case 0:
                min = reports.get(0).getMin();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getMin())
                    {
                        min = reports.get(i).getMin();
                    }
                }
                break;
            case 1:
                min = reports.get(0).getMedian();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getMedian())
                    {
                        min = reports.get(i).getMedian();
                    }
                }
                break;
            case 2:
                min = reports.get(0).getP95();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getP95())
                    {
                        min = reports.get(i).getP95();
                    }
                }
                break;
            case 3:
                min = reports.get(0).getP99();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getP99())
                    {
                        min = reports.get(i).getP99();
                    }
                }
                break;
            case 4:
                min = reports.get(0).getMax();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getMax())
                    {
                        min = reports.get(i).getMax();
                    }
                }
                break;
            default:
                min = reports.get(0).getMin();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getMin())
                    {
                        min = reports.get(i).getMin();
                    }
                }

        }
        System.out.print("-" + min + " + ");
        return min;

    }

    public double getMaxFromReports()
    {
        //find lowest needed
        int j = display.length - 1;
        while (!display[j])
        {
            j--;
        }
        //j is at thing we should use for min
        double max = 0;
        switch (j)
        {
            case 4:
                max = reports.get(0).getMax();
                //(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getMax())
                    {
                        max = reports.get(i).getMax();
                    }
                }
                break;
            case 3:
                max = reports.get(0).getP99();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getP99())
                    {
                        max = reports.get(i).getP99();
                    }
                }
                break;
            case 2:
                max = reports.get(0).getP95();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getP95())
                    {
                        max = reports.get(i).getP95();
                    }
                }
                break;
            case 1:
                max = reports.get(0).getMedian();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getMedian())
                    {
                        max = reports.get(i).getMedian();
                    }
                }
                break;
            case 0:
                max = reports.get(0).getMin();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getMin())
                    {
                        max = reports.get(i).getMin();
                    }
                }
                break;
            default:
                max = reports.get(0).getMax();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getMax())
                    {
                        max = reports.get(i).getMax();
                    }
                }
        }
        System.out.print(max + " = ");
        return max;
    }

    public int getTotalPhaseTime()
    {
        int count = 0;
        for (int i = 0; i < numPhases; i++)
        {
            count += phases.get(i).getDuration();
        }
        return count;
    }

}
