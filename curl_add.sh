#!/usr/bin/env bash

echo ""
echo curl --request POST --url http://localhost:5050/employee --header 'content-type: application/json' --data '{ "id": 1, "type": "regular", "salary": 1000 }'
echo ""
curl --request POST --url http://localhost:5050/employee --header 'content-type: application/json' --data '{ "id": 1, "type": "regular", "salary": 1000 }'
echo ""