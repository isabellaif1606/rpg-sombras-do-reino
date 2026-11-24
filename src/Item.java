public class Item {

    // Encapsulamento: impede alteração direta da quantidade de poções
    private String nome;
    private int valorCura;
    private int quantidade;

    public Item(String nome, int valorCura, int quantidade) {
        this.nome = nome;
        this.valorCura = Math.max(0, valorCura);
        this.quantidade = Math.max(0, quantidade);
    }

    // Regra da mecânica autoral: consome 1 unidade se houver estoque; bloqueia se estiver zerado
    public int usar() {
        if (this.quantidade > 0) {
            this.quantidade--;
            System.out.println("Você usou " + this.nome + "! (+" + this.valorCura + " de vida | Restantes: " + this.quantidade + ")");
            return this.valorCura;
        } else {
            System.out.println("Sem estoque de " + this.nome + "! Você não possui mais poções.");
            return 0;
        }
    }

    public String getNome() {
        return nome;
    }

    public int getQuantidade() {
        return quantidade;
    }
}
