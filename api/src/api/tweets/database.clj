(ns api.tweets.database
  (:require [api.db.db :refer :all]
            [cheshire.core :as json]
            [api.tweets.validation :as v])
  (:import java.util.UUID)
  (:gen-class))

(defn post-tweet
  "Post a tweet to the audience"
  [tweet]
  (let [is-valid (v/validate-tweet tweet)]
    (when is-valid
      (sql-insert-tweet db (assoc tweet :id (java.util.UUID/randomUUID))))))

(defn search-tweets-by-username
  "Find tweets from a specific username"
  [username]
  (let [result (sql-search-tweets-by-username db {:username (str "@" username)})]
    (map #(assoc % :id (str (:id %))) result)))

(defn search-all-tweets
  "Find all tweets"
  []
  (let [result (sql-search-all-tweets db {})]
    (map #(assoc % :id (str (:id %))) result)))

(defn delete-tweet-by-id
  "Delete Tweet"
  [id]
  (let [result (sql-delete-tweet db {:id (str id)})]
    (map #(assoc % :id (str (:id %))) result)))