{:dev  {:dependencies [[org.clojure/tools.namespace "0.2.11"]
                       [javax.servlet/servlet-api "2.5"]
                       [ring/ring-devel "1.6.1"]
                       [ring/ring-mock "0.3.0"]
                       [lein-light-nrepl "0.3.3"]]
        :repl-options {:nrepl-middleware [lighttable.nrepl.handler/lighttable-ops]}
        :source-paths ["dev"]
        :resource-paths ["dev/resources"]
        :env {:config "config.edn"}
        }
 :prod {:env {:config "config.edn"}
        }
 }
