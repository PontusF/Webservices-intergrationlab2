Instruktioner

En user-interface klient saknas d� vi tyv�rr inte fick ordning p� implementation av CORS-filter och tiden r�ckte inte till f�r att l�sa problemet. V�ran javaScript-klient v�grade till�ta oss att l�gga till/h�mta headern Authorization. Dessa services g�r endast att anropas via Insomnia eller postman.    

Alla services registrerar sig hos Eureka server och routing och lastbalansering sker via Zuul-Gateway. F�r att p�visa lastabalanseringen skapas tv� instanser av Auth-service.

F�r att demonstrera att klienter kan kommunicera sinsemellan finns funktionalitet f�r det i servicen �Order�.


 
DOCKER
F�r att k�ra samtliga Docker Images f�lj dessa steg:

1.Ladda nerfil docker-compose.yml
2.�ppna kommandotolken, st�ll dig i mappen d�r yml-fil sparades.
3.K�r kommandot �docker-compose up --scale auth=2�

Services
Auth-Service
url: http://localhost:8762/auth
Method: POST
Content-Type: application/json
Body {
"username":"<username>",
"password":"<password>"
}

Beskrivning:
Skickar en post-request till Authservice, en Bearer tokken skickas tillbaka i 
responsens Header. Det finns tv� anv�ndare h�rdkodat in i Auth-klassen.

Username Pontus st�r som admin.

{ "username": "Pontus", "password": "discot st�nger 05:00" }
{ "username":"Joel", "password":"password" }


Warehouse
url: http://localhost:8762/joelapp/warehouse
Method: POST
Content-Type: application/json
Authorization:$token
Body {
"name":"",
"adress":""
}

Beskrivning:
Skapa ny Warehouse och spara ner den i databas. Observera att Bearer-tokken m�sten klistras in.

___________________________________________________________________________________

url: http://localhost:8762/joelapp/warehouse
Method: GET
Authorization:$token


Beskrivning:
H�mta alla Warehouse i servicens databas. Observera att Bearer-tokken m�sten klistras in.



Product
url: http://localhost:8762/product/new
Method: POST
Content-Type: application/json
Authorization:$token


Body {
"name":"<name>"
}

Beskrivning:
Skapa ny product och spara ner den i servicens databas. Observera att Bearer-tokken m�sten klistras in.



___________________________________________________________________________________


url: http://localhost:8762/product
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
H�mta alla Product i servicens databas. Observera att Bearer-tokken m�sten klistras

___________________________________________________________________________________


url: http://localhost:8762/product/{id}
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
H�mtar �nskad produkt som specificeras i url. . Observera att Bearer-tokken m�sten klistras 
 


Orders
url: http://localhost:8762/order/new
Method: POST
Content-Type: application/json
Authorization:$token


Body {
"productId":"<id>",
"warehouseId":"<id>"
}

Beskrivning:
Skapa ny Order och spara ner den i servicens databas. Servicen g�r ett anrop till warehouse samt order f�r att kontrollera att warehouseid,orderid samt productid �verensst�mmer med id som finns i respektiva service. Om alla id �r korrekt spara order ner i databas och responsen meddelar att order har skapats.



___________________________________________________________________________________


url: http://localhost:8762/order/all
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
H�mta alla Order i servicens databas. Observera att Bearer-tokken m�sten klistras 
___________________________________________________________________________________


url: http://localhost:8762/order/{id}/details
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
H�mtar specifik order, samt name och adress f�lten f�r orderns Product och Warehouse  Orderns id anropas genom att skriva in �nskat id i url.