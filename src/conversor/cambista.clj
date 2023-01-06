(ns conversor.cambista
  (:require [cheshire.core :refer [parse-string]]
            [clj-http.client :as http-client]))

(def chave (System/getenv "CHAVE_API"))

(def api-url "https://free.currencyconverterapi.com/api/v6/convert")

(defn parametrizar-moedas [de para]
  (str de "_" para))

(defn obter-cotacao [de para]
  (let [moedas (parametrizar-moedas de para)]
    (-> (:body (http-client/get api-url {:query-params {"q" (parametrizar-moedas de para)
                                                        "apiKey" chave}}))
        (parse-string)
        (get-in ["results" moedas "val"]))))
