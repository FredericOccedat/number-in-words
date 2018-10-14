(ns number-in-words.routes
  (:require [compojure.api.sweet :refer :all]
            [number-in-words.conversion :as core]
            [ring.util.http-response :refer :all]))

(def app
  (api
    {:swagger
     {:ui "/api-docs"
      :spec "/swagger.json"
      :data {:info {:title "Number to Words API"
                    :description "Use the GET endpoint to convert numbers into English words"}
             :tags [{:name "conversion", :description "conversion apis"}]
             :produces ["application/json"]}}}

    (context "/api" []
      :tags ["conversion"]

      (GET "/:number" []
        :return {:result String}
        :path-params [number :- Long]
        :summary "Converts a number into its English wording"
        (ok {:result (core/convert number)})))))
