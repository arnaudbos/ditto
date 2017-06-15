(ns dev-database
  (:require [com.stuartsierra.component :refer [Lifecycle]]
            [taoensso.timbre :refer [info]]
            [{{ns-name}}.database :refer [Database]]
            [{{ns-name}}.util :refer [clear-keys]]))

(defn- load-seed-data! [conn]
  (reset! conn [{:username "abos" :password "sushi"}
                ]))

(defn- drop-database! [conn]
  (reset! conn []))

(defn- -find-user-pred [user username password]
  (when (= ((juxt :username :password) user)
           [username password])
    user))

(defn- find-user [conn username password]
  (when-let [user (some #(-find-user-pred % username password)
                        (deref conn))]
    (dissoc user :password)))

(defrecord DummyDatabase []
  Lifecycle
  (start [this]
    (when-not (:connection this)
      (info "Starting dev database.")
      (let [conn (atom [])]
        (load-seed-data! conn)
        (assoc this :connection conn))))
  (stop [this]
    (when-let [conn (:connection this)]
      (info "Stopping dev database.")
      (drop-database! conn)
      (clear-keys this :connection)))
  Database
  (find-user [this username password]
    (find-user (:connection this) username password)))

(defn dev-database [config]
  (map->DummyDatabase config))
