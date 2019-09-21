# [Object-Relational-Mapping-with-JPA](https://docs.google.com/document/d/1Vm1sa-aGGsMZQB4EYIk0Zgkegg6kkyhikCgYQCP6GoQ/edit)  

#### Læringsmål:

  *  Kunne demonstrere kode med grundlægende JPQL Queries  
  *  Kunne demonstrere kode med komplekse JPQL Queries  
  *  Kunne vise med egne kode eksempler hvordan man håndterer serialisering af "bidirectional relationships" mellem objekter  

#### General part
 * Explain the rationale behind the topic Object Relational Mapping and the Pros and Cons in using ORM  
_**It reduces the amount of "tedious" mapper code**_  

 * Explain the JPA strategy for handling Object Relational Mapping and important classes/annotations involved.  
_**JPA uses simple entity classes to map/mimic database content.  
It uses the annotation @Entity to declare a class a JPA enity**_  

 * Outline some of the fundamental differences in Database handling using plain JDBC versus JPA  
_**When using JDBC there's a lot of "tedious" code using a lot of variables. Prone to errors.  
With JPA that layer is gone and there's "only" the enitities and some SQL-like queries.**_  


### Practical part

 3) Create a façade and implement as many of the methods below as you have time for, not necessarily in the given order:  
_**The facade TheFacade.java has a main function at the bottom that creates all answers to exercises beneath**_  

  * [Create a Customer](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L205)  
  * [Find a Customer](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L212)  
  * [Get all Customers](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L216)  
  * [Create an ItemType](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L222)  
  * [Find an ItemType](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L229)  
  * [Create an Order and Add it to a Customer](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L233)  
  * [Create an OrderLine for a specific ItemType, and add it to an Order](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L240)  
  * [Find all Orders, for a specific Customer](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L247)  
  * [Find the total price of an order](https://github.com/cph-ms782/review_week38_wednesday_thursday/blob/eb6672acf8370b25b631722d322611178d2b12f5/Object-Relational-Mapping-with-JPA/src/main/java/facades/TheFacade.java#L254)  




