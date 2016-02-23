(ns om-next-test.dashboard.data
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab]))

(defui Main
  static om/IQuery
  (query [this]
         [:about/title :about/content])
  Object
  (render [this]
          (let [{:keys [about/title about/content]} (om/props this)]
            (sab/html [:div
                       [:h1 title]
                       [:p content]]))))
