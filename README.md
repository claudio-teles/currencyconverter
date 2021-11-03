# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.6/maven-plugin/reference/html/#build-image)
* [Spring Data Reactive MongoDB](https://docs.spring.io/spring-boot/docs/2.5.6/reference/htmlsingle/#boot-features-mongodb)

<br/>

I chose to use Spring Webflux for these reasons according to the site:
<a href="https://hypeflame.blog/2021/06/18/o-que-e-e-quando-usar-spring-webflux/#:~:text=O%20Spring%20Webflux%20apresenta%20diversas,utiliza%C3%A7%C3%A3o%20de%20mem%C3%B3ria%20e%20backprassure." target="">Advantages vs. Disadvantages of Spring Webflux</a>

Which briefly, according to the website "Spring Webflux has several advantages such as resource optimization, low memory usage and backprassure. But he's not a silver bullet. In scenarios where the service works with reading/writing files to disk or will use non-reactive drivers, the best way is to use a non-reactive service."

Organized in the following layers: <br/>
-> cofiguration <br/>
-> controller <br/>
-> dao <br/>
-> document <br/>
-> dto <br/>
-> service <br/>
-> util <br/>

![camadas](./img/camadas.png)

<br/>

To test with Junit just follow the steps shown in the image below:

![junit](./img/teste-junit.png)

To run the project locally use the maven command in the project's rais directory: mvn spring-boot:run
Open the link in the browser: <a href="http://localhost:8080/swagger-ui/index.html" target="_blank">localhost</a>

The API can also be tested from the project link on Heroku:
<a href="https://newcurrencyconverter.herokuapp.com/swagger-ui/index.html" target="_blank">heroku</a>

To test the API in Swagger just follow the illustrative images.

<br/>

![img-01](./img/01.png)

<br/>

![img-02](./img/02.png)

<br/>

![img-03](./img/03.png)

<br/>

![img-04](./img/04.png)

<br/>

![img-05](./img/05.png)

<br/>

![img-06](./img/06.png)

<br/>

![img-07](./img/07.png)

<br/>

![img-08](./img/08.png)

<br/>

![img-09](./img/09.png)

<br/>
![img-10](./img/10.png)

<br/>

![img-11](./img/11.png)

<br/>
![img-12](./img/12.png)

<br/>

![img-13](./img/13.png)

<br/>

![img-14](./img/14.png)

<br/>

![img-15](./img/15.png)

<br/>

![img-16](./img/16.png)

<br/>

![img-17](./img/17.png)
