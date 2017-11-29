package br.mus.rastoldo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.mus.rastoldo.factory.ConexaoFactory;
import br.mus.rastoldo.model.Usuario;

public class UsuarioDao {
	
	public void salvar(Usuario usuario) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(nome, sobrenome, email, senha) ");
		sql.append("VALUES (?, ?, ?, ?) ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, usuario.getNome());
		comando.setString(2, usuario.getSobrenome());
		comando.setString(3, usuario.getEmail());
		comando.setString(4, usuario.getSenha());
		
		comando.executeUpdate();
	}
	
	
	public ArrayList<Usuario> listar() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT u.id, u.nome, u.sobrenome, u.email, u.senha ");
		sql.append("FROM usuario u ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Usuario> itens = new ArrayList<Usuario>();
		
		while(resultado.next()) {
			Usuario usuario = new Usuario();
			usuario.setId(resultado.getLong("u.id"));
			usuario.setNome(resultado.getString("u.nome"));
			usuario.setSobrenome(resultado.getString("u.sobrenome"));
			usuario.setEmail(resultado.getString("u.email"));
			usuario.setSenha(resultado.getString("u.senha"));
			itens.add(usuario);
		}
		
		return itens;
	}
	
	public void excluir(Usuario usuario) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM usuario ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, usuario.getId());
		
		comando.executeUpdate();
	}
	
	
	public void editar(Usuario usuario) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE usuario ");
		sql.append("SET nome = ?, sobrenome = ?, email = ?, senha = ? ");
		sql.append("WHERE id = ? ");
		
		Connection conexao = ConexaoFactory.conectar();
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, usuario.getNome());
		comando.setString(2, usuario.getSobrenome());
		comando.setString(3, usuario.getEmail());
		comando.setString(4, usuario.getSenha());
		
		comando.executeUpdate();
	}

}
