(ns om-next-test.app
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab]
            ;;
            [om-next-test.split :as split]))

(defui Home
  static om/IQuery
  (query [this]
         [:home/title :home/content])
  Object
  (render [this]
          (let [{:keys [home/title home/content]} (om/props this)]
            (sab/html [:div
                       [:h1 title]
                       [:p content]]))))

(defui About
  static om/IQuery
  (query [this]
         [:about/title :about/content])
  Object
  (render [this]
          (let [{:keys [about/title about/content]} (om/props this)]
            (sab/html [:div
                       [:h1 title]
                       [:p content]]))))

(def route->component
  {:app/home Home
   :app/about About})

(def route->factory
  (zipmap (keys route->component)
          (map om/factory (vals route->component))))

(defui Main
  static om/IQuery
  (query [this]
         (let [subq-ref (if (om/component? this)
                          (:app/route (om/props this))
                          :app/home) ;; default
               subq-class (get route->component subq-ref)]
           [:app/route {:route/data (om/subquery this subq-ref subq-class)}]))
  Object
  (render [this]
          (let [{:keys [app/route route/data]} (om/props this)]
            ((route->factory  route) (assoc data :ref route)))))
