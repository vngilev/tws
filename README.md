**Техническое задание**
Необходимо написать простой веб-сервис на Java для совершения переводов с карты на карту
На вход поступает XML, на выходе результат выполнения сервиса также в формате XML
Сервис должен поддерживать такие операции:
- оформление перевода
- список отправленных переводов
- список полученных переводов

Дополнительно по желанию:
UI для отображения запросов/ответов при работе с веб-сервисом
Результатом работы должно быть приложение и описание работы приложения



**Описание приложения:**

Сервис простой, авторизации не предусматривает.
Сохранение информаци

Используемые технологии: Spring-MVC + Jersey RESTful API  + JAXB

Приложение развернуто по вдресу http://ec2-34-245-46-182.eu-west-1.compute.amazonaws.com/tws
Windows Server 2012 R2, Apache Tomcat/9.0.12

Исходный код доступен в репозитории https://github.com/vngilev/tws

В роли клиентской части использовалось дополнение RESTClient к браузеру Mozilla FireFox


**Поддерживаемые веб-сервисом операции:**


_**1. Оформление перевода**_

_Пример запроса к сервису с использованием **curl**_

`curl -X POST -H 'Content-Type: application/xml' -H 'Accept: application/xml' -i 'http://ec2-34-245-46-182.eu-west-1.compute.amazonaws.com/tws/transfer' --data '<Transfer>
     <from>card1</from>
     <to>card2</to>
     <amount>5000.00</amount>
 </Transfer>`
 
_HTTP - ответ_

`Status Code: 200
Content-Type: application/xml;charset=UTF-8
Date: Thu, 15 Nov 2018 06:57:04 GMT
Transfer-Encoding: chunked
`

`<Transfer>
   <id>1792423348</id>
   <from>{cardSender}</from>
   <to>{cardReceiver}</to>
   <amount>5000.00</amount>
   <approved>true</approved>
   <date>1542265024833</date>
</Transfer>`

Допущения, сделанное в отсутствии связи с бизнес-аналитиком:

В случае, если на карте отправителя недостаточно средств или карта, операция сохраняется со статусом approved = false, 
при этом остатки по картам не изменяются.
В случае, если на карте отправителя достаточно средств, лимит карты отправителя уменьшается, лимит карты получателя 
увеличивается на сумму операции, операция сохраняется в реестре со статусом = true

Если в реквизитах перевода карта

В любом случае все операции сохраняются в 


**_2. Список отправленных переводов_**

Допущения, сделанное в отсутствии связи с бизнес-аналитиком:
Список отправленных переводов - это список переводов, отправленных с определенной карты, со статусом операции. 

_Пример запроса к сервису с использованием **curl**_

`curl -X GET -H 'Content-Type: application/xml' -H 'Accept: application/xml' -i 'http://ec2-34-245-46-182.eu-west-1.compute.amazonaws.com/tws/transfers/card1/from'`
 
_HTTP - ответ_

`Status Code: 200
Content-Type: application/xml;charset=UTF-8
Date: Thu, 15 Nov 2018 10:40:20 GMT
Transfer-Encoding: chunked
`

`<List>
   <item>
     <id>1714533991</id>
     <from>card1</from>
     <to>card2</to>
     <amount>5000.00</amount>
     <approved>true</approved>
     <date>Thu Nov 15 10:36:15 UTC 2018</date>
   </item>
   <item>
     <id>215871813</id>
     <from>card1</from>
     <to>card2</to>
     <amount>5000.00</amount>
     <approved>true</approved>
     <date>Thu Nov 15 10:36:04 UTC 2018</date>
   </item>
 </List>`

**_3. Список полученных переводов_**

Допущения, сделанное в отсутствии связи с бизнес-аналитиком:
Список полученных переводов - это список переводов на определенную карту, со статусом операции 

_Пример запроса к сервису с использованием **curl**_

`curl -X GET -H 'Content-Type: application/xml' -H 'Accept: application/xml' -i 'http://ec2-34-245-46-182.eu-west-1.compute.amazonaws.com/tws/transfers/card2/to'`
 
_HTTP - ответ_

`Status Code: 200
Content-Type: application/xml;charset=UTF-8
Date: Thu, 15 Nov 2018 11:02:06 GMT
Transfer-Encoding: chunked
`

`<List>
   <item>
     <id>1134744641</id>
     <from>card3</from>
     <to>card2</to>
     <amount>5000.00</amount>
     <approved>true</approved>
     <date>Thu Nov 15 10:36:24 UTC 2018</date>
   </item>
   <item>
     <id>1714533991</id>
     <from>card1</from>
     <to>card2</to>
     <amount>5000.00</amount>
     <approved>true</approved>
     <date>Thu Nov 15 10:36:15 UTC 2018</date>
   </item>
   <item>
     <id>215871813</id>
     <from>card1</from>
     <to>card2</to>
     <amount>5000.00</amount>
     <approved>false</approved>
     <date>Thu Nov 15 10:36:04 UTC 2018</date>
   </item>
 </List>`

**_4. Остатки по картам_**

Список остатков по картам можно получить запросом:

`curl -X GET -H 'Content-Type: application/xml' -H 'Accept: application/xml' -i 'http://ec2-34-245-46-182.eu-west-1.compute.amazonaws.com/tws/cards'`

_HTTP - ответ_
`<Map><card1>20000.00</card1><card2>20000.00</card2><card3>25000.00</card3><card10>0.00</card10></Map>`



TODO:
- Написать тесты
- Реализовать авторизацию, сохранение объектов в БД (есть AWS.S3.MySQL в той же подсети что и сервер приложений)
- Реализовать клиентское приложение (авторизацию, основные операции)
- Реализовать серверную часть в соответствии со стандартом WSDL (как вариант Apache CXF + JBoss FUSE)
- ...