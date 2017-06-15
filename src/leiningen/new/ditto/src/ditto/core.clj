(ns {{ns-name}}.core
  (:require [com.stuartsierra.component :refer [start]]
            [{{ns-name}}.env :refer [env]]
            [{{ns-name}}.systems :refer [build-system]]))

(defn -main
  "Start a production system."
  [& args]
  (let [system (build-system env)]
    (start system)))
