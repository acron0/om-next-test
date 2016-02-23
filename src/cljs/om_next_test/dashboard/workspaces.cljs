(ns om-next-test.dashboard.workspaces
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab]
            ;;
            [om-next-test.shared :as shared]))

(defui Main
  static om/IQuery
  (query [this]
         [:home/title :home/content])
  Object
  (render [this]
          (let [{:keys [home/title home/content]} (om/props this)]
            (sab/html [:div.dashboard
                       [:div.heading
                        [:h1 title]
                        (shared/search-filter "Filter your workspaces" nil)]
                       [:p content]]))))
