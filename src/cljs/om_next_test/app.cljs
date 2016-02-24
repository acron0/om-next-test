(ns om-next-test.app
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab]
            [bidi.bidi :as bidi]
            [accountant.core :as accountant]
            ;;
            [om-next-test.dashboard.workspaces :as workspace-dash]
            [om-next-test.dashboard.data :as data-dash]
            [om-next-test.split :as split]
            [om-next-test.utils :as utils]
            [om-next-test.data :as data])
  (:require-macros [cljs-log.core :as log]))

(def route->component
  {:app/workspace-dash workspace-dash/Main
   :app/data-dash      data-dash/Main
   :app/workspace      split/Main})

(def route->factory
  (zipmap (keys route->component)
          (map om/factory (vals route->component))))

(def route-patterns
  ["/" {"" nil
        "dashboard/" {"data" :app/data-dash
                      "workspace" :app/workspace-dash}
        ["workspace/" :id] :app/workspace}])

(defn path-exists?
  [path]
  (boolean (bidi/match-route route-patterns path)))

(defn dispatch-path!
  [path]
  (let [route (bidi/match-route route-patterns path)]
    (if route
      (let [{:keys [handler]} route]
        (log/debug "Dispatching to route:" path "=>" handler)
        (om/transact! (data/make-reconciler) `[(change/route! {:route ~handler})]))
      (log/severe "Couldn't match a route to this path:" path))))

(defn navigate!
  ([route]
   (navigate! route {}))
  ([route args]
   (let [path (apply bidi/path-for route-patterns route (mapcat vec args))]
     (if path
       (do
         (log/info "Navigating to" route args "=>" path)
         (accountant/navigate! path))
       (log/severe "No path was found for route" route args)))))

(defui Main
  static om/IQuery
  (query [this]
         (let [subq-ref (if (om/component? this)
                          (-> (om/props this) :app/route)
                          :app/workspace-dash)
               subq-class (get route->component subq-ref)]
           [:app/route {:route/data (om/subquery this subq-ref subq-class)}]))
  Object
  (render [this]
          (let [{:keys [app/route route/data]} (om/props this)]
            ((route->factory route) (assoc data :ref route)))))
