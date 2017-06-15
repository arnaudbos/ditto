(ns {{ns-name}}.database
  (:require [{{ns-name}}.env :refer [defcomponent]]))

(defprotocol Database
  (find-user [this username password]))

(defcomponent database)
