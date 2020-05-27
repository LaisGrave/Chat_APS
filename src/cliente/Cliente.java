package cliente;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import model.Arquivo;
import model.Mensagem;

/**
 *
 * 
 */
public class Cliente {

	private ArrayList<ObjectOutputStream> clientes;

	public Cliente() {
	}

	public Cliente(ArrayList<ObjectOutputStream> clientes) {
		setClientes(clientes);
	}

	public void enviarMensagem(Mensagem mensagem) {

		for (ObjectOutputStream cliente : getClientes()) {
			try {
				cliente.writeObject(mensagem);
				cliente.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void enviarArquivo(Arquivo arquivo) {
		for (ObjectOutputStream cliente : getClientes()) {
			try {
				cliente.writeObject(arquivo);
				cliente.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// @return the clientes
	public ArrayList<ObjectOutputStream> getClientes() {
		return clientes;
	}

	// @param clientes the clientes to set
	public void setClientes(ArrayList<ObjectOutputStream> clientes) {
		this.clientes = clientes;
	}

}
