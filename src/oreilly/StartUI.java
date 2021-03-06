package oreilly;

import java.io.IOException;
import javax.swing.JOptionPane;

public class StartUI extends javax.swing.JFrame
{
    public StartUI()
    {
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

        lblOlmec = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnAnalyse = new javax.swing.JButton();
        txfFilename = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblOlmec.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblOlmec.setText("OLMEC");

        jLabel1.setText("Select Artillery Run");

        btnAnalyse.setText("Analyse");
        btnAnalyse.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnAnalyseActionPerformed(evt);
            }
        });

        jLabel2.setText(".txt");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(lblOlmec, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txfFilename)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAnalyse)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOlmec)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnAnalyse)
                    .addComponent(txfFilename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAnalyseActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnAnalyseActionPerformed
    {//GEN-HEADEREND:event_btnAnalyseActionPerformed
        String filename = txfFilename.getText().trim();
        if(!filename.equals(""))
        {
            try
            {
                new OlmecUI(filename).setVisible(true);
            } catch (IOException e)
            {
                JOptionPane.showMessageDialog(null, "No file with that name!");
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Enter a valid filename!");
        }
    }//GEN-LAST:event_btnAnalyseActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnalyse;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel lblOlmec;
    private javax.swing.JTextField txfFilename;
    // End of variables declaration//GEN-END:variables
}
