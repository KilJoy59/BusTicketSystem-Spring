# README #

Cистема покупки билетов на автобус, которая содержит:

1) Entity:
	Водитель
		имя
		фамилия
		номер водительского удостоверения

	Автобус
		модель
		номер
		водитель

	Билет
		номер места
		стоимость
		оплачен или нет

	Рейс
		номер
		автобус
		билеты - Set

2) Соответствующие классы Repository и Service

3) ReSTful API, который позволит:
	добавить нового водителя
	добавить автобус
	добавить рейс
	добавить билеты на рейс
	изменить автобус на рейсе
	продать билет. При этом статус билета должен меняться на "оплачен" и должен возвращать информацию: 
номер рейса, номер и модель автобуса, имя и фамилию водителя, стоимость билета и номер купленного места в автобусе.


Для работы приложения и прохождения тестов необходимо создать:
DB = busStation, busStationTest

username = postgres
password = postgres

ReSTFulAPI:

/busStation/addDriver - добавить нового водителя
/busStation/addBu - добавить автобус
/busStation/addVoyage - добавить рейс
/busStation/bus/{id}/driver/{driverId} - добавить водителя на автобус
/busStation/voyage/{id}/bus/{busId} - добавить/изменить автобус на рейс
/busStation/voyage/{id}/addTicket - добавить один билет на рейс
/busStation/voyage/{id}/addTickets - добавить билеты на рейс
/busStation/voyage/{id}/ticket/{ticketId} - продать билет

/busStation/driver/{id} - найти водителя по id
/busStation/bus/{id} - найти автобус по id
/busStation/ticket/{id} - найти билет по id
/busStation/voyage/{id} - найти рейс по id

/busStation/findAllDrivers - возвращает список водителей в БД
/busStation/findAllBuses - возвращает список автобусов в БД
/busStation/findAllTickets - возвращает список билетов в БД
/busStation/findAllVoyages - возвращает список рейсов в БД
