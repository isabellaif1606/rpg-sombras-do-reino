import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SOMBRAS DO REINO ===");
        System.out.print("Digite o nome do seu herói: ");
        String nomeHeroi = scanner.nextLine().trim();
        if (nomeHeroi.isEmpty()) {
            nomeHeroi = "Aventureiro";
        }

        // Escolha da classe do herói (demonstra polimorfismo logo no início)
        Personagem heroi = null;
        while (heroi == null) {
            System.out.println("\nEscolha sua classe:");
            System.out.println("[1] Mago (Vida: 100 | Mana: 30 | Dano: 25)");
            System.out.println("[2] Arqueiro (Vida: 100 | Flechas: 5 | Dano: 20)");
            System.out.print("Opção: ");
            String escolhaClasse = scanner.nextLine();

            if (escolhaClasse.equals("1")) {
                heroi = new Mago(1, nomeHeroi, 100, 1, 30);
            } else if (escolhaClasse.equals("2")) {
                heroi = new Arqueiro(1, nomeHeroi, 100, 1, 5);
            } else {
                System.out.println("Opção inválida! Digite 1 ou 2.");
            }
        }

        // Mecânica autoral: inventário com 2 poções de cura limitadas
        Item pocaoVida = new Item("Poção de Vida", 30, 2);

        // Coleção polimórfica de inimigos (Versão 0.5)
        List<Personagem> horda = new ArrayList<>();
        horda.add(new Mago(2, "Goblin Xamã", 45, 1, 20));
        horda.add(new Arqueiro(3, "Batedor Orc", 50, 1, 3));
        horda.add(new Mago(4, "Necromante Sombrio", 60, 2, 30));

        System.out.println("\nUma horda com " + horda.size() + " inimigos bloqueia o caminho!");

        // Game Loop controlado por booleano (Versão 1.0)
        boolean jogando = true;

        while (jogando) {
            System.out.println("\n----------------------------------------");
            System.out.println("HERÓI: " + heroi.getNome() + " | Vida: " + heroi.getVida() + "/100 | Poções: " + pocaoVida.getQuantidade());
            System.out.println("INIMIGOS RESTANTES: " + horda.size());
            if (!horda.isEmpty()) {
                Personagem alvoAtual = horda.get(0);
                System.out.println("PRÓXIMO ALVO: " + alvoAtual.getNome() + " (Vida: " + alvoAtual.getVida() + ")");
            }
            System.out.println("----------------------------------------");
            System.out.println("[1] Atacar  [2] Usar Poção  [3] Status da Horda  [4] Fugir");
            System.out.print("Escolha sua ação: ");

            int opcao;
            try {
                // Tratamento contra InputMismatchException / letras no menu (Teste T18)
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida! Digite apenas números de 1 a 4.");
                continue;
            }

            boolean turnoConsumido = false;

            switch (opcao) {
                case 1:
                    if (!horda.isEmpty()) {
                        Personagem inimigo = horda.get(0);
                        int danoCausado = heroi.atacar();
                        inimigo.receberDano(danoCausado);
                        System.out.println(inimigo.getNome() + " ficou com " + inimigo.getVida() + " de vida.");

                        if (!inimigo.estaVivo()) {
                            System.out.println(">> " + inimigo.getNome() + " foi derrotado!");
                            horda.remove(0);
                        }
                        turnoConsumido = true;
                    }
                    break;

                case 2:
                    int cura = pocaoVida.usar();
                    if (cura > 0) {
                        heroi.receberCura(cura);
                        System.out.println("Vida atual de " + heroi.getNome() + ": " + heroi.getVida() + "/100");
                    }
                    turnoConsumido = true;
                    break;

                case 3:
                    System.out.println("\n--- STATUS DA HORDA ---");
                    for (Personagem p : horda) {
                        System.out.println("- " + p.getNome() + " | Vida: " + p.getVida());
                    }
                    break;

                case 4:
                    System.out.println("\nVocê recuou estrategicamente das Sombras do Reino. Fim de jogo!");
                    jogando = false;
                    break;

                default:
                    System.out.println("Opção inexistente! Escolha entre 1 e 4.");
            }

            // Verifica condição de vitória (Teste T05 / T15)
            if (jogando && horda.isEmpty()) {
                System.out.println("\nVITÓRIA! Todos os inimigos da horda foram derrotados!");
                jogando = false;
            }

            // Reação do inimigo ativo se o jogo ainda estiver ativo
            if (jogando && turnoConsumido && !horda.isEmpty()) {
                Personagem inimigoAtivo = horda.get(0);
                System.out.println("\n[Turno do Inimigo]");
                int danoInimigo = inimigoAtivo.atacar();
                heroi.receberDano(danoInimigo);
                System.out.println(heroi.getNome() + " agora tem " + heroi.getVida() + "/100 de vida.");

                // Verifica condição de derrota (Teste T06 / T16)
                if (!heroi.estaVivo()) {
                    System.out.println("\nDERROTA! " + heroi.getNome() + " caiu em batalha...");
                    jogando = false;
                }
            }
        }

        scanner.close();
    }
}
