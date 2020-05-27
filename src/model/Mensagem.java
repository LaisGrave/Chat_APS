/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Jéssica
 */
public class Mensagem implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String mensagem;

	private String usuario;

	public Mensagem(String mensagem, String usuario) {
		setMensagem(mensagem);
		setUsuario(usuario);
	}

	/**
	 * @return the mensagem
	 */
	public String getMensagem() {
		return mensagem;
	}

	/**
	 * @param mensagem the mensagem to set
	 */
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return usuario + " diz: " + mensagem;
	}
}
