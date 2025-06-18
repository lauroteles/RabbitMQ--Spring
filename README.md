# Order Management Microservice (orderms)

Este é um microserviço de gestão de pedidos desenvolvido com **Java + Spring Boot**, que integra uma **API REST** e um sistema de **mensageria assíncrona com RabbitMQ**, tudo configurado para rodar com **Docker**.

---

## 📌 Principais Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **Spring Data MongoDB**
- **Spring AMQP (RabbitMQ)**
- **Docker**
- **RabbitMQ**
- **MongoDB**
- **REST API**

---

## 📖 Funcionalidades Implementadas

✅ **Recebimento de eventos de criação de pedidos via RabbitMQ**  
✅ **Persistência de pedidos no MongoDB**  
✅ **Consulta paginada de pedidos por cliente via REST API**  
✅ **Conversão de entidades em DTOs de resposta (OrderResponse)**  
✅ **Serialização JSON com Jackson para mensagens na fila**  
✅ **Configuração via Docker para ambiente de mensageria**

---

## 📡 Fluxo de Funcionamento

1. **Produtor de Eventos:**  
   Um sistema externo publica mensagens no RabbitMQ (fila: `order-created`) com informações do pedido.

2. **Consumer (Orderms):**  
   Este serviço consome o evento, processa os dados e salva no banco de dados MongoDB.

3. **API REST:**  
   Exposição de um endpoint REST para listar pedidos de um cliente específico com suporte a paginação.

---

## 📂 Estrutura de Pastas (Principais Pacotes)

src/main/java
└── tech.javaback.raabitMQSpring.orderms
├── config # Configuração do RabbitMQ
├── controller # Endpoints REST
├── controller.dto # DTOs para resposta da API
├── entity # Modelos de dados persistidos no MongoDB
├── repository # Repositório Spring Data
└── service # Lógica de negócio

markdown

---

## 🧪 Exemplo de Endpoint REST

### Listar pedidos de um cliente:

**GET** `/customers/{customerId}/orders`

**Parâmetros:**

- `customerId`: ID do cliente
- `page`: número da página (default: 0)
- `pageSize`: quantidade de itens por página (default: 10)

**Exemplo de chamada:**
Resposta JSON:
```bash

{
  "content": [
    {
      "orderId": 1,
      "customerId": 123,
      "total": 150.75
    }
  ],
  "pagination": {
    "pageNumber": 0,
    "pageSize": 5,
    "totalElements": 20,
    "totalPages": 4
  }
}
```

🐳 Como subir com Docker (RabbitMQ e MongoDB)
Se quiser testar localmente com Docker:

Exemplo de docker-compose.yml (RabbitMQ + MongoDB):

Conversor de mensagem: Jackson2JsonMessageConverter
```bash
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

```
```bash
docker-compose up
```
