# 📐 Arquitetura do Sistema — Sombras do Reino

## 1. Arquitetura Orientada a Objetos (Java — `src/`)

A camada Java é dividida em três responsabilidades: **Modelo de Domínio** (regras do RPG), **Persistência** (acesso ao banco via DAO) e **Controle/Console** (Game Loop).

### 1.1 Diagrama Textual de Classes

```text
          «abstract»
          Personagem
   ┌───────────────────────┐
   │ - id: int             │
   │ - nome: String        │
   │ - vida: int           │
   │ - nivel: int          │
   ├───────────────────────┤
   │ + receberDano(int)    │
   │ + receberCura(int)    │
   │ + estaVivo(): boolean │
   │ + {abstract} atacar() │
   └───────────▲───────────┘
               │ (Herança)
       ┌───────┴───────┐
       │               │
     Mago          Arqueiro
┌────────────┐  ┌──────────────┐
│ - mana:int │  │ - flechas:int│
├────────────┤  ├──────────────┤
│ + atacar() │  │ + atacar()   │
└────────────┘  └──────────────┘
```

### 1.2 Especificação das Classes

1. **`Personagem` (`public abstract class Personagem`)**
   * **Pilares de POO:** Abstração e Encapsulamento. É `abstract` para impedir a criação de um personagem genérico sem ataque definido (`new Personagem(...)`).
   * **Atributos (`private`):** `id` (`int`), `nome` (`String`), `vida` (`int`), `nivel` (`int`).
   * **Métodos:**
     * Construtor parametrizado e `getters` públicos.
     * `receberDano(int dano)`: subtrai o dano da vida garantindo piso mínimo `0` (`if (this.vida < 0) this.vida = 0`).
     * `receberCura(int valor)`: soma a cura à vida garantindo teto máximo `100` (`if (this.vida > 100) this.vida = 100`).
     * `estaVivo()`: retorna `this.vida > 0`.
     * `public abstract int atacar()`: obriga cada subclasse a implementar sua própria regra de ataque e retornar o dano causado.

2. **`Mago` (`public class Mago extends Personagem`)**
   * **Pilares de POO:** Herança e Polimorfismo.
   * **Atributo (`private`):** `mana` (`int`).
   * **Comportamento:** Sobrescreve `@Override atacar()`. Se `mana >= 10`, consome 10 de mana e retorna o dano mágico (ex: `25`); caso contrário, exibe aviso de mana insuficiente e retorna `0`.

3. **`Arqueiro` (`public class Arqueiro extends Personagem`)**
   * **Pilares de POO:** Herança e Polimorfismo.
   * **Atributo (`private`):** `flechas` (`int`).
   * **Comportamento:** Sobrescreve `@Override atacar()`. Se `flechas >= 1`, consome 1 flecha e retorna o dano físico (ex: `20`); caso contrário, exibe aviso de falta de flechas e retorna `0`.

4. **`Item` (`public class Item`)**
   * **Responsabilidade:** Representar itens carregados do banco (como a *Poção de Vida*) e controlar a quantidade disponível durante a partida (mecânica autoral).
   * **Atributos (`private`):** `idItem` (`int`), `nome` (`String`), `tipo` (`String`), `valorEfeito` (`int`), `raridade` (`String`), `quantidade` (`int`).
   * **Métodos:** `usar()`: verifica se `quantidade > 0`; se sim, decrementa 1 unidade e retorna `true` (liberando a cura); se não, retorna `false` e bloqueia o uso.

5. **`ConexaoDB` (`public class ConexaoDB`)**
   * **Responsabilidade:** Centralizar a configuração da URL, usuário e driver JDBC, fornecendo `conectar()` que retorna `java.sql.Connection` com tratamento de exceções.

