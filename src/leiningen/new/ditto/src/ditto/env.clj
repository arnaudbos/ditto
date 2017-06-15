(ns {{ns-name}}.env
  (:require [environ.core :as environ]
            [clojure.edn :as edn]
            [clojure.java.io :as io]))


(defn- load-config
  "Given a filename, load & return a config file"
  [filename]
  (edn/read-string (slurp filename)))

(def env
  (update environ/env :config
          #(-> % io/resource load-config)))

(defmacro defcomponent
  "Useful for switching the implementation of a component
  between two systems.

  Generates a function of the same name which, given a
  config map containing the key :component containing
  a component function taking a config map and returning
  a component, will require this component function's namespace
  and call it with the given config map, eventually returning
  the component instance initialized but not started.

  Ex: (defcomponent database) will generate two functions:
  * load-database which takes a config map and a component function
    and returns the initialized component
  * database which takes the config map containing the :component
    key pointing to the fully qualified name of the component function
    and calls load-database with it.

  database can then be called with one specific implementation of the
  Database component or another:

  In dev: (database (:component dev.database/database))
  In prod: (database (:component com.myapp.database/database))"
  [name]
  (let [load-component-fn (symbol (str "load-" name))
        config (gensym 'config)
        sym (gensym 'sym)
        sysfn (gensym 'sysfn)
        ]
    `(do
       (defn- ~load-component-fn
         [~config ~sym]
         (require (symbol (namespace ~sym)))
         (let [~sysfn (var-get (find-var ~sym))]
           (~sysfn ~config)))
       (defn ~name
         [~config]
         (let [~sym (-> ~config :component)]
           (~load-component-fn ~config ~sym))))))
