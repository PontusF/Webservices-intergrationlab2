Instruktioner

En user-interface klient saknas då vi tyvärr inte fick ordning på implementation av CORS-filter och tiden räckte inte till för att lösa problemet. Våran javaScript-klient vägrade tillåta oss att lägga till/hämta headern Authorization. Dessa services går endast att anropas via Insomnia eller postman.    

Alla services registrerar sig hos Eureka server och routing och lastbalansering sker via Zuul-Gateway. För att påvisa lastabalanseringen skapas två instanser av Auth-service.

För att demonstrera att klienter kan kommunicera sinsemellan finns funktionalitet för det i servicen “Order”.


 
DOCKER
För att köra samtliga Docker Images följ dessa steg:

1.Ladda nerfil docker-compose.yml
2.Öppna kommandotolken, ställ dig i mappen där yml-fil sparades.
3.Kör kommandot “docker-compose up --scale auth=2”

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
responsens Header. Det finns två användare hårdkodat in i Auth-klassen.

Username Pontus står som admin.

{ "username": "Pontus", "password": "discot stänger 05:00" }
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
Skapa ny Warehouse och spara ner den i databas. Observera att Bearer-tokken måsten klistras in.

___________________________________________________________________________________

url: http://localhost:8762/joelapp/warehouse
Method: GET
Authorization:$token


Beskrivning:
Hämta alla Warehouse i servicens databas. Observera att Bearer-tokken måsten klistras in.



Product
url: http://localhost:8762/product/new
Method: POST
Content-Type: application/json
Authorization:$token


Body {
"name":"<name>"
}

Beskrivning:
Skapa ny product och spara ner den i servicens databas. Observera att Bearer-tokken måsten klistras in.



___________________________________________________________________________________


url: http://localhost:8762/product
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
Hämta alla Product i servicens databas. Observera att Bearer-tokken måsten klistras

___________________________________________________________________________________


url: http://localhost:8762/product/{id}
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
Hämtar önskad produkt som specificeras i url. . Observera att Bearer-tokken måsten klistras 
 


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
Skapa ny Order och spara ner den i servicens databas. Servicen gör ett anrop till warehouse samt order för att kontrollera att warehouseid,orderid samt productid överensstämmer med id som finns i respektiva service. Om alla id är korrekt spara order ner i databas och responsen meddelar att order har skapats.



___________________________________________________________________________________


url: http://localhost:8762/order/all
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
Hämta alla Order i servicens databas. Observera att Bearer-tokken måsten klistras 
___________________________________________________________________________________


url: http://localhost:8762/order/{id}/details
Method: GET
Content-Type: application/json
Authorization:$token


Beskrivning:
Hämtar specifik order, samt name och adress fälten för orderns Product och Warehouse  Orderns id anropas genom att skriva in önskat id i url.