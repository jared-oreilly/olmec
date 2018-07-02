/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

import java.awt.*;
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
    private int numReports;
    
    public Analyser(String filename) throws FileNotFoundException
    {
        reports = new ArrayList<Report>();
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
                }
            } catch (StringIndexOutOfBoundsException e)
            {

            }
        }

        //reports arraylist now filled
        for (int i = 0; i < reports.size(); i++)
        {
            System.out.println(reports.get(i));
        }
        
        
        drawGraph();

    }
    
    public void drawGraph()
    {
        //create frame with settings
        JFrame frame = new JFrame("Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        
        //add x and y axes
        JLabel n2 = new JLabel("Y-axis");
        frame.add(n2, BorderLayout.WEST);
        JLabel n1 = new JLabel("X-axis");
        frame.add(n1, BorderLayout.PAGE_END);
        
        //create panel and gridlayout with it
        int x = 50, y = 50;
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(x, y));
        panel.setSize(350, 250);
        panel.setBounds(0, 0, 350, 250);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        frame.add(panel);
        
        //make label array to fill the grid, empty cells
        JLabel lblArr[][] = new JLabel[x][y];
        for(int i = 0; i < x; i++)
        {
            for(int j = 0; j < y; j++)
            {
                lblArr[i][j] = new JLabel("");
                
                panel.add(lblArr[i][j]);
            }
        }
        
        //draw something
        for(int i = 0; i < x; i++)
        {
            lblArr[i][i].setText(".");
        }
        
        
        
        //show frame
        frame.setResizable(false);
        frame.setVisible(true);
        System.out.println("hi");
    }
    
    
}
