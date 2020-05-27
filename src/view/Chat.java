
package view;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.event.KeyEvent;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

import dao.MensagensDao;
import model.Mensagem;

/**
 *
 * @author Lais
 */
public class Chat extends javax.swing.JFrame {

	private String nome;
	private Socket socket;

	public Chat() {
		initComponents();
	}

	// <editor-fold defaultstate="collapsed" desc="Generated
	// Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		pnChat = new javax.swing.JPanel();
		btSair = new javax.swing.JButton();
		psMensagemEnviar = new javax.swing.JScrollPane();
		txMensagemEnviar = new javax.swing.JTextArea();
		btEnviar = new javax.swing.JButton();
		psMenagemRecebida = new javax.swing.JScrollPane();
		txMensagemRecebida = new javax.swing.JTextArea();
		lbLogoChat = new javax.swing.JLabel();
		lbTituloChat = new javax.swing.JLabel();
		jbAnexo = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
		setResizable(false);

		pnChat.setBackground(new java.awt.Color(190, 242, 191));
		pnChat.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 51, 51), 10, true));
		pnChat.setForeground(new java.awt.Color(190, 242, 191));

		btSair.setBackground(new java.awt.Color(0, 51, 51));
		btSair.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
		btSair.setForeground(new java.awt.Color(255, 255, 255));
		btSair.setText("Sair");
		btSair.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btSairActionPerformed(evt);
			}
		});

		txMensagemEnviar.setColumns(20);
		txMensagemEnviar.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
		txMensagemEnviar.setRows(5);
		txMensagemEnviar.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				txMensagemEnviarKeyPressed(evt);
			}
		});
		psMensagemEnviar.setViewportView(txMensagemEnviar);

		btEnviar.setBackground(new java.awt.Color(0, 51, 51));
		btEnviar.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
		btEnviar.setForeground(new java.awt.Color(255, 255, 255));
		btEnviar.setText("Enviar");
		btEnviar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btEnviarActionPerformed(evt);
			}
		});

		txMensagemRecebida.setEditable(false);
		txMensagemRecebida.setColumns(20);
		txMensagemRecebida.setFont(new java.awt.Font("Gadugi", 1, 14)); // NOI18N
		txMensagemRecebida.setRows(5);
		psMenagemRecebida.setViewportView(txMensagemRecebida);

		lbLogoChat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagem/logo.png"))); // NOI18N

		lbTituloChat.setFont(new java.awt.Font("Gadugi", 1, 24)); // NOI18N
		lbTituloChat.setText("Bate-papo");

		jbAnexo.setBackground(new java.awt.Color(0, 51, 51));
		jbAnexo.setFont(new java.awt.Font("Gadugi", 1, 10)); // NOI18N
		jbAnexo.setForeground(new java.awt.Color(255, 255, 255));
		jbAnexo.setText("Anexo");
		jbAnexo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jbAnexoActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout pnChatLayout = new javax.swing.GroupLayout(pnChat);
		pnChat.setLayout(pnChatLayout);
		pnChatLayout.setHorizontalGroup(pnChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(pnChatLayout.createSequentialGroup()
						.addGroup(pnChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(pnChatLayout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE)
										.addComponent(jbAnexo))
								.addGroup(javax.swing.GroupLayout.Alignment.LEADING,
										pnChatLayout.createSequentialGroup().addGap(21, 21, 21)
												.addComponent(lbLogoChat, javax.swing.GroupLayout.PREFERRED_SIZE, 80,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
												.addComponent(lbTituloChat).addGap(0, 0, Short.MAX_VALUE))
								.addGroup(pnChatLayout.createSequentialGroup().addContainerGap(23, Short.MAX_VALUE)
										.addGroup(pnChatLayout
												.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
												.addGroup(pnChatLayout.createSequentialGroup()
														.addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE,
																122, javax.swing.GroupLayout.PREFERRED_SIZE)
														.addGap(37, 37, 37).addComponent(btEnviar,
																javax.swing.GroupLayout.PREFERRED_SIZE, 172,
																javax.swing.GroupLayout.PREFERRED_SIZE))
												.addComponent(psMensagemEnviar,
														javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(psMenagemRecebida,
														javax.swing.GroupLayout.Alignment.LEADING))))
						.addContainerGap(23, Short.MAX_VALUE)));
		pnChatLayout
				.setVerticalGroup(pnChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnChatLayout.createSequentialGroup()
								.addGroup(pnChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(pnChatLayout.createSequentialGroup()
												.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(lbLogoChat, javax.swing.GroupLayout.PREFERRED_SIZE, 66,
														javax.swing.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
										.addGroup(pnChatLayout.createSequentialGroup().addGap(20, 20, 20)
												.addComponent(lbTituloChat)
												.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
														javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(psMenagemRecebida, javax.swing.GroupLayout.PREFERRED_SIZE, 267,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jbAnexo, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(psMensagemEnviar, javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addGap(18, 18, 18)
								.addGroup(pnChatLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
										.addComponent(btEnviar, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addComponent(btSair, javax.swing.GroupLayout.PREFERRED_SIZE, 41,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addGap(24, 24, 24)));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				pnChat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(
				pnChat, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE,
				javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE));

		pack();
	}// </editor-fold>//GEN-END:initComponents

	private void txMensagemEnviarKeyPressed(java.awt.event.KeyEvent evt) {// GEN-FIRST:event_txMensagemEnviarKeyPressed
		if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
			this.enviarMensagem();
		}

	}// GEN-LAST:event_txMensagemEnviarKeyPressed

	private void btEnviarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btEnviarActionPerformed
		this.enviarMensagem();
	}// GEN-LAST:event_btEnviarActionPerformed

	private void btSairActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btSairActionPerformed
		try {
			getSocket().close();
			System.exit(0);
		} catch (IOException ex) {
		}
	}// GEN-LAST:event_btSairActionPerformed

	private void jbAnexoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jbAnexoActionPerformed
		Anexo anexo = new Anexo();
		anexo.setVisible(true);
		anexo.setSocket(getSocket());

	}// GEN-LAST:event_jbAnexoActionPerformed

	private void enviarMensagem() {
		String mensagem = null;
		try {
			ObjectOutputStream output = new ObjectOutputStream(getSocket().getOutputStream());
			mensagem = txMensagemEnviar.getText();
			Mensagem message = new Mensagem(mensagem, getNome());
			MensagensDao.getInstance().create(message);
			output.writeObject(message);
			output.flush();
			txMensagemEnviar.setText("");
			mensagens();
		} catch (IOException ex) {
			showMessageDialog(null, "Mensagem não foi enviada", "", ERROR_MESSAGE);
		}
	}

	public void mensagens() {
		List<Mensagem> mensagens = MensagensDao.getInstance().read();
		txMensagemRecebida.setText("");
		for (Mensagem mensagem : mensagens) {
			txMensagemRecebida.setText(txMensagemRecebida.getText() + "\n" + mensagem.toString());
		}
	}

	// Variables declaration - do not modify//GEN-BEGIN:variables
	private javax.swing.JButton btEnviar;
	private javax.swing.JButton btSair;
	private javax.swing.JButton jbAnexo;
	private javax.swing.JLabel lbLogoChat;
	private javax.swing.JLabel lbTituloChat;
	private javax.swing.JPanel pnChat;
	private javax.swing.JScrollPane psMenagemRecebida;
	private javax.swing.JScrollPane psMensagemEnviar;
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

	/**
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}

	/**
	 * @param socket the socket to set
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}

}
