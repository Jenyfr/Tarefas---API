# API de Gerenciamento de Tarefas Pessoais

Projeto autoral desenvolvido em Spring Boot para praticar a configuração de uma API RESTful e a implementação dos métodos HTTP `GET`, `POST`, `PUT` e `DELETE`.

## Tecnologias usadas

- Java 17
- Spring Boot 3
- Spring Web
- Bean Validation
- Maven

## Como executar

No terminal, dentro da pasta do projeto:

```bash
mvn spring-boot:run
```

A API ficará disponível em:

```text
http://localhost:8080
```

## Endpoints da API

### Listar todas as tarefas

```http
GET /api/tarefas
```

Resposta de sucesso:

```http
200 OK
```

Exemplo de resposta:

```json
[
  {
    "id": 1,
    "titulo": "Estudar Spring Boot",
    "descricao": "Praticar métodos HTTP",
    "concluida": false
  }
]
```

### Buscar uma tarefa por ID

```http
GET /api/tarefas/{id}
```

Respostas possíveis:

- `200 OK`: tarefa encontrada.
- `404 Not Found`: tarefa não encontrada.

### Criar uma nova tarefa

```http
POST /api/tarefas
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Praticar métodos HTTP",
  "concluida": false
}
```

Respostas possíveis:

- `201 Created`: tarefa criada com sucesso.
- `400 Bad Request`: dados inválidos.

### Atualizar uma tarefa existente

```http
PUT /api/tarefas/{id}
Content-Type: application/json
```

Corpo da requisição:

```json
{
  "titulo": "Estudar API REST",
  "descricao": "Testar PUT no Postman ou Insomnia",
  "concluida": true
}
```

Respostas possíveis:

- `200 OK`: tarefa atualizada com sucesso.
- `400 Bad Request`: dados inválidos.
- `404 Not Found`: tarefa não encontrada.

### Excluir uma tarefa

```http
DELETE /api/tarefas/{id}
```

Respostas possíveis:

- `204 No Content`: tarefa excluída com sucesso.
- `404 Not Found`: tarefa não encontrada.

## Regras de validação

- `titulo` é obrigatório.
- `titulo` deve ter no máximo 100 caracteres.
- `descricao` deve ter no máximo 255 caracteres.
- `concluida` é opcional; caso não seja enviada, será considerada `false`.

## Exemplos para testar no Postman ou Insomnia

1. Criar uma tarefa usando `POST /api/tarefas`.
2. Listar todas usando `GET /api/tarefas`.
3. Atualizar a tarefa criada usando `PUT /api/tarefas/1`.
4. Excluir a tarefa usando `DELETE /api/tarefas/1`.
5. Tentar buscar a mesma tarefa usando `GET /api/tarefas/1` para verificar o erro `404`.

O arquivo `requests.http` tambem possui requisicoes prontas para testar a API em ferramentas como VS Code, IntelliJ, Postman ou Insomnia.

## Como o projeto atende ao exercício

- Configura um projeto Spring Boot com dependências para API REST.
- Implementa os métodos HTTP exigidos: `GET`, `POST`, `PUT` e `DELETE`.
- Trata casos de sucesso e falha com códigos HTTP adequados.
- Inclui documentação simples dos endpoints, parâmetros e respostas.
