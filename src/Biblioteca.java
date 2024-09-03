import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private List<Livro> livros;

    public Biblioteca() {
        this.livros = new ArrayList<>(); // Inicializa a lista de livros
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro); // Adiciona um livro à lista
    }

    public void removerLivro(Livro livro) {
        livros.remove(livro); // Remove um livro da lista
    }

    public Livro buscarLivro(String titulo) {
        for (Livro livro : livros) {
            if (livro.getTitulo().equalsIgnoreCase(titulo)) {
                return livro; // Retorna o livro se encontrado
            }
        }
        return null; // Retorna null se o livro não for encontrado
    }

    public List<Livro> getLivros() {
        return livros; // Retorna a lista de livros
    }
}
