(ns {{ns-name}}.systems
  (:require [com.stuartsierra.component :refer [system-map system-using]]
            [{{ns-name}}.api :refer [api]]
            [{{ns-name}}.database :refer [database]]
            [ring.component.jetty :refer [jetty-server]]
            ))

(defn- merge-global-config
  [config global-keys components-keys]
  (let [global-config (select-keys config global-keys)]
    (reduce (fn [result component-key]
              (let [component-config (-> config
                                         (get component-key)
                                         (merge global-config))]
                (assoc result component-key component-config)))
            {}
            components-keys)))

(defn build-system [env]
  (let [config (-> env
                   :config
                   (merge-global-config [:production] [:database :api :http]))]
    (println "Confing:" config)
    (-> (system-map
          :database (database (:database config))
          :app      (api (:api config))
          :http     (jetty-server (:http config)))
        (system-using
         {:http     [:app]
          :app      [:database]
          :database []}))))
