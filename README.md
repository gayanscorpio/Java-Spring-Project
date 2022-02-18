# Java-Spring-Project

Gayan kumara <ukumag22@gmail.com>
	
Below are mentioned the JSON payload request with appropriate response with samples.
POST: create an account
http://localhost:8082/accounts

{
    "bsb": "123456789",
    "identification" : "111222444",
    "openingDate" : "2020-05-20T05:40:08.721Z",
    "balanceDate" : "",
    "balance" : 120.35

}

PUT: update the current accounts with given date- month , this will list all the avaiable accounts with respect of balance date.
http://localhost:8082/accounts/20-Feb-2022


[
    {
        "accountid": 0,
        "bsb": "123456789",
        "identification": "111222333",
        "openingDate": "2020-05-20T11:10:08.721+05:30",
        "balanceDate": "2022-02-19T18:30:00Z",
        "balance": 1164.35
    },
    {
        "accountid": 0,
        "bsb": "123456789",
        "identification": "111222444",
        "openingDate": "2020-05-20T11:10:08.721+05:30",
        "balanceDate": "2022-02-19T18:30:00Z",
        "balance": 504.35
    }
]



PUT : This will give monthely pay interest rate with given account and due to balanace date- month
http://localhost:8082/account/111222333/20-Feb-2022

{
    "accountid": 0,
    "bsb": "123456789",
    "identification": "111222333",
    "openingDate": "2020-05-20T11:10:08.721+05:30",
    "balanceDate": "2022-02-19T18:30:00Z",
    "balance": 780.35
}

Below is the link to view inmemory database which is H2
http://localhost:8082/h2-console


