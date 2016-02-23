(ns om-next-test.split
  (:require [om.next :as om :refer-macros [defui]]
            [om.dom :as dom]
            [sablono.core :as sab]))

(defui Counter
  static om/IQuery
  (query [this]
         [{:app/counter [:db/id :app/count]}])
  Object
  (render [this]
          (let [{:keys [app/title app/count] :as entity}
                (get-in (om/props this) [:app/counter 0])]
            (dom/div nil
                     (dom/h2 nil title)
                     (dom/span nil (str "Count: " count))
                     (dom/button
                      #js {:onClick
                           (fn [e]
                             (om/transact! this
                                           `[(app/increment ~entity)]))}
                      "Click me!")))))

#_(defui Main
    static om/IQuery
    (query [this]
           [:app/count])
    Object
    (componentDidMount [this]
                       (js/Split.
                        (clj->js ["#primary" "#secondary"])
                        (clj->js {:direction "vertical"
                                  :sizes [50, 50]
                                  :gutterSize 8
                                  :minSize 200
                                  :cursor "row-resize"})))
    (render [this]
            (println this)
            (let [{:keys [app/count]} (om/props this)]
              (sab/html
               [:div#split-container
                [:span count]
                [:div#primary]
                [:div#secondary]]))))

(def split-ui (om/factory Counter))
