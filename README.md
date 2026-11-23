# ⚔️ Sombras do Reino

Mini-RPG de console desenvolvido em Java com persistência em banco de dados relacional para o **Projeto Avaliativo Integrador: RPG - Do Código ao Produto** (IFTO — Campus Pedro Afonso, Prof. Bruno Ribeiro de Freitas Machado).

---

## 👥 Integrantes e Papéis

| Integrante | Papel Inicial (até Checkpoint 2) | Papel Pós-Rotação (Checkpoints 3 a 5) |
| :--- | :--- | :--- |
| **Isabella Vilas** | Versionamento / Qualidade | Integração JDBC & Documentação Final |
| **Ediny** | Banco de Dados | Qualidade, Testes de Aceitação & Validação Java |
| **Maria Eduarda** | Desenvolvimento / POO | Persistência SQL & Reprodutibilidade Final |

> **Governança da Equipe:** Conforme diretriz do projeto, os papéis serão rotacionados após o Checkpoint 2 para que todas tenham commits e domínio técnico em POO, Banco de Dados e Git/Qualidade.

---

## 📖 Visão Geral do Jogo

Em **Sombras do Reino**, o jogador escolhe um herói cadastrado no banco de dados (**Mago** ou **Arqueiro**) e enfrenta uma horda de inimigos em combates por turnos no console. Ao encerrar a partida (por vitória, derrota ou fuga), a quantidade restante de poções e o histórico do combate são salvos no banco de dados.

### ✨ Mecânica Autoral: Inventário com Poções Limitadas
* O herói inicia a partida com uma quantidade finita de poções de vida carregadas da tabela `inventario`.
* Durante o combate, o jogador pode consumir uma poção para recuperar pontos de vida (respeitando o limite máximo de `100`).
* Cada uso reduz a quantidade disponível na memória e atualiza o estoque no banco de dados. Quando o estoque chega a `0`, o sistema bloqueia o consumo e avisa que não há mais poções.

---

## 🏗️ Principais Classes e Estrutura do Repositório

```text
rpg-sombras-do-reino/
├── README.md                 # Apresentação, instruções e checklist do projeto
├── IA_LOG.md                 # Registro crítico das interações com IA (5 a 8 registros)
├── docs/
│   └── arquitetura.md        # Esboço das classes Java e modelagem das tabelas SQL
├── sql/
│   ├── 01_criacao.sql        # Estrutura DDL (CREATE TABLE, PKs, FKs)
│   └── 02_dados_iniciais.sql # Povoamento inicial DML (INSERT, UPDATE, DELETE)
└── src/
    ├── Personagem.java       # Classe abstrata com encapsulamento (vida 0..100)
    ├── Mago.java             # Subclasse concreta (ataque consome mana)
    ├── Arqueiro.java         # Subclasse concreta (ataque consome flechas)
    ├── Item.java             # Controle de poções limitadas em memória
    ├── ConexaoDB.java        # Configuração de conexão JDBC
    ├── JogoDAO.java          # Consultas e atualizações no banco de dados
    └── Main.java             # Game Loop no console e gerenciamento da horda
```

---

## 🚀 Como Preparar o Banco e Executar *(Em construção)*
1. **Banco de Dados:** Executar `sql/01_criacao.sql` e `sql/02_dados_iniciais.sql` em um banco limpo *(será detalhado no Checkpoint 3)*.
2. **Aplicação Java:** Compilar os arquivos da pasta `src/` com o driver JDBC e executar `Main` *(será detalhado no Checkpoint 4)*.

---

## 📅 Checklist dos Checkpoints (2 Semanas)
- [x] **Dia 1 — Lançamento:** Definição do tema, papéis iniciais, criação do repositório e `README.md` inicial.
- [x] **Checkpoint 1 — Planejamento:** Esboço das classes Java e modelagem das tabelas SQL (`docs/arquitetura.md`).
- [ ] **Checkpoint 2 — Núcleo POO:** Classes `Personagem`, `Mago`, `Arqueiro`, coleção `horda` e Game Loop parcial.
- [ ] **Checkpoint 3 — Banco de Dados:** Scripts DDL e DML testados em ambiente limpo sem erros.
- [ ] **Checkpoint 4 — Integração:** Conexão JDBC, carregamento de herói/inventário e salvamento de partida.
- [ ] **Checkpoint 5 — Qualidade e Documentação:** Bateria de testes (T01–T10), atualização do `README.md` e `IA_LOG.md`.
- [ ] **Entrega Final:** Revisão geral e tag/commit final para defesa técnica.