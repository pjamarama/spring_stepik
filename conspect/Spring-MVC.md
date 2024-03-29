# Spring MVC
## Знакомство со Spring MVC
MVC, это такой шаблон разработки. Модель отвечает за хранение и передачу данньх, вид отвечает за представление данных (как они выглядят в браузере, веб-страница ), контроллер содержит эндпоинты для внешнего взаимодействия с программой и иногда логику работы программы.  
![](/Users/alexey/IdeaProjects/spring_stepik/conspect/pics/mvc-example.png)
Front controller так же известен как DispatcherServlet, является частью Spring MVC, его не нужно создавать программистиу.

## Конфигурация MVC
Конфигурация состоит из следующих этапов (вероятно, устаревший подход):  
1. Создаем Maven-проект, используя maven-archetype-webapp.
2. Добавляем зависимости в pom-файл.
3. Скачиваем Tomcat и связываем его с средой разработки.
4. Добавляем папки\пакеты в иерархию классовю
5. Конфигурируем web.xml
6. Конфигурируем applicationContext.xml

web.xml нужен для конфигурации диспетчер-сервлета. Диспетчер-сервлет, так называется наш фронт-контроллер.
src/main/webapp/WEB-INF/web.xml, в этой секции указываем где находится файл ApplicationContext, который отвечает за конфигурацию Спринг-приложения:
```xml
<init-param>
<param-name>contextConfigLocation</param-name>
<param-value>/WEB-INF/applicationContext.xml</param-value>
</init-param>
```

В этой секции указываем URL-адрес для диспетчер-сервлета:
```xml
 <servlet-mapping>
    <servlet-name>dispatcher</servlet-name>
    <url-pattern>/</url-pattern>
  </servlet-mapping>
```

### Конфигурация applicationContext.xml
Прописываем бин вью резолвера InternalResourceViewResolver, то, как мы будем работать с нашими представлениями. В нем указываеам префикс и суффикс. Они позовлят обращаться к нашим созданным вью (например, в папке WEB-INF/view) просто по имени: myView вместо blabla/WEB-INF/view/myView.jsp.


# Создаем первое приложение
## Создаем вью
Создаем first-view.jsp в webapp/WEB-INF/view

## Создаем контроллер
Создаем файл MyController, над классом прописываем аннотацию @Controller. Контроллер, это компонент, и он имеет аннотацию @Component внутри себя. Благодаря этой аннотации при сканировании компонентов (пакет, где сканируем, указан в applicationContext.xml) наш контроллер будет найден.

Указываем, при каком адресе какой вью должен отображаться:
```java
@Controller
public class MyController {
    @RequestMapping("/")
    public String showFirstView() {
        return "first-view";
    }
}
```

Запускаем сервер. Если страница не открылась самостоятельно (это указывается в конфигурации томката в идее), переходим по адресу: http://localhost:8080/spring_mvc_stepik_war_exploded/ , где spring-mvc-stepik, это название проекта. Этот адрес можно изменить в идее в редактировании конфигурации, вкладка Deployment, строка Application context.

При изменении вью можно не делать рестарт или редеплой, а выбрать Update resourses, это значительно быстрее рестарта сервера.

## Использование данных во вью
![](/Users/alexey/IdeaProjects/spring_stepik/conspect/pics/data-in-view.png)  
Добавляем пару методов:
```java
    @RequestMapping("/askDetails")
    public String askEmployeeDetails() {
        return "ask-emp-details-view";
    }

    @RequestMapping("/showDetails")
    public String showEmployeeDetails() {
        return "show-emp-details-view";
    }
```  
Создаем необходимые вью с именами ask-emp-details-view и show-emp-details-view. В них делаем формы ввода.  

## Работа с моделью
Добавляем модель в параметры метода, добавляем атрибуты в модель. Второй параметр при добавлении атрибута - Object, то есть, мы можем добавить в модель любой объект.
```java
@RequestMapping("/showDetails")
public String showEmployeeDetails(HttpServletRequest request, Model model) {
    String empName = request.getParameter("employeeName");
    empName = "Mr " + empName;
    model.addAttribute("nameAttribute", empName); // вместо nameAttribute можно использовать любое имя
    model.addAttribute("description"," - software designer");
    return "show-emp-details-view";
}
```

Потом в .jsp вью извлекаем параметр:
```
Your name: ${nameAttribute} ${description}
```  

## Аннотация для чтения параметров @RequestParam
При работе с формами эта аннотация позволяет нам связывать поле формы с параметром метода из контроллера.
Извлечение параметра из HttpServletRequest'а происходит "за кулисами": 
```java
@RequestMapping("/showDetails")
public String showEmployeeDetails(@RequestParam("employeeName") String empName, Model model) {
    empName = "Mr " + empName + "!";
    model.addAttribute("nameAttribute", empName); // вместо nameAttribute можно использовать любое имя
    model.addAttribute("description"," - software designer");
    return "show-emp-details-view";
}
```

## Аннотация @RequestMapping для контроллера
Когда мы поставим эту аннотацию над классом, путь в аннотации будет добавляться в начало пути, который указан над методами класса. Другими словами, @RequestMapping, указанный для контроллера, связывает URL со всеми методами контроллера. Аннотация над контроллером называется controller mapping, аннотация над методом - method mapping.
```java
@Controller
@RequestMapping("/employee")
public class MyController {}
```  

