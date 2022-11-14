# Hibernate
Создаем файл src/main/java/hibernate.cfg.xml с конфигурацией Hibernate.
Подключаем PostgreSQL-коннектор: добавляем зависимость в мавен или качаем джарник и добавляем его к либам.

## Связь между классом и таблицей
Конфигурируется с помощью XML-файла (старый способ) или с помощью Java-аннотаций.

Н. использует понятие entity-класс. Это POJO-класс, в котором используются определенные Н.-аннотации для связи класса с таблицей из БД.

### Создание entity-класса  
Создаем entity-класс, в котором указываем аннотацию @Entity и @Table с указанием имени таблицы. При совпадении имени таблицы и имени класса @Table можно не ставить (но лучше все равно ставить для улучшения читаемости). Аннотации берем из пакета javax.persistence.*. 

Создаем поля, конструктор без аргументов, конструктор с аргументами (для удобства создания объектов), геттеры и сеттеры и переопределим toString().

Все поля помечаются аннотациями **@Column(name="blabla")** с указанием названия столбца. Столбец с PK помечается **@Id**. Если имя столбца совпадает с именем поля, информацию name="bla" можно не писать. Указать **@GeneratedValue(strategy = GenerationType.IDENTITY)**, без нее не записывалось, Н. ругался на primary_key_constraint

```java
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
}
```
Стратегии:
- GenerationType.IDENTITY полагается на автоинкремент столбца по правилам, прописанным в БД.
- GenerationType.SEQUENCE - полагается на работу Sequence, хороша с точки зрения производительности, но поддерживается не всеми БД (можно использовать для БД Oracle).
- GenerationType.TABLE достаточно неэффективная стратегия, полагается на значение столбца таблицы БД. Цель - поддержка уникальности значений, используется отдельная таблица, значение столбца инкементируется вручную.
- GenerationType.AUTO - вариант по-умолчанию, зависит от используемой таблицы.

JPA, это стандартизированная спецификация, которая описывает сохранение Java-объектов в таблицы БД. Hibernate - самая популярная реализация спецификации JPA. Т.е. JPA описывает правила, а Hibernate реализует их. Иными словами, JPA описывает правила, Hibernate реализует их. Таким образом, при использовании JPA-аннотаций (java.persistence.*) мы можем легко поменять реализацию JPA с Hibernate на к-л другую.

### Создание сессии
Сессия, это обертка вокруг подключения к БД с помощью JDBC. Она является основой для работы с БД, с помощью сессии мы совершаем CRUD-операции.  
Чтобы создать сессию, сначала нужно создать sessionFactory. Ее создаем, указывая конфиг Н. Если файл конфигурации называется hibernate.cfg.xml, его имя можно не писать:
```java
    SessionFactory factory = new Configuration()
        .configure("hibernate.cfg.xml")
        .addAnnotatedClass(Employee.class)
        .buildSessionFactory();
```

### Создание записи в БД
- Создаем транзакцию
- Совершаем CRUD-операции
- Закрываем транзакцию
```java
    session.beginTransaction();
    session.save(employee);
    session.getTransaction().commit();
```

Сессия обязательно должна быть закрыта, поэтому код работы с бд нужно обернуть в try-catch.
Настроить бд, раздать права. В консоли делать муторно, просто установил пароль для суперпользователя posgres и подключался им же.
```sql
GRANT CONNECT
ON DATABASE database_name 
TO user_name;

grant all privileges on database postgres to alexey;
grant all privileges on table employees to alexey;
alter user postgres password 'postgres';
```

### Получение записи из БД
```java
session.get(Employee.class, 14);
```

 
