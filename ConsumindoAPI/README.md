<h2>Desafio GFT - API Rest</h2>

<h5>Breve descrição:</h5>
O projeto tem como objetivo principal facilitar a busca personalizada sobre notícias do dia, de acordo com etiquetas (assunto) que podem ser cadastradas <br>
por usuário.

<h5>Tecnologias utilizadas:</h5>
- Java 17; <br>
- Maven; <br>
- Spring boot 2.7.4; <br>
- Spring Web; <br>
- Spring DevTools; <br>
- Spring Data JPA; <br>
- Spring Security; <br>
- Spring Validation; <br>
- MySQL; <br>

<h5>Funcionalidades:</h5>
- Cadastro de usuário informando se possui perfil de administrador ou não, realizado somente por perfil administrador;
- Cadastro de etiqueta por usuário, realizado somente pelo próprio usuário sem administrador;
- Busca de listagem de notícias ao informar etiqueta relacionada ao assunto que deseja visualizar, realizada somente por usuários sem administrador;
- Histórico de parâmetros (etiqueta e data) acessados no dia atual, que pode ser visualizado pelo próprio usuário que fez os acessos ou um perfil administrador que visualiza <br>
os acessos de todos os usuários;
- Histórico de etiquetas mais acessadas independente da data, que pode ser visualizado somente por perfil administrador;
- Banco de dados é populado no ato de criação das tabelas.

<h5>Exceed:</h5>
- JWT para gerar token;
- Swagger para documentar os endpoints da API;
- Projeto no Postman para documentar os endpoints da API;
- Envio de e-mail com confirmação de sucesso no ato de cadastro de um usuário;
- Envio de e-mail contendo todas as notícias da data atual, de acordo com as etiquetas dos usuários, para cada usuário, realizado por um perfil administrador;
- Busca de listagem de notícias ao informar data da notícia e etiqueta relacionada ao assunto que deseja visualizar, realizada somente por usuários sem administrador;
- Exceptions com retorno de status http personalizados de acordo com o erro, dentro da classe customizada handler;
- Uso de DTOs para facilitar a transferência de dados de uma camada para outra;
- Spring Security para permitir retringir acessos.