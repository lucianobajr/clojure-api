# api

## Set postgres configs before init API

- Go to src/api/db/db.clj
- change password for your

## Run Script to create Tweets table

- go to resources/database/helper.sql
- execute this script

## Start

    lein run

## Build

    lein uberjar

## Execute .jar after build

    java -jar target/uberjar/api-0.1.0-SNAPSHOT-standalone.jar

## Endpoints

#### Request

`GET /`

    curl -i -H 'Accept: application/json' http://localhost:3000/thing/

#### Response

    {"ok": "Welcome to clojure-twitter-api! 😁"}

### Get all tweets

#### Request

`GET /tweets/all`

    curl -i -H 'Accept: application/json' http://localhost:3000/tweets/all

#### Response

    [
        {
            "id": "aca7125c-8117-4d7a-8f69-a76aae0cb9e9",
            "body": "test - 1",
            "username": "@lucianobajr"
        },
        {
            "id": "7bc42d2d-9956-437f-859b-4d9953519334",
            "body": "test - 3",
            "username": "@guiaguiasilva"
        }
    ]

### Get all user tweets

#### Request

`GET /tweets?username=`

    curl -i -H 'Accept: application/json' http://localhost:3000/tweets?username=

#### Response

    [
        {
            "id": "e525c863-cd37-4a5b-817b-927c990bda1e",
            "body": "test - 3",
            "username": "@guiaguiasilva"
        }
    ]

### Create Tweet

#### Request

`POST /tweets`

    curl -i -H 'Accept: application/json' http://localhost:3000/tweets

#### Body

    {
        "body": "test - 4",
        "username" : "@guiaguiasilva"
    }

#### Response

    {"status":201,"Created"}

### Delete tweet with id

#### Request

`DELETE /tweets?id=`

    curl -i -H 'Accept: application/json' http://localhost:3000/tweets?id=

### Response

    [
        {
            "update-count": 1,
            "id": ""
        }
    ]