(ns number-in-words.system
  (:require [com.stuartsierra.component :as component]
            [number-in-words.components.web-server :refer [web-server]]
            [number-in-words.routes :refer [app]]))

(defn new-system [options]
  (component/system-map :web-server (web-server app options)))
