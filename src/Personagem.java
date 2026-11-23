public abstract class Personagem {

    // Encapsulamento: atributos privados protegidos contra alteração direta
    private int id;
    private String nome;
    private int vida;
    private int nivel;

    public Personagem(int id, String nome, int vida, int nivel) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;

        // Garante que a vida inicial já respeite o intervalo de 0 a 100
        if (vida < 0) {
            this.vida = 0;
        } else if (vida > 100) {
            this.vida = 100;
        } else {
            this.vida = vida;
        }
    }

    // Aplica dano respeitando o piso mínimo 0 (Testes T01 e T02)
    public void receberDano(int dano) {
        if (dano > 0) {
            this.vida -= dano;
            if (this.vida < 0) {
                this.vida = 0;
            }
        }
    }

    // Aplica cura respeitando o teto máximo 100 (Teste T03)
    public void receberCura(int valor) {
        if (valor > 0) {
            this.vida += valor;
            if (this.vida > 100) {
                this.vida = 100;
            }
        }
    }

    public boolean estaVivo() {
        return this.vida > 0;
    }

    // Abstração: obriga Mago e Arqueiro a implementarem seu próprio ataque
    public abstract int atacar();

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getVida() {
        return vida;
    }

    public int getNivel() {
        return nivel;
    }
}
