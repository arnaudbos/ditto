(ns {{ns-name}}.api
  (:require [com.stuartsierra.component :refer [Lifecycle]]
            [compojure.core :refer [defroutes GET PUT POST DELETE ANY]]
            [compojure.handler :as compojure]
            [compojure.route :as route]
            [clojure.java.io :as io]
            [{{ns-name}}.util :refer [clear-keys]]
            [ring.middleware.stacktrace :as trace]
            [ring.middleware.session :as session]
            [ring.middleware.session.cookie :as cookie]
            [taoensso.timbre :refer [info]]
            ))

(defroutes api-routes
  (GET "/" []
       {:status 200
        :headers {"Content-Type" "text/html;charset=UTF-8"}
        :body "My father's great-grandfather was the last of the Unicorns 🦄\n"})
  (route/not-found "Not Found\n"))

(defn wrap-error [handler]
  (fn [req]
    (try (handler req)
         (catch Exception e
           {:status 500
            :headers {"Content-Type" "text/plain"}
            :body "Error\n"}))))


(defrecord API [database]
  Lifecycle
  (start [this]
    (when-not (:handler this)
      (info "Starting API handler.")
      (assoc this :handler (-> api-routes
                               ((if (:production this)
                                  wrap-error
                                  trace/wrap-stacktrace))
                               compojure/api))))
  (stop [this]
    (when-let [conn (:handler this)]
      (info "Stopping API handler.")
      (clear-keys this :handler))))

(defn api [config]
  (map->API config))
