CREATE TABLE tb_perfil (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(255) NOT NULL);

INSERT INTO tb_perfil (nome)
VALUES
   ('Admin'),
   ('Usuario');