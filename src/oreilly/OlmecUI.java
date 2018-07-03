/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oreilly;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileNotFoundException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jared.oreilly
 */
public class OlmecUI extends javax.swing.JFrame
{
    private Color[] colArr;
    Analyser a;
    /**
     * Creates new form OlmecUI
     */
    public OlmecUI()
    {
        colArr = new Color[]
        {
            Color.red, Color.blue, new Color(0, 153, 0), new Color(255, 102, 0), new Color(102, 0, 153), Color.orange
        };
        initComponents();
        
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
        jLabel2 = new javax.swing.JLabel();
        txfAnalyse = new javax.swing.JTextField();
        btnAnalyse = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaFeedback = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        cbxMin = new javax.swing.JCheckBox();
        cbxMedian = new javax.swing.JCheckBox();
        cbxP95 = new javax.swing.JCheckBox();
        cbxP99 = new javax.swing.JCheckBox();
        cbxMax = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("OLMEC");

        jLabel2.setText("File to Analyse");

        txfAnalyse.setText("olmectest");
        txfAnalyse.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                txfAnalyseActionPerformed(evt);
            }
        });

        btnAnalyse.setText("Analyse");
        btnAnalyse.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAnalyseActionPerformed(evt);
            }
        });

        jLabel3.setText(".txt");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(0, 84, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txfAnalyse)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnAnalyse)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txfAnalyse, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAnalyse)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(cbxMin)
                    .addComponent(cbxMedian)
                    .addComponent(cbxP95)
                    .addComponent(cbxP99)
                    .addComponent(cbxMax))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 208, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalyseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAnalyseActionPerformed
    {//GEN-HEADEREND:event_btnAnalyseActionPerformed
        if(!txfAnalyse.getText().equals(""))
        {
            try
            {
                String s = "";
                if(cbxMin.isSelected())
                {
                    s += "0,";
                }
                if(cbxMedian.isSelected())
                {
                    s += "1,";
                }
                if(cbxP95.isSelected())
                {
                    s += "2,";
                }
                if(cbxP99.isSelected())
                {
                    s += "3,";
                }
                if(cbxMax.isSelected())
                {
                    s += "4,";
                }
                if(s.equals(""))
                {
                    JOptionPane.showMessageDialog(null, "Select at least one aspect to graph!");
                }
                else
                {
                    s = s.substring(0, s.length()-1);
                }
                a = new Analyser(txfAnalyse.getText(), s, this);
            }
            catch(FileNotFoundException e)
            {
                JOptionPane.showMessageDialog(null, "There is no file with that name!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Enter a valid filename!");
        }
    }//GEN-LAST:event_btnAnalyseActionPerformed

    private void txfAnalyseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_txfAnalyseActionPerformed
    {//GEN-HEADEREND:event_txfAnalyseActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txfAnalyseActionPerformed

    public void appendToFeedback(String text)
    {
        System.out.println(text);
        txaFeedback.setText(txaFeedback.getText() + "\n" + text);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(OlmecUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(OlmecUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(OlmecUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(OlmecUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new OlmecUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalyse;
    private javax.swing.JCheckBox cbxMax;
    private javax.swing.JCheckBox cbxMedian;
    private javax.swing.JCheckBox cbxMin;
    private javax.swing.JCheckBox cbxP95;
    private javax.swing.JCheckBox cbxP99;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txaFeedback;
    private javax.swing.JTextField txfAnalyse;
    // End of variables declaration//GEN-END:variables
}
