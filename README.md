# SQLite Database IDE JAVA

**A graphical interface for SQLite database manipulation, developed in Java with Swing**

![Project Logo](https://via.placeholder.com/800x200?text=SQLite+Database+IDE)

## 📋 Table of Contents

- [About the Project](#about-the-project)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [System Requirements](#system-requirements)
- [How to Install](#how-to-install)
- [How to Use](#how-to-use)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

## 📄 About the Project

The **SQLite Database IDE** is a desktop application developed in Java that allows users to connect, view, and manipulate SQLite databases intuitively through a user-friendly graphical interface.

Similar to tools like SQLiteStudio, this application allows you to execute SQL queries, view results in tabular format, and manage multiple databases, all within a simple and functional interface.

## ✨ Features

### Database Management
- **Database Selection**: Choose from available databases in the application folder
- **File Navigation**: Select databases from any location on your system
- **Database Creation**: Easily create new SQLite databases

### SQL Editor
- **Query Editor**: Interface for writing and editing SQL commands with monospaced font
- **Command Execution**: Support for SELECT, UPDATE, DELETE, INSERT commands and more
- **Tabular Visualization**: Results displayed in table format for easy reading

### Intuitive Interface
- **Status Bar**: Information about connection, execution time, and query results
- **Table List**: Automatic display of available tables in the connected database
- **Intuitive Menu**: Quick access to features through an organized menu

### SQL Command Support
```sql
-- Examples of supported commands:
SELECT * FROM Positions
UPDATE Positions SET POS_Title = 'Senior Java Developer' WHERE POS_ID = 1
DELETE FROM Positions WHERE POS_ID = 1
INSERT INTO Positions (POS_Title, POS_Description, POS_Code) VALUES ('Junior Systems Analyst', 'Entry Level Systems Analyst', 77)
```

## 🛠️ Technologies Used

- **Java 11**: Main programming language
- **Swing**: GUI framework
- **SQLite JDBC**: Driver for SQLite database connection
- **Maven**: Dependency management and build

## 📋 System Requirements

- Java Runtime Environment (JRE) 11 or higher
- Minimum 100MB RAM
- Disk space to store SQLite databases
- Compatible with Windows, macOS, and Linux

## 📥 How to Install

### Option 1: Run the JAR (Recommended)
1. Download the `sqlite-ide-app-1.0-SNAPSHOT-jar-with-dependencies.jar` file from the releases section
2. Place the JAR file in any directory on your system
3. Run the JAR with a double-click or via command line:
   ```bash
   java -jar sqlite-ide-app-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

### Option 2: Compile from source code
1. Clone this repository:
   ```bash
   git clone https://github.com/your-username/sqlite-ide-app.git
   cd sqlite-ide-app
   ```

2. Compile the project with Maven:
   ```bash
   mvn clean package
   ```

3. Run the generated JAR:
   ```bash
   java -jar target/sqlite-ide-app-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## 📝 How to Use

### Connect to a Database
1. Start the application
2. Select a database from the dropdown list, or
3. Click "Browse..." to locate a database on your system, or
4. Go to File > New Database to create a new one

### Execute SQL Queries
1. Type your SQL command in the editor text area
2. Click the "Execute" button or press Ctrl+Enter
3. View the results in the table below the editor
4. Check additional information in the status bar

### Query Examples

#### Simple Query
```sql
SELECT * FROM Positions
```

#### Record Update
```sql
UPDATE Positions 
SET POS_Title = 'Senior Java Developer' 
WHERE POS_ID = 1
```

#### Record Deletion
```sql
DELETE FROM Positions 
WHERE POS_ID = 1
```

#### Data Insertion
```sql
INSERT INTO Positions (POS_Title, POS_Description, POS_Code) 
VALUES ('Junior Systems Analyst', 'Entry Level Systems Analyst', 77)
```

## 📁 Project Structure

```
sqlite-ide-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── database/
│       │           └── ide/
│       │               └── SqliteIdeApp.java
│       └── resources/
│           └── ...
├── pom.xml
├── README.md
└── LICENSE
```

### Description of main files

- `SqliteIdeApp.java`: Main class containing all application logic and graphical interface
- `pom.xml`: Maven configuration file with project dependencies

## 🧠 Design Decisions

### Why Java Swing?
Swing was chosen to create the graphical interface due to its portability across operating systems and native integration with Java. Although there are more modern frameworks, Swing offers stability and a well-established API.

### Code Architecture
The project was implemented following an object-oriented approach with a single main file that encapsulates all functionality. For more complex applications, it would be advisable to refactor into multiple classes following patterns like MVC.

### Error Handling
The application implements exception handling to deal with database connection problems, SQL syntax errors, and other exceptional situations, always providing feedback to the user.

## 🤝 Contributing

Contributions are welcome! If you want to improve this project, follow these steps:

1. Fork the project
2. Create a branch for your feature (`git checkout -b feature/new-feature`)
3. Commit your changes (`git commit -m 'Add new feature'`)
4. Push to the branch (`git push origin feature/new-feature`)
5. Open a Pull Request

### Areas for Contribution
- Improve result visualization
- Add query history
- Implement result export
- Add table schema visualization
- Implement SQL autocomplete features

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 📞 Contact: (011) 98678-2984

Name: esricci26@gmail.com

Project Link: https://github.com/ESRicci26/Aplicativo_IDE_BD_SQLite

---

## 📊 Screenshots

### Main Screen
![Main Screen](https://via.placeholder.com/800x500?text=Main+Screen)

### Database Selection
![DB Selection](https://via.placeholder.com/800x500?text=Database+Selection)

### Query Execution
![Query Execution](https://via.placeholder.com/800x500?text=Query+Execution)

---

⭐️ Star this project if it was useful to you!

# IDE de Banco de Dados SQLite JAVA

**Uma interface gráfica para manipulação de bancos de dados SQLite, desenvolvida em Java com Swing**

![Logo do Projeto](https://via.placeholder.com/800x200?text=IDE+de+Banco+de+Dados+SQLite)

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Requisitos do Sistema](#requisitos-do-sistema)
- [Como Instalar](#como-instalar)
- [Como Usar](#como-usar)
- [Estrutura do Projeto](#estrutura-do-projeto)
- [Contribuição](#contribuição)
- [Licença](#licença)
- [Contato](#contato)

## 📄 Sobre o Projeto

A **IDE de Banco de Dados SQLite** é uma aplicação desktop desenvolvida em Java que permite aos usuários conectar, visualizar e manipular bancos de dados SQLite de forma intuitiva através de uma interface gráfica amigável.

Semelhante a ferramentas como SQLiteStudio, esta aplicação permite executar consultas SQL, visualizar os resultados em formato tabular e gerenciar múltiplos bancos de dados, tudo dentro de uma interface simples e funcional.

## ✨ Funcionalidades

### Gerenciamento de Banco de Dados
- **Seleção de Banco de Dados**: Escolha entre os bancos de dados disponíveis na pasta da aplicação
- **Navegação de Arquivos**: Selecione bancos de dados em qualquer local do seu sistema
- **Criação de Banco de Dados**: Crie novos bancos de dados SQLite facilmente

### Editor SQL
- **Editor de Consultas**: Interface para escrever e editar comandos SQL com fonte monoespaçada
- **Execução de Comandos**: Suporte para comandos SELECT, UPDATE, DELETE, INSERT e outros
- **Visualização Tabular**: Resultados exibidos em formato de tabela para fácil leitura

### Interface Intuitiva
- **Barra de Status**: Informações sobre conexão, tempo de execução e resultados das consultas
- **Lista de Tabelas**: Exibição automática das tabelas disponíveis no banco conectado
- **Menu Intuitivo**: Acesso rápido às funcionalidades através de um menu organizado

### Suporte a Comandos SQL
```sql
-- Exemplos de comandos suportados:
SELECT * FROM Cargos
UPDATE Cargos SET CAR_D1sCargoRes = 'Programador Java Pleno' WHERE ID_Cargo = 1
DELETE FROM Cargos WHERE ID_Cargo = 1
INSERT INTO Cargos (CAR_D1sCargoRes, CAR_DescritivoCargo, CAR_IDCBO) VALUES ('Analista Sistemas JR', 'Analista Sistemas Junior', 77)
```

## 🛠️ Tecnologias Utilizadas

- **Java 11**: Linguagem de programação principal
- **Swing**: Framework para interface gráfica
- **SQLite JDBC**: Driver para conexão com bancos de dados SQLite
- **Maven**: Gerenciamento de dependências e build

## 📋 Requisitos do Sistema

- Java Runtime Environment (JRE) 11 ou superior
- Mínimo de 100MB de RAM
- Espaço em disco para armazenar bancos de dados SQLite
- Compatível com Windows, macOS e Linux

## 📥 Como Instalar

### Opção 1: Executar o JAR (Recomendado)
1. Faça o download do arquivo `sqlite-ide-app-1.0-SNAPSHOT-jar-with-dependencies.jar` da seção de releases
2. Coloque o arquivo JAR em qualquer diretório do seu sistema
3. Execute o JAR com um duplo clique ou via linha de comando:
   ```bash
   java -jar sqlite-ide-app-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

### Opção 2: Compilar a partir do código fonte
1. Clone este repositório:
   ```bash
   git clone https://github.com/seu-usuario/sqlite-ide-app.git
   cd sqlite-ide-app
   ```

2. Compile o projeto com Maven:
   ```bash
   mvn clean package
   ```

3. Execute o JAR gerado:
   ```bash
   java -jar target/sqlite-ide-app-1.0-SNAPSHOT-jar-with-dependencies.jar
   ```

## 📝 Como Usar

### Conectar a um Banco de Dados
1. Inicie o aplicativo
2. Selecione um banco de dados da lista suspensa, ou
3. Clique em "Procurar..." para localizar um banco de dados em seu sistema, ou
4. Vá em Arquivo > Novo Banco de Dados para criar um novo

### Executar Consultas SQL
1. Digite seu comando SQL na área de texto do editor
2. Clique no botão "Executar" ou pressione Ctrl+Enter
3. Visualize os resultados na tabela abaixo do editor
4. Confira informações adicionais na barra de status

### Exemplos de Consultas

#### Consulta Simples
```sql
SELECT * FROM Cargos
```

#### Atualização de Registros
```sql
UPDATE Cargos 
SET CAR_D1sCargoRes = 'Programador Java Pleno' 
WHERE ID_Cargo = 1
```

#### Exclusão de Registros
```sql
DELETE FROM Cargos 
WHERE ID_Cargo = 1
```

#### Inserção de Dados
```sql
INSERT INTO Cargos (CAR_D1sCargoRes, CAR_DescritivoCargo, CAR_IDCBO) 
VALUES ('Analista Sistemas JR', 'Analista Sistemas Junior', 77)
```

## 📁 Estrutura do Projeto

```
sqlite-ide-app/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── database/
│       │           └── ide/
│       │               └── AplicativoIdeBd.java
│       └── resources/
│           └── ...
├── pom.xml
├── README.md
└── LICENSE
```

### Descrição dos arquivos principais

- `AplicativoIdeBd.java`: Classe principal que contém toda a lógica da aplicação e interface gráfica
- `pom.xml`: Arquivo de configuração do Maven com as dependências do projeto

## 🧠 Decisões de Design

### Por que Java Swing?
Swing foi escolhido para criar a interface gráfica devido à sua portabilidade entre sistemas operacionais e integração nativa com o Java. Embora existam frameworks mais modernos, Swing oferece estabilidade e uma API bem estabelecida.

### Arquitetura do Código
O projeto foi implementado seguindo uma abordagem orientada a objetos com um único arquivo principal que encapsula toda a funcionalidade. Para aplicações mais complexas, seria recomendável refatorar em múltiplas classes seguindo padrões como MVC.

### Tratamento de Erros
A aplicação implementa tratamento de exceções para lidar com problemas de conexão com o banco de dados, erros de sintaxe SQL e outras situações excepcionais, sempre fornecendo feedback ao usuário.

## 🤝 Contribuição

Contribuições são bem-vindas! Se você quiser melhorar este projeto, siga estes passos:

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Faça commit das suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

### Áreas para Contribuição
- Melhorar a visualização de resultados
- Adicionar histórico de consultas
- Implementar exportação de resultados
- Adicionar visualização de esquema de tabelas
- Implementar funcionalidades de autocompletar para SQL

## 📄 Licença

Este projeto está licenciado sob a Licença MIT - veja o arquivo [LICENSE](LICENSE) para detalhes.

## 📞 Contato

[Seu Nome] - [@seu_twitter](https://twitter.com/seu_twitter) - email@exemplo.com

Link do Projeto: [https://github.com/seu-usuario/sqlite-ide-app](https://github.com/seu-usuario/sqlite-ide-app)

---

## 📊 Screenshots

### Tela Principal
![Tela Principal](https://via.placeholder.com/800x500?text=Tela+Principal)

### Seleção de Banco de Dados
![Seleção de BD](https://via.placeholder.com/800x500?text=Selecao+de+Banco+de+Dados)

### Execução de Consulta
![Execução de Consulta](https://via.placeholder.com/800x500?text=Execucao+de+Consulta)

---

⭐️ Dê uma estrela se este projeto foi útil para você!
