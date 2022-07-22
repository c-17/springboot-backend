# Java Springboot Backend
API REST CRUD for Users

**Endpoints**

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

