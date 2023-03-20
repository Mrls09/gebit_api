# gebit_servicio
Proyecto integradora, Servicio springboot

Si es la primera ver que se corre el servicio, debe de descomentar el archivo CreateRoles.java dentro de Utils !!


JSON POST USER-STUDENT 
{
    "name":"pepe",
    "lastname":"gonzalez",
    "status":true,
    "group":{
        "id":1
    },
    "user":{
        "username":"pepegonzalez@utez.edu.mx",
        "password":"123456789"
    }
}

JSON POST /reset-password FOR USER 
{
    "username":"20213tn073@utez.edu.mx",
    "password":"123456789",
    "newPass":"0000000000"
 }
