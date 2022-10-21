# README.md

# Telros Test

## Краткое описание

REST-сервис на Java.
### Описание задания
Тестовое задание на позицию Java Backend разработчика.

Описание задачи:
Необходимо реализовать REST-API средствами языка программирования Java и фреймворка Spring.
Выбор СУБД для хранения данных остаётся за Вами.
Пункты помеченные звёздочкой "*", являются дополнительными.

Обязательные условия:
1*) Реализовать возможность авторизации по логину и паролю (admin:admin). Тип авторизации разрешается выбрать с обоснованием: Base Auth, OAuth, JWT;
2 ) Реализовать CRUD модель для работы с пользователями (контактная информация);
3 ) Реализовать CRUD модель для работы с детальной информацией о пользователе;
4*) Реализовать CRUD модель для возможности работы с фотографией пользователя;

Хранимая информации о "пользователе":
1 ) Фамилия
2 ) Имя
3 ) Отчество
4 ) Дата рождения
5 ) Электронная почта
6 ) Номер телефона
7*) Фотография

Будет плюсом, если:
1 ) Выбранная Вами СУБД - PostgreSQL;
2 ) Проект будет покрыт автотестами;
3 ) В проекте будут присутствовать комментарии;
4 ) Для работы с базой данных будет использован JDBC / Hibernate;
5*) Хотя бы одно из дополнительных заданий в разделе обязательных будет выполнено;

Ожидаемые данные:
1 ) Исходный код готового проекта;
2 ) Экспортированный JSON из Postman для тестирования REST-API;

Сроки выполнения задания:
На выполнение задания предоставляется 3 дня.

## Используемые фреймворки и библиотеки:

- Spring Boot (Hibernate, Spring Security, Spring Data JPA)
- Lombok
- Swagger-UI
- HSQLDB

## Сборка проекта

```
$ cd telros-test
$ mvn package
```

## Swagger документация

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Логин и пароль

Согласно пункту 1* тестового задания в приложении реализована базовая авторизация по логину `admin` и паролю `admin`. Этот тип авторизации был выбран…

# Краткое описание классов

В пакете `io/github/anton_petrunov/telros_test` проекта находятся классы:

- `TelrosTestApplication` — содержит в себе точку входа в приложение
- `SpringFoxConfig` — служит для конфигурирования документации `Swagger-UII`

В пакете `error` содержатся классы:

- `AppException`
- `IllegalRequestDataException`
- `NotFoundException`

В пакете`model` содержатся Entity-классы:

- `BaseEntity` — класс-родитель для всех классов-моделей (содержит `ID` и метод `isNew`)
- `Contact` — класс-модель и Entity данных, хранящихся в `ContactRepository`
- `Photo` — класс-модель и Entity данных, хранящихся в `PhotoRepository`
- `Type` — Enum-класс, который должен служить для хранения типов контактов
- `User` — класс-модель и Entity данных, хранящихся `UserRepository`

В пакете`repository` содержатся интерфейсы хранилищ данных, логика которых имплементируется автоматически с помощью библиотеки `Spring Data JPA`

- `ContactRepository` — интерфейс взаимодействия с БД `contacts`
- `PhotoRepository` — интерфейс взаимодействия с БД `photos`
- `UserRepository` — интерфейс взаимодействия с БД `users`

В пакете `util` содержится класс:

- `ValidationUtil` — служит для валидации данных, приходящих в контроллеры

В пакете`web` содержатся классы:

- `ContactRestController` — контроллер, который принимает команды на CRUD операции с контактами пользователя `ID = userId`
    - `/users/{userId}/contacts` — создать новый контакт пользователя и показать все контакты пользователя
    - `/users/{userId}/contacts/{id}`
- `PhotoRestController` — контроллер, который принимает команды на CRUD операции с фотографиями пользователя `ID = userId`
    - `/users/{userId}/photos`
    - `/users/{userId}/photos/{id}`
- `UserRestController` — контроллер, который принимает команды на CRUD операции с пользователями
    - `/users`
    - `/users/{id}`

# Описание API

## CRUD для работы с пользователем

Для просмотра всех пользователей необходимо послать GET-запрос на адрес `/users`

В ответе будет содержаться массив `JSON`-объектов со следующими полями:

- `id` — ID пользователя
- `lastName` — фамилия пользователя
- `firstName` — имя
- `patronymic` — отчество
- `birthday` — дата рождения

Пример запроса:

```
http://localhost:8080/users
```

Пример ответа:

```json
[
  {
    "id": 0,
    "lastName": "petrunov",
    "firstName": "anton",
    "patronymic": "nikolaevich",
    "birthday": "16 ноября 1984",
    "contacts": null,
    "photos": null
  },
  {
    "id": 1,
    "lastName": "Sochnev",
    "firstName": "Ignat",
    "patronymic": "Ivanovich",
    "birthday": "29 октября 2000",
    "contacts": null,
    "photos": null
  },
  {
    "id": 2,
    "lastName": "Ivanov",
    "firstName": "Denis",
    "patronymic": null,
    "birthday": "31 декабря 1999",
    "contacts": null,
    "photos": null
  }
]
```

Для создания нового нового пользователя необходимо послать POST-запрос на адрес `/users`

Пример запроса:

```json
{
  "birthday": "2022-10-21",
  "firstName": "Kirill",
  "lastName": "Kirillov",
  "patronymic": "Kirillovich"
}
```

Пример ответа:

```json
{
  "id": 3,
  "lastName": "Kirillov",
  "firstName": "Kirill",
  "patronymic": "Kirillovich",
  "birthday": "2022-10-21T00:00:00.000+00:00",
  "contacts": null,
  "photos": null
}
```

Для создания нового нового пользователя необходимо послать POST-запрос на адрес `/users/{id}`

Пример запроса:

```json
{
  "birthday": "2022-10-21",
  "firstName": "Kirill",
  "lastName": "Kirillov",
  "patronymic": "Kirillovich"
}
```

Пример ответа:

```json

```

```
http://localhost:8080/users/1
```

```
{"id":1,"lastName":"Sochnev","firstName":"Ignat","patronymic":"Ivanovich","birthday":"2000-10-29T21:00:00.000+00:00","contacts":null,"photos":null}
```

{
"birthday": "2022-10-21",
"firstName": "UPD",
"lastName": "Kirillov",
"patronymic": "Kirillovich"
}

## CRUD для работы с контактной информацией пользователя

```json
{
  "type": "PHONE",
  "value": "111"
}
```

```json
{
  "id": 4,
  "type": "PHONE",
  "value": "111"
}
```

## CRUD для работы с фотографиями пользователя
