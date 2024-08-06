package com.knight.estoque.modelos;

import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({ EBook.class })
public class Livro {

    private Integer anoDePublicacao;

    @XmlElementWrapper(name = "autores")
    @XmlElement(name = "autor")
    @XmlJavaTypeAdapter(value = AdaptadorAutores.class)
    private List<Autor> autores;
    private String editora;
    private String nome;
    private String resumo;
    private Date dataDeCriacao = new Date();

    public Livro() {
    }

    public Livro(Integer anoDePublicacao, List<Autor> autores, String editora,
            String nome, String resumo) {
        super();
        this.anoDePublicacao = anoDePublicacao;
        this.autores = autores;
        this.editora = editora;
        this.nome = nome;
        this.resumo = resumo;
    }

    public Integer getAnoDePublicacao() {
        return anoDePublicacao;
    }

    public void setAnoDePublicacao(Integer anoDePublicacao) {
        this.anoDePublicacao = anoDePublicacao;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public Date getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(Date dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public static class AdaptadorAutores extends XmlAdapter<String, Autor> {

        @Override
        public String marshal(Autor autor) throws Exception {
            return autor.getNome();
        }

        @Override
        public Autor unmarshal(String autor) throws Exception {
            return new Autor(autor, null);
        }

    }
}
