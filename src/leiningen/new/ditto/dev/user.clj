(ns user
  (:require [com.stuartsierra.component :as component]
            [clojure.tools.namespace.repl :refer [refresh]]
            [{{ns-name}}.env :refer [env]]
            [{{ns-name}}.systems :refer [build-system]]))

(def system nil)

(defn init []
  (alter-var-root #'system
                  (constantly (build-system env))))

(defn start []
  (alter-var-root #'system component/start))

(defn stop []
  (alter-var-root #'system
                  (fn [s] (when s (component/stop s)))))

(defn go []
  (init)
  (start))

(defn reset []
  (stop)
  (refresh :after 'user/go))

;(go)
;(reset)
