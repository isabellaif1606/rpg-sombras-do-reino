CREATE DATABASE IF NOT EXISTS sombras_do_reino;
USE sombras_do_reino;

CREATE TABLE classe_personagem (
    id_classe INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(40) NOT NULL,
    recurso_especial VARCHAR(30) NOT NULL
);

CREATE TABLE personagem (
    id_personagem INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    vida INT NOT NULL,
    nivel INT NOT NULL DEFAULT 1,
    recurso_atual INT NOT NULL,
    id_classe INT NOT NULL,
    CONSTRAINT fk_personagem_classe 
        FOREIGN KEY (id_classe) REFERENCES classe_personagem(id_classe)
);

CREATE TABLE item (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(60) NOT NULL,
    tipo VARCHAR(30) NOT NULL,
    valor INT NOT NULL,
    raridade VARCHAR(20) NOT NULL
);

CREATE TABLE inventario (
    id_personagem INT NOT NULL,
    id_item INT NOT NULL,
    quantidade INT NOT NULL CHECK (quantidade >= 0),
    PRIMARY KEY (id_personagem, id_item),
    CONSTRAINT fk_inv_personagem 
        FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem),
    CONSTRAINT fk_inv_item 
        FOREIGN KEY (id_item) REFERENCES item(id_item)
);

CREATE TABLE partida (
    id_partida INT AUTO_INCREMENT PRIMARY KEY,
    id_personagem INT NOT NULL,
    resultado VARCHAR(20) NOT NULL,
    turnos_jogados INT NOT NULL,
    inimigos_derrotados INT NOT NULL,
    CONSTRAINT fk_partida_personagem 
        FOREIGN KEY (id_personagem) REFERENCES personagem(id_personagem)
);