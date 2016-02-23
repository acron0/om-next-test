(ns om-next-test.dashboard.workspaces
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab]))

(defui Main
  static om/IQuery
  (query [this]
         [:home/title :home/content])
  Object
  (render [this]
          (let [{:keys [home/title home/content]} (om/props this)]
            (sab/html [:div
                       [:h1 title]
                       [:p content]]))))
