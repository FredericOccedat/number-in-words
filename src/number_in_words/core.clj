(ns number-in-words.core
  (:require [com.stuartsierra.component :as component]
            [number-in-words.system :refer [new-system]])
  (:gen-class))

(defn -main
  "Starting point of the number-in-words app"
  [& args]
  (component/start (new-system {:port 3000})))
