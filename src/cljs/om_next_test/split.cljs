(ns om-next-test.split
  (:require [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab]
            ;;
            [om-next-test.utils :as utils]))

(defui Main
  static om/IQuery
  (query [this]
         [:workspace/min-size])
  Object
  (componentDidMount [this]
                     (let [{:keys [workspace/min-size]} (om/props this)]
                       (js/Split.
                        (clj->js ["#primary" "#secondary"])
                        (clj->js {:direction "vertical"
                                  :sizes [50, 50]
                                  :gutterSize 8
                                  :minSize min-size
                                  :cursor "row-resize"}))))
  (render [this]
          (sab/html [:div#split-container
                     [:div#primary
                      [:h1 "Top"]]
                     [:div#secondary
                      [:h2 "Bottom"]]])))