## Формы Spring MVC
**form:form** - основная форма, содержащая в себе другие формы, форма-контейнер.
**form:input** - форма, предназначенная для текста. Используется всего лишь одна строка.  

### Форма Form
Какого-то особого параграфа об этой форме не будет, так уж вот.  
Когда форма попадает в браузер, для каждого поля input срабатывает геттер.
```
<form:form action="showDetails" modelAttribute="employee">
Name <form:input path="name"/>
<input type="submit" value="OK"/>
</form:form>
```
### Форма Input
Когда форма попадает в браузер, для каждого поля input срабатывает геттер. Значение полей на данном этапе - по-умолчанию (null, 0). После нажатия submit срабатывают сеттеры, и каждое поле аттрибута employee получает значение из форм, которые мы туда ввели.

При работе с формами аннотация @ModelAttribute в параметре метода контроллера дает доступ к конкретному аттрибуту модели:
```java
@RequestMapping("/showDetails")
public String showEmployeeDetails(@ModelAttribute("employee") Employee emp) {
    return "show-emp-details-view";
}
```
Во вью обращение к значению идет по имени аттрибута и его полю:
```
Your name: ${employee.name}
Your salary ${employee.salary}
```

### Форма Select
Отвечает за выпадающий список, drop down list.
```
Department <form:select path="department">
<form:option value="Information Technology" label="IT"/>
<form:option value="Human Resourses" label="HR"/>
<form:option value="Sales" label="Sales"/>
</form:select>
```
Чтобы не хардкодить значения департаментов в форму, можно создать в employee еще одно поле с соответсвующими геттерами и сеттерами, создать мапу в конструкторе класса employee и положить туда соответствующие значения:
```java
private Map<String, String> departments;
```
Тогда вместо нескольких опций в форме будет следующий код:
```
Department <form:select path="department">
<form:options items="${employee.departments}"/>
</form:select></form:select>
```

### Форма Radio button
Выбор машины. Можно глянуть в лекции, не стал реализовывать.

### Форма Check box
Выбор языков. Можно глянуть в лекции, не стал реализовывать.

## Валидация форм Spring MVC
Java Standard Bean Validation API - это спецификация, которая описывает правила валидации. 
Hibernate Validator - реализация правил, описанных в Java Standard Bean Validation API.

### Валидация @Size
В модели ставим аннотацию javax.validation.constraints над полем, которое хотим валидировать. Так же существует параметр max.
```java
@Size(min = 2, message = "Name must be at least 2 symbols")
private String name;
```
В контроллере указываем аннотацию @Valid и для получения результата валидации аннотацию @BindingResult. На случай нескольких параметров в методе: параметр BindingResult должен идти сразу после параметра атрибута модели, иначе валидация может не сработать.
```java
public String showEmployeeDetails(@Valid @ModelAttribute("employee") Employee emp, BindingResult bindingResult) {
  return bindingResult.hasErrors() ? "ask-emp-details-view" : "show-emp-details-view";
}
```

В форме указываем имя аттрибута:
```
<form:errors path="name"/>
```

### Валидация @NotEmpty
Эта аннотация требует, чтобы поле было и не null, и не пустой строкой.
```java
@NotEmpty(message = "Surname cannot be empty")
    private String surname;
```
В форме так же указываем имя аттрибута:
```
Surname <form:input path="surname"/>
    <form:errors path="surname"/>
```

### Валидация @NotBlank
Эта аннотация проверяет все то же, что и @NotEmpty, но проверяет, чтобы поле не состояло из пробелов.
Использование аналогично аннотациям выше.

### Валидация чисел, валидация по шаблону
```java
@Min(value = 500, message = "must be greater than 499")
@Max(value = 1000, message = "must be less than 1001")
private int salary;

@Pattern(regexp = "\\d{3}-\\d{2}-\\d{2}", message = "please use pattern XXX-XX-XX")
private String phoneNumber;
```

Так же прописываем формы ошибки с соответствующим именем аттрибута:
```
Salary <form:input path="salary"/>
<form:errors path="salary"/>

Phone number <form:input path="phoneNumber"/>
<form:errors path="phoneNumber"/>
```

### Создание собственной аннотации
Пример: адрес почты должен заканчиваться на xyz.com.  
Создаем аннотацию:
```java
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CheckEmailValidator.class)
public @interface CheckEmail {
    public String value() default "xyz.com";
    public String message() default "email must end with xyz.com";
    public Class<?>[] groups() default {};
    public Class<? extends Payload> [] payload() default {};
}
```
Создаем обработчик аннотации:
```java
public class CheckEmailValidator implements ConstraintValidator<CheckEmail, String> {
    private String endOfEmail;

    @Override
    public void initialize(CheckEmail checkEmail) {
        endOfEmail = checkEmail.value();
    }

    @Override
    public boolean isValid(String enteredValue, ConstraintValidatorContext constraintValidatorContext) {
        return enteredValue.endsWith(endOfEmail);
    }
}
```
Применяем аннотацию. По-умолчанию проверяется домен "xyz.com", но можно его изменить, добавив к аннотации параметры value и message.
```java
@CheckEmail
private String email;
```

В форме указываем форму ошибки аналогично предыдущим примерам.