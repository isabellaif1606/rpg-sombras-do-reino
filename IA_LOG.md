# 🤖 Registro de Uso de Inteligência Artificial — Sombras do Reino

> Conforme diretriz da disciplina, este log documenta as interações mais relevantes com IA generativa durante o desenvolvimento, evidenciando análise crítica, adaptação e validação técnica pelo trio.

---

### Registro 01 — Planejamento da Arquitetura POO e Modelagem do Banco (Checkpoint 1)
* **Problema:** Precisávamos estruturar a divisão de responsabilidades entre as classes Java (incluindo a mecânica autoral de poções limitadas) e as tabelas do banco de dados antes de iniciar a codificação, garantindo aderência aos roteiros da disciplina.
* **Prompt:** Solicitamos o esboço arquitetural das classes Java (`Personagem`, `Mago`, `Arqueiro`, `Item`, `ConexaoDB`, `JogoDAO`, `Main`) e a modelagem das tabelas SQL (`classe_personagem`, `personagem`, `item`, `inventario`, `partida`) com suas chaves primárias e estrangeiras para o jogo Sombras do Reino.
* **Sugestão da IA:** Manter `Personagem` como classe abstrata com encapsulamento rigoroso (`private` e limites de vida 0 a 100), isolar o acesso JDBC em `ConexaoDB` e `JogoDAO`, e estruturar 5 tabelas relacionais usando chave primária composta `(id_personagem, id_item)` na tabela `inventario` para controlar o estoque de poções.
* **Decisão do grupo:** Revisamos o esboço em trio e aprovamos a inclusão da coluna `recurso_atual` na tabela `personagem` para persistir a mana inicial do Mago e as flechas do Arqueiro diretamente do banco, além de manter a tabela `partida` para registrar o desfecho dos combates.
* **Teste realizado:** Conferimos manualmente todas as ligações de PK/FK entre as 5 tabelas e verificamos se cada requisito obrigatório de POO e persistência estava mapeado no documento `docs/arquitetura.md`.

---

### Registro 02 — Planejamento Operacional por Integrante e Rotação de Papéis (Checkpoint 1)
* **Problema:** Precisávamos desdobrar o plano geral do projeto em tarefas práticas, critérios de aceite e metas de commits para cada integrante do trio, contemplando a rotação obrigatória de papéis após o Checkpoint 2.
* **Prompt:** Solicitamos a criação de planos individuais de execução para Isabella Vilas, Ediny e Maria Eduarda alinhados aos roteiros de POO, Banco de Dados e Git/GitHub.
* **Sugestão da IA:** Dividir as responsabilidades em Fase 1 (papéis iniciais até o Checkpoint 2) e Fase 2 (rotação de lideranças nos Checkpoints 3 a 5), vinculando cada etapa aos testes de aceitação oficiais (T01–T10) e à preparação para a banca individual.
* **Decisão do grupo:** Definimos a ordem sequencial de inicialização do repositório pela Isabella e, em seguida, o trabalho paralelo em pastas separadas (`src/` e `sql/`) para evitar conflitos no Git antes da integração.
* **Teste realizado:** Validamos em trio se todas as entregas obrigatórias e trocas de papel estavam distribuídas de forma equilibrada entre as três integrantes.

---

### Registro 03 — Implementação de Herança e Polimorfismo em Mago e Arqueiro (Checkpoint 2)
* **Problema:** Precisávamos criar as classes concretas `Mago` e `Arqueiro` herdando de `Personagem`, garantindo que cada uma controlasse seu próprio recurso especial (mana e flechas) e bloqueasse o ataque quando o recurso acabasse.
* **Prompt:** Solicitamos a implementação incremental das subclasses `Mago.java` e `Arqueiro.java` alinhadas à classe abstrata `Personagem` e aos testes de polimorfismo e bloqueio de recurso do roteiro de POO.
* **Sugestão da IA:** Usar `extends Personagem` com chamada `super(...)` nos construtores, encapsular `mana` e `flechas` como `private` e sobrescrever `@Override atacar()` descontando o recurso e retornando `0` quando insuficiente.
* **Decisão do grupo:** Definimos o ataque do Mago com dano 25 (custo de 10 de mana) e o ataque do Arqueiro com dano 20 (custo de 1 flecha), exibindo no console o consumo e o saldo restante a cada turno.
* **Teste realizado:** Compilamos `src/*.java` com `javac` sem erros e validamos que o `.gitignore` oculta os arquivos `.class` gerados.

---

---

### Registro 04 — Estruturação do Banco Relacional e Carga Inicial no MySQL (Checkpoint 3)
* **Problema:** Precisávamos construir os scripts SQL (`01_criacao.sql` e `02_dados_iniciais.sql`) no MySQL representando classes, personagens, itens, inventário e partidas sem usar comandos `SELECT`, garantindo integridade referencial e todos os testes DDL/DML do laboratório Banco do Reino.
* **Prompt:** Solicitamos revisão e validação dos scripts DDL e DML criados para o banco `sombras_do_reino` conforme os requisitos de chaves primárias/estrangeiras, evolução com `ALTER TABLE` e manipulações seguras com `UPDATE` e `DELETE`.
* **Sugestão da IA:** Manter a chave primária composta `(id_personagem, id_item)` em `inventario` e a tabela `partida`, complementar o catálogo para 5 itens e 6 registros de inventário, e incluir `ALTER TABLE` (`experiencia`), `UPDATE ... WHERE`, `DELETE ... WHERE` e `DROP TABLE rascunho`.
* **Decisão do grupo:** Aplicamos os complementos nos scripts da pasta `sql/` alinhando os nomes e valores dos personagens (`Eldrin` e `Lyra`) e poções com a mecânica autoral do jogo em Java.
* **Teste realizado:** Reexecutamos `01_criacao.sql` e `02_dados_iniciais.sql` em um banco limpo no MySQL e validamos pela árvore de objetos e pela grade de dados (sem `SELECT`) a criação das 5 tabelas, rejeição de chaves inválidas e remoção segura dos itens de teste.
