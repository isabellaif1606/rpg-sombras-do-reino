public class Mago extends Personagem {

    // Atributo específico e encapsulado do Mago
    private int mana;

    public Mago(int id, String nome, int vida, int nivel, int mana) {
        // Repassa os atributos comuns para o construtor da superclasse Personagem
        super(id, nome, vida, nivel);
        this.mana = Math.max(0, mana);
    }

    @Override
    public int atacar() {
        int custoMana = 10;
        int danoMagico = 25;

        if (this.mana >= custoMana) {
            this.mana -= custoMana;
            System.out.println(getNome() + " lançou Bola de Fogo! (-" + custoMana + " mana | Restante: " + this.mana + ")");
            return danoMagico;
        } else {
            System.out.println(getNome() + " está sem mana suficiente para atacar! (Mana atual: " + this.mana + ")");
            return 0;
        }
    }

    public int getMana() {
        return mana;
    }
}
