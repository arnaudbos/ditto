(ns {{ns-name}}.database.db
  (:require [com.stuartsierra.component :refer (Lifecycle start stop)]
            [taoensso.timbre :refer [info]]
            [{{ns-name}}.database :refer [Database]]
            [{{ns-name}}.util :refer [clear-keys]]
            ))

(defrecord ProductionDB [uri conn]
  Lifecycle
  (start [this]
    (when-not conn
      (info "Starting database.")
      (let [db nil
            conn nil
            ]
        (assoc this :conn conn))))
  (stop [this]
    (when conn
      (info "Stopping database.")
      )
    (clear-keys this :conn))
  Database
  (find-user [this username password]
    ;TODO
    nil))

(defn db [config]
  (map->ProductionDB {:uri (-> config ::uri)}))
