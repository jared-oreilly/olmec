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

    private String toDisplay3;
    private boolean[] display3;
    private final int numDisplayOptions3 = 5;

    public static final Color[] colArr = new Color[]
    {
        Color.red, Color.blue, new Color(0, 153, 0), new Color(255, 102, 0), new Color(102, 0, 153), Color.orange
    };

    public static final Color[] colArrOpaque = new Color[]
    {
        new Color(255, 0, 0, 50), new Color(0, 0, 255, 50), new Color(0, 153, 0, 50), new Color(255, 102, 0, 50), new Color(102, 0, 153, 50), Color.orange
    };

    private final int x = 200, y = 200;
    private int min, max;

    public Analyser(String overallFileName, OlmecUI par) throws FileNotFoundException
    {
        reports = new ArrayList<Report>();
        phases = new ArrayList<Phase>();
        numReports = 0;
        numPhases = 0;
        parent = par;

        tests = new ArrayList<SingleTest>();
        numTests = 0;

        //put main file into arraylist
        Scanner scannerFile = new Scanner(new File("../../mayan/Mayan/gen/runs/" + overallFileName + "/" + overallFileName + ".txt"));
        ArrayList<String> componentArr = new ArrayList<String>();
        while (scannerFile.hasNextLine())
        {
            componentArr.add(scannerFile.nextLine());
        }
        scannerFile.close();

        parent.appendToFeedback("Starting report and phase capture...\t");

        //extract report info
        for (int i = 0; i < componentArr.size(); i++)
        {
            try
            {
                if (componentArr.get(i).substring(0, 6).equals("Report"))
                {
                    String header = componentArr.get(i).trim();
                    i++;

                    String scenariosLaunched = componentArr.get(i).trim();
                    scenariosLaunched = scenariosLaunched.substring(21);
                    i++;

                    String scenariosCompleted = componentArr.get(i).trim();
                    scenariosCompleted = scenariosCompleted.substring(21);
                    i++;

                    String requestsCompleted = componentArr.get(i).trim();
                    requestsCompleted = requestsCompleted.substring(21);
                    i++;

                    String rps = componentArr.get(i).trim();
                    rps = rps.substring(10);
                    i++;
                    i++;

                    String min = componentArr.get(i).trim();
                    min = min.substring(5);
                    i++;

                    String max = componentArr.get(i).trim();
                    max = max.substring(5);
                    i++;

                    String med = componentArr.get(i).trim();
                    med = med.substring(8);
                    i++;

                    String p95 = componentArr.get(i).trim();
                    p95 = p95.substring(5);
                    i++;

                    String p99 = componentArr.get(i).trim();
                    p99 = p99.substring(5);
                    i++;

                    String codes = "";
                    i++;
                    String nextLine = componentArr.get(i).trim();
                    while (!nextLine.equals("") && !nextLine.equals("Errors"))
                    {
                        codes += nextLine + ",";
                        i++;
                        nextLine = componentArr.get(i).trim();
                    }
                    codes = codes.substring(0, codes.length() - 1);
                    
                    Report temp = new Report(header, Integer.parseInt(scenariosLaunched), Integer.parseInt(scenariosCompleted), Integer.parseInt(requestsCompleted), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), codes);
                    reports.add(temp);
                    numReports++;

                } else if (componentArr.get(i).substring(0, 14).equals("Summary report"))
                {
                    String header = componentArr.get(i).trim();
                    i++;

                    String scenariosLaunched = componentArr.get(i).trim();
                    scenariosLaunched = scenariosLaunched.substring(21);
                    i++;

                    String scenariosCompleted = componentArr.get(i).trim();
                    scenariosCompleted = scenariosCompleted.substring(21);
                    i++;

                    String requestsCompleted = componentArr.get(i).trim();
                    requestsCompleted = requestsCompleted.substring(21);
                    i++;

                    String rps = componentArr.get(i).trim();
                    rps = rps.substring(10);
                    i++;
                    i++;

                    String min = componentArr.get(i).trim();
                    min = min.substring(5);
                    i++;

                    String max = componentArr.get(i).trim();
                    max = max.substring(5);
                    i++;

                    String med = componentArr.get(i).trim();
                    med = med.substring(8);
                    i++;

                    String p95 = componentArr.get(i).trim();
                    p95 = p95.substring(5);
                    i++;

                    String p99 = componentArr.get(i).trim();
                    p99 = p99.substring(5);
                    i++;

                    String scenarioCounts = "";
                    i++;
                    String nextLineScen = componentArr.get(i).trim();
                    while (!nextLineScen.equals("Codes:"))
                    {
                        scenarioCounts += nextLineScen + ",";
                        i++;
                        nextLineScen = componentArr.get(i).trim();
                    }
                    scenarioCounts = scenarioCounts.substring(0, scenarioCounts.length() - 1);

                    String codes = "";
                    i++;
                    String nextLineCodes = componentArr.get(i).trim();
                    while (!nextLineCodes.equals("") && !nextLineCodes.equals("Errors:"))
                    {
                        codes += nextLineCodes + ",";
                        i++;
                        nextLineCodes = componentArr.get(i).trim();
                    }
                    codes = codes.substring(0, codes.length() - 1);
                    
                    Report temp = new Report(header, Integer.parseInt(scenariosLaunched), Integer.parseInt(scenariosCompleted), Integer.parseInt(requestsCompleted), Double.parseDouble(rps), Double.parseDouble(min), Double.parseDouble(max), Double.parseDouble(med), Double.parseDouble(p95), Double.parseDouble(p99), scenarioCounts, codes);
                    
                    reports.add(temp);
                    numReports++;

                } else if (componentArr.get(i).substring(0, 7).equals("Started"))
                {
                    String phaseLine = componentArr.get(i);
                    int phaseNumber = Integer.parseInt(phaseLine.substring(14, phaseLine.indexOf(",")));
                    int duration = Integer.parseInt(phaseLine.substring(phaseLine.indexOf(":") + 2, phaseLine.indexOf("s @")));
                    String timeDate = phaseLine.substring(phaseLine.indexOf("@") + 2);
                    Phase phase = new Phase(phaseNumber, duration, timeDate);

                    phases.add(phase);
                    numPhases++;
                }
            } catch (StringIndexOutOfBoundsException e)
            {

            }
        }

        //now, capture all single test
        boolean firstFileSeen = false;
        boolean filesAreAverages = false;
        for (File file : new File("../../mayan/Mayan/gen/runs/" + overallFileName).listFiles())
        {

            String filename = file.getName();
            if (!filename.equals(overallFileName + ".txt"))
            {

                if (!firstFileSeen)
                {
                    firstFileSeen = true;
                    try
                    {
                        String fileNameChanged = filename.substring(0, filename.length() - 4);
                        fileNameChanged = fileNameChanged.substring(fileNameChanged.length() - 4, fileNameChanged.length() - 1);
                        if (fileNameChanged.equals("___"))
                        {
                            filesAreAverages = true;
                        }
                    } catch (StringIndexOutOfBoundsException e)
                    {
                    }
                }

                SingleTest test = new SingleTest(overallFileName, filename);
                tests.add(test);
                numTests++;

            }
        }
        parent.appendToFeedback("\t\t\tdone\n");
        if (filesAreAverages)
        {
            convertScenariosToAverages();
        }

    }

    public void convertScenariosToAverages()
    {
        parent.appendToFeedback("Converting to averages...\t");
        for (int i = 0; i < tests.size(); i++)
        {
            //s1 is our thing at i
            SingleTest test1 = tests.get(i), test2 = null, test3 = null;

            //get the common name without ___x
            String commonName = test1.getFilename();
            commonName = commonName.substring(0, commonName.length() - 4);
            commonName = commonName.substring(0, commonName.length() - 4);
            String dir = test1.getDir();

            //look through the rest of the array for the same commom name without ___x
            int j;
            boolean found = false;
            for (j = i + 1; j < tests.size() && !found; j++)
            {

                //get the current one
                SingleTest test = tests.get(j);
                //extract the name
                String testName = test.getFilename();
                testName = testName.substring(0, testName.length() - 4);
                testName = testName.substring(0, testName.length() - 4);

                //check if current name is same as common
                if (commonName.equals(testName))
                {
                    found = true;
                    test2 = tests.get(j);
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
                SingleTest test = tests.get(k);
                //extract the name
                String testName = test.getFilename();
                testName = testName.substring(0, testName.length() - 4);
                testName = testName.substring(0, testName.length() - 4);

                //check if current name is same as common
                if (commonName.equals(testName))
                {
                    found = true;
                    test3 = tests.get(k);
                    tests.remove(k);
                    numTests--;
                }
            }

            //now, group those 3 into one, finding averages, assume same size
            SingleTest test =  new SingleTest(dir, commonName); //this will throw exception
            //add phases
            for (int x = 0; x < test1.getNumPhases(); x++)
            {
                Phase phaseToCopy = test1.getPhases().get(x);
                Phase phase = new Phase(phaseToCopy.getPhaseNum(), phaseToCopy.getDuration(), phaseToCopy.getTimedate());
                test.addPhase(phase);
            }
            //add reports with averages
            for (int x = 0; x < test1.getNumReports() && x < test2.getNumReports() && x < test3.getNumReports(); x++)
            {
                Report reportToCopy1 = test1.getReports().get(x);
                Report reportToCopy2 = test2.getReports().get(x);
                Report reportToCopy3 = test3.getReports().get(x);

                String h = reportToCopy3.getHeader();
                int scenariosLaunched = (int) Math.round(avg3(reportToCopy1.getScenariosLaunched(), reportToCopy2.getScenariosLaunched(), reportToCopy3.getScenariosLaunched()));
                int scenariosCompleted = (int) Math.round(avg3(reportToCopy1.getScenariosCompleted(), reportToCopy2.getScenariosCompleted(), reportToCopy3.getScenariosCompleted()));
                int requestsCompleted = (int) Math.round(avg3(reportToCopy1.getRequestsCompleted(), reportToCopy2.getRequestsCompleted(), reportToCopy3.getRequestsCompleted()));
                double rps = roundTo(avg3(reportToCopy1.getRpsSent(), reportToCopy2.getRpsSent(), reportToCopy3.getRpsSent()), 2);
                double min = roundTo(avg3(reportToCopy1.getMin(), reportToCopy2.getMin(), reportToCopy3.getMin()), 2);
                double max = roundTo(avg3(reportToCopy1.getMax(), reportToCopy2.getMax(), reportToCopy3.getMax()), 2);
                double median = roundTo(avg3(reportToCopy1.getMedian(), reportToCopy2.getMedian(), reportToCopy3.getMedian()), 2);
                double p95 = roundTo(avg3(reportToCopy1.getP95(), reportToCopy2.getP95(), reportToCopy3.getP95()), 2);
                double p99 = roundTo(avg3(reportToCopy1.getP99(), reportToCopy2.getP99(), reportToCopy3.getP99()), 2);
                String scenarioCounts = "";
                boolean isSummary = false;
                if (reportToCopy1.isSummary())
                {
                    isSummary = true;
                    scenarioCounts = reportToCopy1.getScenarioCountsString();
                } else if (reportToCopy2.isSummary())
                {
                    isSummary = true;
                    scenarioCounts = reportToCopy2.getScenarioCountsString();
                } else if (reportToCopy3.isSummary())
                {
                    isSummary = true;
                    scenarioCounts = reportToCopy3.getScenarioCountsString();
                }

                String codes = reportToCopy3.getCodesString();

                Report newReport;
                if (isSummary)
                {
                    newReport = new Report(h, scenariosLaunched, scenariosCompleted, requestsCompleted, rps, min, max, median, p95, p99, scenarioCounts, codes);
                } else
                {
                    newReport = new Report(h, scenariosLaunched, scenariosCompleted, requestsCompleted, rps, min, max, median, p95, p99, codes);
                }

                test.addReport(newReport);
            }
            
            //make this grouped one into pos i
            tests.remove(i);
            numTests--;
            tests.add(i, test);
            numTests++;
        }

        parent.appendToFeedback("\t\t\t\tdone\n");
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

        parent.appendToFeedback("Initializing graph and grid for overall times...\t");
        
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
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setSize(900, 700);
        panel.setBounds(0, 0, 900, 1100);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(x, y));

        frame.add(panel);

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

        parent.appendToFeedback("\tdone\n");

        drawPhaseLines1(lblArr);

        drawGraph1(lblArr);

        parent.appendToFeedback("Rendering graph overall times graph(please be patient)...\t");
        
        //show frame
        frame.setVisible(true);
        
        parent.appendToFeedback("done\n");
    }

    public void drawPhaseLines1(JLabel lblArr[][])
    {
        int totalTime = (numReports - 1) * 10;

        int phaseAddingUp = 0;
        for (int i = 0; i < numPhases; i++)
        {
            Phase currentPhase = phases.get(i);
            int xPosBasedOnProgress = (int) Math.round((phaseAddingUp / (double) totalTime) * y);
            for (int j = 0; j < x; j++)
            {
                lblArr[j][xPosBasedOnProgress].setOpaque(true);
                lblArr[j][xPosBasedOnProgress].setBackground(Color.lightGray);
            }
            phaseAddingUp += currentPhase.getDuration();
        }

        int xPosBasedOnProgress = (int) Math.round((phaseAddingUp / (double) totalTime) * y);
        for (int j = 0; j < x; j++)
        {
            lblArr[j][xPosBasedOnProgress].setOpaque(true);
            lblArr[j][xPosBasedOnProgress].setBackground(Color.lightGray);
        }

    }

    public void drawGraph1(JLabel lblArr[][])
    {
        parent.appendToFeedback("Starting to draw overall times graph...\t");

        Report currentReport = reports.get(0);
        //draw first point if need to display
        if (display1[0])
        {
            lblArr[transX1(currentReport.getMin())][transY1(1)].setOpaque(true);
            lblArr[transX1(currentReport.getMin())][transY1(1)].setBackground(colArr[0]);
        }
        if (display1[1])
        {
            lblArr[transX1(currentReport.getMedian())][transY1(1)].setOpaque(true);
            lblArr[transX1(currentReport.getMedian())][transY1(1)].setBackground(colArr[1]);
        }
        if (display1[2])
        {
            lblArr[transX1(currentReport.getP95())][transY1(1)].setOpaque(true);
            lblArr[transX1(currentReport.getP95())][transY1(1)].setBackground(colArr[2]);
        }
        if (display1[3])
        {
            lblArr[transX1(currentReport.getP99())][transY1(1)].setOpaque(true);
            lblArr[transX1(currentReport.getP99())][transY1(1)].setBackground(colArr[3]);
        }
        if (display1[4])
        {
            lblArr[transX1(currentReport.getMax())][transY1(1)].setOpaque(true);
            lblArr[transX1(currentReport.getMax())][transY1(1)].setBackground(colArr[4]);
        }

        //for each report, draw the lines if they should be drawn
        for (int i = 1; i < numReports - 1; i++)
        {
            currentReport = reports.get(i);
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
                lblArr[transX1(currentReport.getMin())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(currentReport.getMin())][transY1(i + 1)].setBackground(colArr[0]);
            }
            if (display1[1])
            {
                lblArr[transX1(currentReport.getMedian())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(currentReport.getMedian())][transY1(i + 1)].setBackground(colArr[1]);
            }
            if (display1[2])
            {
                lblArr[transX1(currentReport.getP95())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(currentReport.getP95())][transY1(i + 1)].setBackground(colArr[2]);
            }
            if (display1[3])
            {
                lblArr[transX1(currentReport.getP99())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(currentReport.getP99())][transY1(i + 1)].setBackground(colArr[3]);
            }
            if (display1[4])
            {
                lblArr[transX1(currentReport.getMax())][transY1(i + 1)].setOpaque(true);
                lblArr[transX1(currentReport.getMax())][transY1(i + 1)].setBackground(colArr[4]);
            }

        }
        parent.appendToFeedback("\t\t\tdone\n");
    }

    public int transX1(double in)
    {
        int toReturn = x - ((int) (((Math.round(in) - min) / (double) (max - min)) * x));
        if (toReturn < 0)
        {
            return 0;
        } else if (toReturn >= x)
        {
            return x - 1;
        } else
        {
            return toReturn;
        }

    }

    public int transY1(int i)
    {
        int toReturn =  (y / (numReports - 1)) * i;
        if (toReturn < 0)
        {
            toReturn = 0;
        } else if (toReturn >= y)
        {
            toReturn = y - 1;
        }
        return toReturn;
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

        int dist1 = (new1 - old1);
        int dist2 = new2 - old2;
        for (int j = 1; j < dist2; j++)
        {
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setOpaque(true);
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setBackground(colArrOpaque[caseOf]);

        }
    }

    public double getMinFromReports1()
    {
        //find lowest needed
        int bottomMeasure = 0;
        while (!display1[bottomMeasure])
        {
            bottomMeasure++;
        }
        //j is at thing we should use for min
        double minVal = 0;
        switch (bottomMeasure)
        {
            case 0:
                minVal = reports.get(0).getMin();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getMin())
                    {
                        minVal = reports.get(i).getMin();
                    }
                }
                break;
            case 1:
                minVal = reports.get(0).getMedian();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getMedian())
                    {
                        minVal = reports.get(i).getMedian();
                    }
                }
                break;
            case 2:
                minVal = reports.get(0).getP95();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getP95())
                    {
                        minVal = reports.get(i).getP95();
                    }
                }
                break;
            case 3:
                minVal = reports.get(0).getP99();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getP99())
                    {
                        minVal = reports.get(i).getP99();
                    }
                }
                break;
            case 4:
                minVal = reports.get(0).getMax();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getMax())
                    {
                        minVal = reports.get(i).getMax();
                    }
                }
                break;
            default:
                minVal = reports.get(0).getMin();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getMin())
                    {
                        minVal = reports.get(i).getMin();
                    }
                }

        }
        return minVal;

    }

    public double getMaxFromReports1()
    {
        //find lowest needed
        int topMeasure = display1.length - 1;
        while (!display1[topMeasure])
        {
            topMeasure--;
        }
        //j is at thing we should use for min
        double maxVal = 0;
        switch (topMeasure)
        {
            case 4:
                maxVal = reports.get(0).getMax();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getMax())
                    {
                        maxVal = reports.get(i).getMax();
                    }
                }
                break;
            case 3:
                maxVal = reports.get(0).getP99();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getP99())
                    {
                        maxVal = reports.get(i).getP99();
                    }
                }
                break;
            case 2:
                maxVal = reports.get(0).getP95();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getP95())
                    {
                        maxVal = reports.get(i).getP95();
                    }
                }
                break;
            case 1:
                maxVal = reports.get(0).getMedian();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getMedian())
                    {
                        maxVal = reports.get(i).getMedian();
                    }
                }
                break;
            case 0:
                maxVal = reports.get(0).getMin();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getMin())
                    {
                        maxVal = reports.get(i).getMin();
                    }
                }
                break;
            default:
                maxVal = reports.get(0).getMax();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getMax())
                    {
                        maxVal = reports.get(i).getMax();
                    }
                }
        }
        return maxVal;
    }

    public int getTotalPhaseTime()
    {
        int phaseTimeCount = 0;
        for (int i = 0; i < numPhases; i++)
        {
            phaseTimeCount += phases.get(i).getDuration();
        }
        return phaseTimeCount;
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

        parent.appendToFeedback("Initializing overall scenarios and reqs graph and grid...\t");
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
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setSize(900, 700);
        panel.setBounds(0, 0, 900, 1100);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(x, y));

        frame.add(panel);

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

        parent.appendToFeedback("done\n");

        drawPhaseLines2(lblArr);

        drawGraph2(lblArr);

        parent.appendToFeedback("Rendering overall scens and reqs graph (please be patient)...\t");
        
        //show frame
        frame.setVisible(true);
        
        parent.appendToFeedback("done\n");
    }

    public void drawPhaseLines2(JLabel lblArr[][])
    {
        int totalTime = (numReports - 1) * 10;

        int phaseAddingUp = 0;
        for (int i = 0; i < numPhases; i++)
        {
            Phase currentPhase = phases.get(i);
            int xPosBasedOnProgress = (int) Math.round((phaseAddingUp / (double) totalTime) * y);
            for (int j = 0; j < x; j++)
            {
                lblArr[j][xPosBasedOnProgress].setOpaque(true);
                lblArr[j][xPosBasedOnProgress].setBackground(Color.lightGray);
            }
            phaseAddingUp += currentPhase.getDuration();
        }

        int xPosBasedOnProgress = (int) Math.round((phaseAddingUp / (double) totalTime) * y);
        for (int j = 0; j < x; j++)
        {
            lblArr[j][xPosBasedOnProgress].setOpaque(true);
            lblArr[j][xPosBasedOnProgress].setBackground(Color.lightGray);
        }

    }

    public void drawGraph2(JLabel lblArr[][])
    {
        parent.appendToFeedback("Starting to draw overall scenarios and requests graph...\t");

        Report currentReport = reports.get(0);
        //draw first point if need to display
        if (display2[0])
        {
            lblArr[transX2(currentReport.getScenariosLaunched())][transY2(1)].setOpaque(true);
            lblArr[transX2(currentReport.getScenariosLaunched())][transY2(1)].setBackground(colArr[0]);
        }
        if (display2[1])
        {
            lblArr[transX2(currentReport.getScenariosCompleted())][transY2(1)].setOpaque(true);
            lblArr[transX2(currentReport.getScenariosCompleted())][transY2(1)].setBackground(colArr[1]);
        }
        if (display2[2])
        {
            lblArr[transX2(currentReport.getRequestsCompleted())][transY2(1)].setOpaque(true);
            lblArr[transX2(currentReport.getRequestsCompleted())][transY2(1)].setBackground(colArr[2]);
        }

        //for each report, draw the lines if they should be drawn
        for (int i = 1; i < numReports - 1; i++)
        {
            currentReport = reports.get(i);
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
                lblArr[transX2(currentReport.getScenariosLaunched())][transY2(i + 1)].setOpaque(true);
                lblArr[transX2(currentReport.getScenariosLaunched())][transY2(i + 1)].setBackground(colArr[0]);
            }
            if (display2[1])
            {
                lblArr[transX2(currentReport.getScenariosCompleted())][transY2(i + 1)].setOpaque(true);
                lblArr[transX2(currentReport.getScenariosCompleted())][transY2(i + 1)].setBackground(colArr[1]);
            }
            if (display2[2])
            {
                lblArr[transX2(currentReport.getRequestsCompleted())][transY2(i + 1)].setOpaque(true);
                lblArr[transX2(currentReport.getRequestsCompleted())][transY2(i + 1)].setBackground(colArr[2]);
            }

        }
        parent.appendToFeedback("done\n");
    }

    public int transX2(double in)
    {
        int toReturn = x - ((int) (((Math.round(in) - min) / (double) (max - min)) * x));
        if (toReturn < 0)
        {
            return 0;
        } else if (toReturn >= x)
        {
            return x - 1;
        } else
        {
            return toReturn;
        }

    }

    public int transY2(int i)
    {
        int toReturn = (y / (numReports - 1)) * i;
        if (toReturn < 0)
        {
            toReturn = 0;
        } else if (toReturn >= y)
        {
            toReturn = y - 1;
        }
        return toReturn;
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

        int dist1 = (new1 - old1);
        int dist2 = new2 - old2;
        for (int j = 1; j < dist2; j++)
        {
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setOpaque(true);
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setBackground(colArrOpaque[caseOf]);
        }
    }

    public double getMinFromReports2()
    {
        //find lowest needed
        int bottomMeasure = 0;
        while (!display2[bottomMeasure])
        {
            bottomMeasure++;
        }
        //j is at thing we should use for min
        double minVal = 0;
        switch (bottomMeasure)
        {
            case 0:
                minVal = reports.get(0).getScenariosLaunched();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getScenariosLaunched())
                    {
                        minVal = reports.get(i).getScenariosLaunched();
                    }
                }
                break;
            case 1:
                minVal = reports.get(0).getScenariosCompleted();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getScenariosCompleted())
                    {
                        minVal = reports.get(i).getScenariosCompleted();
                    }
                }
                break;
            case 2:
                minVal = reports.get(0).getRequestsCompleted();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getRequestsCompleted())
                    {
                        minVal = reports.get(i).getRequestsCompleted();
                    }
                }
                break;
            default:
                minVal = reports.get(0).getScenariosLaunched();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (minVal > reports.get(i).getScenariosLaunched())
                    {
                        minVal = reports.get(i).getScenariosLaunched();
                    }
                }

        }
        return minVal;

    }

    public double getMaxFromReports2()
    {
        //find lowest needed
        int topMeasure = display2.length - 1;
        while (!display2[topMeasure])
        {
            topMeasure--;
        }
        //j is at thing we should use for min
        double maxVal = 0;
        switch (topMeasure)
        {
            case 2:
                maxVal = reports.get(0).getRequestsCompleted();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getRequestsCompleted())
                    {
                        maxVal = reports.get(i).getRequestsCompleted();
                    }
                }
                break;
            case 1:
                maxVal = reports.get(0).getScenariosCompleted();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getScenariosCompleted())
                    {
                        maxVal = reports.get(i).getScenariosCompleted();
                    }
                }
                break;
            case 0:
                maxVal = reports.get(0).getScenariosLaunched();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getScenariosLaunched())
                    {
                        maxVal = reports.get(i).getScenariosLaunched();
                    }
                }
                break;
            default:
                maxVal = reports.get(0).getRequestsCompleted();
                for (int i = 1; i < numReports - 1; i++)
                {
                    if (maxVal < reports.get(i).getRequestsCompleted())
                    {
                        maxVal = reports.get(i).getRequestsCompleted();
                    }
                }
        }
        return maxVal;
    }

    public Report getSummaryReport()
    {
        return reports.get(numReports - 1);
    }

    public String getLagReport(boolean showSome)
    {
        String buildUp = "";
        for (int i = 0; i < numTests; i++)
        {
            if (showSome)
            {
                String checkBeforeAddingOn = tests.get(i).getLagReport();
                if (!checkBeforeAddingOn.substring(checkBeforeAddingOn.length() - 2, checkBeforeAddingOn.length()).equals("-\n"))
                {
                    buildUp += checkBeforeAddingOn;
                }

            } else
            {
                buildUp += tests.get(i).getLagReport();
            }
        }
        return buildUp;
    }

    public double roundTo(double num, int decimalPlaces)
    {
        int toTimesBy = (int) Math.pow(10, decimalPlaces);
        num *= toTimesBy;
        num = Math.round(num);
        num /= toTimesBy;
        return num;
    }

    public void drawGraph3(String s, String filename)
    {
        int reportPos = 0;
        while (!tests.get(reportPos).getFilename().equals(filename))
        {
            reportPos++;
        }
        SingleTest report = tests.get(reportPos);

        //decide what to display
        toDisplay3 = s;
        display3 = new boolean[numDisplayOptions3];
        for (int i = 0; i < numDisplayOptions3; i++)
        {
            display3[i] = false;
            if (toDisplay3.contains(i + ""))
            {
                display3[i] = true;
            }
        }

        parent.appendToFeedback("Initializing individual test graph and grid...\t");
        //create frame with settings
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1300, 700);

        //add x and y axes
        min = (int) getMinFromReports3(report);
        max = (int) getMaxFromReports3(report);
        //JLabel n2 = new JLabel("Time in ms");
        //frame.add(n2, BorderLayout.WEST);
        JLabel n1 = new JLabel(min + "");
        frame.add(n1, BorderLayout.PAGE_END);
        JLabel n3 = new JLabel(max + "");
        frame.add(n3, BorderLayout.PAGE_START);

        //create panel and gridlayout with it
        JPanel panel = new JPanel();
        panel.setBackground(Color.white);
        panel.setSize(900, 700);
        panel.setBounds(0, 0, 900, 1100);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setLayout(new GridLayout(x, y));

        frame.add(panel);

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

        parent.appendToFeedback("\t\tdone\n");

        drawPhaseLines3(lblArr, report);

        drawGraph3(lblArr, report);

        parent.appendToFeedback("Rendering individual test graph (please be patient)...\t");
        
        //show frame
        frame.setVisible(true);
        
        parent.appendToFeedback("\tdone\n");
    }

    public void drawPhaseLines3(JLabel lblArr[][], SingleTest report)
    {
        int totalTime = (report.getNumReports() - 1) * 10;

        int phaseAddingUp = 0;
        SingleTest first = tests.get(0);
        for (int i = 0; i < report.getNumPhases(); i++)
        {
            Phase currentPhase = first.getPhases().get(i);
            int xPosBasedOnProgress = (int) Math.round((phaseAddingUp / (double) totalTime) * y);
            for (int j = 0; j < x; j++)
            {
                lblArr[j][xPosBasedOnProgress].setOpaque(true);
                lblArr[j][xPosBasedOnProgress].setBackground(Color.lightGray);
            }
            phaseAddingUp += currentPhase.getDuration();
        }

        int xPosBasedOnProgress = (int) Math.round((phaseAddingUp / (double) totalTime) * y);
        for (int j = 0; j < x; j++)
        {
            lblArr[j][xPosBasedOnProgress].setOpaque(true);
            lblArr[j][xPosBasedOnProgress].setBackground(Color.lightGray);
        }

    }

    public void drawGraph3(JLabel lblArr[][], SingleTest report)
    {
        parent.appendToFeedback("Starting to draw individual test graph...\t");

        Report currentReport = report.getReports().get(0);
        //draw first point if need to display
        if (display3[0])
        {
            lblArr[transX3(currentReport.getMin())][transY3(1, report)].setOpaque(true);
            lblArr[transX3(currentReport.getMin())][transY3(1, report)].setBackground(colArr[0]);
        }
        if (display3[1])
        {
            lblArr[transX3(currentReport.getMedian())][transY3(1, report)].setOpaque(true);
            lblArr[transX3(currentReport.getMedian())][transY3(1, report)].setBackground(colArr[1]);
        }
        if (display3[2])
        {
            lblArr[transX3(currentReport.getP95())][transY3(1, report)].setOpaque(true);
            lblArr[transX3(currentReport.getP95())][transY3(1, report)].setBackground(colArr[2]);
        }
        if (display3[3])
        {
            lblArr[transX3(currentReport.getP99())][transY3(1, report)].setOpaque(true);
            lblArr[transX3(currentReport.getP99())][transY3(1, report)].setBackground(colArr[3]);
        }
        if (display3[4])
        {
            lblArr[transX3(currentReport.getMax())][transY3(1, report)].setOpaque(true);
            lblArr[transX3(currentReport.getMax())][transY3(1, report)].setBackground(colArr[4]);
        }

        //for each report, draw the lines if they should be drawn
        for (int i = 1; i < report.getNumReports() - 1; i++)
        {
            currentReport = report.getReports().get(i);
            //draw a connecting line
            for (int j = 0; j < numDisplayOptions3; j++)
            {
                if (display3[j])
                {
                    drawLine3(lblArr, i, j, report);
                }
            }

            //draw the next point
            if (display3[0])
            {
                lblArr[transX3(currentReport.getMin())][transY3(i + 1, report)].setOpaque(true);
                lblArr[transX3(currentReport.getMin())][transY3(i + 1, report)].setBackground(colArr[0]);
            }
            if (display3[1])
            {
                lblArr[transX3(currentReport.getMedian())][transY3(i + 1, report)].setOpaque(true);
                lblArr[transX3(currentReport.getMedian())][transY3(i + 1, report)].setBackground(colArr[1]);
            }
            if (display3[2])
            {
                lblArr[transX3(currentReport.getP95())][transY3(i + 1, report)].setOpaque(true);
                lblArr[transX3(currentReport.getP95())][transY3(i + 1, report)].setBackground(colArr[2]);
            }
            if (display3[3])
            {
                lblArr[transX3(currentReport.getP99())][transY3(i + 1, report)].setOpaque(true);
                lblArr[transX3(currentReport.getP99())][transY3(i + 1, report)].setBackground(colArr[3]);
            }
            if (display3[4])
            {
                lblArr[transX3(currentReport.getMax())][transY3(i + 1, report)].setOpaque(true);
                lblArr[transX3(currentReport.getMax())][transY3(i + 1, report)].setBackground(colArr[4]);
            }

        }
        parent.appendToFeedback("\t\tdone\n");
    }

    public int transX3(double in)
    {
        int toReturn = x - ((int) (((Math.round(in) - min) / (double) (max - min)) * x));
        if (toReturn < 0)
        {
            return 0;
        } else if (toReturn >= x)
        {
            return x - 1;
        } else
        {
            return toReturn;
        }

    }

    public int transY3(int i, SingleTest report)
    {
        int toReturn = (y / (report.getNumReports() - 1)) * i;
        if (toReturn < 0)
        {
            toReturn = 0;
        } else if (toReturn >= y)
        {
            toReturn = y - 1;
        }
        return toReturn;
    }

    public void drawLine3(JLabel lblArr[][], int i, int caseOf, SingleTest report)
    {
        int old1, new1;
        switch (caseOf)
        {
            case 0:
                old1 = transX3(report.getReports().get(i - 1).getMin());
                new1 = transX3(Math.round(report.getReports().get(i).getMin()));
                break;
            case 1:
                old1 = transX3(report.getReports().get(i - 1).getMedian());
                new1 = transX3(report.getReports().get(i).getMedian());
                break;
            case 2:
                old1 = transX3(report.getReports().get(i - 1).getP95());
                new1 = transX3(report.getReports().get(i).getP95());
                break;
            case 3:
                old1 = transX3(report.getReports().get(i - 1).getP99());
                new1 = transX3(report.getReports().get(i).getP99());
                break;
            case 4:
                old1 = transX3(report.getReports().get(i - 1).getMax());
                new1 = transX3(report.getReports().get(i).getMax());
                break;
            default:
                old1 = transX3(report.getReports().get(i - 1).getMedian());
                new1 = transX3(report.getReports().get(i).getMedian());
        }

        int old2 = (int) ((y / (report.getNumReports() - 1)) + (double) y / (report.getNumReports() - 1) * (i - 1));
        int new2 = (int) ((y / (report.getNumReports() - 1)) + (double) y / (report.getNumReports() - 1) * i);

        int dist1 = (new1 - old1);
        int dist2 = new2 - old2;
        for (int j = 1; j < dist2; j++)
        {
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setOpaque(true);
            lblArr[(int) Math.round(old1 + ((double) dist1 / dist2) * (j))][old2 + j].setBackground(colArrOpaque[caseOf]);

        }
    }

    public double getMinFromReports3(SingleTest report)
    {

        //find lowest needed
        int lowestMeasure = 0;
        while (!display3[lowestMeasure])
        {
            lowestMeasure++;
        }
        //j is at thing we should use for min
        double minVal = 0;
        switch (lowestMeasure)
        {
            case 0:
                minVal = report.getReports().get(0).getMin();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (minVal > report.getReports().get(i).getMin())
                    {
                        minVal = report.getReports().get(i).getMin();
                    }
                }
                break;
            case 1:
                minVal = report.getReports().get(0).getMedian();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (minVal > report.getReports().get(i).getMedian())
                    {
                        minVal = report.getReports().get(i).getMedian();
                    }
                }
                break;
            case 2:
                minVal = report.getReports().get(0).getP95();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (minVal > report.getReports().get(i).getP95())
                    {
                        minVal = report.getReports().get(i).getP95();
                    }
                }
                break;
            case 3:
                minVal = report.getReports().get(0).getP99();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (minVal > report.getReports().get(i).getP99())
                    {
                        minVal = report.getReports().get(i).getP99();
                    }
                }
                break;
            case 4:
                minVal = report.getReports().get(0).getMax();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (minVal > report.getReports().get(i).getMax())
                    {
                        minVal = report.getReports().get(i).getMax();
                    }
                }
                break;
            default:
                minVal = report.getReports().get(0).getMin();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (minVal > report.getReports().get(i).getMin())
                    {
                        minVal = report.getReports().get(i).getMin();
                    }
                }

        }
        return minVal;

    }

    public double getMaxFromReports3(SingleTest report)
    {
        //find lowest needed
        int topMeasure = display3.length - 1;
        while (!display3[topMeasure])
        {
            topMeasure--;
        }
        //j is at thing we should use for min
        double maxVal = 0;
        switch (topMeasure)
        {
            case 4:
                maxVal = report.getReports().get(0).getMax();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (maxVal < report.getReports().get(i).getMax())
                    {
                        maxVal = report.getReports().get(i).getMax();
                    }
                }
                break;
            case 3:
                maxVal = report.getReports().get(0).getP99();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (maxVal < report.getReports().get(i).getP99())
                    {
                        maxVal = report.getReports().get(i).getP99();
                    }
                }
                break;
            case 2:
                maxVal = report.getReports().get(0).getP95();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (maxVal < report.getReports().get(i).getP95())
                    {
                        maxVal = report.getReports().get(i).getP95();
                    }
                }
                break;
            case 1:
                maxVal = report.getReports().get(0).getMedian();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (maxVal < report.getReports().get(i).getMedian())
                    {
                        maxVal = report.getReports().get(i).getMedian();
                    }
                }
                break;
            case 0:
                maxVal = report.getReports().get(0).getMin();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (maxVal < report.getReports().get(i).getMin())
                    {
                        maxVal = report.getReports().get(i).getMin();
                    }
                }
                break;
            default:
                maxVal = report.getReports().get(0).getMax();
                for (int i = 1; i < report.getNumReports() - 1; i++)
                {
                    if (maxVal < report.getReports().get(i).getMax())
                    {
                        maxVal = report.getReports().get(i).getMax();
                    }
                }
        }
        return maxVal;
    }

    public String[] getScenNames()
    {
        String[] scenNames = new String[numTests];
        for (int i = 0; i < numTests; i++)
        {
            scenNames[i] = tests.get(i).getFilename();
        }
        return scenNames;
    }

}
