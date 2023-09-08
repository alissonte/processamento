# Projeto GFT

Este projeto consiste em duas aplicações Spring Boot (Associado e Boleto) e um banco de dados MySQL. A seguir, está um guia de como configurar e executar este projeto usando Docker.

## Pré-requisitos

- [Docker](https://www.docker.com/get-started)
- [Docker Compose](https://docs.docker.com/compose/install/)

## Configuração e Execução

1. **Clone o repositório**

   Comece clonando o repositório para sua máquina local:

   ```bash
   git clone https://github.com/alissonte/processamento.git

 2. **Navegar até o diretório do projeto**
    ```bash
    cd processamento

 3. **Iniciar os serviços usando Docker Compose**
    - Dentro do diretório principal do projeto, execute o seguinte comando para iniciar os serviços:
    ```bash
    docker-compose up -d
    ```
    > **Nota:** A opção `-d` faz com que os serviços sejam iniciados em segundo plano. Se desejar ver os logs dos serviços, omita esta opção.

 4. **Acessar as aplicações**    
    
    Com os serviços em execução, agora você pode acessar as aplicações:
    - Associado: http://localhost:8080/swagger-ui.html
    - Boleto: http://localhost:8085/swagger-ui.html
![image](https://github.com/alissonte/processamento/assets/585455/f1950781-7ecb-4992-b0d7-0e241aeb14d6)

![image](https://github.com/alissonte/processamento/assets/585455/36a8fa5e-291d-415b-87f2-bc41ddfc2f98)


 5. 📜 **API de Associados**

   - Este serviço oferece uma série de endpoints para gerenciar associados. Abaixo estão as operações disponíveis:
   ---
    
   ## 📌 _Obter um Associado por ID_
    
   - **Endpoint:** `/associados/{associadoId}`
   - **Método HTTP:** `GET`
   - **Descrição:** Retorna os detalhes de um associado específico baseado no seu ID.
    
   ```bash
   curl -X GET http://<dominio>/associados/12345
   ```
   ## 📌 _Atualizar um Associado_
    
   - **Endpoint:** `/associados/{associadoId}`
   - **Método HTTP:** `PUT`
   - **Descrição:** Atualiza os detalhes de um associado existente.
    
   **Parâmetros no corpo da requisição (JSON):**
   - `numeroDocumento`: Nome do documento associado (String).
   - `tipoPessoa`: Tipo de Pessoa(PJ ou PF) (String).
    
   **Exemplo de requisição:**
    
   ```bash
   curl -X PUT -H "Content-Type: application/json" -d '{
       "numeroDocumento": "Numero doc",
       "tipoPessoa": "PJ",
       "nome": "Fulano"
   }' http://[SEU_DOMINIO]/associados/12345
   ```
  ### 📌 Deletar um Associado

  - **Endpoint:** `/associados/{associadoId}`
  - **Método HTTP:** `DELETE`
  - **Descrição:** Remove um associado com base no ID fornecido.

  **Parâmetros no path:**
  - `associadoId`: ID do associado que deseja remover.

  **Exemplo de requisição:**

  ```bash
  curl -X DELETE http://[SEU_DOMINIO]/associados/12345
  ```
  ### 📌 Criar um Novo Associado

  - **Endpoint:** `/associados`
  - **Método HTTP:** `POST`
  - **Descrição:** Adiciona um novo associado ao sistema.

  **Corpo da requisição:**
  
  ```json
  {
    "numeroDocumento": "12345678",
    "tipoPessoa": "TIPO_AQUI",
    "nome": "Nome do Associado"
  }
  ```
  **Exemplo de requisição:**
  ```bash
  curl -X POST http://[SEU_DOMINIO]/associados \
       -H "Content-Type: application/json" \
       -d '{
            "numeroDocumento": "12345678",
            "tipoPessoa": "TIPO_AQUI",
            "nome": "Nome do Associado"
           }'
