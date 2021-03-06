
package view;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.*;
import static javax.swing.JOptionPane.*;


/**
 *
 * @author Lais
 */
public class Chat extends javax.swing.JFrame {
    
    private String nome;
    private Socket s;
    private BufferedReader br;
    private InputStreamReader is;
   
    
   
    public Chat(String nome) {
        
        initComponents();
        this.nome = nome;
      
        
        try{
        s = new Socket("127.0.0.1",5000);
        }
        catch(IOException exp){
            showMessageDialog(null, "Servidor não conectado", "", ERROR_MESSAGE);
            System.exit(0);
        }
       Thread();
    }
    private void Thread(){
        Thread t = new Thread(new Runnable() {
            
            String mensagem;
            
            @Override
            public void run() {
                
                try{
                   is = new InputStreamReader(s.getInputStream());
                   br = new BufferedReader(is);
                   
                    while ((mensagem = br.readLine()) != null){
                            txMensagemRecebida.setText(txMensagemRecebida.getText()+ mensagem + "\n");
                    }
                }catch(IOException ex){
                    showMessageDialog (null, "Erro na conexão com o servidor", "", ERROR_MESSAGE);
                    
                
            }
            }
        
            
        });
        t.start();
    }
    
    public void enviarMensagem(){
        String mensagem = getNome() + " diz: ";
        try{
            PrintStream ps = new PrintStream(s.getOutputStream());
            mensagem += txMensagemEnviar.getText();
            ps.println(mensagem);
            ps.flush();
            txMensagemEnviar.setText("");

        }catch (IOException ex){
            showMessageDialog(null, "Mensagem não foi enviada", "", ERROR_MESSAGE);
        }
    }

    
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        txMensagemRecebida = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txMensagemEnviar = new javax.swing.JTextArea();
        btEnviar = new javax.swing.JButton();
        btSair = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txMensagemRecebida.setEditable(false);
        txMensagemRecebida.setColumns(20);
        txMensagemRecebida.setRows(5);
        jScrollPane1.setViewportView(txMensagemRecebida);

        txMensagemEnviar.setColumns(20);
        txMensagemEnviar.setRows(5);
        txMensagemEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txMensagemEnviarKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(txMensagemEnviar);

        btEnviar.setText("Enviar");
        btEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEnviarActionPerformed(evt);
            }
        });

        btSair.setText("Sair");
        btSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSairActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btEnviar)
                    .addComponent(btSair))
                .addContainerGap(22, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txMensagemEnviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txMensagemEnviarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER){
            this.enviarMensagem();
        }
       
    }//GEN-LAST:event_txMensagemEnviarKeyPressed

    private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEnviarActionPerformed
       this.enviarMensagem();
    }//GEN-LAST:event_btEnviarActionPerformed

    private void btSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSairActionPerformed
            try {
                s.close();
            } catch (IOException ex) {
            } 
    }//GEN-LAST:event_btSairActionPerformed
  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btEnviar;
    private javax.swing.JButton btSair;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txMensagemEnviar;
    private javax.swing.JTextArea txMensagemRecebida;
    // End of variables declaration//GEN-END:variables

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }
}
