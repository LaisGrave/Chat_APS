package util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import model.Arquivo;

public class TransformarArquivo {

	private final long TAMANHO_PERMITIDO = 5120;

	private FileInputStream fileInput;

	private Arquivo arquivo;

	public byte[] transformar(File fileSelected) {
		byte[] bFile = new byte[(int) fileSelected.length()];
		try {
			fileInput = new FileInputStream(fileSelected);
			fileInput.read(bFile);
			fileInput.close();
		} catch (IOException ex) {
			Logger.getLogger(TransformarArquivo.class.getName()).log(Level.SEVERE, null, ex);
		}

		long tamanho = fileSelected.length() / 1024;

		arquivo = new Arquivo();
		arquivo.setConteudo(bFile);
		arquivo.setDataHoraUpload(new Date());
		arquivo.setNome(fileSelected.getName());
		arquivo.setTamanhoKB(tamanho);
		arquivo.setPortaDestino("5000");
		arquivo.setDiretorioDestino("Downloads");
		
		if(validaArquivo()) {
			return serializarArquivo();
		}else return null;
	}

	private byte[] serializarArquivo() {
		try {
			ByteArrayOutputStream bytes = new ByteArrayOutputStream();
			ObjectOutputStream outpuObject;
			outpuObject = new ObjectOutputStream(bytes);
			outpuObject.writeObject(arquivo);
			return bytes.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	private boolean validaArquivo() {
		if (arquivo.getTamanhoKB() > TAMANHO_PERMITIDO) {
			JOptionPane.showMessageDialog(null, "Tamanho máximo permitido atingido (" + (TAMANHO_PERMITIDO / 1024) + ")");
			return false;
		} else {
			return true;
		}
	}

	public FileInputStream getFis() {
		return fileInput;
	}

	public void setFis(FileInputStream fis) {
		this.fileInput = fis;
	}

}
