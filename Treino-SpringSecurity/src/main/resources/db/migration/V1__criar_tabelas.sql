CREATE TABLE tb_permission (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
descricao VARCHAR(255) NOT NULL);

CREATE TABLE tb_users (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
email VARCHAR(255) NOT NULL,
nome VARCHAR(255) NOT NULL,
senha VARCHAR(255) NOT NULL,
contaNaoExpirada VARCHAR(255) NOT NULL,
contaNaoBloqueada VARCHAR(255) NOT NULL,
credenciaisNaoExpiradas VARCHAR(255) NOT NULL,
enabled VARCHAR(255) NOT NULL,
permission_id BIGINT NOT NULL,
FOREIGN KEY (permission_id) REFERENCES tb_permission(id));