# Java Springboot Backend
**API REST CRUD for Users**

- **Java JDK** `18.0.2` - _Kit de desarrollo para programación en Java._
- **Spring Framework** `2.7.1` - _Framework base para la generación de la API REST._
- **Swagger** `2.1.2` - _Biblioteca para generar documentación de la API REST._
- **JPA** `2.7.1` - _Biblioteca para la manipulación de datos en base de datos._
- **Lombok** `1.18.24` - _Biblioteca para la facilitación de código como getters/setters, equals, constructores, etc._
- **MySQL Connector** `8.0.29` - _Biblioteca para la conectividad con la base de datos._

**Endpoints**

`Swagger` http://localhost:8080/swagger-ui/index.html

## `GET` `{base_url}/api/users`

**Response**
```json
[
  {
    "id": 0,
    "imageURL": "string",
    "name": "string",
    "emails": [
      "string"
    ],
    "gender": "string",
    "status": "string"
  },
  ...
]
```

## `GET` `{base_url}/api/users/{id}`

**Response**
```json
  {
    "id": 0,
    "imageURL": "string",
    "name": "string",
    "emails": [
      "string"
    ],
    "gender": "string",
    "status": "string"
  }
```

## `POST` `{base_url}/api/users`

**Request**
```json
  {
    "image": "blob",
    "name": "string",
    "emails": [
      "string"
    ],
    "gender": "string",
    "status": "string"
  }
```

**Response**
```json
  {
    "id": 0,
    "imageURL": "string",
    "name": "string",
    "emails": [
      "string"
    ],
    "gender": "string",
    "status": "string"
  }
```

## `PUT` `{base_url}/api/users/{id}`

**Request**
```json
  {
    "image": "blob",
    "name": "string",
    "emails": [
      "string"
    ],
    "gender": "string",
    "status": "string"
  }
```

**Response**
```json
  {
    "id": 0,
    "imageURL": "string",
    "name": "string",
    "emails": [
      "string"
    ],
    "gender": "string",
    "status": "string"
  }
```

## `DELETE` `{base_url}/api/users/{id}`

**Response**

```
User deleted successfully!.
```

