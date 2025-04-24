package javaricci.com.br.VIEW;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class AplicativoIdeBd extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private JTextArea areaComandoSQL;
    private JTable tabelaResultados;
    private JScrollPane painelRolagem;
    private JButton botaoExecutar;
    private JButton botaoSelecionarBD;
    private JLabel rotuloStatus;
    private JComboBox<String> comboBancosDados;
    private Connection conexao;
    private String caminhoAtualBD = "";
    private final String DIRETORIO_PADRAO = "./";

    public AplicativoIdeBd() {
        setTitle("IDE de Banco de Dados SQLite");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        inicializarComponentes();
        carregarBancosDeDados();
    }

    private void inicializarComponentes() {
        // Painel principal com BorderLayout
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Painel superior com seletor de banco de dados
        JPanel painelSelecaoBD = new JPanel(new BorderLayout());
        JPanel painelControlesBD = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        JLabel rotuloBD = new JLabel("Banco de Dados:");
        comboBancosDados = new JComboBox<>();
        comboBancosDados.setPreferredSize(new Dimension(250, 25));
        comboBancosDados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conectarAoBancoDados();
            }
        });
        
        botaoSelecionarBD = new JButton("Procurar...");
        botaoSelecionarBD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selecionarBancoDados();
            }
        });
        
        painelControlesBD.add(rotuloBD);
        painelControlesBD.add(comboBancosDados);
        painelControlesBD.add(botaoSelecionarBD);
        painelSelecaoBD.add(painelControlesBD, BorderLayout.WEST);
        
        // Painel do editor de consultas
        JPanel painelEditor = new JPanel(new BorderLayout());
        areaComandoSQL = new JTextArea(8, 40);
        areaComandoSQL.setFont(new Font("Monospaced", Font.PLAIN, 14));
        areaComandoSQL.setText("-- Digite seu comando SQL aqui\nSELECT * FROM sqlite_schema WHERE type ='table' AND name NOT LIKE 'sqlite_%'");
        JScrollPane painelRolagemEditor = new JScrollPane(areaComandoSQL);
        painelEditor.add(painelRolagemEditor, BorderLayout.CENTER);

        // Botão executar
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        botaoExecutar = new JButton("Executar");
        botaoExecutar.setBackground(new Color(66, 139, 202));
        botaoExecutar.setForeground(Color.WHITE);
        botaoExecutar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executarConsulta();
            }
        });
        painelBotoes.add(botaoExecutar);
        painelEditor.add(painelBotoes, BorderLayout.SOUTH);

        // Combinando os painéis superiores
        JPanel painelSuperior = new JPanel(new BorderLayout());
        painelSuperior.add(painelSelecaoBD, BorderLayout.NORTH);
        painelSuperior.add(painelEditor, BorderLayout.CENTER);

        // Tabela de resultados
        tabelaResultados = new JTable();
        tabelaResultados.setFont(new Font("Monospaced", Font.PLAIN, 12));
        painelRolagem = new JScrollPane(tabelaResultados);

        // Rótulo de status na parte inferior
        rotuloStatus = new JLabel("Aguardando conexão ao banco de dados.");
        rotuloStatus.setFont(new Font("SansSerif", Font.PLAIN, 12));
        rotuloStatus.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Adicionar componentes ao painel principal
        painelPrincipal.add(painelSuperior, BorderLayout.NORTH);
        painelPrincipal.add(painelRolagem, BorderLayout.CENTER);
        painelPrincipal.add(rotuloStatus, BorderLayout.SOUTH);

        // Definir o painel principal como conteúdo
        setContentPane(painelPrincipal);

        // Barra de menu
        JMenuBar barraMenu = new JMenuBar();
        
        JMenu menuArquivo = new JMenu("Arquivo");
        JMenuItem itemNovoBD = new JMenuItem("Novo Banco de Dados");
        itemNovoBD.addActionListener(e -> criarNovoBancoDados());
        
        JMenuItem itemAbrirBD = new JMenuItem("Abrir Banco de Dados");
        itemAbrirBD.addActionListener(e -> selecionarBancoDados());
        
        JMenuItem itemSair = new JMenuItem("Sair");
        itemSair.addActionListener(e -> System.exit(0));
        
        menuArquivo.add(itemNovoBD);
        menuArquivo.add(itemAbrirBD);
        menuArquivo.addSeparator();
        menuArquivo.add(itemSair);
        barraMenu.add(menuArquivo);

        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem itemSobre = new JMenuItem("Sobre");
        itemSobre.addActionListener(e -> JOptionPane.showMessageDialog(this,
                "IDE de Banco de Dados SQLite\nDesenvolvido para gerenciar bancos de dados SQLite",
                "Sobre", JOptionPane.INFORMATION_MESSAGE));
        menuAjuda.add(itemSobre);
        barraMenu.add(menuAjuda);

        setJMenuBar(barraMenu);
    }

    private void carregarBancosDeDados() {
        File diretorio = new File(DIRETORIO_PADRAO);
        File[] arquivos = diretorio.listFiles((dir, nome) -> 
            nome.toLowerCase().endsWith(".db") || 
            nome.toLowerCase().endsWith(".sqlite") || 
            nome.toLowerCase().endsWith(".sqlite3"));
        
        comboBancosDados.removeAllItems();
        comboBancosDados.addItem("-- Selecione um banco de dados --");
        
        if (arquivos != null) {
            for (File arquivo : arquivos) {
                comboBancosDados.addItem(arquivo.getName());
            }
        }
        
        // Definir JAVAMVC.DB como padrão se existir
        for (int i = 0; i < comboBancosDados.getItemCount(); i++) {
            if ("JAVAMVC.DB".equals(comboBancosDados.getItemAt(i))) {
                comboBancosDados.setSelectedIndex(i);
                return;
            }
        }
        
        comboBancosDados.setSelectedIndex(0);
    }

    private void selecionarBancoDados() {
        JFileChooser selecionadorArquivo = new JFileChooser();
        selecionadorArquivo.setDialogTitle("Selecione o Banco de Dados SQLite");
        selecionadorArquivo.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String nome = f.getName().toLowerCase();
                return nome.endsWith(".db") || nome.endsWith(".sqlite") || nome.endsWith(".sqlite3");
            }

            @Override
            public String getDescription() {
                return "Arquivos de Banco de Dados SQLite (*.db, *.sqlite, *.sqlite3)";
            }
        });

        if (selecionadorArquivo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = selecionadorArquivo.getSelectedFile();
            String nomeArquivo = arquivoSelecionado.getName();
            String caminhoArquivo = arquivoSelecionado.getAbsolutePath();
            
            // Adicionar à lista se ainda não existe
            boolean encontrado = false;
            for (int i = 0; i < comboBancosDados.getItemCount(); i++) {
                if (nomeArquivo.equals(comboBancosDados.getItemAt(i))) {
                    comboBancosDados.setSelectedIndex(i);
                    encontrado = true;
                    break;
                }
            }
            
            if (!encontrado) {
                comboBancosDados.addItem(nomeArquivo);
                comboBancosDados.setSelectedItem(nomeArquivo);
            }
            
            caminhoAtualBD = caminhoArquivo;
            conectarAoBancoDados();
        }
    }

    private void criarNovoBancoDados() {
        JFileChooser selecionadorArquivo = new JFileChooser();
        selecionadorArquivo.setDialogTitle("Criar Novo Banco de Dados SQLite");
        selecionadorArquivo.setFileFilter(new javax.swing.filechooser.FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) return true;
                String nome = f.getName().toLowerCase();
                return nome.endsWith(".db") || nome.endsWith(".sqlite") || nome.endsWith(".sqlite3");
            }

            @Override
            public String getDescription() {
                return "Arquivos de Banco de Dados SQLite (*.db, *.sqlite, *.sqlite3)";
            }
        });

        if (selecionadorArquivo.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            File arquivoSelecionado = selecionadorArquivo.getSelectedFile();
            String caminho = arquivoSelecionado.getAbsolutePath();
            
            // Adicionar extensão .db se não tiver uma extensão válida
            if (!caminho.toLowerCase().endsWith(".db") && 
                !caminho.toLowerCase().endsWith(".sqlite") && 
                !caminho.toLowerCase().endsWith(".sqlite3")) {
                caminho += ".db";
                arquivoSelecionado = new File(caminho);
            }
            
            try {
                // Tentar criar um novo banco de dados SQLite
                Connection novoBanco = DriverManager.getConnection("jdbc:sqlite:" + caminho);
                novoBanco.close();
                
                JOptionPane.showMessageDialog(this,
                        "Banco de dados criado com sucesso: " + arquivoSelecionado.getName(),
                        "Banco Criado", JOptionPane.INFORMATION_MESSAGE);
                
                // Atualizar a lista de bancos de dados
                carregarBancosDeDados();
                
                // Selecionar o novo banco
                comboBancosDados.setSelectedItem(arquivoSelecionado.getName());
                caminhoAtualBD = caminho;
                conectarAoBancoDados();
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this,
                        "Erro ao criar banco de dados: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void conectarAoBancoDados() {
        // Fechar conexão anterior se existir
        fecharConexao();
        
        String itemSelecionado = (String) comboBancosDados.getSelectedItem();
        if (itemSelecionado == null || itemSelecionado.startsWith("--")) {
            rotuloStatus.setText("Nenhum banco de dados selecionado.");
            return;
        }
        
        try {
            // Garantir que o driver SQLite JDBC está no classpath
            Class.forName("org.sqlite.JDBC");
            
            // Determinar o caminho do banco de dados
            String url;
            if (!caminhoAtualBD.isEmpty() && caminhoAtualBD.endsWith(itemSelecionado)) {
                url = "jdbc:sqlite:" + caminhoAtualBD;
            } else {
                url = "jdbc:sqlite:" + DIRETORIO_PADRAO + itemSelecionado;
                caminhoAtualBD = DIRETORIO_PADRAO + itemSelecionado;
            }
            
            conexao = DriverManager.getConnection(url);
            rotuloStatus.setText("Conexão estabelecida com " + itemSelecionado);
            
            // Limpar resultados anteriores
            tabelaResultados.setModel(new DefaultTableModel());
            
            // Mostrar tabelas disponíveis
            listarTabelasDoBanco();
            
        } catch (ClassNotFoundException e) {
            rotuloStatus.setText("Erro: Driver SQLite não encontrado");
            JOptionPane.showMessageDialog(this,
                    "Driver SQLite não encontrado.\nVerifique se a dependência foi adicionada ao Maven.",
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            rotuloStatus.setText("Erro ao conectar: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Não foi possível conectar ao banco de dados.\nErro: " + e.getMessage(),
                    "Erro de Conexão", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void listarTabelasDoBanco() {
        if (conexao == null) return;
        
        try {
            DatabaseMetaData metadados = conexao.getMetaData();
            ResultSet tabelasRS = metadados.getTables(null, null, "%", new String[]{"TABLE"});
            
            List<String> tabelas = new ArrayList<>();
            while (tabelasRS.next()) {
                tabelas.add(tabelasRS.getString("TABLE_NAME"));
            }
            
            if (!tabelas.isEmpty()) {
                StringBuilder info = new StringBuilder("Tabelas disponíveis: ");
                for (int i = 0; i < tabelas.size(); i++) {
                    if (i > 0) info.append(", ");
                    info.append(tabelas.get(i));
                }
                rotuloStatus.setText(info.toString());
            } else {
                rotuloStatus.setText("Banco de dados conectado. Nenhuma tabela encontrada.");
            }
            
        } catch (SQLException e) {
            rotuloStatus.setText("Erro ao listar tabelas: " + e.getMessage());
        }
    }

    private void executarConsulta() {
        String sql = areaComandoSQL.getText().trim();
        
        if (sql.isEmpty()) {
            rotuloStatus.setText("Por favor insira um comando SQL.");
            return;
        }
        
        if (conexao == null) {
            rotuloStatus.setText("Nenhum banco de dados conectado. Selecione um banco primeiro.");
            return;
        }
        
        try {
            if (conexao.isClosed()) {
                conectarAoBancoDados();
                if (conexao == null) return;
            }
            
            long tempoInicial = System.currentTimeMillis();
            
            // Determinar tipo de consulta
            String tipoConsulta = sql.trim().split("\\s+")[0].toUpperCase();
            
            switch (tipoConsulta) {
                case "SELECT":
                    executarConsultaSelect(sql);
                    break;
                case "UPDATE":
                case "DELETE":
                case "INSERT":
                    executarConsultaAtualizacao(sql);
                    break;
                default:
                    executarConsultaPersonalizada(sql);
                    break;
            }
            
            long tempoFinal = System.currentTimeMillis();
            rotuloStatus.setText(rotuloStatus.getText() + " (Tempo: " + (tempoFinal - tempoInicial) + "ms)");
            
        } catch (SQLException e) {
            rotuloStatus.setText("Erro na execução: " + e.getMessage());
            JOptionPane.showMessageDialog(this,
                    "Erro ao executar o comando SQL:\n" + e.getMessage(),
                    "Erro de Execução", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void executarConsultaSelect(String sql) throws SQLException {
        try (Statement instrucao = conexao.createStatement();
             ResultSet conjuntoResultados = instrucao.executeQuery(sql)) {
            
            ResultSetMetaData metaDados = conjuntoResultados.getMetaData();
            int contagemColunas = metaDados.getColumnCount();
            
            // Criar cabeçalhos das colunas
            Vector<String> nomesColunas = new Vector<>();
            for (int i = 1; i <= contagemColunas; i++) {
                nomesColunas.add(metaDados.getColumnName(i));
            }
            
            // Criar linhas de dados
            Vector<Vector<Object>> dados = new Vector<>();
            while (conjuntoResultados.next()) {
                Vector<Object> linha = new Vector<>();
                for (int i = 1; i <= contagemColunas; i++) {
                    linha.add(conjuntoResultados.getObject(i));
                }
                dados.add(linha);
            }
            
            // Atualizar modelo da tabela
            DefaultTableModel modelo = new DefaultTableModel(dados, nomesColunas);
            tabelaResultados.setModel(modelo);
            
            rotuloStatus.setText("SELECT executado: " + dados.size() + " linhas retornadas.");
        }
    }

    private void executarConsultaAtualizacao(String sql) throws SQLException {
        try (Statement instrucao = conexao.createStatement()) {
            int linhasAfetadas = instrucao.executeUpdate(sql);
            rotuloStatus.setText("Comando executado: " + linhasAfetadas + " linhas afetadas.");
            
            // Limpar a tabela
            tabelaResultados.setModel(new DefaultTableModel());
            
            // Mostrar confirmação em um diálogo
            JOptionPane.showMessageDialog(this,
                    linhasAfetadas + " linhas afetadas pela operação.",
                    "Operação concluída", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void executarConsultaPersonalizada(String sql) throws SQLException {
        try (Statement instrucao = conexao.createStatement()) {
            boolean ehConjuntoResultados = instrucao.execute(sql);
            
            if (ehConjuntoResultados) {
                ResultSet conjuntoResultados = instrucao.getResultSet();
                ResultSetMetaData metaDados = conjuntoResultados.getMetaData();
                int contagemColunas = metaDados.getColumnCount();
                
                // Criar cabeçalhos das colunas
                Vector<String> nomesColunas = new Vector<>();
                for (int i = 1; i <= contagemColunas; i++) {
                    nomesColunas.add(metaDados.getColumnName(i));
                }
                
                // Criar linhas de dados
                Vector<Vector<Object>> dados = new Vector<>();
                while (conjuntoResultados.next()) {
                    Vector<Object> linha = new Vector<>();
                    for (int i = 1; i <= contagemColunas; i++) {
                        linha.add(conjuntoResultados.getObject(i));
                    }
                    dados.add(linha);
                }
                
                // Atualizar modelo da tabela
                DefaultTableModel modelo = new DefaultTableModel(dados, nomesColunas);
                tabelaResultados.setModel(modelo);
                
                rotuloStatus.setText("Consulta executada: " + dados.size() + " linhas retornadas.");
            } else {
                int linhasAfetadas = instrucao.getUpdateCount();
                rotuloStatus.setText("Comando executado: " + linhasAfetadas + " linhas afetadas.");
                tabelaResultados.setModel(new DefaultTableModel());
            }
        }
    }

    private void fecharConexao() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            System.err.println("Erro ao fechar conexão: " + e.getMessage());
        }
    }

    @Override
    public void dispose() {
        fecharConexao();
        super.dispose();
    }

    public static void main(String[] args) {
        try {
            // Definir aparência para aparência do sistema
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AplicativoIdeBd().setVisible(true);
            }
        });
    }
}
