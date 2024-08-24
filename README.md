Para testar a api deixei as urls e json.


adicionar processo com cliente 

http://localhost:8080/processos


{
    
    "numero": "1234567",
    "reus": [
        {
            
            "nome": "Sabrina Souza",
            "cpf": "98765449100"
        }
       
    ]
}




adicionar reu no processo existente 

http://localhost:8080/reus/1234567/reus


[

    {
        "nome": "Marina Oliveira",
        "cpf": "98765563000"
    },
    {
        "nome": "João oliveira",
        "cpf": "12345678500"
    }

    
]




buscar processo pelo numero dele 

http://localhost:8080/processos/1234567



deletar processo pelo numero dele

http://localhost:8080/processos/1234567


