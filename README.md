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
JSON PUT UPDATE STUDENT ONLY NAME OR LASTNAME OR BOTH
{
    "id":11,
    "name":"nombre",
    "lastname": "apellido",
    "user":{
        "id":18
    }
}

JSON POST /auth/reset-password FOR USER 
{
    "username":"20213tn073@utez.edu.mx",
    "password":"123456789",
    "newPass":"0000000000"
 }
 
 JSON POST BITACORA /bitacora/
 {
    "computer":{
        "id":1
    },
    "user":{
        "id":6
    }
}

JSON POST REPORT -> /report/
{
    "description": "Reporte de prueba",
    "status": 1,
    "bitacora": {
        "id": 1
    },
    "user": {
        "id": 2
    },
    "reasons": [
        {
            "id": 1
        },
        {
            "id": 2
        }
    ]
}




*get students with group specific -> /student/grupo/{id} 

*get students with status -> /student/status/{status} (Value Boolean)  

*get laboratory with building specific -> /laboratory/builging/{id}

*get bitacora depending of the computer -> /bitacora/computer/{id} 

*get bitacora depending of the user -> /bitacora/user/{id}

*get computer depending of the laboratory -> /computer/laboratory/{id}

*Get report depending of the user -> /report/user/{id}

*Get Report depending of the reason -> /report/reason/{id}

*GET REPORT SOLUCIONADO http://localhost:8080/api-gebit/report/finalizados/

*GET REPORT PENDIENTE http://localhost:8080/api-gebit/report/pendientes/

*Get Reason count for graph -> /api-gebit/reason/count/
