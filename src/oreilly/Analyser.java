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

    private ArrayList<SingleTest> tests;
    private int numTests;

    private String toDisplay1;
    private boolean[] display1;
    private final int numDisplayOptions1 = 5;

    private String toDisplay2;
    private boolean[] display2;
    private final int numDisplayOptions2 = 3;

    public static final Color[] colArr = new Color[]
    {
        Color.red, Color.blue, new Color(0, 153, 0), new Color(255, 102, 0), new Color(102, 0, 153), Color.orange
    };

    public static final Color[] colArro = new Color[]
    {
        new Color(255, 0, 0, 50), new Color(0, 0, 255, 50), new Color(0, 153, 0, 50), new Color(255, 102, 0, 50), new Color(102, 0, 153, 50), Color.orange
    };

    private final int x = 200, y = 200;
    private int min, max;

    public Analyser(String filename, OlmecUI g) throws FileNotFoundException
    {
        reports = new ArrayList<Report>();
        phases = new ArrayList<Phase>();
        numReports = 0;
        numPhases = 0;
        parent = g;

        tests = new ArrayList<SingleTest>();
        numTests = 0;

        //put main file into arraylist
        Scanner scFile = new Scanner(new File("../../mayan/Mayan/gen/runs/" + filename + "/" + filename + ".txt"));
        //Scanner scFile = new Scanner(new File("../../mayan/Mayan/gen/runs/" + filename + "/" + "Test0" + ".txt"));
        ArrayList<String> compArr = new ArrayList<String>();
        while (scFile.hasNextLine())
        {
            compArr.add(scFile.nextLine());
        }
        scFile.close();

        parent.appendToFeedback("Starting report and phase capture:");

        //extract report info
        for (int i = 0; i < compArr.size(); i++)
        {
            try
            {
                if (compArr.get(i).substring(0, 6).equals("Report"))
                {
                    String h = compArr.get(i).trim();
                    i++;

                    String sl = compArr.get(i).trim();
                    sl = sl.substring(21);
                    i++;

                    String sc = compArr.get(i).trim();
                    sc = sc.substring(21);
                    i++;

                    String rc = compArr.get(i).trim();
                    rc = rc.substring(21);
                    i++;

                    String rps = compArr.get(i).trim();
                    rps = rps.substring(10);
                    i++;
                    i++;

                    String min = compArr.get(i).trim();
                    min = min.substring(5);
                    i++;

                    String max = compArr.get(i).trim();
                    max = max.substring(5);
                    i++;

                    String med = compArr.get(i).trim();
                    med = med.substring(8);
                    i++;

                    String p95 = compArr.get(i).trim();
                    p95 = p95.substring(5);
                    i++;

                    String p99 = compArr.get(i).trim();
                    p99 = p99.substring(5);
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

                    Report temp = new Report(h, Integer.parseInt(sl), Integer.parseInt(sc), Integer.parseInt(rc), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), codes);
                    //System.out.println(temp);
                    reports.add(temp);
                    numReports++;

                } else if (compArr.get(i).substring(0, 14).equals("Summary report"))
                {
                    String h = compArr.get(i).trim();
                    i++;

                    String sl = compArr.get(i).trim();
                    sl = sl.substring(21);
                    i++;

                    String sc = compArr.get(i).trim();
                    sc = sc.substring(21);
                    i++;

                    String rc = compArr.get(i).trim();
                    rc = rc.substring(21);
                    i++;

                    String rps = compArr.get(i).trim();
                    rps = rps.substring(10);
                    i++;
                    i++;

                    String min = compArr.get(i).trim();
                    min = min.substring(5);
                    i++;

                    String max = compArr.get(i).trim();
                    max = max.substring(5);
                    i++;

                    String med = compArr.get(i).trim();
                    med = med.substring(8);
                    i++;

                    String p95 = compArr.get(i).trim();
                    p95 = p95.substring(5);
                    i++;

                    String p99 = compArr.get(i).trim();
                    p99 = p99.substring(5);
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

                    Report temp = new Report(h, Integer.parseInt(sl), Integer.parseInt(sc), Integer.parseInt(rc), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), scenarioCounts, codes);
                    //System.out.println(temp);
                    reports.add(temp);
                    numReports++;

                } else if (compArr.get(i).substring(0, 7).equals("Started"))
                {
                    String s = compArr.get(i);
                    int pn = Integer.parseInt(s.substring(14, s.indexOf(",")));
                    int d = Integer.parseInt(s.substring(s.indexOf(":") + 2, s.indexOf("s @")));
                    String td = s.substring(s.indexOf("@") + 2);
                    Phase temp = new Phase(pn, d, td);
                    //System.out.println(temp);
                    phases.add(temp);
                    numPhases++;
                }
            } catch (StringIndexOutOfBoundsException e)
            {

            }
        }

        //now, capture all single test
        //System.out.println("THE FOLDER: ../../mayan/Mayan/gen/runs/" + filename);
        boolean firstSeen = false;
        boolean averages = false;
        for (File file : new File("../../mayan/Mayan/gen/runs/" + filename).listFiles())
        {

            String f = file.getName();
            //System.out.println("CHECKING EQUALS: " + f + "\t" + filename + ".txt");
            if (!f.equals(filename + ".txt"))
            {

                if (!firstSeen)
                {
                    firstSeen = true;
                    try
                    {
                        String newF = f.substring(0, f.length() - 4);
                        newF = newF.substring(newF.length() - 4, newF.length() - 1);
                        if (newF.equals("___"))
                        {
                            //System.out.println("AVERAGES!");
                            averages = true;
                        }
                    } catch (StringIndexOutOfBoundsException e)
                    {
                    }
                }

                //System.out.println("\t\tTEST FILE GO!!!!");
                //System.out.println("-------------------------------");
                SingleTest t = new SingleTest(filename, f);
                //System.out.println("THIS SINGLETEST:\n" + t);
                tests.add(t);
                numTests++;
                //System.out.println(t);

            }
//            else
//            {
//                System.out.println("\t\tMAIN FILE STOP!!!!!!!");
//            }
        }

        //System.out.println(numTests);
        if (averages)
        {
            convertToAverages();
        }
        //System.out.println(numTests);
        //System.out.println("---------------------------------------------------------------------------------------------");
        /*for (int i = 0; i < numTests; i++)
        {
            System.out.println(tests.get(i));
            System.out.println("---------------------------------------------------------------------------------------------");
        }*/

        {
            parent.appendToFeedback("Reports and phases captured!");
        }
    }

    public void convertToAverages()
    {
        //System.out.println("//////////////////////////////////////////////////////////////////////////////////////////\n");

        for (int i = 0; i < tests.size(); i++)
        {
            /*
            System.out.println("//////////////////////////////////////////////////////////////////////////////////////////\n");

            for (int q = 0; q < tests.size(); q++)
            {
                System.out.println(tests.get(q).getFilename());
            }

            System.out.println("//////////////////////////////////////////////////////////////////////////////////////////\n");
             */

            //s1 is our thing at i
            SingleTest s1 = tests.get(i), s2 = null, s3 = null;

            //System.out.println("i=" + i + "\t" + s1.getFilename() + "<----");
            //get the common name without ___x
            String commonName = s1.getFilename();
            commonName = commonName.substring(0, commonName.length() - 4);
            commonName = commonName.substring(0, commonName.length() - 4);
            //System.out.println(commonName);
            String dir = s1.getDir();

            //look through the rest of the array for the same commom name without ___x
            int j;
            boolean found = false;
            for (j = i + 1; j < tests.size() && !found; j++)
            {

                //get the current one
                SingleTest s = tests.get(j);
                //extract the name
                String sName = s.getFilename();
                sName = sName.substring(0, sName.length() - 4);
                sName = sName.substring(0, sName.length() - 4);
                //System.out.println(sName);

                //System.out.println("j=" + j + "\t" + s.getFilename());
                //check if current name is same as common
                if (commonName.equals(sName))
                {
                    //System.out.println("FOUND!");
                    found = true;
                    s2 = tests.get(j);
                    tests.remove(j);
                    numTests--;
                    j--;
                }
            }

            //check through rest of array for our next one same as common without ___x
            found = false;
            for (int k = j; k < tests.size() && !found; k++)
            {
                //get current one
                SingleTest s = tests.get(k);
                //extract the name
                String sName = s.getFilename();
                sName = sName.substring(0, sName.length() - 4);
                sName = sName.substring(0, sName.length() - 4);
                //System.out.println(sName);

                //System.out.println("k=" + k + "\t" + s.getFilename());
                //check if current name is same as common
                if (commonName.equals(sName))
                {
                    //System.out.println("FOUND!");
                    found = true;
                    s3 = tests.get(k);
                    tests.remove(k);
                    numTests--;
                }
            }

            //now, group those 3 into one, finding averages, assume same size
            SingleTest s = new SingleTest(dir, commonName); //this will throw exception
            //add phases
            for (int x = 0; x < s1.getNumPhases(); x++)
            {
                Phase copy = s1.getPhases().get(x);
                Phase p = new Phase(copy.getPhaseNum(), copy.getDuration(), copy.getTimedate());
                s.addPhase(p);
            }
            //add reports with averages
            for (int x = 0; x < s1.getNumReports() && x < s2.getNumReports() && x < s3.getNumReports(); x++)
            {
                Report copy1 = s1.getReports().get(x);
                Report copy2 = s2.getReports().get(x);
                Report copy3 = s3.getReports().get(x);

                String h = copy3.getHeader();
                int sl = (int) Math.round(avg3(copy1.getScenariosLaunched(), copy2.getScenariosLaunched(), copy3.getScenariosLaunched()));
                int sc = (int) Math.round(avg3(copy1.getScenariosCompleted(), copy2.getScenariosCompleted(), copy3.getScenariosCompleted()));
                int rc = (int) Math.round(avg3(copy1.getRequestsCompleted(), copy2.getRequestsCompleted(), copy3.getRequestsCompleted()));
                double rps = roundTo(avg3(copy1.getRpsSent(), copy2.getRpsSent(), copy3.getRpsSent()), 2);
                double min = roundTo(avg3(copy1.getMin(), copy2.getMin(), copy3.getMin()), 2);
                double max = roundTo(avg3(copy1.getMax(), copy2.getMax(), copy3.getMax()), 2);
                double median = roundTo(avg3(copy1.getMedian(), copy2.getMedian(), copy3.getMedian()), 2);
                double p95 = roundTo(avg3(copy1.getP95(), copy2.getP95(), copy3.getP95()), 2);
                double p99 = roundTo(avg3(copy1.getP99(), copy2.getP99(), copy3.getP99()), 2);
                String scenarioCounts = "";
                boolean isSummary = false;
                if (copy1.isSummary())
                {
                    isSummary = true;
                    scenarioCounts = copy1.getScenarioCountsString();
                } else if (copy2.isSummary())
                {
                    isSummary = true;
                    scenarioCounts = copy2.getScenarioCountsString();
                } else if (copy3.isSummary())
                {
                    isSummary = true;
                    scenarioCounts = copy3.getScenarioCountsString();
                }

                String codes = copy3.getCodesString();

                Report r;
                if (isSummary)
                {
                    r = new Report(h, sl, sc, rc, rps, min, max, median, p95, p99, scenarioCounts, codes);
                } else
                {
                    r = new Report(h, sl, sc, rc, rps, min, max, median, p95, p99, codes);
                }

                s.addReport(r);
            }
            //make this grouped one into pos i
            //System.out.println(i);
            //System.out.println(s);
            tests.remove(i);
            numTests--;
            tests.add(i, s);
            numTests++;
        }

        /*
        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////\n");

        for (int q = 0; q < tests.size(); q++)
        {
            System.out.println(tests.get(q).getFilename());
        }

        System.out.println("//////////////////////////////////////////////////////////////////////////////////////////\n");
         */
    }

    public double avg3(double n1, double n2, double n3)
    {
        return (n1 + n2 + n3) / 3.0;
    }

    public void drawGraph1(String s)
    {
        //decide what to display
        toDisplay1 = s;
        display1 = new boolean[numDisplayOptions1];
        for (int i = 0; i < numDisplayOptions1; i++)
        {
            display1[i] = false;
            if (toDisplay1.contains(i + ""))
            {
                display1[i] = true;
            }
        }

        parent.appendToFeedback("Initializing graph and grid:");
        //create frame with settings
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1300, 700);

        //add x and y axes
        min = (int) getMinFromReports1();
        max = (int) getMaxFromReports1();
        //JLabel n2 = new JLabel("Time in ms");
        //frame.add(n2, BorderLayout.WEST);
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

        drawPhaseLines1(lblArr);

        drawGraph1(lblArr);

        parent.appendToFeedback("Rendering graph (please be patient):");
        //show frame
        //frame.setResizable(false);
        frame.setVisible(true);
        parent.appendToFeedback("Graph rendered!");
        //frame.pack();
        //System.out.println("hi");
    }

    public void drawPhaseLines1(JLabel lblArr[][])
    {
        int totalTime = (numReports - 1) * 10;

        int last = 0;
        for (int i = 0; i < numPhases; i++)
        {
            Phase cur = phases.get(i);
            //System.out.print(last + " / " + totalTime + " ==> ");
            int blah = (int) Math.round((last / (double) totalTime) * y);
            //System.out.println(blah + " / " + y);
            for (int j = 0; j < x; j++)
            {
                lblArr[j][blah].setOpaque(true);
                lblArr[j][blah].setBackground(Color.lightGray);
            }
            last += cur.getDuration();
        }

        int blah = (int) Math.round((last / (double) totalTime) * y);
        for (int j = 0; j < x; j++)
        {
            lblArr[j][blah].setOpaque(true);
            lblArr[j][blah].setBackground(Color.lightGray);
        }

    }

    public void drawGraph1(JLabel lblArr[][])
    {
        parent.appendToFeedback("Starting to draw graph:");

        Report cur = reports.get(0);
        //draw first point if need to display
        if (display1[0])
        {
            //System.out.println("(" + cur.getMin() + ", 1) -> (" + transX(cur.getMin()) + "," + transY(1) + ")");
            lblArr[transX1(cur.getMin())][transY1(1)].setOpaque(true);
            lblArr[transX1(cur.getMin())][transY1(1)].setBackground(colArr[0]);
            lblArr[transX1(cur.getMin())][transY1(1)].addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    System.out.println("Yay you clicked me");
                }
            });
        }
        if (display1[1])
        {
            lblArr[transX1(cur.getMedian())][transY1(1)].setOpaque(true);
            lblArr[transX1(cur.getMedian())][transY1(1)].setBackground(colArr[1]);
            //lblArr[transX(cur.getMedian())][0].setText("^");
            //lblArr[transX(cur.getMedian())][0].setForeground(colArr[1]);
        }
        if (display1[2])
        {
            //System.out.println(transX(cur.getP95()) + " " + transY(1));
            lblArr[transX1(cur.getP95())][transY1(1)].setOpaque(true);
            lblArr[transX1(cur.getP95())][transY1(1)].setBackground(colArr[2]);
            //lblArr[transX(cur.getP95()][0].setText("^");
            //lblArr[transX(cur.getP95()][0].setForeground(colArr[2]);
        }
        if (display1[3])
        {
            lblArr[transX1(cur.getP99())][transY1(1)].setOpaque(true);
            lblArr[transX1(cur.getP99())][transY1(1)].setBackground(colArr[3]);
            //lblArr[transX(cur.getP99()][0].setText("^");
            //lblArr[transX(cur.getP99()][0].setForeground(colArr[3]);
        }
        if (display1[4])
        {
            lblArr[transX1(cur.getMax())][transY1(1)].setOpaque(true);
            lblArr[transX1(cur.getMax())][transY1(1)].setBackground(colArr[4]);
            //lblArr[transX(cur.getMax())][0].setText("^");
            //lblArr[transX(cur.getMax())][0].setForeground(colArr[4]);
        }

        //for each report, draw the lines if they should be drawn
        for (int i = 1; i < numReports - 1; i++)
        {
            cur = reports.get(i);
            //draw a connecting line
            for (int j = 0; j < numDisplayOptions1; j++)
            {
                if (display1[j])
                {
                    drawLine1(lblArr, i, j);
                }
            }

            //draw the next point
            if (display1[0])
            {
                lblArr[transX1(cur.getMin())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(cur.getMin())][transY1(i + 1)].setBackground(colArr[0]);
                //lblArr[x - (((int) cur.getMin()) - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[x - (((int) cur.getMin()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[0]);
            }
            if (display1[1])
            {
                //System.out.println("MEDIAN: (" + cur.getMedian() + ", " + (i + 1) + ") -> (" + transX(cur.getMedian()) + "," + transY(i + 1) + ")");
                lblArr[transX1(cur.getMedian())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(cur.getMedian())][transY1(i + 1)].setBackground(colArr[1]);
                //lblArr[x - (((int) cur.getMedian()) - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[x - (((int) cur.getMedian()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[1]);
            }
            if (display1[2])
            {
                //System.out.println("P95: (" + cur.getP95() + ", " + (i + 1) + ") -> (" + transX(cur.getP95()) + "," + transY(i + 1) + ")");
                lblArr[transX1(cur.getP95())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(cur.getP95())][transY1(i + 1)].setBackground(colArr[2]);
                //lblArr[transX(cur.getP95() - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[transX(cur.getP95() - min)][(y / (numReports - 1)) * i].setForeground(colArr[2]);
            }
            if (display1[3])
            {
                lblArr[transX1(cur.getP99())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(cur.getP99())][transY1(i + 1)].setBackground(colArr[3]);
                //lblArr[transX(cur.getP99() - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[transX(cur.getP99() - min)][(y / (numReports - 1)) * i].setForeground(colArr[3]);
            }
            if (display1[4])
            {
                lblArr[transX1(cur.getMax())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(cur.getMax())][transY1(i + 1)].setBackground(colArr[4]);
                //lblArr[x - (((int) cur.getMax()) - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[x - (((int) cur.getMax()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[4]);
            }

        }
        parent.appendToFeedback("Graph drawn!");
        //lblArr[0][0].setText("^");
    }

    public int transX1(double in)
    {
        //old = return x - (((int) in) - min);
        //System.out.println("--" + in);
        //in is the ms
        //to get it to right level, in - min
        //then, calc percentage of (max-min), so (in-min)/(max-min)
        //then, times that into x, so ((in-min)/(max-min))*x
        int r = x - ((int) (((Math.round(in) - min) / (double) (max - min)) * x));
        if (r < 0)
        {
            return 0;
        } else if (r >= x)
        {
            return x - 1;
        } else
        {
            return r;
        }

    }

    public int transY1(int i)
    {
        //returns 10s intervals * i
        int r = (y / (numReports - 1)) * i;
        if (r < 0)
        {
            r = 0;
        } else if (r >= y)
        {
            r = y - 1;
        }
        return r;
    }

    public void drawLine1(JLabel lblArr[][], int i, int caseOf)
    {
        int old1, new1;
        switch (caseOf)
        {
            case 0:
                old1 = transX1(reports.get(i - 1).getMin());
                new1 = transX1(Math.round(reports.get(i).getMin()));
                break;
            case 1:
                old1 = transX1(reports.get(i - 1).getMedian());
                new1 = transX1(reports.get(i).getMedian());
                break;
            case 2:
                old1 = transX1(reports.get(i - 1).getP95());
                new1 = transX1(reports.get(i).getP95());
                break;
            case 3:
                old1 = transX1(reports.get(i - 1).getP99());
                new1 = transX1(reports.get(i).getP99());
                break;
            case 4:
                old1 = transX1(reports.get(i - 1).getMax());
                new1 = transX1(reports.get(i).getMax());
                break;
            default:
                old1 = transX1(reports.get(i - 1).getMedian());
                new1 = transX1(reports.get(i).getMedian());
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
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setOpaque(true);
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setBackground(colArro[caseOf]);
            //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setText("^");
            //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setForeground(colArr[caseOf]);
            //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setVerticalAlignment(SwingConstants.TOP);

        }
        //System.out.println("----------------------------");
    }

    public double getMinFromReports1()
    {
        //find lowest needed
        int j = 0;
        while (!display1[j])
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

    public double getMaxFromReports1()
    {
        //find lowest needed
        int j = display1.length - 1;
        while (!display1[j])
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
        System.out.println(max + " = ");
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

    public void drawGraph2(String s)
    {
        //decide what to display
        toDisplay2 = s;
        display2 = new boolean[numDisplayOptions2];
        for (int i = 0; i < numDisplayOptions2; i++)
        {
            display2[i] = false;
            if (toDisplay2.contains(i + ""))
            {
                display2[i] = true;
            }
        }

        parent.appendToFeedback("Initializing graph and grid:");
        //create frame with settings
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1300, 700);

        //add x and y axes
        min = (int) getMinFromReports2();
        max = (int) getMaxFromReports2();
        //JLabel n2 = new JLabel("Number of requests or scenarios");
        //frame.add(n2, BorderLayout.WEST);
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

        drawPhaseLines2(lblArr);

        drawGraph2(lblArr);

        parent.appendToFeedback("Rendering graph (please be patient):");
        //show frame
        //frame.setResizable(false);
        frame.setVisible(true);
        parent.appendToFeedback("Graph rendered!");
        //frame.pack();
        //System.out.println("hi");
    }

    public void drawPhaseLines2(JLabel lblArr[][])
    {
        int totalTime = (numReports - 1) * 10;

        int last = 0;
        for (int i = 0; i < numPhases; i++)
        {
            Phase cur = phases.get(i);
            //System.out.print(last + " / " + totalTime + " ==> ");
            int blah = (int) Math.round((last / (double) totalTime) * y);
            //System.out.println(blah + " / " + y);
            for (int j = 0; j < x; j++)
            {
                lblArr[j][blah].setOpaque(true);
                lblArr[j][blah].setBackground(Color.lightGray);
            }
            last += cur.getDuration();
        }

        int blah = (int) Math.round((last / (double) totalTime) * y);
        for (int j = 0; j < x; j++)
        {
            lblArr[j][blah].setOpaque(true);
            lblArr[j][blah].setBackground(Color.lightGray);
        }

    }

    public void drawGraph2(JLabel lblArr[][])
    {
        parent.appendToFeedback("Starting to draw graph:");

        Report cur = reports.get(0);
        //draw first point if need to display
        if (display2[0])
        {
            //System.out.println("(" + cur.getScenariosLaunched() + ", 1) -> (" + transX(cur.getScenariosLaunched()) + "," + transY(1) + ")");
            lblArr[transX2(cur.getScenariosLaunched())][transY2(1)].setOpaque(true);
            lblArr[transX2(cur.getScenariosLaunched())][transY2(1)].setBackground(colArr[0]);
            /*
            lblArr[transX2(cur.getScenariosLaunched())][transY2(1)].addMouseListener(new MouseAdapter()
            {
                @Override
                public void mouseClicked(MouseEvent e)
                {
                    System.out.println("Yay you clicked me");
                }
            });
             */
        }
        if (display2[1])
        {
            lblArr[transX2(cur.getScenariosCompleted())][transY2(1)].setOpaque(true);
            lblArr[transX2(cur.getScenariosCompleted())][transY2(1)].setBackground(colArr[1]);
            //lblArr[transX(cur.getScenariosCompleted())][0].setText("^");
            //lblArr[transX(cur.getScenariosCompleted())][0].setForeground(colArr[1]);
        }
        if (display2[2])
        {
            //System.out.println(transX(cur.getRequestsCompleted()) + " " + transY(1));
            lblArr[transX2(cur.getRequestsCompleted())][transY2(1)].setOpaque(true);
            lblArr[transX2(cur.getRequestsCompleted())][transY2(1)].setBackground(colArr[2]);
            //lblArr[transX(cur.getRequestsCompleted()][0].setText("^");
            //lblArr[transX(cur.getRequestsCompleted()][0].setForeground(colArr[2]);
        }

        //for each report, draw the lines if they should be drawn
        for (int i = 1; i < numReports - 1; i++)
        {
            cur = reports.get(i);
            //draw a connecting line
            for (int j = 0; j < numDisplayOptions2; j++)
            {
                if (display2[j])
                {
                    drawLine2(lblArr, i, j);
                }
            }

            //draw the next point
            if (display2[0])
            {
                lblArr[transX2(cur.getScenariosLaunched())][transY2(i + 1)].setOpaque(true);
                lblArr[transX2(cur.getScenariosLaunched())][transY2(i + 1)].setBackground(colArr[0]);
                //lblArr[x - (((int) cur.getScenariosLaunched()) - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[x - (((int) cur.getScenariosLaunched()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[0]);
            }
            if (display2[1])
            {
                //System.out.println("MEDIAN: (" + cur.getScenariosCompleted() + ", " + (i + 1) + ") -> (" + transX(cur.getScenariosCompleted()) + "," + transY(i + 1) + ")");
                lblArr[transX2(cur.getScenariosCompleted())][transY2(i + 1)].setOpaque(true);
                lblArr[transX2(cur.getScenariosCompleted())][transY2(i + 1)].setBackground(colArr[1]);
                //lblArr[x - (((int) cur.getScenariosCompleted()) - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[x - (((int) cur.getScenariosCompleted()) - min)][(y / (numReports - 1)) * i].setForeground(colArr[1]);
            }
            if (display2[2])
            {
                //System.out.println("P95: (" + cur.getRequestsCompleted() + ", " + (i + 1) + ") -> (" + transX(cur.getRequestsCompleted()) + "," + transY(i + 1) + ")");
                lblArr[transX2(cur.getRequestsCompleted())][transY2(i + 1)].setOpaque(true);
                lblArr[transX2(cur.getRequestsCompleted())][transY2(i + 1)].setBackground(colArr[2]);
                //lblArr[transX(cur.getRequestsCompleted() - min)][(y / (numReports - 1)) * i].setText("^");
                //lblArr[transX(cur.getRequestsCompleted() - min)][(y / (numReports - 1)) * i].setForeground(colArr[2]);
            }

        }
        parent.appendToFeedback("Graph drawn!");
        //lblArr[0][0].setText("^");
    }

    public int transX2(double in)
    {
        //old = return x - (((int) in) - min);
        //System.out.println("--" + in);
        //in is the ms
        //to get it to right level, in - min
        //then, calc percentage of (max-min), so (in-min)/(max-min)
        //then, times that into x, so ((in-min)/(max-min))*x
        int r = x - ((int) (((Math.round(in) - min) / (double) (max - min)) * x));
        if (r < 0)
        {
            return 0;
        } else if (r >= x)
        {
            return x - 1;
        } else
        {
            return r;
        }

    }

    public int transY2(int i)
    {
        //returns 10s intervals * i
        int r = (y / (numReports - 1)) * i;
        if (r < 0)
        {
            r = 0;
        } else if (r >= y)
        {
            r = y - 1;
        }
        return r;
    }

    public void drawLine2(JLabel lblArr[][], int i, int caseOf)
    {
        int old1, new1;
        switch (caseOf)
        {
            case 0:
                old1 = transX2(reports.get(i - 1).getScenariosLaunched());
                new1 = transX2(Math.round(reports.get(i).getScenariosLaunched()));
                break;
            case 1:
                old1 = transX2(reports.get(i - 1).getScenariosCompleted());
                new1 = transX2(reports.get(i).getScenariosCompleted());
                break;
            case 2:
                old1 = transX2(reports.get(i - 1).getRequestsCompleted());
                new1 = transX2(reports.get(i).getRequestsCompleted());
                break;
            default:
                old1 = transX2(reports.get(i - 1).getScenariosCompleted());
                new1 = transX2(reports.get(i).getScenariosCompleted());
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
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setOpaque(true);
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setBackground(colArro[caseOf]);
            //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setText("^");
            //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setForeground(colArr[caseOf]);
            //lblArr[old1 + (dist1 / dist2) * (j)][old2 + j].setVerticalAlignment(SwingConstants.TOP);

        }
        //System.out.println("----------------------------");
    }

    public double getMinFromReports2()
    {
        //find lowest needed
        int j = 0;
        while (!display2[j])
        {
            j++;
        }
        //j is at thing we should use for min
        double min = 0;
        switch (j)
        {
            case 0:
                min = reports.get(0).getScenariosLaunched();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getScenariosLaunched())
                    {
                        min = reports.get(i).getScenariosLaunched();
                    }
                }
                break;
            case 1:
                min = reports.get(0).getScenariosCompleted();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getScenariosCompleted())
                    {
                        min = reports.get(i).getScenariosCompleted();
                    }
                }
                break;
            case 2:
                min = reports.get(0).getRequestsCompleted();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getRequestsCompleted())
                    {
                        min = reports.get(i).getRequestsCompleted();
                    }
                }
                break;
            default:
                min = reports.get(0).getScenariosLaunched();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (min > reports.get(i).getScenariosLaunched())
                    {
                        min = reports.get(i).getScenariosLaunched();
                    }
                }

        }
        System.out.print("-" + min + " + ");
        return min;

    }

    public double getMaxFromReports2()
    {
        //find lowest needed
        int j = display2.length - 1;
        while (!display2[j])
        {
            j--;
        }
        //j is at thing we should use for min
        double max = 0;
        switch (j)
        {
            case 2:
                max = reports.get(0).getRequestsCompleted();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getRequestsCompleted())
                    {
                        max = reports.get(i).getRequestsCompleted();
                    }
                }
                break;
            case 1:
                max = reports.get(0).getScenariosCompleted();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getScenariosCompleted())
                    {
                        max = reports.get(i).getScenariosCompleted();
                    }
                }
                break;
            case 0:
                max = reports.get(0).getScenariosLaunched();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getScenariosLaunched())
                    {
                        max = reports.get(i).getScenariosLaunched();
                    }
                }
                break;
            default:
                max = reports.get(0).getRequestsCompleted();
                //System.out.println(0);
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (max < reports.get(i).getRequestsCompleted())
                    {
                        max = reports.get(i).getRequestsCompleted();
                    }
                }
        }
        System.out.println(max + " = ");
        return max;
    }

    public Report getSummaryReport()
    {
        return reports.get(numReports - 1);
    }

    public String getLagReport(boolean showSome)
    {
        String b = "";
        for (int i = 0; i < numTests; i++)
        {
            if (showSome)
            {
                String check = tests.get(i).getLagReport();
                if(!check.substring(check.length()-2, check.length()).equals("-\n"))
                {
                    b += check;
                }
                
            } else
            {
                b += tests.get(i).getLagReport();
            }
        }
        return b;
    }

    public double roundTo(double num, int dec)
    {
        int toTimesBy = (int) Math.pow(10, dec);
        num *= toTimesBy;
        num = Math.round(num);
        num /= toTimesBy;
        return num;
    }

}
