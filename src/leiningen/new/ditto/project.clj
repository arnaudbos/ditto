(defproject {{ns-name}} "0.1.0-SNAPSHOT"
  :description "Clojure app for ..."
  :url "http://{{ns-name}}.herokuapp.com"
  :dependencies [[com.stuartsierra/component "0.3.2"]
                 [com.taoensso/timbre "4.7.4"]
                 [compojure "1.6.0"]
                 [environ "1.1.0"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/core.async "0.2.391"]
                 [ring-jetty-component "0.3.1"]]
  :min-lein-version "2.0.0"
  :plugins [[lein-environ "1.1.0"]]
  :uberjar-name "{{ns-name}}-standalone.jar")
