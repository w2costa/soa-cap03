package com.knight.estoque.servicos;

import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.soap.SOAPException;
import javax.xml.ws.Endpoint;
import com.knight.estoque.daos.LivroDAO;
import com.knight.estoque.modelos.Livro;
import com.knight.estoque.modelos.Usuario;

@WebService
public class LivrosService {

	@WebResult(name = "livro")
	public List<Livro> listarLivros() {
		LivroDAO livroDAO = obterDAO();
		return livroDAO.listarLivros();
	}

	@WebResult(name = "livro")
	public List<Livro> listarLivrosPaginacao(int numeroDaPagina,
			int tamanhoDaPagina) {
		LivroDAO livroDAO = obterDAO();

		return livroDAO.listarLivros(numeroDaPagina, tamanhoDaPagina);
	}

	public void criarLivro(@WebParam(name = "livro") Livro livro,
			@WebParam(name = "usuario", header = true) Usuario usuario)
			throws UsuarioNaoAutorizadoException, SOAPException {
		if (usuario.getLogin().equals("soa")
				&& usuario.getSenha().equals("soa")) {
			obterDAO().criarLivro(livro);
		} else {
			throw new UsuarioNaoAutorizadoException("Usuário não autorizado");
		}
	}

	private LivroDAO obterDAO() {
		return new LivroDAO();
	}

	public static void main(String[] args) {
		Endpoint.publish("http://localhost:8080/livros", new LivrosService());
		System.out.println("Serviço inicializado!");

	}

}
