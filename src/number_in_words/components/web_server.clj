(ns number-in-words.components.web-server
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [org.httpkit.server :refer [run-server]]))

(defrecord WebServer [handler options]
  component/Lifecycle
  (start [component]
    (if (:web-server component)
      component
      (let [server (run-server handler options)]
        (log/info "Started component: HttpKit, port:" (:port options))
        (assoc component :web-server server))))
  (stop [component]
    (when-let [server (:web-server component)]
      (server))
    (dissoc component :web-server)))

(defn web-server [handler options]
  (map->WebServer {:handler handler :options options}))
