(ns om-next-test.data
  (:require [datascript.core :as d]
            [om.next :as om]))

(defonce app-state
  (atom
   {:app/side {:side/upper '([:button :workspaces]
                             [:button :data])
               :side/lower '([:button :help]
                             [:button :logout])}
    :app/route :app/home
    :app/dashboard-data {}
    :app/dashboard-workspaces {}
    :app/home {:home/title "Home page"
               :home/content "This is the homepage. There isn't a lot to see here."}
    :app/about {:about/title "About page"
                :about/content "This is the about page, the place where one might write things about their own self."}}))

(def conn (d/create-conn {}))

(d/transact! conn [{:db/id -1
                    :app/count 3}])

(defmulti read om/dispatch)

(defmulti mutate om/dispatch)

(defn make-reconciler
  []
  (om/reconciler
   {:state app-state
    :parser (om/parser {:read read :mutate mutate})}))

(defmethod read :app/route
  [{:keys [state query]} k _]
  (let [st @state]
    {:value (get st k)}))

(defmethod read :route/data
  [{:keys [state query]} k _]
  (let [st @state]
    {:value (get st (get st :app/route))}))

(defmethod read :app/side
  [{:keys [state query]} _ _]
  {:value (select-keys (:app/side @state) query)})

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

(defmethod mutate 'change/route!
  [{:keys [state]} _ {:keys [route]}]
  {:value {:keys [:app/route]}
   :action #(swap! state assoc :app/route route)})
