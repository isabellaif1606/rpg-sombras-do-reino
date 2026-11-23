USE sombras_do_reino;

INSERT INTO classe_personagem (nome, recurso_especial) VALUES 
('Mago', 'Mana'),
('Arqueiro', 'Flechas'),
('Monstro', 'Fúria');

INSERT INTO personagem (nome, vida, nivel, recurso_atual, id_classe) VALUES 
('Eldrin, o Sábio', 100, 1, 50, 1),    
('Lyra, Olho de Águia', 100, 1, 15, 2), 
('Goblin Saqueador', 40, 1, 10, 3),
('Orc Brutamontes', 70, 2, 20, 3);

INSERT INTO item (nome, tipo, valor, raridade) VALUES 
('Poção de Vida Menor', 'CURA', 30, 'Comum'),
('Poção de Vida Maior', 'CURA', 60, 'Rara');


INSERT INTO inventario (id_personagem, id_item, quantidade) VALUES 
(1, 1, 3), 
(2, 1, 3); 

INSERT INTO partida (id_personagem, resultado, turnos_jogados, inimigos_derrotados) VALUES 
(1, 'VITORIA', 5, 2);