Order Management Microservice (orderms)
Este é um microserviço de gestão de pedidos desenvolvido com Java + Spring Boot, que integra uma API REST e um sistema de mensageria assíncrona com RabbitMQ, tudo configurado para rodar com Docker.

📌 Principais Tecnologias Utilizadas
Java 17

Spring Boot

Spring Data MongoDB

Spring AMQP (RabbitMQ)

Docker

RabbitMQ

MongoDB

REST API

📖 Funcionalidades Implementadas
✅ Recebimento de eventos de criação de pedidos via RabbitMQ
✅ Persistência de pedidos no MongoDB
✅ Consulta paginada de pedidos por cliente via REST API
✅ Conversão de entidades em DTOs de resposta (OrderResponse)
✅ Serialização JSON com Jackson para mensagens na fila
✅ Configuração via Docker para ambiente de mensageria

📡 Fluxo de Funcionamento
Produtor de Eventos:
Um sistema externo publica mensagens no RabbitMQ (fila: order-created) com informações do pedido.

Consumer (Orderms):
Este serviço consome o evento, processa os dados e salva no banco de dados MongoDB.

API REST:
Exposição de um endpoint REST para listar pedidos de um cliente específico com suporte a paginação.

📂 Estrutura de Pastas (Principais Pacotes)
bash
Copiar
Editar
src/main/java
└── tech.javaback.raabitMQSpring.orderms
    ├── config              # Configuração do RabbitMQ
    ├── controller          # Endpoints REST
    ├── controller.dto      # DTOs para resposta da API
    ├── entity              # Modelos de dados persistidos no MongoDB
    ├── repository          # Repositório Spring Data
    └── service             # Lógica de negócio
🧪 Exemplo de Endpoint REST
Listar pedidos de um cliente:
GET /customers/{customerId}/orders

Parâmetros:

customerId: ID do cliente

page: número da página (default: 0)

pageSize: quantidade de itens por página (default: 10)

Exemplo de chamada:

bash
Copiar
Editar
GET http://localhost:8080/customers/123/orders?page=0&pageSize=5
Resposta JSON:

json
Copiar
Editar
{
  "content": [
    {
      "orderId": 1,
      "customerId": 123,
      "total": 150.75
    },
    ...
  ],
  "pagination": {
    "pageNumber": 0,
    "pageSize": 5,
    "totalElements": 20,
    "totalPages": 4
  }
}
🐇 Configuração RabbitMQ (fila utilizada)
Fila: order-created

Formato da mensagem: JSON

Conversor de mensagem: Jackson2JsonMessageConverter

🐳 Como subir com Docker (RabbitMQ e MongoDB)
Se quiser testar localmente com Docker:

Exemplo de docker-compose.yml (RabbitMQ + MongoDB):
yaml
Copiar
Editar
version: '3.8'

services:
  rabbitmq:
    image: rabbitmq:3-management
    ports:
      - "5672:5672"
      - "15672:15672"

  mongo:
    image: mongo
    ports:
      - "27017:27017"
Depois:

bash
Copiar
Editar
docker-compose up
🎯 Aprendizados pessoais com o projeto
Integração de Spring Boot com RabbitMQ (mensageria assíncrona)

Criação de APIs REST com Spring

Trabalhar com MongoDB como banco de dados NoSQL

Uso de DTOs para respostas paginadas

Deploy local usando Docker
