/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import cliente.Cliente;
import dao.MensagensDao;
import model.Arquivo;
import model.Mensagem;
import servidor.Servidor;

/**
 *
 * @author Laís
 */
public class ServidorController {

	public static void main(String[] args) {
		Servidor server = new Servidor(5000);
		server.rodarServer();
	}

	public void threadServidor(Socket socket, Cliente cliente) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

					while (input.readObject() != null) {
						if (input.readObject() instanceof Mensagem) {
							Mensagem mensagem = (Mensagem) input.readObject();
							MensagensDao.getInstance().create(mensagem);
							cliente.enviarMensagem(mensagem);
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
					ex.printStackTrace();
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

}
