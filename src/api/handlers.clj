(ns api.handlers
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [api.tweets.database :as d])
  (:gen-class))

(defn post-twitter-handler
  [req]
  (let [tweet-json (:body req)
        saved (try
                (d/post-tweet tweet-json)
                (catch Exception e
                  (do
                    (log/error e)
                    false)))]
    (log/info tweet-json)
    {:status  (if saved 201 400)
     :headers {"Content-Type" "text/html"}
     :body    (when (not saved)
                "error or saving tweet")}))

(defn get-twitter-handler
  [req]
  (log/info req)
  (let
   [username (-> req
                 :params
                 :username)
    tweets (d/search-tweets-by-username username)]

    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str tweets)}))

(defn get-all-twitter-handler
  [req]
  (log/info req)
  (let
   [tweets (d/search-all-tweets)]

    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str tweets)}))

(defn delete-twitter-handler
  [req]
  (log/info req)
  (let
   [id (-> req
                 :params
                 :id)
    tweets (d/delete-tweet-by-id id)]

    {:status 200
     :headers {"Content-type" "application/json"}
     :body (json/write-str tweets)}))

(defn get-api
  [req]
  (log/info req)
  (json/write-str {:ok "Welcome to api! 😁"}))