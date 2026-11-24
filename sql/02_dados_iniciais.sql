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
-- Completando mínimo de 5 itens no catálogo (Teste A04)
INSERT INTO item (nome, tipo, valor, raridade) VALUES 
('Elixir de Mana', 'MANA', 20, 'Comum'),
('Aljava Reforçada', 'MUNICAO', 5, 'Comum'),
('Amuleto das Sombras', 'DEFESA', 15, 'Épico');

-- Completando mínimo de 6 registros no inventário (Teste A05)
INSERT INTO inventario (id_personagem, id_item, quantidade) VALUES 
(1, 3, 2),
(2, 4, 2),
(3, 1, 1),
(4, 5, 1);

-- Atualização segura com WHERE (DML)
UPDATE personagem SET experiencia = 150, nivel = 2 WHERE id_personagem = 1;
UPDATE item SET raridade = 'Rara' WHERE id_item = 3;

-- Teste seguro de inserção e remoção de registro com DELETE ... WHERE (Teste A09)
INSERT INTO item (id_item, nome, tipo, valor, raridade) VALUES 
(999, 'Item de Teste', 'TESTE', 1, 'Comum');

DELETE FROM item WHERE id_item = 999;