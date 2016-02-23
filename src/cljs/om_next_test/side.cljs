(ns om-next-test.side
  (:require [cljs.test :refer-macros [is async]]
            [goog.dom :as gdom]
            [om.next :as om :refer-macros [defui]]
            [sablono.core :as sab :include-macros true]
            ;;
            [om-next-test.icons :as icons])
  (:require-macros
   [devcards.core :as dc :refer [defcard]]))

(defn get-icon
  [icon]
  (let [props [:medium :light]]
    (apply
     (get
      {:workspaces  icons/workspace
       :data        icons/data
       :settings    icons/cog
       :help        icons/help
       :logout      icons/logout}
      icon) props)))

(defn add-side-elements!
  [element-list]
  (for [[element-type element-key element-content] element-list]
    [:div.side-element
     {:key element-key}
     (condp = element-type
       :button (get-icon element-key)
       :hr [:hr])]))

(defui Main
  static om/IQuery
  (query [this]
         [{:app/side [:side/upper :side/lower]}])
  Object
  (render [this]
          (let [{:keys [side/upper side/lower]} (->> [:app/side]
                                                     (get-in (om/props this)))]
            (sab/html [:div#side-container
                       [:div#side-upper
                        (add-side-elements! upper)]
                       [:div#side-lower
                        {:align "center"}
                        (add-side-elements! lower)]]))))


;;;;;;;;;;;;;;;;;;;;;;;;;


(def side-bar (om/factory Main))

(defcard side-bar
  (sab/html
   [:div
    {:style {:background-color "gray"
             :position "fixed"
             :bottom "0"
             :left "0"
             :height "100%"}}
    (side-bar {:app/side {:side/upper '([:button :workspaces]
                                        [:button :data]
                                        [:hr]
                                        [:button :settings])
                          :side/lower '([:button :help]
                                        [:button :logout])}})]))
