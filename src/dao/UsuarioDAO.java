/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import corn.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

/**
 *
 * @author Jéssica
 */
public class UsuarioDAO implements Persistencia<Usuario> {

	private static UsuarioDAO usuarioDao = null;

	private UsuarioDAO() {
	}

	public static UsuarioDAO getInstance() {
		if (usuarioDao == null) {
			usuarioDao = new UsuarioDAO();
		}
		return usuarioDao;
	}

	@Override
	public int create(Usuario r) {
		String sql = "insert into Usuario" + "(Nome, Equipe, Senha)" + "values (?,?,?)";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int codigo = 0;
		Usuario user = findUser(r.getNome(), r.getSenha(), r.getEquipe());
		if (user == null) {
			try {
				pst = con.prepareStatement(sql, RETURN_GENERATED_KEYS);
				pst.setString(1, r.getNome());
				pst.setInt(2, r.getEquipe());
				pst.setString(3, r.getSenha());
				pst.executeUpdate();
				rs = pst.getGeneratedKeys();
				if (rs.next()) {
					codigo = rs.getInt(1);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ConnectionFactory.closeConnection(con, pst);
			}
		}
		return codigo;
	}

	@Override
	public List<Usuario> read() {
		String sql = "select * from Usuario";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<Usuario> lista = new ArrayList<>();
		try {
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				String nome = rs.getString("Nome");
				int equipe = rs.getInt("Equipe");
				String senha = rs.getString("Senha");
				Usuario usuario = new Usuario(nome, equipe, senha);
				lista.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, pst);
		}
		return lista;
	}

	@Override
	public boolean update(Usuario r) {
		String sql = "update Usuario" + "set Senha=?" + "where Nome=? and Equipe=?";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean atualizado = false;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, r.getSenha());
			pst.setString(2, r.getNome());
			pst.setInt(3, r.getEquipe());
			pst.executeUpdate();
			rs = pst.getGeneratedKeys();
			if (rs.next()) {
				atualizado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, pst);
		}
		return atualizado;
	}

	@Override
	public boolean delet(Usuario r) {
		boolean deletado = false;
		String sql = "delete from Usuario" + "where Nome=?, Equipe=?, Senha=?";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, r.getNome());
			pst.setInt(2, r.getEquipe());
			pst.setString(3, r.getSenha());
			pst.executeUpdate();
			if (rs.next()) {
				deletado = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			ConnectionFactory.closeConnection(con, pst);
		}
		return deletado;
	}

	public Usuario findUser(String nomeUsuario, String senhaUsuario, int equipeUsuario) {
		String sql = "select * from Usuario " + "where Nome=? and Senha=?";
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Usuario user = null;
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, nomeUsuario);
			pst.setString(2, senhaUsuario);
			rs = pst.executeQuery();
			if (rs.next()) {
				String nome = rs.getString("Nome");
				int equipe = rs.getInt("Equipe");
				String senha = rs.getString("Senha");
				user = new Usuario(nome, equipe, senha);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.closeConnection(con, pst);
		}
		return user;
	}
}
