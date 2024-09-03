import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Sistema {
    private Biblioteca biblioteca = new Biblioteca();
    private JTextArea listaLivrosTextArea; // Área de texto para listar os livros

    public Sistema() {
        // Criação da janela principal
        JFrame frame = new JFrame("Sistema de Biblioteca");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);
        frame.setLayout(new BorderLayout());

        // Criação dos componentes da interface
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton btnAdicionar = new JButton("Adicionar Livro");
        JButton btnListar = new JButton("Listar Livros");
        JButton btnRemover = new JButton("Remover Livro");
        JButton btnEmprestar = new JButton("Emprestar Livro");
        JButton btnDevolver = new JButton("Devolver Livro");
        JButton btnSair = new JButton("Sair");

        panel.add(btnAdicionar);
        panel.add(btnListar);
        panel.add(btnRemover);
        panel.add(btnEmprestar);
        panel.add(btnDevolver);
        panel.add(btnSair);

        // Área de texto para listar os livros
        listaLivrosTextArea = new JTextArea();
        listaLivrosTextArea.setEditable(false); // Impede que o usuário edite o texto manualmente
        JScrollPane scrollPane = new JScrollPane(listaLivrosTextArea); // Adiciona uma barra de rolagem

        frame.add(panel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Ações dos botões
        btnAdicionar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Digite o título do livro:");
                String autor = JOptionPane.showInputDialog("Digite o autor do livro:");
                Livro novoLivro = new Livro(titulo, autor);
                biblioteca.adicionarLivro(novoLivro);
                atualizarListaLivros(); // Atualiza a lista de livros exibida
                JOptionPane.showMessageDialog(frame, "Livro adicionado com sucesso!");
            }
        });

        btnListar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizarListaLivros(); // Atualiza a lista de livros exibida
            }
        });

        btnRemover.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Digite o título do livro para remover:");
                Livro livroParaRemover = biblioteca.buscarLivro(titulo);
                if (livroParaRemover != null) {
                    biblioteca.removerLivro(livroParaRemover);
                    atualizarListaLivros(); // Atualiza a lista de livros exibida
                    JOptionPane.showMessageDialog(frame, "Livro removido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Livro não encontrado.");
                }
            }
        });

        btnEmprestar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Digite o título do livro para emprestar:");
                Livro livroParaEmprestar = biblioteca.buscarLivro(titulo);
                if (livroParaEmprestar != null && livroParaEmprestar.disponivel) {
                    livroParaEmprestar.emprestar();
                    atualizarListaLivros(); // Atualiza a lista de livros exibida
                    JOptionPane.showMessageDialog(frame, "Livro emprestado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Livro não disponível ou não encontrado.");
                }
            }
        });

        btnDevolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String titulo = JOptionPane.showInputDialog("Digite o título do livro para devolver:");
                Livro livroParaDevolver = biblioteca.buscarLivro(titulo);
                if (livroParaDevolver != null && !livroParaDevolver.disponivel) {
                    livroParaDevolver.devolver();
                    atualizarListaLivros(); // Atualiza a lista de livros exibida
                    JOptionPane.showMessageDialog(frame, "Livro devolvido com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Livro já está disponível ou não encontrado.");
                }
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });

        // Exibe a janela
        frame.setVisible(true);
    }

    // Método para atualizar a lista de livros exibida na área de texto
    private void atualizarListaLivros() {
        listaLivrosTextArea.setText(""); // Limpa o texto atual
        for (Livro livro : biblioteca.getLivros()) { // Itera sobre os livros na biblioteca
            listaLivrosTextArea.append("Título: " + livro.getTitulo() + ", Autor: " + livro.getAutor() +
                (livro.disponivel ? " (Disponível)\n" : " (Emprestado)\n"));
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Sistema();
            }
        });
    }
}
