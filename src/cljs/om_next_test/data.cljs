(ns om-next-test.data
  (:require [datascript.core :as d]
            [om.next :as om]))

(def side-bar-state {:side/upper '([:button :workspaces]
                                   [:button :data])
                     :side/lower '([:button :help]
                                   [:button :logout])})

(def conn (d/create-conn {}))

(d/transact! conn [{:db/id -1
                    :app/count 3}])

(defmulti read om/dispatch)

(defmulti mutate om/dispatch)

(defn make-reconciler
  []
  (om/reconciler
   {:state conn
    :parser (om/parser {:read read :mutate mutate})}))

(defmethod read :app/main
  [{:keys [state query]} _ _]
  #_(println "query" query)
  (let [local-parser (om/parser {:read read})]
    {:value (local-parser {:state conn} query)}))

(defmethod read :app/side
  [{:keys [state query]} _ _]
  {:value (select-keys side-bar-state query)})

(defmethod read :app/counter
  [{:keys [state query]} _ _]
  #_(println "query" query)
  {:value (d/q '[:find [(pull ?e ?selector) ...]
                 :in $ ?selector
                 :where [?e :app/count]]
               (d/db state) query)})

(defmethod mutate 'app/increment
  [{:keys [state]} _ entity]
  {:value {:keys [:app/counter]}
   :action (fn []
             (d/transact!
              state
              [(update-in entity [:app/count] inc)]))})

;;