6. **`JogoDAO` (`public class JogoDAO`)**
   * **Responsabilidade:** Camada de Acesso a Dados (DAO) que converte registros SQL em objetos Java e salva mudanças no banco:
     * `listarHerois()`: consulta `personagem` + `classe_personagem` para seleção inicial.
     * `carregarPocaoDoHeroi(int idPersonagem)`: busca o item de cura e sua quantidade na tabela `inventario`.
     * `atualizarQuantidadeItem(int idPersonagem, int idItem, int novaQtd)`: executa `UPDATE inventario` após o consumo de poções.
     * `registrarPartida(int idPersonagem, String resultado, int turnos, int inimigosDerrotados)`: executa `INSERT INTO partida` ao final do combate.

7. **`Main` (`public class Main`)**
   * **Responsabilidade:** Ponto de entrada da aplicação e controle do **Game Loop**.
   * **Estrutura:**
     * Lê opções do usuário via `Scanner` com validação de entrada (`try-catch` ou leitura de `String` convertida) para evitar `InputMismatchException`.
     * Gerencia os inimigos em `List<Personagem> horda = new ArrayList<>()`, sempre verificando `!horda.isEmpty()` antes de acessar índices.
     * Controla o laço `while (jogando)` com ações `[1] Atacar`, `[2] Usar Poção`, `[3] Consultar Status` e `[4] Fugir` até atingir vitória (`horda.isEmpty()`), derrota (`!heroi.estaVivo()`) ou fuga.

---

## 2. Modelagem do Banco de Dados (`sql/01_criacao.sql`)

O modelo relacional segue a estrutura do laboratório *Banco do Reino*, acrescentando a tabela `partida` para registrar o histórico dos combates.

### 2.1 Tabelas, Chaves e Integridade Referencial

1. **`classe_personagem`** (Arquétipos de classe do jogo)
   * `id_classe` `INTEGER PRIMARY KEY`
   * `nome` `VARCHAR(40) NOT NULL` (ex: `'Mago'`, `'Arqueiro'`)
   * `recurso_especial` `VARCHAR(30) NOT NULL` (ex: `'Mana'`, `'Flechas'`)

2. **`personagem`** (Heróis e inimigos cadastrados)
   * `id_personagem` `INTEGER PRIMARY KEY`
   * `nome` `VARCHAR(60) NOT NULL`
   * `vida` `INTEGER NOT NULL`
   * `nivel` `INTEGER NOT NULL DEFAULT 1`
   * `recurso_atual` `INTEGER NOT NULL` (mana inicial do Mago ou flechas do Arqueiro)
   * `id_classe` `INTEGER NOT NULL` — `FOREIGN KEY (id_classe) REFERENCES classe_personagem(id_classe)`

3. **`item`** (Catálogo de itens do reino)
   * `id_item` `INTEGER PRIMARY KEY`
   * `nome` `VARCHAR(60) NOT NULL` (ex: `'Poção de Vida Menor'`)
   * `tipo` `VARCHAR(30) NOT NULL` (ex: `'CURA'`)
   * `valor` `INTEGER NOT NULL` (ex: `30` pontos de vida recuperados)
   * `raridade` `VARCHAR(20) NOT NULL` (ex: `'Comum'`, `'Rara'`)

4. **`inventario`** (Relacionamento N:N entre personagem e item — suporte à mecânica autoral)
   * `id_personagem` `INTEGER NOT NULL` — `FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem)`
   * `id_item` `INTEGER NOT NULL` — `FOREIGN KEY (id_item) REFERENCES item(id_item)`
   * `quantidade` `INTEGER NOT NULL CHECK (quantidade >= 0)`
   * **Chave Primária Composta:** `PRIMARY KEY (id_personagem, id_item)` — garante unicidade por par personagem/item sem duplicar linhas.

5. **`partida`** (Histórico de sessões jogadas)
   * `id_partida` `INTEGER PRIMARY KEY` (autoincremento conforme SGBD adotado)
   * `id_personagem` `INTEGER NOT NULL` — `FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem)`
   * `resultado` `VARCHAR(20) NOT NULL` (ex: `'VITORIA'`, `'DERROTA'`, `'FUGA'`)
   * `turnos_jogados` `INTEGER NOT NULL`
   * `inimigos_derrotados` `INTEGER NOT NULL`