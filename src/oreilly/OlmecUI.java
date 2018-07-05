package oreilly;

import java.awt.Color;
import java.io.FileNotFoundException;
import javax.swing.JOptionPane;

public class OlmecUI extends javax.swing.JFrame
{

    boolean progress = true;
    Analyser a;
    private String filename;
    public static final Color[] colArr = new Color[]
    {
        Color.red, Color.blue, new Color(0, 153, 0), new Color(255, 102, 0), new Color(102, 0, 153), Color.orange
    };

    public OlmecUI(String filename) throws FileNotFoundException
    {
        initComponents();
        this.filename = filename;
        lblDesc.setText("The report for the main Artillery run (" + filename + ".txt)");
        lblDesc1.setText("Individual scenario tests");
        a = new Analyser(filename, this);
        String[] scenNames = a.getScenNames();
        cbxPickScenario.removeAllItems();
        for(int i = 0; i < scenNames.length; i++)
        {
            cbxPickScenario.addItem(scenNames[i]);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jLabel1 = new javax.swing.JLabel();
        btnGraphResponseTimes = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaFeedback = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cbxMin = new javax.swing.JCheckBox();
        cbxMedian = new javax.swing.JCheckBox();
        cbxP95 = new javax.swing.JCheckBox();
        cbxP99 = new javax.swing.JCheckBox();
        cbxMax = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        btnGraphScenReq = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cbxSL = new javax.swing.JCheckBox();
        cbxSC = new javax.swing.JCheckBox();
        cbxRC = new javax.swing.JCheckBox();
        btnShow = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        lblDesc = new javax.swing.JLabel();
        btnShow1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        lblDesc1 = new javax.swing.JLabel();
        cbxOnlyShow = new javax.swing.JCheckBox();
        cbxMax1 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        btnGraphResponseTimes1 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        cbxMin1 = new javax.swing.JCheckBox();
        cbxMedian1 = new javax.swing.JCheckBox();
        cbxP96 = new javax.swing.JCheckBox();
        cbxP100 = new javax.swing.JCheckBox();
        cbxPickScenario = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OLMEC ANALYSER");

        btnGraphResponseTimes.setText("Graph");
        btnGraphResponseTimes.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGraphResponseTimesActionPerformed(evt);
            }
        });

        txaFeedback.setEditable(false);
        txaFeedback.setColumns(20);
        txaFeedback.setRows(5);
        jScrollPane1.setViewportView(txaFeedback);

        jLabel4.setText("What to Graph");

        cbxMin.setForeground(colArr[0]);
        cbxMin.setSelected(true);
        cbxMin.setText("Min");

        cbxMedian.setForeground(colArr[1]);
        cbxMedian.setSelected(true);
        cbxMedian.setText("Median");

        cbxP95.setForeground(colArr[2]);
        cbxP95.setSelected(true);
        cbxP95.setText("P95");

        cbxP99.setForeground(colArr[3]);
        cbxP99.setSelected(true);
        cbxP99.setText("P99");

        cbxMax.setForeground(colArr[4]);
        cbxMax.setSelected(true);
        cbxMax.setText("Max");

        jLabel5.setText("OVERALL RESPONSE TIMES");

        btnGraphScenReq.setText("Graph");
        btnGraphScenReq.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGraphScenReqActionPerformed(evt);
            }
        });

        jLabel6.setText("OVERALL SCENARIOS AND REQUESTS");

        jLabel7.setText("What to Graph");

        cbxSL.setForeground(colArr[0]);
        cbxSL.setSelected(true);
        cbxSL.setText("S Launched");

        cbxSC.setForeground(colArr[1]);
        cbxSC.setSelected(true);
        cbxSC.setText("S Completed");

        cbxRC.setForeground(colArr[2]);
        cbxRC.setSelected(true);
        cbxRC.setText("R Completed");

        btnShow.setText("Show");
        btnShow.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnShowActionPerformed(evt);
            }
        });

        jLabel8.setText("OVERALL SUMMARY REPORT");

        lblDesc.setText("jLabel2");

        btnShow1.setText("Show");
        btnShow1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnShow1ActionPerformed(evt);
            }
        });

        jLabel9.setText("INDIVIDUAL SCENARIO TESTS");

        lblDesc1.setText("jLabel2");

        cbxOnlyShow.setSelected(true);
        cbxOnlyShow.setText("Only show scenarios with lag");

        cbxMax1.setForeground(colArr[4]);
        cbxMax1.setSelected(true);
        cbxMax1.setText("Max");

        jLabel10.setText("GRAPH INDIVIDUAL SCENARIO TEST");

        btnGraphResponseTimes1.setText("Graph");
        btnGraphResponseTimes1.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnGraphResponseTimes1ActionPerformed(evt);
            }
        });

        jLabel11.setText("What to Graph");

        cbxMin1.setForeground(colArr[0]);
        cbxMin1.setSelected(true);
        cbxMin1.setText("Min");

        cbxMedian1.setForeground(colArr[1]);
        cbxMedian1.setSelected(true);
        cbxMedian1.setText("Median");

        cbxP96.setForeground(colArr[2]);
        cbxP96.setSelected(true);
        cbxP96.setText("P95");

        cbxP100.setForeground(colArr[3]);
        cbxP100.setSelected(true);
        cbxP100.setText("P99");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbxPickScenario, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSL)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxSC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxRC)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGraphScenReq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMin)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMedian)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxP95)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxP99)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMax)
                        .addGap(24, 24, 24)
                        .addComponent(btnGraphResponseTimes, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDesc)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShow, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDesc1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxOnlyShow)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnShow1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9)
                            .addComponent(jLabel10))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMin1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMedian1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxP96)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxP100)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cbxMax1)
                        .addGap(24, 24, 24)
                        .addComponent(btnGraphResponseTimes1, javax.swing.GroupLayout.DEFAULT_SIZE, 80, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxMin)
                    .addComponent(cbxMedian)
                    .addComponent(cbxP95)
                    .addComponent(cbxP99)
                    .addComponent(cbxMax)
                    .addComponent(btnGraphResponseTimes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(btnGraphScenReq)
                    .addComponent(cbxSL)
                    .addComponent(cbxSC)
                    .addComponent(cbxRC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShow)
                    .addComponent(lblDesc))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnShow1)
                    .addComponent(lblDesc1)
                    .addComponent(cbxOnlyShow))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxPickScenario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(cbxMin1)
                    .addComponent(cbxMedian1)
                    .addComponent(cbxP96)
                    .addComponent(cbxP100)
                    .addComponent(cbxMax1)
                    .addComponent(btnGraphResponseTimes1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 235, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGraphResponseTimesActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGraphResponseTimesActionPerformed
    {//GEN-HEADEREND:event_btnGraphResponseTimesActionPerformed
        String s = "";
        if (cbxMin.isSelected())
        {
            s += "0,";
        }
        if (cbxMedian.isSelected())
        {
            s += "1,";
        }
        if (cbxP95.isSelected())
        {
            s += "2,";
        }
        if (cbxP99.isSelected())
        {
            s += "3,";
        }
        if (cbxMax.isSelected())
        {
            s += "4,";
        }
        if (s.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Select at least one aspect to graph!");
        } else
        {
            s = s.substring(0, s.length() - 1);
        }

        a.drawGraph1(s);
    }//GEN-LAST:event_btnGraphResponseTimesActionPerformed

    private void btnGraphScenReqActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGraphScenReqActionPerformed
    {//GEN-HEADEREND:event_btnGraphScenReqActionPerformed
        String s = "";
        if (cbxSL.isSelected())
        {
            s += "0,";
        }
        if (cbxSC.isSelected())
        {
            s += "1,";
        }
        if (cbxRC.isSelected())
        {
            s += "2,";
        }
        if (s.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Select at least one aspect to graph!");
        } else
        {
            s = s.substring(0, s.length() - 1);
        }

        a.drawGraph2(s);
    }//GEN-LAST:event_btnGraphScenReqActionPerformed

    private void btnShowActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnShowActionPerformed
    {//GEN-HEADEREND:event_btnShowActionPerformed
        new SummaryUI(a.getSummaryReport(), filename).setVisible(true);
    }//GEN-LAST:event_btnShowActionPerformed

    private void btnShow1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnShow1ActionPerformed
    {//GEN-HEADEREND:event_btnShow1ActionPerformed
        new SingleTestUI(a.getLagReport(cbxOnlyShow.isSelected())).setVisible(true);
    }//GEN-LAST:event_btnShow1ActionPerformed

    private void btnGraphResponseTimes1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnGraphResponseTimes1ActionPerformed
    {//GEN-HEADEREND:event_btnGraphResponseTimes1ActionPerformed
        String s = "";
        if (cbxMin1.isSelected())
        {
            s += "0,";
        }
        if (cbxMedian1.isSelected())
        {
            s += "1,";
        }
        if (cbxP96.isSelected())
        {
            s += "2,";
        }
        if (cbxP100.isSelected())
        {
            s += "3,";
        }
        if (cbxMax1.isSelected())
        {
            s += "4,";
        }
        if (s.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Select at least one aspect to graph!");
        } else
        {
            s = s.substring(0, s.length() - 1);
        }

        a.drawGraph3(s, (String) cbxPickScenario.getSelectedItem());
    }//GEN-LAST:event_btnGraphResponseTimes1ActionPerformed

    public void appendToFeedback(String text)
    {
        if (progress)
        {
            System.out.print(text);
        }
        txaFeedback.setText(txaFeedback.getText() + text);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGraphResponseTimes;
    private javax.swing.JButton btnGraphResponseTimes1;
    private javax.swing.JButton btnGraphScenReq;
    private javax.swing.JButton btnShow;
    private javax.swing.JButton btnShow1;
    private javax.swing.JCheckBox cbxMax;
    private javax.swing.JCheckBox cbxMax1;
    private javax.swing.JCheckBox cbxMedian;
    private javax.swing.JCheckBox cbxMedian1;
    private javax.swing.JCheckBox cbxMin;
    private javax.swing.JCheckBox cbxMin1;
    private javax.swing.JCheckBox cbxOnlyShow;
    private javax.swing.JCheckBox cbxP100;
    private javax.swing.JCheckBox cbxP95;
    private javax.swing.JCheckBox cbxP96;
    private javax.swing.JCheckBox cbxP99;
    private javax.swing.JComboBox<String> cbxPickScenario;
    private javax.swing.JCheckBox cbxRC;
    private javax.swing.JCheckBox cbxSC;
    private javax.swing.JCheckBox cbxSL;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDesc;
    private javax.swing.JLabel lblDesc1;
    private javax.swing.JTextArea txaFeedback;
    // End of variables declaration//GEN-END:variables
}
