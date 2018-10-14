(defproject number-in-words "0.1.0-SNAPSHOT"
  :description "Translates numbers into grammatically correct English words"
  :url "http://localhost:3000/number-in-words/api"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/tools.logging "0.4.1"]
                 [com.stuartsierra/component "0.3.2"]
                 [http-kit "2.3.0"]
                 [metosin/compojure-api "1.1.11"]]
  :main number-in-words.core
  :profiles {:dev     {:source-paths ["dev"]
                       :dependencies [[org.clojure/tools.namespace "0.2.11"]
                                      [reloaded.repl "0.2.4"]]}
             :uberjar {:uberjar-name "number-in-words.jar"
                       :aot          :all}}
  :repl-options {:init-ns user})
