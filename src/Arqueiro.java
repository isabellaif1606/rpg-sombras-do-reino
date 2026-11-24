public class Arqueiro extends Personagem {

    // Atributo específico e encapsulado do Arqueiro
    private int flechas;

    public Arqueiro(int id, String nome, int vida, int nivel, int flechas) {
        // Repassa os atributos comuns para o construtor da superclasse Personagem
        super(id, nome, vida, nivel);
        this.flechas = Math.max(0, flechas);
    }

    @Override
    public int atacar() {
        int danoFisico = 20;

        if (this.flechas >= 1) {
            this.flechas -= 1;
            System.out.println(getNome() + " disparou Flecha Precisa! (-1 flecha | Restantes: " + this.flechas + ")");
            return danoFisico;
        } else {
            System.out.println(getNome() + " está sem flechas na aljava para atacar!");
            return 0;
        }
    }

    public int getFlechas() {
        return flechas;
    }
}
