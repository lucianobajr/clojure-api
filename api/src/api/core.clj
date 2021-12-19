(ns api.core
  (:require [org.httpkit.server :as server]
            [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer :all]
            [ring.middleware.json :as mj]
            [clojure.pprint :as pp]
            [clojure.string :as str]
            [clojure.data.json :as json]
            [clojure.tools.logging :as log]
            [api.handlers :refer :all]
            [api.tweets.database :as d])
  (:gen-class))

(defroutes app-routes
  (POST "/tweets" [] (mj/wrap-json-body post-twitter-handler {:keywords? true :bigdecimals? true}))
  (GET "/tweets" [] get-twitter-handler)
  (GET "/tweets/all" [] get-all-twitter-handler)
  (DELETE "/tweets" [] delete-twitter-handler)
  (GET "/" [] get-api))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (let [port 3000]
    (server/run-server (wrap-defaults #'app-routes api-defaults)  {:port port})
    (println (str "API is Running on post " port " 🔥!"))))