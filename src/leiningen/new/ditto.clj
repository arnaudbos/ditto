(ns leiningen.new.ditto
  (:use [leiningen.new.templates :only [renderer name-to-path sanitize-ns ->files]]))

(def render (renderer "ditto"))

(defn ditto
  [name]
  (let [data {:name name
              :ns-name (sanitize-ns name)
              :sanitized (name-to-path name)}]
    (->files data
             ["project.clj" (render "project.clj" data)]
             ["profiles.clj" (render "profiles.clj" data)]
             ["dev/user.clj" (render "dev/user.clj" data)]
             ["dev/dev_database.clj" (render "dev/dev_database.clj" data)]
             ["dev/resources/config.edn" (render "dev/resources/config.edn" data)]
             ["src/{{sanitized}}/env.clj" (render "src/ditto/env.clj" data)]
             ["src/{{sanitized}}/core.clj" (render "src/ditto/core.clj" data)]
             ["src/{{sanitized}}/systems.clj" (render "src/ditto/systems.clj" data)]
             ["src/{{sanitized}}/database.clj" (render "src/ditto/database.clj" data)]
             ["src/{{sanitized}}/database/db.clj" (render "src/ditto/database/db.clj" data)]
             ["src/{{sanitized}}/api.clj" (render "src/ditto/api.clj" data)]
             ["src/{{sanitized}}/util.clj" (render "src/ditto/util.clj" data)]
             ["test/{{sanitized}}/api_test.clj" (render "test/ditto/api_test.clj" data)]
             ["resources/config.edn" (render "resources/config.edn" data)]
             )))
