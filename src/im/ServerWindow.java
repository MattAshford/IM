package IM;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWindow extends javax.swing.JFrame {

    static ServerSocket serverSocket;
    static Socket socket;
    static DataInputStream dataIn;
    static DataOutputStream dataOut;
    
    public ServerWindow() {
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        displayIM = new javax.swing.JTextArea();
        composeIM = new javax.swing.JTextField();
        sendIM = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        displayIM.setColumns(20);
        displayIM.setRows(5);
        jScrollPane1.setViewportView(displayIM);

        composeIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                composeIMActionPerformed(evt);
            }
        });

        sendIM.setText("Send");
        sendIM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sendIMActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(composeIM, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(sendIM, javax.swing.GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(composeIM)
                    .addComponent(sendIM, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap())
        );

        getAccessibleContext().setAccessibleName("Server");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void composeIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_composeIMActionPerformed
    }//GEN-LAST:event_composeIMActionPerformed

    private void sendIMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sendIMActionPerformed
        try{
            String messageOut = "";
            messageOut = composeIM.getText().trim();
            dataOut.writeUTF(messageOut); 
            displayIM.setText(displayIM.getText().trim()+"\n Server:\t"+ messageOut);
        }catch(Exception e){}
    }//GEN-LAST:event_sendIMActionPerformed

    public static void main(String args[]) {
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ServerWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ServerWindow().setVisible(true);
                
            }
        });
        
        try{
            String messageIn = "";
            serverSocket = new ServerSocket(8192); 
            socket = serverSocket.accept(); 
            
            dataIn = new DataInputStream(socket.getInputStream());
            dataOut = new DataOutputStream(socket.getOutputStream());
            
            while(!messageIn.equals("exit")){
                messageIn = dataIn.readUTF();
                displayIM.setText(displayIM.getText().trim()+"\n Client:\t"+ messageIn); // displaying the messge.. from client.
                
            }
            
            
        }catch(Exception e){
            
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField composeIM;
    private static javax.swing.JTextArea displayIM;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton sendIM;
    // End of variables declaration//GEN-END:variables
}
