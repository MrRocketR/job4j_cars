
<h1>job4j_cars</h1>

Проект представляет собой простой сервис объявлений о продаже машин,
пользователь может составить в свободной форме пост с название машины, описанием объявления, ценой и фотографией.

Основной стек технологий:
- Spring boot 2.5.2
- Thymeleaf
- Bootstrap
- PostgreSQL 42.2.9
- Hibernate 5.6.11
- lombok 1.18.22
- checkstyle-plugin 3.1.2
- puppycrawl 9.0
- Liquibase 3.6.2

Необходимая среда:
- Java 17
- Maven 3.8
- PostgreSQL 14

Для запуска проекта необходимо:

1. Создать БД данных cars
```ql
    create database cars;
```

2. Соберите проект и запустите приложение Spring Boot
```
    mvn clean install
    mvn spring-boot:run
```

### Страница приветствия, регистрации и авторизации:
![](imgs/index.png)
![](imgs/login.png)
![](imgs/registration.png)

### Страница с объявлениями:
![](imgs/posts.png)
![](imgs/posts2.png)

### Страница с моими объявлениями и редактирования:
![](imgs/myposts.png)
![](imgs/update.png)




Для генерации изображений в примерах использовал нейросеть Kandinsky 2.1

Если у вас есть вопросы по этому проекту, пожалуйста, напишите мне по адресу kuptsovns@gmail.com

