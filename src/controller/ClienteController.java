/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import dao.MensagensDao;
import model.Arquivo;
import model.Mensagem;
import view.Chat;
import view.Inicio;

/**
 *
 * @author Lais
 */
public class ClienteController {

	private Socket socket;
	private Chat chat;

	public static void main(String[] args) {

		Inicio inicio = new Inicio();
		inicio.setVisible(true);
	}

	public void iniciarConexao(Chat chat) {
		try {
			socket = new Socket("127.0.0.1", 5000);
		} catch (IOException exp) {
			showMessageDialog(null, "Servidor não conectado", "", ERROR_MESSAGE);
			System.exit(0);
		}
		Thread();
		setChat(chat);
		getChat().setSocket(socket);
	}

	private void Thread() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					chat.mensagens();
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

					while (input.readObject() != null) {
						if (input.readObject() instanceof Mensagem) {
							Mensagem mensagem = (Mensagem) input.readObject();
							MensagensDao.getInstance().create(mensagem);
							chat.mensagens();
						}
						if (input.readObject() instanceof Arquivo) {
							byte[] objectAsByte = new byte[socket.getReceiveBufferSize()];
							BufferedInputStream bf = new BufferedInputStream(socket.getInputStream());
							bf.read(objectAsByte);
							Arquivo arquivo = (Arquivo) getObjectFromByte(objectAsByte);
							String dir = arquivo.getDiretorioDestino().endsWith("/")
									? arquivo.getDiretorioDestino() + arquivo.getNome()
									: arquivo.getDiretorioDestino() + "/" + arquivo.getNome();
							FileOutputStream fos = new FileOutputStream(dir);
							fos.write(arquivo.getConteudo());
							fos.close();
						}
					}
				} catch (IOException | ClassNotFoundException ex) {
					showMessageDialog(null, "Erro na conexão com o servidor", "", ERROR_MESSAGE);
				}
			}

		});
		thread.start();
	}
	
	private Object getObjectFromByte(byte[] objectAsByte) {
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			bis = new ByteArrayInputStream(objectAsByte);
			ois = new ObjectInputStream(bis);
			obj = ois.readObject();

			bis.close();
			ois.close();

		} catch (ClassNotFoundException |IOException e) {
			e.printStackTrace();
		}
		return obj;
	}


	/**
	 * @return the chat
	 */
	public Chat getChat() {
		return chat;
	}

	/**
	 * @param chat the chat to set
	 */
	public void setChat(Chat chat) {
		this.chat = chat;
	}
}
