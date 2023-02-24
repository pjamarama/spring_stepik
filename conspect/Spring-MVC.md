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