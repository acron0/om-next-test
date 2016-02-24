(ns om-next-test.app
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab]
            ;;
            [om-next-test.dashboard.workspaces :as workspace-dash]
            [om-next-test.dashboard.data :as data-dash]
            [om-next-test.split :as split]))

(def route->component
  {:app/workspace-dash workspace-dash/Main
   :app/data-dash      data-dash/Main
   :app/workspace      split/Main})

(def route->factory
  (zipmap (keys route->component)
          (map om/factory (vals route->component))))

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
